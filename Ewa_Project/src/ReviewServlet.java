import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.HtmlLoader;

public class ReviewServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		String hotelName = request.getParameter("HotelName");
		String hotelId = request.getParameter("HotelId");
		String HotelPrice = request.getParameter("HotelPrice");
		
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		
		PrintWriter out = response.getWriter();
		HtmlLoader loader =  new HtmlLoader();
		
		int userId = (int)request.getSession().getAttribute("userId");
		
		out.println(loader.readFile(headerHtml));
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		if(userId >= 1){
			out.println("<form  action='SubmitReview'>");
		  	out.println("<h2>SUBMIT REVIEW</h2>");
		  	out.println("<table>");
		  	out.println("<tr>");
		  	out.println("<td>Hotel Id </td>");
		  	out.println("<td><input type='text' readonly height='20' width='30' name='hotelId' value='"+hotelId+"'"+"></td>");
		  	out.println("</tr>");
		  	out.println("<tr>");
		  	out.println("<td> Hotel Name</td>");
		  	out.println("<td> <input type='text' class ='FontColor' name='hotelName' value='"+hotelName+"'"+"></td>");
		  	out.println("</tr>");
		  	out.println("<tr>");
		  	out.println("<td> Hotel Price</td>");
		  	out.println("<td> <input type='text' class ='FontColor' readonly name='hotelPrice' value='"+HotelPrice+"'"+"></td>");
		  	out.println("</tr>");
		  	out.println("<tr>");
		  	out.println("<td> Review Rating</td>");
		  	out.println("<td> <input type='text' class ='FontColor' name='reviewRating'></td>");
		  	out.println("</tr>");
		  	out.println("<tr>");
		  	out.println("<td> Review Date</td>");
		  	out.println("<td> <input type='text' class ='FontColor' name='reviewDate'></td>");
		  	out.println("</tr>");
		  	out.println("<tr>");
		  	out.println("<td> Review Text</td>");
		  	out.println("<td> <textarea rows='4' cols='50' class ='FontColor' name='reviewText'></textarea></td>");
		  	out.println("</tr>");
		  	out.println("<tr><td> <input type='submit' class ='FontColor' value='Submit'></td> </tr>");
		  	out.println("</table>");
		  	out.println("</form>");
		}
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
