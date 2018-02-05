package project;

public class ReviewPojo {

	private String hotelId;
	private String hotelName;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	@Override
	public String toString() {
		return "ReivewPojo [hotelId=" + hotelId + ", hotelName=" + hotelName + ", reviewRating=" + reviewRating
				+ ", reviewDate=" + reviewDate + ", reviewText=" + reviewText + "]";
	}
	
	
}
