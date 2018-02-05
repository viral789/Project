import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Hotel;
import project.HtmlLoader;
import project.MySQLDataStore;

public class SaveHotel extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("storeManagerheader.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		
		try{
			String action = request.getParameter("action");
			int hotelId = Integer.parseInt(request.getParameter("hotelId"));
			String hotelName = request.getParameter("hotelName");
			String city = request.getParameter("city");
			double price = Double.parseDouble(request.getParameter("price"));
			String image = request.getParameter("image");
			String role = request.getParameter("role");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Hotel hotel = new Hotel();
			
			hotel.setCity(city);
			hotel.setHotelId(hotelId);
			hotel.setHotelName(hotelName);
			hotel.setHotelRoom(role);
			hotel.setQuantity(quantity);
			hotel.setImages(image);
			hotel.setPrice(price);
			
			int result =0;
			MySQLDataStore mysql = new MySQLDataStore();
			if(action.equals("1")){
				result = mysql.addHotel(hotel);
				if(result == 1){
					out.println("Hotel added successfully");
				}
				else{
					out.println("Error while adding Hotel ");
				}

			}
			if(action.equals("2")){
				result = mysql.updateHotel(hotel);
				if(result == 1){
					out.println("Hotel Updated successfully");
				}
				else{
					out.println("Error while updating Hotel ");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>"); 
			
	}

}
