package school.libenhe.hotel.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import school.libenhe.hotel.entity.User;
import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.service.IUserService;
import school.libenhe.hotel.utils.PageBean;
import school.libenhe.hotel.utils.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    // 鍒涘缓service
    private IUserService service = BeanFactory.getInstance("userService",
            IUserService.class);

    // 澹版槑璺緞
    private Object uri;
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setPageCount(6);
        service.getAll(pageBean);
        List<User> list = service.query();
        config.getServletContext().setAttribute("food", list);
        config.getServletContext().setAttribute("pb", pageBean);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            add(request, response);
        } else if ("list".equals(method)) {
            list(request, response);
        } else if ("update".equals(method)) {
            update(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("search".equals(method)) {
            search(request, response);
        } else if ("showUser".equals(method)) {
            
            uri = request.getRequestDispatcher("/sys/user/userSave.jsp");
            WebUtils.goTo(request, response, uri);
        } else if ("show".equals(method)) {
            show(request, response);
        } else if ("getMenu".equals(method)) {
          //  getMenu(request, response);
        }else if("login".equals(method)){
            login(request,response);
        } else if("password".equals(method)){
            password(request,response);
        }

    }


   
    
    private void password(HttpServletRequest request, HttpServletResponse response){
        Long id =  Long.valueOf(request.getParameter("id").toString());
        String newpassword = request.getParameter("newpassword");
        String password = request.getParameter("password");
        User user = service.findById(id.intValue());
        if(password.equals(user.getPassword())){
            user.setPassword(newpassword);
            service.updatapassword(user);
            request.setAttribute("msg","修改成功");
            uri = request.getRequestDispatcher("/sys/public/right.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("msg","修改失败，密码错误！");
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
        List<User> list = service.query(username,password);
        if(null != list && list.size()>0){
            request.getSession().setAttribute("username",list.get(0).getName());
            request.getSession().setAttribute("number",list.get(0).getNumber());
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
            request.setAttribute("msg","登录失败，用户工号或密码错误");
            uri = request.getRequestDispatcher("/sys/login2.jsp");
            try {
                WebUtils.goTo(request, response, uri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // 鍗曚釜鏂囦欢澶у皬闄愬埗
            upload.setSizeMax(50 * 1024 * 1024); // 鎬绘枃浠跺ぇ灏忛檺鍒?
            upload.setHeaderEncoding("UTF-8"); // 瀵逛腑鏂囨枃浠剁紪鐮佸鐞?

            if (upload.isMultipartContent(request)) {

                User user = new User();
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// 鏅?氭湰鏂囧唴瀹?
                        String name = item.getFieldName();
                        // 鑾峰彇鍊?
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(user, name, value);
                    } 
                }
                service.updata(user);

            } else {

            }
            list(request, response);

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
                List<User> list = service.query(keyword);
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
            service.delete(Integer.parseInt(id));
            list(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
            WebUtils.goTo(request, response, uri);
        }
    }
    //鍦ㄨ彍绯绘洿鏂颁腑鏄剧ず鑿滅郴瀵瑰簲鐨勭被鍨嬬殑鍚嶇О
    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            User user = service.findById(Integer.parseInt(id));
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
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // 鍗曚釜鏂囦欢澶у皬闄愬埗
            upload.setSizeMax(50 * 1024 * 1024); // 鎬绘枃浠跺ぇ灏忛檺鍒?
            upload.setHeaderEncoding("UTF-8"); // 瀵逛腑鏂囨枃浠剁紪鐮佸鐞?

            if (upload.isMultipartContent(request)) {

                User user = new User();
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {

                    if (item.isFormField()) {// 鏅?氭湰鏂囧唴瀹?
                        String name = item.getFieldName();
                        // 鑾峰彇鍊?
                        String value = item.getString();
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8");
                        BeanUtils.setProperty(user, name, value);
                    }
                }
                service.add(user);

            } else {

            }
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
            // 鑾峰彇鈥滃綋鍓嶉〉鈥濆弬鏁帮紱 (绗竴娆¤闂綋鍓嶉〉涓簄ull)
            String currPage = request.getParameter("currentPage");
            // 鍒ゆ柇
            if (currPage == null || "".equals(currPage.trim())) {
                currPage = "1"; // 绗竴娆¤闂紝璁剧疆褰撳墠椤典负1;
            }
            // 杞崲
            int currentPage = Integer.parseInt(currPage);

            // 鍒涘缓PageBean瀵硅薄锛岃缃綋鍓嶉〉鍙傛暟锛? 浼犲叆service鏂规硶鍙傛暟
            PageBean<User> pageBean = new PageBean<User>();
            pageBean.setCurrentPage(currentPage);

            // 璋冪敤service
            service.getAll(pageBean); // 銆恜ageBean宸茬粡琚玠ao濉厖浜嗘暟鎹??
            // 淇濆瓨pageBean瀵硅薄锛屽埌request鍩熶腑

            List<User> list = pageBean.getPageData();

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("list", list);
            uri = request.getRequestDispatcher("/sys/user/user.jsp");
        } catch (Exception e) {
            e.printStackTrace(); // 娴嬭瘯浣跨敤
            // 鍑虹幇閿欒锛岃烦杞埌閿欒椤甸潰
            uri = "/error/error.jsp";
        }
        WebUtils.goTo(request, response, uri);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);

    }

}
