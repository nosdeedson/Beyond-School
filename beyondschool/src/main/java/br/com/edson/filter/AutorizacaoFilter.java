package br.com.edson.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.edson.Model.Usuario;

@WebFilter( filterName = "AutoricacaoFilter", urlPatterns = "/APP/*")
public class AutorizacaoFilter implements Filter {

	@Inject
	private Usuario user;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = ( HttpServletResponse ) res;
		HttpServletRequest request = ( HttpServletRequest ) req;
		HttpSession session = request.getSession();
		
		Usuario user = (Usuario) session.getAttribute("usuario");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/public/login.xhtml");
		}
		else {
			chain.doFilter(req, res);	
		}

		
	}




	

}
