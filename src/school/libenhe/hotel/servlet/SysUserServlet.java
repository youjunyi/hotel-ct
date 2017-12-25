package school.libenhe.hotel.servlet;

import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.service.ISysuserStrvice;
import school.libenhe.hotel.utils.PageBean;
import school.libenhe.hotel.utils.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SysUserServlet extends HttpServlet {

    private ISysuserStrvice service = BeanFactory.getInstance("sysuserService",
            ISysuserStrvice.class);

    // ?¨´?¡Â????
    private Object uri;
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        PageBean<Sysuser> pageBean = new PageBean<Sysuser>();
        pageBean.setPageCount(6);
        service.getAll(pageBean);
        List<Sysuser> list = service.query();
        config.getServletContext().setAttribute("food", list);
        config.getServletContext().setAttribute("pb", pageBean);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            //add(request, response);
        } else if ("list".equals(method)) {
            list(request, response);
        } else if ("update".equals(method)) {
          /*  update(request, response);*/
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("search".equals(method)) {
            search(request, response);
        } else if ("showSysuser".equals(method)) {

            uri = request.getRequestDispatcher("/sys/sysuser/sysuserSave.jsp");
            WebUtils.goTo(request, response, uri);
        } else if ("show".equals(method)) {
            show(request, response);
        } else if ("getMenu".equals(method)) {
            //  getMenu(request, response);
        }else if("login".equals(method)){
            login(request,response);
        }else if("password".equals(method)){
            password(request,response);
        }


    }

    private void password(HttpServletRequest request, HttpServletResponse response){
        Long id =  Long.valueOf(request.getParameter("id").toString());
        String newpassword = request.getParameter("newpassword");
        String password = request.getParameter("password");
        Sysuser sysuser = service.findById(id.intValue());
        if(password.equals(sysuser.getPassword())){
            sysuser.setPassword(newpassword);
            service.updatapassword(sysuser);
            request.setAttribute("msg","ÐÞ¸Ä³É¹¦");
            uri = request.getRequestDispatcher("/sys/public/right.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("msg","ÐÞ¸ÄÊ§°Ü£¬ÃÜÂë´íÎó£¡");
            uri = request.getRequestDispatcher("/sys/password.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Sysuser> list = service.query(username,password);
        if(null != list && list.size()>0){
            request.getSession().setAttribute("username",list.get(0).getName());
            request.getSession().setAttribute("number",list.get(0).getNumber());
            request.getSession().setAttribute("state","sysuser");
            request.getSession().setAttribute("id",list.get(0).getId());
            uri = request.getRequestDispatcher("/sys/index.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("msg","µÇÂ¼Ê§°Ü£¬ÓÃ»§¹¤ºÅ»òÃÜÂë´íÎó");
            uri = request.getRequestDispatcher("/sys/login.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // ?????????¨®??????
            upload.setSizeMax(50 * 1024 * 1024); // ¡Á??????¨®??????
            upload.setHeaderEncoding("UTF-8"); // ??????????¡À¨¤?????¨ª

            if (upload.isMultipartContent(request)) {

                Sysuser sysuser = new Sysuser();
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// ???¡§¡À???????
                        String name = item.getFieldName();
                        // ??????
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(sysuser, name, value);
                    }
                }
                service.updata(sysuser);

            } else {

            }
            list(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }*/
    //}

    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            Sysuser sysuser = service.findById(Integer.parseInt(id));
            request.setAttribute("sysuser", sysuser);
            uri = request.getRequestDispatcher("/sys/sysuser/sysuserUpdate.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            uri="/error/error.jsp";
        }finally {
            WebUtils.goTo(request, response, uri);
        }
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            if (keyword != null) {
                List<Sysuser> list = service.query(keyword);
                request.setAttribute("list", list);
                uri = request.getRequestDispatcher("/sys/sysuser/sysuser.jsp");
            }
        } catch (Exception e) {
            uri = "/error/error.jsp";
            e.printStackTrace();
        }
        WebUtils.goTo(request, response, uri);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.delete(Integer.parseInt(id));
            list(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String currPage = request.getParameter("currentPage");
            // ????
            if (currPage == null || "".equals(currPage.trim())) {
                currPage = "1";
            }

            int currentPage = Integer.parseInt(currPage);


            PageBean<Sysuser> pageBean = new PageBean<Sysuser>();
            pageBean.setCurrentPage(currentPage);


            service.getAll(pageBean);

            List<Sysuser> list = pageBean.getPageData();

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("list", list);
            uri = request.getRequestDispatcher("/sys/sysuser/sysuser.jsp");
        } catch (Exception e) {
            e.printStackTrace(); // ????????
            // ?????¨ª?¨®????¡Á????¨ª?¨®????
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }
   /* private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // ?????????¨®??????
            upload.setSizeMax(50 * 1024 * 1024); // ¡Á??????¨®??????
            upload.setHeaderEncoding("UTF-8"); // ??????????¡À¨¤?????¨ª

            if (upload.isMultipartContent(request)) {

                Sysuser sysuser = new Sysuser();
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// ???¡§¡À???????
                        String name = item.getFieldName();
                        // ??????
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(sysuser, name, value);
                    }
                }
                service.add(sysuser);

            } else {

            }
            list(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }*/

    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);

    }
    
}
