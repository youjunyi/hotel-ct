package school.libenhe.hotel.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import school.libenhe.hotel.entity.Task;
import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.service.IUserService;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.PageBean;
import school.libenhe.hotel.utils.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UserServlet extends HttpServlet {

    private IUserService service = BeanFactory.getInstance("userService",
            IUserService.class);

    private Object uri;
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        PageBean<Task> pageBean = new PageBean<Task>();
        pageBean.setPageCount(6);
        service.getAll(pageBean);
        List<Task> list = service.query();
        config.getServletContext().setAttribute("food", list);
        config.getServletContext().setAttribute("pb", pageBean);

    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            add(request, response);
        } else if ("list".equals(method)) {
            list(request, response,null);
        } else if ("update".equals(method)) {
            update(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("search".equals(method)) {
            search(request, response);
        } else if ("showUser".equals(method)) {
            String chapterid = request.getParameter("chapterid");
            request.setAttribute("chapterid",chapterid);
            uri = request.getRequestDispatcher("/sys/user/userSave.jsp");
            WebUtils.goTo(request, response, uri);
        } else if ("show".equals(method)) {
            show(request, response);
        } else if ("ajax".equals(method)) {
            ajax(request, response);
        }else if("login".equals(method)){
        } else if("password".equals(method)){
        }

    }

    private void ajax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dm = request.getParameter("dm");
        String chapterid = request.getParameter("chapterid");
        String classname = request.getParameter("classname");

        String byout = "";
        String yxjg = "";
        String path = "D:/upload";
        path = path+"/"+ chapterid+"/"+classname+".java";
        File file = new File(path);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
        try {
            PrintWriter out = new PrintWriter(file);
            out.print(dm);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cmd = "javac D:/upload/"+chapterid+"/"+classname+".java";
        String cmd1 = "java -cp D:/upload/"+chapterid+"/ "+classname;
        
        Process p1  = Runtime.getRuntime().exec(cmd);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(
                p1.getErrorStream()));
        String line1 = null;
        while ((line1 = in1.readLine()) != null) {
            byout +=  line1;
        }
        

        Process p = Runtime.getRuntime().exec(cmd1);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            yxjg +=line;
        }


        BufferedReader ine = new BufferedReader(new InputStreamReader(
                p.getErrorStream()));
        String linee = null;
        while ((linee = ine.readLine()) != null) {
            yxjg+=linee;
        }
        yxjg = byout+yxjg;
        String djavac = "D:/upload/"+chapterid+"/"+classname+".class";
        File file2 = new File(djavac);
        if(file2.isFile() && file2.exists()){
            file2.delete();
        }else{
        }
        PrintWriter out = response.getWriter();

        out.print(yxjg);
        out.close();

    }


    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024);
            upload.setSizeMax(50 * 1024 * 1024);
            upload.setHeaderEncoding("UTF-8"); 
            Task user = new Task();
            String dm  = "";
            if (upload.isMultipartContent(request)) {


                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        // 鑾峰彇鍊?
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        if("dm".equals(name)){
                            dm= value;
                        }
                        BeanUtils.setProperty(user, name, value);
                    } 
                }

                String path = "D:/upload";
                // boolean flag = (new File(path)).delete();
                path = path+"/"+ user.getChapterid()+"/"+user.getClassname()+".java";
                File file = new File(path);
                File fileParent = file.getParentFile();
                if(!fileParent.exists()){
                    fileParent.mkdirs();
                }
                file.createNewFile();
                try {
                    PrintWriter out = new PrintWriter(file);
                    out.print(dm);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                service.updata(user);

            } else {

            }
            list(request, response,String.valueOf(user.getChapterid()));

        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }






    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            if (keyword != null) {
                List<Task> list = service.query(keyword);
                request.setAttribute("list", list);
                uri = request.getRequestDispatcher("/sys/user/user.jsp");
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
            Task byId = service.findById(Long.valueOf(id).intValue());
            String djavac = "D:/upload/"+byId.getChapterid()+"/"+byId.getPath();
            File file2 = new File(djavac);
            if(file2.isFile() && file2.exists()){
                file2.delete();
            }else{
            }
            service.delete(Integer.parseInt(id));
            String chapterid = request.getParameter("chapterid");
            request.setAttribute("chapterid",chapterid);
            list(request, response,chapterid);
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            Task user = service.findById(Integer.parseInt(id));
            String path = "D:/upload/"+user.getChapterid()+"/"+user.getPath();
            File file = new File(path);
            try {
                // 读取文件，并且以utf-8的形式写出去
                BufferedReader bufread;
                StringBuffer sb = new StringBuffer();
                String read = "";
                bufread = new BufferedReader(new FileReader(file));
                while ((read = bufread.readLine()) != null) {
                    sb.append("\r\n"+read);
                }
                bufread.close();
                request.setAttribute("dm", sb.toString());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String chapterid = request.getParameter("chapterid");
            request.setAttribute("chapterid",chapterid);
            request.setAttribute("user", user);
            uri = request.getRequestDispatcher("/sys/user/userUpdate.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            uri="/error/error.jsp";
        }finally {
            WebUtils.goTo(request, response, uri);
        }
    }
    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String rad = "";
            String chapterid = "";
            String classname = "";
            String dm = "";
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024);
            upload.setSizeMax(50 * 1024 * 1024);
            upload.setHeaderEncoding("UTF-8");
            Task user = new Task();
            if (upload.isMultipartContent(request)) {


                List<FileItem> list = upload.parseRequest(request);

                for (FileItem item : list) {

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        if("rad".equals(name)) {
                            rad = value;
                        }
                        if("chapterid".equals(name)) {
                            chapterid = value;
                        }
                        if("classname".equals(name)) {
                            classname = value;
                        }
                        if("dm".equals(name)){
                            dm = value;
                        }
                        BeanUtils.setProperty(user, name, value);
                    }else {
                        if (rad.equals("2")) {
                            String fieldName = item.getFieldName();
                            String path = "D:/upload/" + chapterid + "/";
                            String name = item.getName();
                            path = path + name;
                            System.out.println(path);
                            File f = new File(path);
                            File fileParent = f.getParentFile();
                            if (!fileParent.exists()) {
                                fileParent.mkdirs();
                            }
                            f.createNewFile();
                            // 全部绝对路径

                            BeanUtils
                                    .setProperty(user, "path", name);

                            // 拼接文件名
                            // 上传
                            if (!f.isDirectory()) {
                                item.write(f);
                            }
                            item.delete(); // 删除组件运行时产生的临时文件
                        }
                    }
                }



                if(rad.equals("1")){
                    String path = "D:/upload";
                    // boolean flag = (new File(path)).delete();
                    path = path+"/"+ chapterid+"/"+classname+".java";
                    File file = new File(path);
                    File fileParent = file.getParentFile();
                    if(!fileParent.exists()){
                        fileParent.mkdirs();
                    }
                    file.createNewFile();
                    try {
                        PrintWriter out = new PrintWriter(file);
                        out.print(dm);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user.setPath(classname+".java");
                }

                service.add(user);

            } else {

            }
            list(request, response,chapterid);
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response,String chapterid)
            throws ServletException, IOException {

        try {

            String currPage = request.getParameter("currentPage");
            String chapterid1 = "";
            if(null!=chapterid && !chapterid.equals("")){
                chapterid1 = chapterid;
            } else{
                chapterid1= request.getParameter("chapterid");
            }

            if (currPage == null || "".equals(currPage.trim())) {
                currPage = "1";
            }
            int currentPage = Integer.parseInt(currPage);

            PageBean<Task> pageBean = new PageBean<Task>();
            pageBean.setCurrentPage(currentPage);
            Condition condition = new Condition();
            condition.setChapterid(chapterid1);
            
            pageBean.setCondition(condition);
            service.getAll(pageBean);

            List<Task> list = pageBean.getPageData();

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("list", list);
            request.setAttribute("chapterid",chapterid);
            uri = request.getRequestDispatcher("/sys/user/user.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }

    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);

    }

}
