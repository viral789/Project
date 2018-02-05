import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.MySQLDataStore;


public class SendOrderCompleteEmail extends HttpServlet{

	private String host;
    private String port;
    private String user;
    private String pass;
    
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	this.init();
		MySQLDataStore mysql = new MySQLDataStore();
				
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		
		String recipient = mysql.getuserEmail(userId);
		
		String subject = "Hotel Booking confirmation";
		String Content = "Your booking is confirm from "+ request.getSession().getAttribute("checkinDate")+ "till " +request.getSession().getAttribute("checkoutDate")+". Enjoy your stay at "+request.getSession().getAttribute("hotelName");
		
		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, Content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
 
}
