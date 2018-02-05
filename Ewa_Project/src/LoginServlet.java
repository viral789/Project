import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.MySQLDataStore;
import project.UserPojo;

public class LoginServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		UserPojo user = new UserPojo();
		MySQLDataStore mysql = new MySQLDataStore();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		
		HashMap<String, UserPojo> hashUser = new HashMap<String, UserPojo>();
		HttpSession session = request.getSession();
		int userExist = mysql.userExits(email);
		System.out.println(userExist);
		if(userExist == 1){
			
			hashUser = mysql.getLoginDetails(email);
			
			if(hashUser.get(user.getEmail()).getRole().equals(user.getRole()) 
					&& hashUser.get(user.getEmail()).getPassword().equals(user.getPassword())){
				System.out.println(hashUser.get(user.getEmail()).getUserId());
				session.setAttribute("userId",hashUser.get(user.getEmail()).getUserId());
				session.setAttribute("fName",user.getfName());
				session.setAttribute("role", user.getRole());
				if(user.getRole().equals("customer")){
					response.sendRedirect("index.html");
				}
				else{
					response.sendRedirect("HotelPageServlet");
				}
			}
			else{
				response.sendRedirect("index.html");
				//rpassword and role dont match
			}
		}
		else{
			response.sendRedirect("index.html");
			//user not register
		}
	}
}
