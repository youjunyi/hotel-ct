package school.libenhe.hotel.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 将iso-8859-1编码转化为utf-8
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-7 下午10:05:48
 */
public class EncodingRequest extends HttpServletRequestWrapper{

	private HttpServletRequest req;
	/**
	 * @param request
	 */
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.req = request;
	}
	
	public String getParameter(String name) {
		String value = req.getParameter(name);
		
		try {
			if(value!=null){
				value = new String(value.getBytes("iso-8859-1"), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		return value;
	}

}
