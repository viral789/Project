

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.Hotel;
import project.MySQLDataStore;

public class AjaxUtility {

	public StringBuffer getData(String search){
		
		HashMap<String, ArrayList<Hotel>> hashHotel = new HashMap<String, ArrayList<Hotel>>();
		StringBuffer stringBuffer = new StringBuffer();
		MySQLDataStore mysql = new MySQLDataStore();
		hashHotel = mysql.getHotelName();
		
		for(Map.Entry<String, ArrayList<Hotel>> map: hashHotel.entrySet()){
			for(Hotel list: map.getValue()){
				if(list.getHotelName().toLowerCase().startsWith(search)){
					stringBuffer.append("<hotel>");
					stringBuffer.append("<hotelId>"+list.getHotelId()+"</hotelId>");
					stringBuffer.append("<hotelName>"+list.getHotelName()+"</hotelName>");
					stringBuffer.append("</hotel>");
					
				}
			}
		}
		
		return stringBuffer;
	}
}

