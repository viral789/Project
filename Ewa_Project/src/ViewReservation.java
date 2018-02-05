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
import javax.servlet.http.HttpSession;

import project.Hotel;
import project.HotelReservation;
import project.HtmlLoader;
import project.MySQLDataStore;

public class ViewReservation extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String staffHeaderHtml = sc.getRealPath("staffHeader.html");
		HtmlLoader loader = new HtmlLoader();
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		HttpSession session = request.getSession();
		String role = (String) request.getSession().getAttribute("role");
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
		HashMap<String, List<HotelReservation>> hashReservation = new HashMap<String, List<HotelReservation>>();
		
		hashReservation = mysql.getUserBooking(userId);
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		

		for(Map.Entry<String,List<HotelReservation>> map : hashReservation.entrySet()){
			out.println("<table>");
			for(HotelReservation list : map.getValue()){
				out.println("<form>");
		        out.println("<tr>");
		        out.println("<td>");
		        out.println("<span>Hotel Id: "+list.getHotelId()+"</span><br/>");
		        out.println("<span>Number of Rooms: "+list.getQuantity()+"</span><br/>");
		        out.println("<span>Booking from "+list.getCheckinDate()+"</span><br/>");
		        out.println("<span>Booking till "+list.getCheckoutDate()+"</span><br/>");
		        out.println("<span>Total Price is: "+list.getTotalPrice()+"</span><br/>");
		        out.println("<br/>");
		        /*out.println("<a href='CancelReservation?id='"+list.getHotelId()+"><input type='Button' value='Cancel Reservation'>");*/
		        out.println("<a href='CancelReservation?id="+list.getHotelId()+"'"+"><input type='Button' class='FontColor' value='Cancel Reservation'>");
		        out.println("</a><br/>");
		        
		        out.println("<a href='UpdateReservation?id="+list.getHotelId()+"'"+"><input type='Button' class='FontColor' value='Update Reservation'>");
		        out.println("</a><br/>");
		        
		        out.println("</td>");
		        out.println("</tr>");
		        out.println("</form>");
			}
			 out.println("</table>");
		}
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 	
	}
}
