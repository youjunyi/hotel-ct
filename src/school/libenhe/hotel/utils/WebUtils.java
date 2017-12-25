package school.libenhe.hotel.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装跳转的通用工具类
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-8 下午12:36:08
 */
public  class  WebUtils {

	public static void goTo(HttpServletRequest request,
			HttpServletResponse response, Object uri) throws ServletException,
			IOException {
		if (uri instanceof RequestDispatcher) {
			((RequestDispatcher) uri).forward(request, response);
		} else if (uri instanceof String) {
			response.sendRedirect(request.getContextPath() + uri);
		}
	}
}
