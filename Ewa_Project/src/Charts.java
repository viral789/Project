import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Hotel;
import project.HtmlLoader;
import project.MySQLDataStore;

public class Charts extends HttpServlet{

	protected void doGet(HttpServletRequest  request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("storeManagerheader.html");
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		
		out.println("<div class='wrap'>");
		out.println("<div class='gallerys'>");
		out.println("<div class='gallery-grids'>");
		
		MySQLDataStore mysql = new MySQLDataStore();
		ArrayList<Hotel> hotelProoduct = new ArrayList<Hotel>();
		ArrayList<Hotel> hotelReservation = new ArrayList<Hotel>();
		
		hotelProoduct = mysql.getHotelChart();
		hotelReservation = mysql.getReservationCharts();
		
		out.println("<h1>********Hotel Rooms Bar Chart***********</h1>");
		out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
        out.println("<div id='charts'></div>");
        out.println("<script type='text/javascript'>");

        out.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
        out.println("google.charts.setOnLoadCallback(drawChart);");

        out.println("function drawChart() {");

        out.println("var data = google.visualization.arrayToDataTable([");
          out.println("['Hotel Name', 'Quantity',],");
          
          for(Hotel list : hotelProoduct){
        	  out.println("['"+list.getHotelName()+"', "+list.getQuantity()+"],");
          }
          out.println("]);");

          out.println("var options = {");
            out.println("title: 'Available Rooms',");
            out.println("width: 600,");
            out.println("height: 400,");
            out.println("hAxis: {");
              out.println("title: 'Quantity',");
              out.println("minValue: 0");
            out.println("},");
            out.println("vAxis: {");
              out.println("title: 'Hotel Name'");
            out.println("}");
          out.println("};");

          out.println("var chart = new google.visualization.BarChart(document.getElementById('charts'));");

          out.println("chart.draw(data, options);");
          out.println("}");
          out.println("</script>");
          out.println("</div>");
          
          out.println("<h1>********Room Reserved Bar Chart***********</h1>");
  		out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
          out.println("<div id='charts1'></div>");
          out.println("<script type='text/javascript'>");

          out.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
          out.println("google.charts.setOnLoadCallback(drawChart1);");

          out.println("function drawChart1() {");

          out.println("var data = google.visualization.arrayToDataTable([");
            out.println("['Hotel Name', 'No of rooms booked',],");
            
            for(Hotel list : hotelReservation){
          	  out.println("['"+list.getHotelName()+"', "+list.getQuantity()+"],");
            }
            out.println("]);");

            out.println("var options = {");
              out.println("title: 'Rooms booked',");
              out.println("width: 600,");
              out.println("height: 400,");
              out.println("hAxis: {");
                out.println("title: 'No of rooms booked',");
                out.println("minValue: 0");
              out.println("},");
              out.println("vAxis: {");
                out.println("title: 'Hotel Name'");
              out.println("}");
            out.println("};");

            out.println("var chart = new google.visualization.BarChart(document.getElementById('charts1'));");

            out.println("chart.draw(data, options);");
            out.println("}");
            out.println("</script>");
            out.println("</div>");
            
            
            out.println("</div>");
    		out.println("</div>");
    		out.println("</div>");
    		out.println("</body>");
    		out.println("</html>"); 
            
            
	}
}
