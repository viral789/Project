import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
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

public class BookHotelServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		List<String> hotelImages = new ArrayList<String>();
		List<String> hotelNames = new ArrayList<String>();
		List<Double> hotelPrices = new ArrayList<Double>();
		List<Integer> hotelIds = new ArrayList<Integer>();
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		String hotelId = request.getParameter("id");
		
		HashMap<String,List<Hotel>> hashHotel = new HashMap<String,List<Hotel>>();
		
		hashHotel = mysql.getSelectedHotel(hotelId);
		
		HttpSession session = request.getSession();
		double totalPrice = 0.0;
		int userId = (int)request.getSession().getAttribute("userId");
		String checkinDate = (String)request.getSession().getAttribute("checkinDate");
		String checkoutDate = (String)request.getSession().getAttribute("checkoutDate");
		String quantity = (String)request.getSession().getAttribute("rooms");
		String hotelRoom = (String)request.getSession().getAttribute("roomType");
		String city = (String) request.getSession().getAttribute("city");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date checkInDate = null, checkOutDate = null;
		try{
			checkInDate = format.parse(checkinDate);
			checkOutDate = format.parse(checkoutDate);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		int numberOfDays = dateDifference(checkInDate, checkOutDate);
		HashMap<String, List<Hotel>> hashHotelbyCity = new HashMap<String, List<Hotel>>();
		
		hashHotelbyCity = mysql.getHotelByCity(city);
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		HotelReservation reservation = new HotelReservation();
		HashMap<String, ArrayList<HotelReservation>> hashReserve = new HashMap<String, ArrayList<HotelReservation>>();
		ArrayList<HotelReservation> reservationList = new ArrayList<>();
		
		if(hotelId != null){
			out.println("<form action='OrderServlet' class='FontColor'>");
			
			for(Map.Entry<String,List<Hotel>> map : hashHotel.entrySet()){
				for(Hotel list : map.getValue()){
					totalPrice = Integer.parseInt(quantity) * list.getPrice() * numberOfDays;
					reservation.setHotelId(Integer.parseInt(hotelId));
					reservation.setUserId(userId);
					reservation.setHotelRoom(hotelRoom);
					reservation.setQuantity(Integer.parseInt(quantity));
					reservation.setCheckinDate(checkinDate);
					reservation.setCheckoutDate(checkoutDate);
					reservation.setTotalPrice(totalPrice);
					
					reservationList.add(reservation);
					hashReserve.put(hotelId, reservationList);
					session.setAttribute("hotelId", hotelId);
					session.setAttribute("sessionReservation", hashReserve);
					out.println("<table>");
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("<span>Hotel Id: "+hotelId+"</span><br/>");
			        out.println("<span>Hotel Name is: "+list.getHotelName()+"</span><br/>");
			        out.println("<span>Hotel Price per night: "+list.getPrice()+"</span><br/>");
			        out.println("<span>Number of Rooms: "+quantity+"</span><br/>");
			        out.println("<span>Total Price is: "+totalPrice+"</span><br/>");
			        out.println("</td>");
			        out.println("</tr>");
			        out.println("</table>");
			        out.println("<br/>");
			        out.println("<a href='index.html'><input type='Button' value='Change Hotel'>");
			        out.println("</a><br/>");
				}
			}
			
			for(Map.Entry<String,List<Hotel>> map : hashHotelbyCity.entrySet()){
				for(Hotel list : map.getValue()){
					hotelImages.add(list.getImages());
					hotelNames.add(list.getHotelName());
					hotelPrices.add(list.getPrice());
					hotelIds.add(list.getHotelId());
				}
			}
			out.println("<table>");
	        out.println("<tr>");
			out.println("<td>");
			out.println("<label for='name' >   Enter Your Full Name   : </label>");
			out.println("</th>");
			out.println("<td>");
			out.println("<input type='text' class ='FontColor' name='name' id='name' maxlength='50' /><br>");
			out.println("</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>");
			out.println("<label for='address'> Address         :</label>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text' class ='FontColor' name='address' id='address' maxlength='50' /><br>");
			out.println("</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>");
			out.println("<label for='Eid'>   Email Id         :</label>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='email' class ='FontColor' name='emailid' id='username' maxlength='50' /><br>");
			out.println("</td>");
			out.println("</tr>");


			out.println("<tr>");
			out.println("<td>");
			out.println("<label for='phnumber'>Phone Number:</label>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='telephone' class ='FontColor' name='phnumber'  maxlength='50' /><br>");
			out.println("</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>");
			out.println("<label for='ccnumber'>Credit card Number:</label>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text' class ='FontColor' name='cardnumber' id='cardnumber' maxlength='50' /><br>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<label for='totalAmount'>Total Amount:</label>");
			out.println("</td>");
			out.println("<td>");
			out.println(totalPrice + "<br>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			
			
			out.println("<td colspan='2' align='center'>");
			out.println("</table>");
	        out.println("<br/><input type='submit' style='background-color:#333;' class='btn btn-primary btn-lg' value='Confirm booking'></a>"+"<br/>");
	        out.println("</form>");
	        
	        out.println("<div class='container'>");
			out.println("<div id='myCarousel' class='carousel slide' data-ride='carousel' style='width:100%;>");
			out.println("<span class='carousel-indicators'>");
			out.println("<span data-target='#myCarousel' data-slide-to='0' class='active'></span>");
			for(int i = 1; i< hotelImages.size(); i++)
			{
				out.println("<span data-target='#myCarousel' data-slide-to='"+i+"' class='active'></span>");
			}
			out.println("</span>");
			out.println("<div class='carousel-inner'>");
			
			out.println("<div class='item active'>");
			out.println("<img src='"+hotelImages.get(0)+"' style='width:100%;'>");
			out.println("<div class='carousel-caption'>");
			out.println("<form method = 'get' action = 'ViewHotel'>");
			out.println("<input class = 'FontColor' type = 'submit' name = 'submit' value = 'Buy'>");
			out.println("<input type = 'hidden' style='background-color:#ddd; color:#000;'  name='hotelName' value='"+hotelNames.get(0)+"'>");
			out.println("</form>");
			/*out.println("<form  method = 'get' action = 'ViewHotel?hotelName="+hotelNames.get(0)+"'>");
			out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;' type = 'submit' name = 'submit'  width='70' height='40' value = '      Book      '>");
			out.println("</form>");*/
			out.println("<form  method = 'get' action = 'ReviewServlet'>");
			out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;'  type = 'submit' name = 'submit' value = 'Write Review'>");
			out.println("<input type = 'hidden' name='HotelId' value='"+hotelIds.get(0)+"'>");
			out.println("<input type = 'hidden' name='HotelName' value='"+hotelNames.get(0)+"'>");
			out.println("<input type = 'hidden' name='HotelPrice' value='"+hotelPrices.get(0)+"'>");
			out.println("</form>");

			out.println("<form method = 'get' action = 'ViewReview'>");
			out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;'  type = 'submit' name = 'submit' value = 'View Reviews'>");
			out.println("<input type = 'hidden' name='HotelName' value='"+hotelNames.get(0)+"'>");
			out.println("</form>");
			out.println("</div>");
			out.println("</div>");
			
 			for(int i = 1; i< hotelImages.size(); i++)
			{
				out.println("<div class='item'>");
				out.println("<img src='"+hotelImages.get(i)+"' style='width:100%;'>");
				out.println("<div class='carousel-caption'>");
				out.println("<form method = 'get' action = 'ViewHotel'>");
				out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;'  type = 'submit' name = 'submit' value = 'Buy'>");
				out.println("<input type = 'hidden' name='hotelName' value='"+hotelNames.get(i)+"'>");
				out.println("</form>");
				out.println("<form  method = 'get' action = 'ReviewServlet'>");
				out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;'  type = 'submit' name = 'submit' value = 'Write Review'>");
				out.println("<input type = 'hidden' name='HotelId' value='"+hotelIds.get(i)+"'>");
				out.println("<input type = 'hidden' name='HotelName' value='"+hotelNames.get(i)+"'>");
				out.println("<input type = 'hidden' name='HotelPrice' value='"+hotelPrices.get(i)+"'>");
				out.println("</form>");

				out.println("<form method = 'get' action = 'ViewReview'>");
				out.println("<input class = 'FontColor' style='background-color:#ddd; color:#000;'  type = 'submit' name = 'submit' value = 'View Reviews'>");
				out.println("<input type = 'hidden' name='HotelName' value='"+hotelNames.get(i)+"'>");
				out.println("</form>");
				out.println("</div>");
				out.println("</div>");
			}
		}
		else{
			out.println("Please first select hotel");
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
