package school.libenhe.hotel.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.utils.WebUtils;

/**
 * 閫氱敤鐨剆ervlet锛宻ervlet缁ф壙姝ょ被
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-8 涓嬪崍7:35:33
 */
public abstract class BaseServlet extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 鐢ㄤ簬淇濆瓨璺宠浆璧勬簮
		Object returnValue = null;

		// 鎿嶄綔绫诲瀷鐨勫��,瀵瑰簲servlet涓殑鏂规硶鍚嶇О
		String methodName = request.getParameter("method");

		try {
			// 鑾峰彇褰撳墠杩愯绫荤殑瀛楄妭鐮�
			Class clazz = this.getClass();

			// 鑾峰彇褰撳墠鎵ц鐨勬柟娉曠殑Method绫诲瀷
			Method method = clazz.getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);

			// 鎵ц
			returnValue = method.invoke(this, request, response);
		} catch (Exception e) {

			e.printStackTrace();
			// 璺宠浆鍒伴敊璇〉闈�
			returnValue = "/error/error.jsp";
		}

		// 璺宠浆
		WebUtils.goTo(request, response, returnValue);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}
}
