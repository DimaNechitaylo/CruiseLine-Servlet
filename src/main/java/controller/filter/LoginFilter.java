package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.dto.UserDTO;
import model.entity.Role;

@WebFilter("/*")
public class LoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoginFilter.class.getName());

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		boolean loggedIn = request.getSession().getAttribute("user") != null;

		boolean safePages = request.getRequestURI().contains("index.jsp")
				|| request.getRequestURI().contains("signin.jsp") 
				|| request.getRequestURI().contains("signup.jsp")
				|| request.getRequestURI().equals("/CruiseLine-Servlet/");

		boolean tryingToEnter = "signin".equalsIgnoreCase(request.getParameter("action"))
				|| "signup".equalsIgnoreCase(request.getParameter("action"))
				|| "get_cruises".equalsIgnoreCase(request.getParameter("action"))
				|| "view_cruise".equalsIgnoreCase(request.getParameter("action"))
				|| "filter".equalsIgnoreCase(request.getParameter("action"));

		if (loggedIn) {
			UserDTO user = (UserDTO) request.getSession().getAttribute("user");
			boolean tryingToEnterAdmin = user.getRole().equals(Role.USER)
					&& (request.getRequestURI().contains("admin.jsp")
							||"get_orders_that_require_processing".equalsIgnoreCase(request.getParameter("action"))
							|| "order_admin_operation".equalsIgnoreCase(request.getParameter("action")));
			if (tryingToEnterAdmin) {
				response.sendRedirect(request.getContextPath());
			}
		}
		if (loggedIn || safePages || tryingToEnter) {
			logger.debug(request.getParameter("action"));
			logger.debug("else"+loggedIn+safePages+tryingToEnter+"   "+"signup".equalsIgnoreCase(request.getParameter("action")));
			filterChain.doFilter(request, response);
		} else {
			logger.debug(request.getParameter("action"));
			logger.debug("else"+loggedIn+safePages+tryingToEnter+"   "+"signup".equalsIgnoreCase(request.getParameter("action")));
			response.sendRedirect(request.getContextPath() + "/pages/signin.jsp");
		}

	}

}
