import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.HtmlLoader;
import project.MongoDbUtility;
import project.ReviewPojo;

public class ViewReview extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		String hotelName = request.getParameter("HotelName");
		
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String leftSideBar = sc.getRealPath("leftSideBar.html");
		String footerHtml = sc.getRealPath("footer.html");
		
		HtmlLoader loader = new HtmlLoader();
		
		out.println(loader.readFile(headerHtml));
		
		HashMap<String, ArrayList<ReviewPojo>> hashReview = new HashMap<String, ArrayList<ReviewPojo>>();
		MongoDbUtility storeUtility = new MongoDbUtility();
		
		hashReview = storeUtility.viewReviews(hotelName);
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		for(Map.Entry<String, ArrayList<ReviewPojo>> map: hashReview.entrySet()){
			for(ReviewPojo reviewPojo : map.getValue()){
				out.println("********** Customer Review**********");
				out.println("<br/>");
				out.println("  Hotel Name  "+reviewPojo.getHotelName()+"<br/>");
				out.println("  Hotel Id  "+reviewPojo.getHotelId() +"<br/>");
				out.println("  Review Rating  "+reviewPojo.getReviewRating()+"<br/>");
				out.println("  Review Date  "+reviewPojo.getReviewDate()+"<br/>");
				out.println("  Review Text "+reviewPojo.getReviewText()+"<br/>");
			}
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
