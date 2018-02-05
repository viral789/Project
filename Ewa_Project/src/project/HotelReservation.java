package project;

public class HotelReservation {

	private int userId;
	private int hotelId;
	private String hotelRoom;
	private int quantity;
	private double totalPrice;
	private String checkinDate;
	private String checkoutDate;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	@Override
	public String toString() {
		return "HotelReservation [userId=" + userId + ", hotelId=" + hotelId + ", hotelRoom=" + hotelRoom
				+ ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", checkinDate=" + checkinDate
				+ ", checkoutDate=" + checkoutDate + "]";
	}
	
	
}
