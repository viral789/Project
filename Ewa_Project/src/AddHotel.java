import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.HtmlLoader;
import project.MySQLDataStore;

public class AddHotel extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
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
		out.println("<h2>Enter Hotel details</h2>");     
        out.println("<form method='post' class='FontColor' action='SaveHotel?action=1'>");
		
        out.println("<p><label for='HotelId'>Hotel ID:</label>");
        out.println("<input name='hotelId' class='FontColor' value='' type='text'/></p>");

        out.println("<p><label for='name'>Hotel Name:</label>");
        out.println("<input name='hotelName' class='FontColor' value='' type='text'/></p>");
        
        out.println("<p><label for='city'>City:</label>");
        out.println("<input name='city' class='FontColor' value='' type='text'/></p>");
        
        out.println("<p><label for='price'>Price:</label>");
        out.println("<input name='price' id='price' class='FontColor' value='' type='text'/></p>");
        
        out.println("<p><label for='image'>Image:</label>");
        out.println("<input name='image' id='image' class='FontColor' value='' type='text'/></p>");
        
        out.println("<p><label for='type'>Room Type:</label>");
        out.println("<select class='FontColor' name='role'>");
        out.println("<option>Standard Single Room</option>");
        out.println("<option>Suite room</option>");
        out.println("<option>Single room</option>");
        out.println("<option>Double room</option>");
        
        out.println("</select>");
        
        out.println("<p><label for='quantity'>Quantity:</label>");
        out.println("<input name='quantity' class='FontColor' value='' type='text'/></p>");
        
        out.println("<br/><input type='submit' style='background-color:#333;' class='btn btn-primary btn-lg' value='Add Hotel'></a>"+"<br/>");
        out.println("</form>");
        out.println("<fieldset>");
        out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 
		
	}
}
