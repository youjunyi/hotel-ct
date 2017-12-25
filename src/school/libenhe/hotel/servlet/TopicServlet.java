package school.libenhe.hotel.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import school.libenhe.hotel.entity.Chapter;
import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.service.ISysuserStrvice;
import school.libenhe.hotel.service.ITopicService;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.PageBean;
import school.libenhe.hotel.utils.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TopicServlet extends HttpServlet {
    private ITopicService service = BeanFactory.getInstance("topicService",
            ITopicService.class);
    private ISysuserStrvice sysuserService = BeanFactory.getInstance("sysuserService",
            ISysuserStrvice.class);
    // ????��??
    private Object uri;
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        PageBean<Chapter> pageBean = new PageBean<Chapter>();
        pageBean.setPageCount(6);
        service.getAll(pageBean);
        List<Chapter> list = service.query();
        config.getServletContext().setAttribute("food", list);
        config.getServletContext().setAttribute("pb", pageBean);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            add(request, response);
        } else if ("list".equals(method)) {
            list(request, response,request.getParameter("zhou"));
        } else if ("update".equals(method)) {
             update(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("search".equals(method)) {
            search(request, response);
        } else if ("showTopic".equals(method)) {
            String zhou = request.getParameter("zhou");
            request.setAttribute("zhou", zhou);
            uri = request.getRequestDispatcher("/sys/topic/topicSave.jsp");
            WebUtils.goTo(request, response, uri);
        } else if ("show".equals(method)) {
            show(request, response);
        } else if ("getMenu".equals(method)) {
            //  getMenu(request, response);
        }else if ("listissh".equals(method)) {
              listissh(request, response);
        }else if("shenhe".equals(method)){
           
        }else if ("topictj".equals(method)){
            topictj(request,response);
        }

    }
    
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            if (keyword != null) {
                List<Chapter> list = service.query(keyword);
                request.setAttribute("list", list);
                uri = request.getRequestDispatcher("/sys/user/user.jsp");
            }
        } catch (Exception e) {
            uri = "/error/error.jsp";
            e.printStackTrace();
        }
        WebUtils.goTo(request, response, uri);
    }

    private void topictj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String dept =  request.getParameter("dept");
            String jiaoyanshi =  request.getParameter("jianyanshi");
            String username = request.getParameter("username");
            // ����service
             // ��pageBean�Ѿ���dao��������ݡ�
            // ����pageBean���󣬵�request����
            Condition condition = new Condition();
            condition.setUsername(username);
            condition.setDept(dept);
            List<Chapter> list = service.getAlltj(condition);

            request.setAttribute("list", list);
            uri = request.getRequestDispatcher("/sys/topic/topictj.jsp");
        } catch (Exception e) {
            e.printStackTrace(); // ����ʹ��
            // ���ִ�����ת������ҳ��
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }

    /*private void shenhe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Long id = Long.valueOf(request.getParameter("id").toString());
        Chapter topic = service.findById(id.intValue());
            topic.setIssh("1");
            service.updataissh(topic);
            listissh(request, response);
    }*/



    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // �����ļ���С����
            upload.setSizeMax(50 * 1024 * 1024); // ���ļ���С����
            upload.setHeaderEncoding("UTF-8"); // �������ļ����봦��
            Chapter topic = new Chapter();
            if (upload.isMultipartContent(request)) {


                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// ��ͨ��������
                        String name = item.getFieldName();
                        // ��ȡֵ
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(topic, name, value);
                    } else {// �ϴ�����
                        String fieldName = item.getFieldName();
                        String path = getServletContext()
                                .getRealPath("/upload");
                        File f = new File(path);
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        String name = item.getName();
                        if (name != null && !"".equals(name.trim())) {
                            BeanUtils.setProperty(topic, fieldName,
                                    ("upload/" + name));

                            //  ƴ���ļ���
                            File file = new File(path, name);
                            //  �ϴ�
                            if (!file.isDirectory()) {
                                item.write(file);
                            }
                            item.delete(); // ɾ���������ʱ��������ʱ�ļ�
                        } else {
                           /* Long id = topic.getId();
                           // String img = service.findById(id.intValue()).getWenjinurl();
                            BeanUtils.setProperty(topic, "wenjinurl", img);*/

                        }
                    }
                }
                service.updata(topic);

            } else {

            }
            list(request, response,topic.getZhou());

        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }


    //�ڲ�ϵ��������ʾ��ϵ��Ӧ�����͵�����
    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            Chapter topic = service.findById(Integer.parseInt(id));
            List<Sysuser> list =  sysuserService.query();
            request.setAttribute("sysUser", list);
            request.setAttribute("topic", topic);
            uri = request.getRequestDispatcher("/sys/topic/topicUpdate.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            uri="/error/error.jsp";
        }finally {
            WebUtils.goTo(request, response, uri);
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.delete(Integer.parseInt(id));
            list(request, response,request.getParameter("zhou"));
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }
    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // �����ļ���С����
            upload.setSizeMax(50 * 1024 * 1024); // ���ļ���С����
            upload.setHeaderEncoding("UTF-8"); // �������ļ����봦��
            Chapter topic = new Chapter();
            if (upload.isMultipartContent(request)) {


                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// ��ͨ��������
                        String name = item.getFieldName();
                        // ��ȡֵ
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(topic, name, value);
                    }else {// �ϴ�����
                        String fieldName = item.getFieldName();
                        String path = getServletContext()
                                .getRealPath("/upload");
                        System.out.println(path);
                        File f = new File(path);
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        // ȫ������·��
                        String name = item.getName();

                        BeanUtils
                                .setProperty(topic, fieldName, "upload/" + name);

                        // ƴ���ļ���
                        File file = new File(path, name);
                        // �ϴ�
                        if (!file.isDirectory()) {
                            item.write(file);
                        }
                        item.delete(); // ɾ���������ʱ��������ʱ�ļ�
                    }
                }
                service.add(topic);

            } else {

            }
            list(request, response,topic.getZhou());
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response,String zhou)
            throws ServletException, IOException {

        try {
            // ��ȡ����ǰҳ�������� (��һ�η��ʵ�ǰҳΪnull)
            String currPage = request.getParameter("currentPage");
            String zhou1  = zhou;
            if(zhou1 ==null){
                zhou1 = request.getParameter("zhou");
            }
            // �ж�
            if (currPage == null || "".equals(currPage.trim())) {
                currPage = "1"; // ��һ�η��ʣ����õ�ǰҳΪ1;
            }
            // ת��
            int currentPage = Integer.parseInt(currPage);

            // ����PageBean�������õ�ǰҳ������ ����service��������
            PageBean<Chapter> pageBean = new PageBean<Chapter>();
            pageBean.setCurrentPage(currentPage);
            Condition condition = new Condition();
            condition.setZhou(zhou);
            pageBean.setCondition(condition);
            // ����service
            service.getAll(pageBean); // ��pageBean�Ѿ���dao��������ݡ�
            // ����pageBean���󣬵�request����

            List<Chapter> list = pageBean.getPageData();

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("list", list);
            request.setAttribute("zhou",zhou);
            uri = request.getRequestDispatcher("/sys/topic/topic.jsp");
        } catch (Exception e) {
            e.printStackTrace(); // ����ʹ��
            // ���ִ�����ת������ҳ��
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }

    private void listissh(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ��ȡ����ǰҳ�������� (��һ�η��ʵ�ǰҳΪnull)
            String currPage = request.getParameter("currentPage");
            // �ж�
            if (currPage == null || "".equals(currPage.trim())) {
                currPage = "1"; // ��һ�η��ʣ����õ�ǰҳΪ1;
            }
            // ת��
            int currentPage = Integer.parseInt(currPage);

            // ����PageBean�������õ�ǰҳ������ ����service��������
            PageBean<Chapter> pageBean = new PageBean<Chapter>();
            pageBean.setCurrentPage(currentPage);
            Condition condition = new Condition();
            condition.setIssh("0");
            pageBean.setCondition(condition);
            // ����service
            service.getAll(pageBean); // ��pageBean�Ѿ���dao��������ݡ�
            // ����pageBean���󣬵�request����

            List<Chapter> list = pageBean.getPageData();

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("list", list);
            uri = request.getRequestDispatcher("/sys/topic/topic.jsp");
        } catch (Exception e) {
            e.printStackTrace(); // ����ʹ��
            // ���ִ�����ת������ҳ��
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }



    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);

    }
}
