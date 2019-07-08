package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截除了请求首页的get方法请求
 */
public class GetRequestFilter implements Filter {
	protected FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httprep = (HttpServletResponse) response;
		HttpServletRequest httpreq = (HttpServletRequest) request;
		String method = httpreq.getMethod();
		String path = httpreq.getServletPath(); // 获取请求的路径
		String referer=httpreq.getHeader("Referer");
		String Path = config.getInitParameter("path"); // 获得初始值的首页路径
		String Path2 = config.getInitParameter("path2");
		if (Path.equals(path)||Path2.equals(path)) { // 判断请求,若请求路径为/SelectBeer.jsp,则不拦截
			chain.doFilter(request, response);
			System.out.println("GetRequestFilter:yes");
		} else {
			if (method.equals("GET")&&referer==null) { // 若通过get方法请求则跳转到首页
				httprep.sendRedirect(httpreq.getContextPath() + Path);
				System.out.println("GetRequestFilter:get is not allowed");
			} else { // 若不为get请求，则允许跳转
				chain.doFilter(request, response);
				System.out.println("GetRequestFilter:not get,so pass");
			}
		}

	}

}
