package org.apache.jsp.donneurs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import services.DonneurService;
import entities.Donneur;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Gestion des Donneurs</title>\n");
      out.write("        <style>\n");
      out.write("            table { border-collapse: collapse; width: 100%; }\n");
      out.write("            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
      out.write("            th { background-color: #f2f2f2; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Gestion des Donneurs de Sang</h1>\n");
      out.write("\n");
      out.write("        <fieldset>\n");
      out.write("            <legend>Formulaire Donneur</legend>\n");
      out.write("            <form method=\"POST\" action=\"../DonneurController\">\n");
      out.write("                <input type=\"hidden\" name=\"id\" value=\"");
      out.print( request.getParameter("id") != null ? request.getParameter("id") : "" );
      out.write("\" />\n");
      out.write("                <table>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Nom:</td>\n");
      out.write("                        <td><input type=\"text\" name=\"name\" value=\"");
      out.print( request.getParameter("name") != null ? request.getParameter("name") : "" );
      out.write("\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Email:</td>\n");
      out.write("                        <td><input type=\"email\" name=\"email\" value=\"");
      out.print( request.getParameter("email") != null ? request.getParameter("email") : "" );
      out.write("\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Mot de passe:</td>\n");
      out.write("                        <td><input type=\"password\" name=\"password\" value=\"");
      out.print( request.getParameter("password") != null ? request.getParameter("password") : "" );
      out.write("\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Groupe Sanguin:</td>\n");
      out.write("                        <td>\n");
      out.write("                            <select name=\"groupeSanguin\" required>\n");
      out.write("                                <option value=\"\">Sélectionner</option>\n");
      out.write("                                <option value=\"A+\" ");
      out.print( "A+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">A+</option>\n");
      out.write("                                <option value=\"A-\" ");
      out.print( "A-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">A-</option>\n");
      out.write("                                <option value=\"B+\" ");
      out.print( "B+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">B+</option>\n");
      out.write("                                <option value=\"B-\" ");
      out.print( "B-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">B-</option>\n");
      out.write("                                <option value=\"AB+\" ");
      out.print( "AB+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">AB+</option>\n");
      out.write("                                <option value=\"AB-\" ");
      out.print( "AB-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">AB-</option>\n");
      out.write("                                <option value=\"O+\" ");
      out.print( "O+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">O+</option>\n");
      out.write("                                <option value=\"O-\" ");
      out.print( "O-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" );
      out.write(">O-</option>\n");
      out.write("                            </select>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td></td>\n");
      out.write("                        <td><input type=\"submit\" value=\"Enregistrer\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("        </fieldset>\n");
      out.write("\n");
      out.write("        <fieldset>\n");
      out.write("            <legend>Liste des Donneurs</legend>\n");
      out.write("            <table>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>ID</th>\n");
      out.write("                        <th>Nom</th>\n");
      out.write("                        <th>Email</th>\n");
      out.write("                        <th>Groupe Sanguin</th>\n");
      out.write("                        <th>Actions</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        DonneurService ds = new DonneurService();
                        for (Donneur d : ds.findAll()) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( d.getIdUser() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getName() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getEmail() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getGroupeSanguin() );
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"../DonneurController?id=");
      out.print( d.getIdUser() );
      out.write("&op=delete\" \n");
      out.write("                               onclick=\"return confirm('Voulez-vous vraiment supprimer ce donneur?')\">Supprimer</a>\n");
      out.write("                            <a href=\"../DonneurController?id=");
      out.print( d.getIdUser() );
      out.write("&op=update\">Modifier</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </fieldset>\n");
      out.write("\n");
      out.write("        <fieldset>\n");
      out.write("            <legend>Recherche Avancée</legend>\n");
      out.write("            <form method=\"GET\" action=\"recherche.jsp\">\n");
      out.write("                <h3>Recherche par groupe sanguin</h3>\n");
      out.write("                <select name=\"groupeSanguin\">\n");
      out.write("                    <option value=\"A+\">A+</option>\n");
      out.write("                    <option value=\"A-\">A-</option>\n");
      out.write("                    <option value=\"B+\">B+</option>\n");
      out.write("                    <option value=\"B-\">B-</option>\n");
      out.write("                    <option value=\"AB+\">AB+</option>\n");
      out.write("                    <option value=\"AB-\">AB-</option>\n");
      out.write("                    <option value=\"O+\">O+</option>\n");
      out.write("                    <option value=\"O-\">O-</option>\n");
      out.write("                </select>\n");
      out.write("                <input type=\"submit\" value=\"Rechercher\">\n");
      out.write("            </form>\n");
      out.write("        </fieldset>\n");
      out.write("    </body>\n");
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
