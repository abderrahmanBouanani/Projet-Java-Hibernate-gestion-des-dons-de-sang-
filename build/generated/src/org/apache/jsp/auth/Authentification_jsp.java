package org.apache.jsp.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Authentification_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Connexion - Gestion des Dons de Sang</title>\n");
      out.write("        \n");
      out.write("        <!-- Bootstrap CSS -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- Bootstrap Icons -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- Custom CSS -->\n");
      out.write("        <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/css/style.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/css/auth.css\" rel=\"stylesheet\">\n");
      out.write("        \n");
      out.write("        ");

            // Empêcher la mise en cache pour éviter le retour en arrière après déconnexion
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            
            // Détruire la session existante
            session.invalidate();
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <div class=\"login-left\">\n");
      out.write("                <div>\n");
      out.write("                    <h2><i class=\"bi bi-droplet-fill\"></i> Gestion des Dons de Sang</h2>\n");
      out.write("                    <p class=\"mt-3\">Plateforme de gestion des dons de sang pour sauver des vies</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"login-right\">\n");
      out.write("                <div class=\"auth-body\">\n");
      out.write("                    <h4 class=\"mb-4 text-center\">Connexion</h4>\n");
      out.write("                    \n");
      out.write("                    <!-- Affichage des messages d'erreur -->\n");
      out.write("                    ");
 if (request.getAttribute("error") != null) { 
      out.write("\n");
      out.write("                        <div class=\"alert alert-danger\">\n");
      out.write("                            <i class=\"bi bi-exclamation-triangle-fill\"></i> ");
      out.print( request.getAttribute("error") );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                    \n");
      out.write("                    <!-- Affichage des messages de succès -->\n");
      out.write("                    ");
 if (request.getAttribute("successMessage") != null) { 
      out.write("\n");
      out.write("                        <div class=\"alert alert-success\">\n");
      out.write("                            <i class=\"bi bi-check-circle-fill\"></i> ");
      out.print( request.getAttribute("successMessage") );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                    \n");
      out.write("                    <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/AuthentificationController\" method=\"post\" class=\"auth-form\">\n");
      out.write("                        <div class=\"mb-3\">\n");
      out.write("                            <label for=\"email\" class=\"form-label\">Email</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <span class=\"input-group-text\"><i class=\"bi bi-envelope\"></i></span>\n");
      out.write("                                <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" \n");
      out.write("                                       value=\"");
      out.print( request.getAttribute("email") != null ? request.getAttribute("email") : "" );
      out.write("\" \n");
      out.write("                                       required>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"mb-3\">\n");
      out.write("                            <label for=\"password\" class=\"form-label\">Mot de passe</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <span class=\"input-group-text\"><i class=\"bi bi-lock\"></i></span>\n");
      out.write("                                <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" required>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"mb-3 form-check\">\n");
      out.write("                            <input type=\"checkbox\" class=\"form-check-input\" id=\"remember\">\n");
      out.write("                            <label class=\"form-check-label small-text\" for=\"remember\">Se souvenir de moi</label>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"d-grid\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-primary\">\n");
      out.write("                                <i class=\"bi bi-box-arrow-in-right me-1\"></i>Connexion\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"divider\">ou</div>\n");
      out.write("\n");
      out.write("                        <div class=\"d-grid mb-3\">\n");
      out.write("                            <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=forgotPassword\" class=\"btn btn-outline-secondary\">\n");
      out.write("                                Mot de passe oublié ?\n");
      out.write("                            </a>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"text-center small-text\">\n");
      out.write("                            Vous n'avez pas de compte ?\n");
      out.write("                            <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=inscription\">Créer un compte</a>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- Bootstrap JS Bundle with Popper -->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("        \n");
      out.write("        <!-- Script pour empêcher la navigation arrière après déconnexion -->\n");
      out.write("        <script>\n");
      out.write("            window.onload = function() {\n");
      out.write("                if (window.history && window.history.pushState) {\n");
      out.write("                    window.history.pushState('forward', null, '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=login');\n");
      out.write("                    window.onpopstate = function() {\n");
      out.write("                        window.history.pushState('forward', null, '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=login');\n");
      out.write("                    };\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
