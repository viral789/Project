import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.MySQLDataStore;
import project.UserPojo;

public class ForgetPasswordServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		UserPojo user = new UserPojo();
			MySQLDataStore mysql = new MySQLDataStore();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("cpassword");
			
			
			user.setEmail(email);
			user.setPassword(password);
			if(!password.equals(confirmPassword)){
				/*%>
				<div class="alert alert-warning alert-dismissible fade show" role="alert">
				<strong>Password don't match</strong>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
			<%*/
			}
			else{
				int userExist = mysql.userExits(email);
				System.out.println(userExist);
				if(userExist == 1){
					mysql.updateUserPassword(user);
					response.sendRedirect("index.html");
				}
				else{
					/*%>
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
					<strong>User Not Registered!!</strong>
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
				<%*/
				}
			}
	}
	
}
