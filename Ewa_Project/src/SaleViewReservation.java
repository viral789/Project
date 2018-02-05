import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.HtmlLoader;
import project.MySQLDataStore;

public class SaleViewReservation extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String staffHeaderFile = sc.getRealPath("staffheader.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(staffHeaderFile));
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		HttpSession session = request.getSession();
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		
		out.println("<fieldset>"); 

        out.println("<form class='FontColor' action='ViewReservation'>");
        out.println("<p><label for='hotelId'>Enter User's Email id</label>");
        out.println("<input name='email' type='text'/></p>");
        out.println("<input type='submit' class='FontColor' class='btn btn-default bth-lg' value='View'/>");
        out.println("</form>");
        out.println("<fieldset>");
		
        out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 
	}
}
