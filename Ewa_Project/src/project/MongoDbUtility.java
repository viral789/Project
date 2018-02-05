package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoDbUtility {

	MongoClient mongoClient 	= new MongoClient("localhost", 27017);
	DBCollection dbCollection 	= null;
	
	public void createConnectionMongoDB()
	{	
		DB db       = 	mongoClient.getDB("CustomerReview");
		dbCollection = db.getCollection("myReviews");		
	}
	
	public int submitReview(HashMap<String,ReviewPojo> hashReview)
	{
		int result = 0;
		WriteResult resultSet;

		Set set 	= hashReview.entrySet();
		Iterator iterator = set.iterator();
		ReviewPojo reviewBean = new ReviewPojo();
		
		while(iterator.hasNext())
		{
			Map.Entry<String, ReviewPojo> map = (Map.Entry)iterator.next();
			reviewBean = (ReviewPojo)map.getValue();
		}

		createConnectionMongoDB();
		BasicDBObject basicDBObject = new BasicDBObject("title", "myReviews").
		
		append("reviewHotelId", reviewBean.getHotelId()).
		append("reviewHotelName", reviewBean.getHotelName()).
		append("reviewRatingMongo", reviewBean.getReviewRating()).
		append("reviewDateMongo", reviewBean.getReviewDate()).
		append("reviewTextMongo", reviewBean.getReviewText());
		
	    resultSet = dbCollection.insert(basicDBObject);
	    
	    result = resultSet.getN();
	    
		return result;
	}
	
	public HashMap<String, ArrayList<ReviewPojo>> viewReviews(String hotelName)
	{
		HashMap<String, ArrayList<ReviewPojo>> hrv = new HashMap<String, ArrayList<ReviewPojo>>();
		ArrayList<ReviewPojo> arrayList = new ArrayList<ReviewPojo>();
		
		createConnectionMongoDB();
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("reviewHotelName", hotelName);
		DBCursor dbCursor = dbCollection.find(basicDBObject);
		
		while(dbCursor.hasNext())
		{
			ReviewPojo reviewBean = new ReviewPojo();
			BasicDBObject basicdbobject = (BasicDBObject)dbCursor.next();
			reviewBean.setHotelId(basicdbobject.getString("reviewHotelId"));
			reviewBean.setHotelName(basicdbobject.getString("reviewHotelName"));
			reviewBean.setReviewRating(basicdbobject.getString("reviewRatingMongo"));
			reviewBean.setReviewDate(basicdbobject.getString("reviewDateMongo"));
			reviewBean.setReviewText(basicdbobject.getString("reviewTextMongo"));
			arrayList.add(reviewBean);
		}
		hrv.put("ReviewsHmap",arrayList);
		
		
		return hrv;
	}
}
