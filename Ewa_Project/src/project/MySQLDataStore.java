package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;

public class MySQLDataStore {

	Connection connection=null;
	Statement statement=null;
	
	private Connection createConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		String serverName="localhost";
		String portNumber="3306";
		String sid="project";
		String url="jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
		String username="root";
		String password="password@123";
		connection=DriverManager.getConnection(url, username, password);
		
		statement=connection.createStatement();
		return connection;
	}
	
	public int getlastUser(){
		int lastUser = 0;
		ResultSet rs;
		try{
			createConnection();
			String query = "select max(userId) from registration";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				lastUser = rs.getInt(1);
			}
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lastUser;
	}
	
	public int addUser (HashMap<String, UserPojo> hashUser) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		int result = 0;
		
		UserPojo userPojo = new UserPojo();
		Set set = hashUser.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext())
		{
			Map.Entry entry   = (Map.Entry)iterator.next();
			userPojo = (UserPojo)entry.getValue();
		}
		int userid = getlastUser();
		userid = userid +1 ;
		createConnection();
		String query = "insert into registration(userId, fName, lName, password, role, email) "
				+ "value(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, userid);
		preparedStatement.setString(2,userPojo.getfName());
		preparedStatement.setString(3, userPojo.getlName());
		preparedStatement.setString(4,userPojo.getPassword());
		preparedStatement.setString(5,userPojo.getRole());
		preparedStatement.setString(6, userPojo.getEmail());
		
		result  = preparedStatement.executeUpdate();
		connection.close();
		
		return result;
	}
	
	public int userExits(String email){
		int result = 0;
		ResultSet rs ;
		
		try{
			createConnection();
			String query = "select email from registration where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,email);
			rs = preparedStatement.executeQuery();
			
			if(!rs.next()){
				result = 0;
			}
			else{
				result = 1;
			}
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public HashMap<String, UserPojo> getLoginDetails(String email)
	{
		HashMap<String, UserPojo> hashUser = new HashMap<String, UserPojo>();
		ResultSet rs;
		UserPojo user = new UserPojo();
		try {
			createConnection();
			String query="select userId, fName, lName,  password, role, email from registration where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,email);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				user.setUserId(rs.getInt("userId"));
				user.setfName(rs.getString("fName"));
				user.setlName(rs.getString("lName"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
			}
			hashUser.put(user.getEmail(), user);
			connection.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return hashUser;
	}
	
	public int getuserId(String email){
		int userId = 0;
		ResultSet rs;
		try{
			createConnection();
			String query = "select userId from registration where email = ?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, email);
			
			rs = pst.executeQuery();
			while(rs.next()){
				userId = rs.getInt("userId");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userId;
	}
	
	public String getuserEmail(Integer userId){
		String email = null;
		ResultSet rs;
		try{
			createConnection();
			String query = "select email from registration where userId = ?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, userId);
			
			rs = pst.executeQuery();
			while(rs.next()){
				email = rs.getString("email");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return email;
	}
	
	public int updateUserPassword(UserPojo user){
		int result =0;
		try{
			createConnection();
			String query1 = "SET SQL_SAFE_UPDATES = 0";
			String query ="update registration set password=? where email=?";
			
			PreparedStatement pst = connection.prepareStatement(query1);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getEmail());
			
			pst.executeUpdate();
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public HashMap<String, ArrayList<Hotel>> getCity(){
		
		HashMap<String, ArrayList<Hotel>> hashHotel = new HashMap<String, ArrayList<Hotel>>();
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		ResultSet resultSet = null;
		
		try {
			createConnection();
			String query="Select city from Hotel;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Hotel hotel = new Hotel();
				hotel.setCity(resultSet.getString("city"));
				hotels.add(hotel);

			}
			
			hashHotel.put("product", hotels);
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashHotel;
	}
	
	public int addHotel(Hotel hotel){
		int result = 0;
		try{
			createConnection();
			
			String query = "insert into hotel(hotelId, hotelName, city, hotelRooms, quantity, price, images) "
					+ "values (?,?,?,?,?,?,?);";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, hotel.getHotelId());
			pst.setString(2, hotel.getHotelName());
			pst.setString(3, hotel.getCity());
			pst.setString(4, hotel.getHotelRoom());
			pst.setInt(5, hotel.getQuantity());
			pst.setDouble(6, hotel.getPrice());
			pst.setString(7, hotel.getImages());
			result = pst.executeUpdate();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public HashMap<String, List<Hotel>> getHotelByCity(String city){
		HashMap<String, List<Hotel>> hashHotel = new HashMap<String, List<Hotel>>();
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ResultSet resultSet= null;
		
		try {
			createConnection();
			String query="Select hotelId, hotelName, city, hotelRooms, quantity, price, images from hotel where city =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, city);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Hotel hotel = new Hotel();
				hotel.setHotelId(resultSet.getInt("hotelId"));
				hotel.setHotelName(resultSet.getString("hotelName"));
				hotel.setCity(resultSet.getString("city"));
				hotel.setHotelRoom(resultSet.getString("hotelRooms"));
				hotel.setQuantity(resultSet.getInt("quantity"));
				hotel.setPrice(resultSet.getDouble("price"));
				hotel.setImages(resultSet.getString("images"));
				hotelList.add(hotel);
			}
			
			hashHotel.put("hotel", hotelList);
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashHotel;
	}
	
	public HashMap<String, List<Hotel>> getSelectedHotel(String id){
		HashMap<String, List<Hotel>> hashHotel = new HashMap<String, List<Hotel>>();
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ResultSet resultSet= null;
		
		try {
			createConnection();
			String query="Select hotelId, hotelName, city, hotelRooms, quantity, price, images from hotel where hotelId =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Hotel hotel = new Hotel();
				hotel.setHotelId(resultSet.getInt("hotelId"));
				hotel.setHotelName(resultSet.getString("hotelName"));
				hotel.setCity(resultSet.getString("city"));
				hotel.setHotelRoom(resultSet.getString("hotelRooms"));
				hotel.setQuantity(resultSet.getInt("quantity"));
				hotel.setPrice(resultSet.getDouble("price"));
				hotel.setImages(resultSet.getString("images"));
				hotelList.add(hotel);

			}
			connection.close();
			hashHotel.put("hotel", hotelList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashHotel;
	}
	
	public int addReservation(HashMap<String,ArrayList<HotelReservation>> hashReservation ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
	 int result=0;
	 for(Map.Entry<String, ArrayList<HotelReservation>> map : hashReservation.entrySet())
	 {
		for(HotelReservation reservation : map.getValue())
		{
			createConnection();
			String query = "insert into hotelReservation(userId, hotelId, hotelRoom, quantity, totalPrice, checkinDate, checkoutDate)"
					+ "value (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, reservation.getUserId());
			preparedStatement.setInt(2, reservation.getHotelId());
			preparedStatement.setString(3, reservation.getHotelRoom());
			preparedStatement.setInt(4, reservation.getQuantity());
			preparedStatement.setDouble(5, reservation.getTotalPrice());
			preparedStatement.setString(6, reservation.getCheckinDate());
			preparedStatement.setString(7, reservation.getCheckoutDate());
			result = preparedStatement.executeUpdate();
			connection.close();
		}
	 }
	 return result;	
	} 
	
	public int updateHotelquantity (int quantity, int hotelId){
		int result = 0;
		try{
			createConnection();
			String query =  "SET SQL_SAFE_UPDATES = 0";
			String query1 = "update hotel set quantity=? where hotelId = ?";
			
			PreparedStatement pst = connection.prepareStatement(query);
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, hotelId);
			
			pst.executeUpdate();
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int getQuantity(int hotelId){
		int result =0;
		ResultSet rs ;
		try{
			createConnection();
			String query ="select quantity from hotel where hotelId=?;";
			
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, hotelId);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				result = rs.getInt(1);
			}
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}

	public HashMap<String, List<HotelReservation>> getUserBooking(int userId){
		HashMap<String, List<HotelReservation>> hashHotel = new HashMap<String, List<HotelReservation>>();
		List<HotelReservation> hotelList = new ArrayList<HotelReservation>();
		ResultSet resultSet= null;
		
		try {
			createConnection();
			String query="Select userId, hotelId, hotelRoom, quantity, totalPrice, checkinDate, checkoutDate from hotelReservation where userId =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				HotelReservation reservation = new HotelReservation();
				reservation.setUserId(resultSet.getInt("userId"));
				reservation.setHotelId(resultSet.getInt("hotelId"));
				reservation.setHotelRoom(resultSet.getString("hotelRoom"));
				reservation.setQuantity(resultSet.getInt("quantity"));
				reservation.setTotalPrice(resultSet.getDouble("totalPrice"));
				reservation.setCheckinDate(resultSet.getString("checkinDate"));
				reservation.setCheckoutDate(resultSet.getString("checkoutDate"));
				hotelList.add(reservation);

			}
			connection.close();
			hashHotel.put("product", hotelList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashHotel;
	}
	
	public int cancelReservation(int userId, int hotelId)
	{
		int result =0;
		
		try{
			createConnection();
			String query = "delete from hotelReservation where userId = ? and hotelId = ?; ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, hotelId);
			result = preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public HashMap<String, List<HotelReservation>> getUserBookingByhotelId(int userId, int hotelId){
		HashMap<String, List<HotelReservation>> hashHotel = new HashMap<String, List<HotelReservation>>();
		List<HotelReservation> hotelList = new ArrayList<HotelReservation>();
		ResultSet resultSet= null;
		
		try {
			createConnection();
			String query="Select userId, hotelId, hotelRoom, quantity, totalPrice, checkinDate, checkoutDate from hotelReservation where userId =? and hotelId =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, hotelId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				HotelReservation reservation = new HotelReservation();
				reservation.setUserId(resultSet.getInt("userId"));
				reservation.setHotelId(resultSet.getInt("hotelId"));
				reservation.setHotelRoom(resultSet.getString("hotelRoom"));
				reservation.setQuantity(resultSet.getInt("quantity"));
				reservation.setTotalPrice(resultSet.getDouble("totalPrice"));
				reservation.setCheckinDate(resultSet.getString("checkinDate"));
				reservation.setCheckoutDate(resultSet.getString("checkoutDate"));
				hotelList.add(reservation);

			}
			connection.close();
			hashHotel.put("product", hotelList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashHotel;
	}

	public int updateReservation(HotelReservation reservation) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			createConnection();
			String query =  "SET SQL_SAFE_UPDATES = 0";
			String query1 = "update hotelReservation set userId =?, hotelId =?, hotelRoom =?, quantity=?, totalPrice=?, checkinDate =?, checkoutDate=? where userId = ? and hotelId=?;";
			
			PreparedStatement pst = connection.prepareStatement(query);
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			
			preparedStatement.setInt(1, reservation.getUserId());
			preparedStatement.setInt(2, reservation.getHotelId());
			preparedStatement.setString(3, reservation.getHotelRoom());
			preparedStatement.setInt(4, reservation.getQuantity());
			preparedStatement.setDouble(5, reservation.getTotalPrice());
			preparedStatement.setString(6, reservation.getCheckinDate());
			preparedStatement.setString(7, reservation.getCheckoutDate());
			preparedStatement.setInt(8, reservation.getUserId());
			preparedStatement.setInt(9, reservation.getHotelId());
			
			pst.executeUpdate();
			result = preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateHotel(Hotel hotel) {
		int result = 0;
		try{
			createConnection();
			
			String query = "SET SQL_SAFE_UPDATES = 0";
			String query1 ="update hotel set hotelId=?, hotelName=?, city=?, hotelRooms=?, quantity=?, price =?, images=? where hotelId =?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			PreparedStatement pst = connection.prepareStatement(query1);
			
			pst.setInt(1, hotel.getHotelId());
			pst.setString(2, hotel.getHotelName());
			pst.setString(3, hotel.getCity());
			pst.setString(4, hotel.getHotelRoom());
			pst.setInt(5, hotel.getQuantity());
			pst.setDouble(6, hotel.getPrice());
			pst.setString(7, hotel.getImages());
			pst.setInt(8, hotel.getHotelId());
			
			preparedStatement.executeUpdate();
			result = pst.executeUpdate();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public void truncateTable() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		createConnection();
		String query1 = "truncate table hotel";
		PreparedStatement pst = connection.prepareStatement(query1);
		
		pst.executeUpdate();
		connection.close();
		
	}
	
	public ArrayList<Hotel> getHotelChart(){
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		
		ResultSet rs = null;
		try{
			createConnection();
			String query = "select hotelName, sum(quantity) from hotel group by hotelName;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				Hotel hotel = new Hotel();
				hotel.setHotelName(rs.getString("hotelName"));
				hotel.setQuantity(rs.getInt("sum(quantity)"));
				hotels.add(hotel);
			}
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return hotels;
	}
	
	public ArrayList<Hotel> getReservationCharts(){
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		ResultSet rs;
		try{
			createConnection();
			String query = "select hotelName, sum(r.quantity) from hotel h inner join hotelreservation r where h.hotelId = r.hotelId group by h.hotelName;";
			
			PreparedStatement pst = connection.prepareStatement(query);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				Hotel hotel = new Hotel();
				hotel.setHotelName(rs.getString("hotelName"));
				hotel.setQuantity(rs.getInt("sum(r.quantity)"));
				
				hotels.add(hotel);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return hotels;
	}
	
public HashMap<String, ArrayList<Hotel>> getHotelName(){
		
		HashMap<String, ArrayList<Hotel>> hashHotel = new HashMap<String, ArrayList<Hotel>>();
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		ResultSet resultSet = null;
		
		try {
			createConnection();
			String query="Select hotelName from Hotel;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Hotel hotel = new Hotel();
				hotel.setHotelName(resultSet.getString("hotelName"));
				hotels.add(hotel);

			}
			
			hashHotel.put("hotel", hotels);
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashHotel;
	}
public HashMap<String, List<Hotel>> getHotelInfo(String hotelName){
	HashMap<String, List<Hotel>> hashHotel = new HashMap<String, List<Hotel>>();
	List<Hotel> hotelList = new ArrayList<Hotel>();
	ResultSet resultSet= null;
	
	try {
		createConnection();
		String query="Select hotelId, hotelName, city, hotelRooms, quantity, price, images from hotel where hotelName =?;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, hotelName);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			Hotel hotel = new Hotel();
			hotel.setHotelId(resultSet.getInt("hotelId"));
			hotel.setHotelName(resultSet.getString("hotelName"));
			hotel.setCity(resultSet.getString("city"));
			hotel.setHotelRoom(resultSet.getString("hotelRooms"));
			hotel.setQuantity(resultSet.getInt("quantity"));
			hotel.setPrice(resultSet.getDouble("price"));
			hotel.setImages(resultSet.getString("images"));
			hotelList.add(hotel);
		}
		
		hashHotel.put("hotel", hotelList);
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return hashHotel;
}
}
