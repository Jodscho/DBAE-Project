package de.dbae.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class XSSFilter
 * 
 * @author Jonathan Lochmann
 */
@WebFilter("/*")
public class XSSFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public XSSFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest) request);
		chain.doFilter(wrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	class XSSRequestWrapper extends HttpServletRequestWrapper {

		public XSSRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String parameter) {
			String value = super.getParameter(parameter);
			return stripXSS(value);
		}

		@Override
		public String[] getParameterValues(String parameter) {
			String[] values = super.getParameterValues(parameter);
			if (values == null) {
				return null;
			}
			int count = values.length;
			String[] encodedValues = new String[count];
			for (int i = 0; i < count; i++) {
				encodedValues[i] = stripXSS(values[i]);
			}
			return encodedValues;

		}

		@Override
		public String getHeader(String name) {
			String value = super.getHeader(name);
			return stripXSS(value);
		}

		private String stripXSS(String value) {
			if (value != null) {
				Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll(" ");
				scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll(" ");
				scriptPattern = Pattern.compile("<script>(.*?)>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll(" ");
				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll(" ");
				scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll(" ");
			}

			return value;
		}
	}

}
