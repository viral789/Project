import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class OrderServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		HttpSession session = request.getSession();
		
		
		HashMap<String, ArrayList<HotelReservation>> hashReservation = new HashMap<String, ArrayList<HotelReservation>>();
		hashReservation = (HashMap)session.getAttribute("sessionReservation");
		
		String quantity = (String)request.getSession().getAttribute("rooms");
		String hotelId = (String)request.getSession().getAttribute("hotelId");
		int result = 0;
		try {
			result = mysql.addReservation(hashReservation);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int initialHotelQuantity = mysql.getQuantity(Integer.parseInt(hotelId));
		int alterQuantity = initialHotelQuantity - Integer.parseInt(quantity);
		mysql.updateHotelquantity(alterQuantity, Integer.parseInt(hotelId));
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		if(result == 1){
			out.println("</br>");
			for(Map.Entry<String, ArrayList<HotelReservation>> map :hashReservation.entrySet()){
				for(HotelReservation list : map.getValue()){
					out.println("Hotel with id "+list.getHotelId()+" has been Booked");
					out.println("</br>");
					out.println("Checkin Date is " +list.getCheckinDate()+ " and checkout Date is " +list.getCheckoutDate());
					out.println("</br>");
					out.println("Total price for "+list.getQuantity()+ "rooms is " + list.getTotalPrice());
					out.println("</br>");
					out.println("To view Reservation:");
					out.println("<a href='ViewReservation'>View Reservation</a>");
					
				}
			}
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 	
		
	}
}
