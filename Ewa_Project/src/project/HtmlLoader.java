package project;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServlet;

public class HtmlLoader extends HttpServlet{

	public String readFile(String httpPath) 
	{
		File f = new File(httpPath);
		try{
           byte[] bytes = Files.readAllBytes(f.toPath());
	       return new String(bytes, "UTF-8");
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		return "";

	}
	
}
