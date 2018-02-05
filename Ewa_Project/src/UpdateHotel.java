import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jrockit.jfr.jdkevents.IoTracer;
import project.Hotel;
import project.HtmlLoader;
import project.MySQLDataStore;

public class UpdateHotel extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("storeManagerheader.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		out.println("<fieldset>"); 

        out.println("<form class='FontColor' action='UpdateHotelServlet'>");
        out.println("<p><label for='hotelId'>Enter Hotel ID:</label>");
        out.println("<input name='id' type='text'/></p>");
        out.println("<input type='submit' class='FontColor' class='FontColor' value='Update'/>");
        out.println("</form>");
        out.println("<fieldset>");
		
        out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 
	}
	
	
}
