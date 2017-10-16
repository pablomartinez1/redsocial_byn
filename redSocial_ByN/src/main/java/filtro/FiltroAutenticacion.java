package filtro;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.MbAutenticar;

@WebFilter(filterName="authFilter",urlPatterns="*.xhtml")
public class FiltroAutenticacion implements Filter{

	@Inject
	private MbAutenticar MbAutenticar;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpServletResponse servResp = (HttpServletResponse) resp;
		
		if(servReq.getRequestURI().equals("/home.xhtml") && !MbAutenticar.estaLogeado()){
			servResp.sendRedirect("register.xhtml");
		}  else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
