/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.54
 * Generated at: 2021-12-14 17:34:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class lisaavene_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<script src=\"scripts/main.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">\r\n");
      out.write("<title>Lis???? vene</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onkeydown=\"tutkiKey(event)\">\r\n");
      out.write("<form id=\"tiedot\">\r\n");
      out.write("	<table>\r\n");
      out.write("		<thead>	\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th colspan=\"4\" id=\"ilmo\"></th>\r\n");
      out.write("				<th colspan=\"2\" class=\"oikealle\"><a href=\"listaaveneet.jsp\" id=\"takaisin\">Takaisin listaukseen</a></th>\r\n");
      out.write("			</tr>		\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>Nimi</th>\r\n");
      out.write("				<th>Merkkimalli</th>\r\n");
      out.write("				<th>Pituus</th>\r\n");
      out.write("				<th>Leveys</th>\r\n");
      out.write("				<th>Hinta</th>\r\n");
      out.write("				<th></th>\r\n");
      out.write("			</tr>\r\n");
      out.write("		</thead>\r\n");
      out.write("		<tbody>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td><input type=\"text\" name=\"nimi\" id=\"nimi\"></td>\r\n");
      out.write("				<td><input type=\"text\" name=\"merkkimalli\" id=\"merkkimalli\"></td>\r\n");
      out.write("				<td><input type=\"text\" name=\"pituus\" id=\"pituus\"></td>\r\n");
      out.write("				<td><input type=\"text\" name=\"leveys\" id=\"leveys\"></td>\r\n");
      out.write("				<td><input type=\"text\" name=\"hinta\" id=\"hinta\"></td> \r\n");
      out.write("				<td><input type=\"button\" name=\"nappi\" id=\"tallenna\" value=\"Lis????\" onclick=\"lisaaTiedot()\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("		</tbody>\r\n");
      out.write("	</table>\r\n");
      out.write("</form>\r\n");
      out.write("<span id=\"ilmo\"></span>\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("function tutkiKey(event){\r\n");
      out.write("	if(event.keyCode==13){//Enter\r\n");
      out.write("		lisaaTiedot();\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("document.getElementById(\"nimi\").focus();//vied????n kursori nimikentt????n sivun latauksen yhteydess??\r\n");
      out.write("\r\n");
      out.write("//funktio tietojen lis????mist?? varten. Kutsutaan backin POST-metodia ja v??litet????n kutsun mukana uudet tiedot json-stringin??.\r\n");
      out.write("//POST /veneet/\r\n");
      out.write("function lisaaTiedot(){	\r\n");
      out.write("	var ilmo=\"\";\r\n");
      out.write("	var nimi = document.getElementById(\"nimi\").value;\r\n");
      out.write("	var merkkimalli = document.getElementById(\"merkkimalli\").value;\r\n");
      out.write("	var pituus = siivoa(document.getElementById(\"pituus\").value); //korvataan sy??tteen merkki ennen validointia, jotta voidaan laskea\r\n");
      out.write("	var leveys = siivoa(document.getElementById(\"leveys\").value); \r\n");
      out.write("	var hinta = document.getElementById(\"hinta\").value;\r\n");
      out.write("	\r\n");
      out.write("	if(nimi.length<2||merkkimalli.length<2||pituus*1!=pituus||leveys*1!=leveys||hinta*1!=hinta){ //validoidaan arvot, luvut kerrotaan itsell????n numeraalisen arvon varmistamiseksi\r\n");
      out.write("		document.getElementById(\"ilmo\").innerHTML = \"Antamasi arvot eiv??t kelpaa!\"\r\n");
      out.write("		return;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	if(ilmo!=\"\"){\r\n");
      out.write("		document.getElementById(\"ilmo\").innerHTML=ilmo;\r\n");
      out.write("		setTimeout(function(){ document.getElementById(\"ilmo\").innerHTML=\"\"; }, 3000);\r\n");
      out.write("		return;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	document.getElementById(\"nimi\").value=siivoa(document.getElementById(\"nimi\").value);\r\n");
      out.write("	document.getElementById(\"merkkimalli\").value=siivoa(document.getElementById(\"merkkimalli\").value);\r\n");
      out.write("	document.getElementById(\"pituus\").value=siivoa(document.getElementById(\"pituus\").value);\r\n");
      out.write("	document.getElementById(\"leveys\").value=siivoa(document.getElementById(\"leveys\").value);\r\n");
      out.write("	document.getElementById(\"hinta\").value=siivoa(document.getElementById(\"hinta\").value);	\r\n");
      out.write("	\r\n");
      out.write("	var formJsonStr=formDataToJSON(document.getElementById(\"tiedot\")); //muutetaan lomakkeen tiedot json-stringiksi\r\n");
      out.write("		\r\n");
      out.write("	fetch(\"veneet\",{//L??hetet????n kutsu backendiin\r\n");
      out.write("	      method: 'POST',\r\n");
      out.write("	      body:formJsonStr\r\n");
      out.write("	    })\r\n");
      out.write("	.then( function (response) {//Odotetaan vastausta ja muutetaan JSON-vastaus objektiksi		\r\n");
      out.write("		return response.json()\r\n");
      out.write("	})\r\n");
      out.write("	.then( function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss??	\r\n");
      out.write("		var vastaus = responseJson.response;		\r\n");
      out.write("		if(vastaus==0){\r\n");
      out.write("			document.getElementById(\"ilmo\").innerHTML= \"Veneen lis????minen ep??onnistui\";\r\n");
      out.write("      	}else if(vastaus==1){	        	\r\n");
      out.write("      		document.getElementById(\"ilmo\").innerHTML= \"Veneen lis????minen onnistui\";			      	\r\n");
      out.write("		}\r\n");
      out.write("		setTimeout(function(){ document.getElementById(\"ilmo\").innerHTML=\"\"; }, 5000);\r\n");
      out.write("	});	\r\n");
      out.write("	document.getElementById(\"tiedot\").reset(); //tyhjennet????n tiedot -lomake\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
