package de.dbae.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for every *.jsp file to check if there is a logged in User.
 * If that is not the case the request is redirected to index.jsp.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebFilter( 
		urlPatterns={"*.jsp"}
		)
public class FilterLogin implements Filter {

    /**
     * Default constructor. 
     */
    public FilterLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean logedIn = ((HttpServletRequest) request).getSession().getAttribute("logedInPerson") == null;
		String path = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length());
		if (!path.equals("/index.jsp")) {
			if (!logedIn) {
				// if user is logged in, complete request
				chain.doFilter(request, response);
			} else {
				// not logged in, go to login page
				((HttpServletResponse) response).sendRedirect("index.jsp");
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
