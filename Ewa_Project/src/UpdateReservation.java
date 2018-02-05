import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.HotelReservation;
import project.HtmlLoader;
import project.MySQLDataStore;

public class UpdateReservation extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String staffHeaderHtml = sc.getRealPath("staffHeader.html");
		HtmlLoader loader = new HtmlLoader();
		String role = (String) request.getSession().getAttribute("role");
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		HttpSession session = request.getSession();
		
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
		

		String hotelId = request.getParameter("id");
		
		session.setAttribute("hotelId", hotelId);
		HashMap<String, List<HotelReservation>> hashReservation = new HashMap<String, List<HotelReservation>>();
		
		hashReservation = mysql.getUserBookingByhotelId(userId, Integer.parseInt(hotelId));
		double price = 0.0;
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		for(Map.Entry<String, List<HotelReservation>> map :hashReservation.entrySet()){
			out.println("<fieldset>"); 
			out.println("<h2>Update Product details</h2>");    
			out.println("<form action='UpdateReservationServlet?action=2'>");
		
			for(HotelReservation list : map.getValue()){
				price = list.getTotalPrice() / list.getQuantity();
				out.println("<p><label for='productId'>Hotel ID:</label>");
                out.println("<input name='HotelId' class='FontColor' id='id' value='"+list.getHotelId()+"' type='text'/></p>");

                out.println("<p><label for='name'>Checkin Date</label>");
                out.println("<input name='checkinDate'value='"+list.getCheckinDate()+"' type='text'/></p>");
                
                out.println("<p><label for='condition'>Checkout Date:</label>");
                out.println("<input name='checkoutDate'value='"+list.getCheckoutDate()+"' type='text'/></p>");
                
                out.println("<p><label for='type'>Room Type:</label>");
                out.println("<select class='FontColor'value='"+list.getHotelRoom()+"' name='type'>");
                out.println("<option>Standard Single Room</option>");
                out.println("<option>Suite room</option>");
                out.println("<option>Single room</option>");
                out.println("<option>Double room</option>");
                out.println("</select>");
                
                out.println("<p><label for='price'>Rooms</label>");
                out.println("<input name='rooms' class='FontColor' id='price' value='"+list.getQuantity()+"' type='text'/></p>");
                
                out.println("<p><label for='price'>Price per Room</label>");
                out.println("<span>"+price+"</span></p>");
                session.setAttribute("price", price);
                
			}
			out.println("<input type='submit' class='btn btn-primary btn-lg' value='Update'/>");
	        out.println("</form>");
	        out.println("<fieldset>"); 
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
