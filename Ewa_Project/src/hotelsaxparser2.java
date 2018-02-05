import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import project.Hotel;

public class hotelsaxparser2 extends DefaultHandler {
	
	Hotel hotel;
	List<Hotel> hotels;
	String hotelXMLFile;
	String elementValueRead;
	
    public hotelsaxparser2(String hotelXMLFileName) {
    this.hotelXMLFile = hotelXMLFileName;
	System.out.println(hotelXMLFileName);
    hotels = new ArrayList<Hotel>();
    parseDocument();
  
    }    
    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(hotelXMLFile, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

  
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("hotel")) {
        	hotel = new Hotel();
        	hotel.setHotelId(Integer.parseInt(attributes.getValue("id")));
        	
        }

    }

    
    
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("hotel")) {
            hotels.add(hotel);
            
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            hotel.setImages(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            hotel.setHotelName(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("room")) {
            hotel.setHotelRoom(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
        	hotel.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("city")){
        	hotel.setCity(elementValueRead);
	    return;
        }
       
	    if(element.equalsIgnoreCase("quantity")){
        	hotel.setQuantity(Integer.parseInt(elementValueRead));
	    return;
	    }
	    
        
    }

    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


}
