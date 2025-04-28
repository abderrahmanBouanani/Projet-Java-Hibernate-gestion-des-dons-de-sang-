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
      out.write("        <title>Connexion</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <div class=\"login-left\">\n");
      out.write("                <h2>Bienvenue</h2>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"login-right\">\n");
      out.write("                <h4 class=\"mb-4 text-center\">Connexion</h4>\n");
      out.write("                <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/AuthentificationController\" method=\"post\"> <!-- Modifié par v0 -->\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <label for=\"email\" class=\"form-label\">Email</label>\n");
      out.write("                        <div class=\"input-group\">\n");
      out.write("                            <span class=\"input-group-text\"><i class=\"bi bi-envelope\"></i></span>\n");
      out.write("                            <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" required>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <label for=\"password\" class=\"form-label\">Mot de passe</label>\n");
      out.write("                        <div class=\"input-group\">\n");
      out.write("                            <span class=\"input-group-text\"><i class=\"bi bi-lock\"></i></span>\n");
      out.write("                            <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" required>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"mb-3 form-check\">\n");
      out.write("                        <input type=\"checkbox\" class=\"form-check-input\" id=\"remember\">\n");
      out.write("                        <label class=\"form-check-label small-text\" for=\"remember\">Se souvenir de moi</label>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"d-grid\">\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary\">\n");
      out.write("                            <i class=\"bi bi-box-arrow-in-right me-1\"></i>Connexion\n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"divider\">ou</div>\n");
      out.write("\n");
      out.write("                    <div class=\"d-grid mb-3\">\n");
      out.write("                        <button type=\"button\" class=\"btn btn-outline-secondary\">\n");
      out.write("                            <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=forgotPassword\" class=\"text-center\" style=\"color: black;\">Mot de passe oublié ?</a> <!-- Modifié par v0 -->\n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"text-center small-text\">\n");
      out.write("                        Vous n'avez pas de compte ?\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/RouteController?page=inscription\">Créer un compte</a> <!-- Modifié par v0 -->\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
 if (request.getParameter("msg") != null) {
      out.write("\n");
      out.write("                    <div class=\"mt-3 text-danger text-center\">\n");
      out.write("                        ");
      out.print( request.getParameter("msg"));
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    ");
 }
      out.write("\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
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
