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

import model.service.impl.UserServiceImpl;

@WebFilter("/*")
public class LoginFilter  implements Filter{
    private static Logger logger = Logger.getLogger(LoginFilter.class.getName());

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean loggedIn = request.getSession().getAttribute("user") != null;

        boolean safePages =  (request.getRequestURI().contains("index.jsp")
                || request.getRequestURI().contains("signin.jsp")
                || request.getRequestURI().contains("signup.jsp")
                || request.getRequestURI().contains("test.jsp")
                || request.getRequestURI().equals("/CruiseLine-Servlet/"));

        boolean tryingToEnter =  "signin".equalsIgnoreCase(request.getParameter("action"))
        		|| "signup".equalsIgnoreCase(request.getParameter("action"))
        		|| "get_cruises".equalsIgnoreCase(request.getParameter("action"));

        if (loggedIn || safePages || tryingToEnter) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath()+"/pages/signin.jsp");
        }	
        
	}

}
