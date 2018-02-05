import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.HtmlLoader;
import project.MongoDbUtility;
import project.ReviewPojo;

public class SubmitReview extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		HashMap<String, ReviewPojo> hashReview = new HashMap<String, ReviewPojo>();
		
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		String leftSideBar = sc.getRealPath("leftSideBar.html");
		String footerHtml = sc.getRealPath("footer.html");
		
		HtmlLoader loader = new HtmlLoader();
		
		out.println(loader.readFile(headerHtml));
		
		ReviewPojo review = new ReviewPojo();
		
		review.setHotelId(request.getParameter("hotelId"));
		review.setHotelName(request.getParameter("hotelName"));
		review.setReviewDate(request.getParameter("reviewDate"));
		review.setReviewRating(request.getParameter("reviewRating"));
		review.setReviewText(request.getParameter("reviewText"));
		
		hashReview.put(review.getHotelId(),review);
		
		MongoDbUtility mongo = new MongoDbUtility();
		 
		int result = mongo.submitReview(hashReview);
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		
		if(result == 0){
			out.println("Review has been successfully added for "+review.getHotelName());
			out.println("<a href='index.html'> Return to Main Menu</a>"+"<br/>");
		}
		else{
			out.println("Error while adding review "+review.getHotelName());
			out.println("<a href='index.html'> Return to Main Menu</a>"+"<br/>");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
