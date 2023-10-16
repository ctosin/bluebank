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

@WebFilter(urlPatterns = "/secured/*")
public class SecurityFilter extends HttpFilter implements Filter {
	
	@Inject
	private LoggedUser loggedUser;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (!loggedUser.isLogged()) {
			response.sendRedirect(request.getContextPath() + "/login.xhtml");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
