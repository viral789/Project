
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoComplete extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		try{
			
			String action = request.getParameter("action");
			String SearchId = request.getParameter("id");
			String hotelName = request.getParameter("hotelname");
			StringBuffer stringBuffer = new StringBuffer();
			
			boolean nameAdded = false;
			
			if(action.equals("complete")){
				AjaxUtility ajaxUtility = new AjaxUtility();
				stringBuffer = ajaxUtility.getData(SearchId);
				
				if(stringBuffer != null || !stringBuffer.equals("")){
					nameAdded = true;
				}
				if(nameAdded){
					response.setContentType("text/xml");
					response.getWriter().write("<hotel>"+stringBuffer.toString()+"</hotel>");
				}
			}
			if(action.equals("lookup")){
				if(hotelName != null || !hotelName.equals("")){
					response.sendRedirect("ViewHotel?hotelName="+hotelName);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
