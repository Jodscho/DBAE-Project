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

import de.dbae.administration.Admin;
import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;

/**
 * Filters for the suche.jsp site to check if the logged in person is either
 * a Admin or a premium User.
 * 
 * @author Jonathan Lochmann
 */
@WebFilter(urlPatterns={"/suche.jsp"})
public class FilterPremium implements Filter {

    /**
     * Default constructor. 
     */
    public FilterPremium() {
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

		HttpServletRequest req = (HttpServletRequest) request;
		Nutzer logedInPerson = (Nutzer) req.getSession().getAttribute("logedInPerson");
		
		if(logedInPerson instanceof Admin){
			chain.doFilter(request, response);
		} else if (logedInPerson instanceof Student){
			if(((Student) logedInPerson).isPremium()){
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendRedirect("courseOverview.jsp");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
