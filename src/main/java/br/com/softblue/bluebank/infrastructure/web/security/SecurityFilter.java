package br.com.softblue.bluebank.infrastructure.web.security;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebFilter("/secured/*")
public class SecurityFilter extends HttpFilter implements Filter {
	
	@Inject
	private LoggedUserBean loggedUserBean;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if (!loggedUserBean.isLogged()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
