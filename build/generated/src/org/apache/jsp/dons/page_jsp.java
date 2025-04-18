package org.apache.jsp.dons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import services.DonService;
import entities.Don;
import entities.Donneur;
import entities.CentreDon;
import services.DonService;
import services.DonneurService;
import services.CentreDonService;
import java.text.SimpleDateFormat;

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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Gestion des Dons</title>\n");
      out.write("        <style>\n");
      out.write("            table { border-collapse: collapse; width: 100%; }\n");
      out.write("            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
      out.write("            th { background-color: #f2f2f2; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Gestion des Dons de Sang</h1>\n");
      out.write("\n");
      out.write("        <fieldset>\n");
      out.write("            <legend>Nouveau Don</legend>\n");
      out.write("            <form method=\"POST\" action=\"../DonController\">\n");
      out.write("                <table>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Date:</td>\n");
      out.write("                        <td><input type=\"date\" name=\"dateDon\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Lieu:</td>\n");
      out.write("                        <td><input type=\"text\" name=\"lieuDon\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Donneur:</td>\n");
      out.write("                        <td>\n");
      out.write("                            <select name=\"donneurId\" required>\n");
      out.write("                                <option value=\"\">Sélectionner un donneur</option>\n");
      out.write("                                ");

                                    DonneurService ds = new DonneurService();
                                    for (Donneur d : ds.findAll()) {
                                
      out.write("\n");
      out.write("                                <option value=\"");
      out.print( d.getIdUser());
      out.write('"');
      out.write('>');
      out.print( d.getName());
      out.write(' ');
      out.write('(');
      out.print( d.getGroupeSanguin());
      out.write(")</option>\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Centre:</td>\n");
      out.write("                        <td>\n");
      out.write("                            <select name=\"centreId\" required>\n");
      out.write("                                <option value=\"\">Sélectionner un centre</option>\n");
      out.write("                                ");

                                    CentreDonService cs = new CentreDonService();
                                    for (CentreDon c : cs.findAll()) {
                                
      out.write("\n");
      out.write("                                <option value=\"");
      out.print( c.getIdCentre());
      out.write('"');
      out.write('>');
      out.print( c.getAdresseCentre());
      out.write("</option>\n");
      out.write("                                ");
 } 
      out.write("\n");
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
      out.write("            <legend>Liste des Dons</legend>\n");
      out.write("            <table>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>ID</th>\n");
      out.write("                        <th>Date</th>\n");
      out.write("                        <th>Lieu</th>\n");
      out.write("                        <th>Donneur</th>\n");
      out.write("                        <th>Groupe Sanguin</th>\n");
      out.write("                        <th>Centre</th>\n");
      out.write("                        <th>Action</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        DonService donService = new DonService();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        for (Don d : donService.findAll()) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( d.getId().hashCode() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( sdf.format(d.getId().getDateDon()) );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getId().getLieuDon() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getDonneur().getName() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getDonneur().getGroupeSanguin() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( d.getCentreDon().getAdresseCentre() );
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <form method=\"POST\" action=\"../DonController\">\n");
      out.write("                                <input type=\"hidden\" name=\"op\" value=\"delete\">\n");
      out.write("                                <input type=\"hidden\" name=\"dateDon\" value=\"");
      out.print( new SimpleDateFormat("yyyy-MM-dd").format(d.getId().getDateDon()) );
      out.write("\">\n");
      out.write("                                <input type=\"hidden\" name=\"lieuDon\" value=\"");
      out.print( d.getId().getLieuDon() );
      out.write("\">\n");
      out.write("                                <input type=\"hidden\" name=\"donneurId\" value=\"");
      out.print( d.getDonneur().getIdUser() );
      out.write("\">\n");
      out.write("                                <input type=\"hidden\" name=\"centreId\" value=\"");
      out.print( d.getCentreDon().getIdCentre() );
      out.write("\">\n");
      out.write("                                <input type=\"submit\" value=\"Supprimer\" onclick=\"return confirm('Êtes-vous sûr?')\">\n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
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
