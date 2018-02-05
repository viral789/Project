import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import project.HtmlLoader;
import project.MySQLDataStore;

public class TrendingServlet extends HttpServlet{

	MongoClient mongo;

	public void init() throws ServletException {
	        mongo = new MongoClient("localhost", 27017);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		
		MySQLDataStore mysql = new MySQLDataStore();
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		
		DB db= mongo.getDB("CustomerReview");
        DBCollection myreview = db.getCollection("myReviews");
        
        //------------------Query 1---------------------------
        out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Top Five most liked Hotels</h4>");
        
        DBObject sort = new BasicDBObject();
        DBObject limit = new BasicDBObject();
        DBObject orderby=new BasicDBObject();
        orderby=new BasicDBObject("$sort",sort);
        limit=new BasicDBObject("$limit",5);
       
        sort.put("reviewRating",-1);
        
        AggregationOutput aggregate = myreview.aggregate(orderby,limit);
        int j=0;
        for (DBObject result : aggregate.results()) 
        {
            BasicDBObject bobj= (BasicDBObject) result;
            j++;
            out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+j+"</h5>");
            String hotelName=bobj.getString("reviewHotelName");
            String reviewrate= bobj.getString("reviewRatingMongo");
            out.println("Hotel Name="+hotelName);
            out.println("<br>");
            out.println("Hotel rating="+reviewrate);
            out.println("<br>");
            out.println("<hr>");
        }
        //--------------------------------Query 2------------------------
        out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Top Five most Hotel Booked</h4>");
        
        DBObject groupFields=new BasicDBObject();
        DBObject group=new BasicDBObject();
        groupFields= new BasicDBObject("_id", 0);
        groupFields.put("count",new BasicDBObject("$sum",1));
        groupFields.put("_id", "$reviewHotelName");
        group = new BasicDBObject("$group", groupFields);
     
        aggregate = myreview.aggregate(group,orderby,limit);
        int m=0;
        for (DBObject result : aggregate.results()) 
        {
            BasicDBObject bobj= (BasicDBObject) result;
            m++;
            out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+m+"</h5>");
            String hotelName=bobj.getString("_id");
            String reviewCount= bobj.getString("count");
            out.println("Hotel Name="+hotelName);
            out.println("<br>");
            out.println("Total review="+reviewCount);
            out.println("<br>");
            out.println("<hr>");
        }
	}
}
