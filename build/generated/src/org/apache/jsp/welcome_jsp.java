package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entities.User;

public final class welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Bienvenue</title>\n");
      out.write("    <style>\n");
      out.write("        body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; }\n");
      out.write("        .header { background-color: #d32f2f; color: white; padding: 15px; display: flex; justify-content: space-between; }\n");
      out.write("        .container { width: 80%; margin: 20px auto; padding: 20px; background: white; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }\n");
      out.write("        .welcome-message { font-size: 24px; color: #d32f2f; margin-bottom: 20px; }\n");
      out.write("        .menu { display: flex; gap: 15px; margin-bottom: 20px; }\n");
      out.write("        .menu a { \n");
      out.write("            padding: 10px 15px; background-color: #d32f2f; color: white; \n");
      out.write("            text-decoration: none; border-radius: 4px; \n");
      out.write("        }\n");
      out.write("        .menu a:hover { background-color: #b71c1c; }\n");
      out.write("        .user-info { margin-bottom: 20px; }\n");
      out.write("        .logout { color: white; text-decoration: none; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div>Système de Gestion des Dons de Sang</div>\n");
      out.write("        <div>\n");
      out.write("            Bienvenue, ");
      out.print( ((User)session.getAttribute("user")).getName() );
      out.write("\n");
      out.write("            <a href=\"LogoutController\" class=\"logout\">Déconnexion</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"welcome-message\">Bienvenue dans votre espace personnel</div>\n");
      out.write("        \n");
      out.write("        <div class=\"menu\">\n");
      out.write("            ");
 if(session.getAttribute("user") instanceof entities.Admin) { 
      out.write("\n");
      out.write("                <a href=\"admin/dashboard.jsp\">Tableau de bord Admin</a>\n");
      out.write("            ");
 } else { 
      out.write("\n");
      out.write("                <a href=\"donneurs/page.jsp\">Mes dons</a>\n");
      out.write("                <a href=\"donneurs/profile.jsp\">Mon profil</a>\n");
      out.write("                <a href=\"dons/new.jsp\">Nouveau don</a>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"user-info\">\n");
      out.write("            <p>Email: ");
      out.print( ((User)session.getAttribute("user")).getEmail() );
      out.write("</p>\n");
      out.write("            ");
 if(session.getAttribute("user") instanceof entities.Donneur) { 
      out.write("\n");
      out.write("                <p>Groupe sanguin: ");
      out.print( ((entities.Donneur)session.getAttribute("user")).getGroupeSanguin() );
      out.write("</p>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>");
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
