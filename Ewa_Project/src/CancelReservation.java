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

public class CancelReservation extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String staffHeaderHtml = sc.getRealPath("staffHeader.html");
		HtmlLoader loader = new HtmlLoader();
		String role = (String) request.getSession().getAttribute("role");
		MySQLDataStore mysql = new MySQLDataStore();
		
		int userId = 0;
		if(role.equals("customer")){
			userId = (int)request.getSession().getAttribute("userId");
			out.println(loader.readFile(headerHtml));
		}
		if(role.equals("staff")){
			String email = request.getParameter("email");
			userId = mysql.getuserId(email);
			out.println(loader.readFile(staffHeaderHtml));
		}
		
		
		
		HttpSession session = request.getSession();
		

		String hotelId = request.getParameter("id");
		
		int result = mysql.cancelReservation(userId, Integer.parseInt(hotelId));
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		if(result == 1){
			out.println("Reservation for this hotel "+ hotelId + " is cancelled");
		}
		else{
			out.println("Cannot cancel reservation");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
