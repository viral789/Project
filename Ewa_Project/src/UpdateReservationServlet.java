import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.HotelReservation;
import project.HtmlLoader;
import project.MySQLDataStore;

public class UpdateReservationServlet extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String staffHeaderHtml = sc.getRealPath("staffHeader.html");
		HtmlLoader loader = new HtmlLoader();
		String role = (String) request.getSession().getAttribute("role");
		
		if(role.equals("customer")){
			out.println(loader.readFile(headerHtml));
		}
		if(role.equals("staff")){
			out.println(loader.readFile(staffHeaderHtml));
		}
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		HttpSession session = request.getSession();
		
		int userId = (int)request.getSession().getAttribute("userId");

		String hotelId = (String)request.getSession().getAttribute("hotelId");
		
		String checkinDate = request.getParameter("checkinDate");
		String checkoutDate = request.getParameter("checkoutDate");
		String rooms = request.getParameter("rooms");
		String type = request.getParameter("type");
		double price = (double)request.getSession().getAttribute("price");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date checkInDate = null, checkOutDate = null;
		try{
			checkInDate = format.parse(checkinDate);
			checkOutDate = format.parse(checkoutDate);
			
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		
		int result = 0;
		int difference = dateDifference(checkInDate, checkOutDate);
		
		double totalPrice =difference * price * Integer.parseInt(rooms);
		HotelReservation reservation = new HotelReservation();
		reservation.setCheckinDate(checkinDate);
		reservation.setCheckoutDate(checkoutDate);
		reservation.setHotelId(Integer.parseInt(hotelId));
		reservation.setUserId(userId);
		reservation.setQuantity(Integer.parseInt(rooms));
		reservation.setHotelRoom(type);
		reservation.setTotalPrice(totalPrice);
		
		
		if(difference <1){
			out.println("please choose correct dates.");
		}
		else{
			result = mysql.updateReservation(reservation);
		}
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		if(result == 1){
			out.println("Reservation for this hotel "+ hotelId + " is updated");
		}
		else{
			out.println("Cannot update reservation");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

	private int dateDifference(Date checkinDate, Date checkoutDate) {
		// TODO Auto-generated method stub
		
		int difference = 0;
		difference = (int) ((checkoutDate.getTime() - checkinDate.getTime())/(1000*60*60*24));
		return difference;
	}
}
