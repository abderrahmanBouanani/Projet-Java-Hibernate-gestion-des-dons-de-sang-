package filter;

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
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/users.jsp", "/profil.jsp", "/donHistory.jsp"})
public class SecurityFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        boolean isLoggedIn = (session != null && (session.getAttribute("donneur") != null || session.getAttribute("admin") != null));
        
        if (!isLoggedIn) {
            // Rediriger vers la page de login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/RouteController?page=login");
        } else {
            // Continuer la chaîne des filtres
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}