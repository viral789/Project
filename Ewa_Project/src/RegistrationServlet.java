import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.MySQLDataStore;
import project.UserPojo;

public class RegistrationServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		UserPojo user = new UserPojo();
		MySQLDataStore mysql = new MySQLDataStore();
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("cpassword");
		String role = request.getParameter("role");
		
		user.setEmail(email);
		user.setlName(lName);
		user.setfName(fName);
		user.setPassword(password);
		user.setRole(role);
		System.out.println(user.getRole());
		if(!password.equals(confirmPassword)){
			/*<div class="alert alert-warning alert-dismissible fade show" role="alert">
			<strong>Password don't match</strong>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    	<span aria-hidden="true">&times;</span>
		  	</button>
		</div>*/
			//out.println("<div class="">");
		}
		else{
			HashMap<String, UserPojo> hashUser = new HashMap<String, UserPojo>();
			
			int userExist = mysql.userExits(email);
			int userId = mysql.getlastUser() + 1;
			System.out.println(userExist);
			if(userExist == 1){
				
				/*<div class="alert alert-warning alert-dismissible fade show" role="alert">
				<strong>User Already exist!!</strong>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>*/
			
			}
			else{
				hashUser.put(user.getfName(), user);
				try {
					mysql.addUser(hashUser);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", userId);
				httpSession.setAttribute("fName", user.getfName());
				httpSession.setAttribute("role", user.getRole());
				if(user.getRole().equals("customer")){
					response.sendRedirect("index.html");
				}
				else{
					response.sendRedirect("HotelPageServlet");
				}
			}
		}
	}
}
