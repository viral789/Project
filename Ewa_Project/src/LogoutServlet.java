import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.HtmlLoader;

public class LogoutServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = (String)request.getSession().getAttribute("username");
		int userId = (int)request.getSession().getAttribute("userId");
		ServletContext sc = request.getServletContext();
		String headerHtml = sc.getRealPath("header.html");
		
		HtmlLoader loader = new HtmlLoader();
		out.println(loader.readFile(headerHtml));
		out.println("<div class='Summary'>");
		out.println("<div class='wrap'>");
		out.println("<div class='reservation'>");
		try{
			/*int userId = -1;
			session.setAttribute("userId", userId);
			*/
			//out.println(username+" You are successfully Log out!!!" );
			response.sendRedirect("index.html");
			session.invalidate();
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
