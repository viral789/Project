package project;

public class Hotel {
	private int hotelId;
	private String hotelName;
	private String hotelAdd;
	private String city;
	private String state;
	private String hotelRoom;
	private int quantity;
	private double price;
	private String images;
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAdd() {
		return hotelAdd;
	}
	public void setHotelAdd(String hotelAdd) {
		this.hotelAdd = hotelAdd;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHotelRoom() {
		return hotelRoom;
	}
	public void setHotelRoom(String hotelRoom) {
		this.hotelRoom = hotelRoom;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelAdd=" + hotelAdd + ", city=" + city
				+ ", state=" + state + ", hotelRoom=" + hotelRoom + ", quantity=" + quantity + ", price=" + price
				+ ", images=" + images + "]";
	}
	
	

}
