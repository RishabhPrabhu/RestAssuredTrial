
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.*;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class MainClass {

	@Test
	public void GetFlightDetails()
	{   
		RestAssured.baseURI = "https://www.cleartrip.com/flights/results/airjson";
		 RequestSpecification httpRequest = RestAssured.given();
		 Response response = httpRequest
				 .queryParam("trip_type", "RoundTrip")
				 .queryParam("origin", "New Delhi, IN - Indira Gandhi Airport (DEL)")
				 .queryParam("from", "DEL")
				 .queryParam("destination", "Mumbai, IN - Chatrapati Shivaji Airport (BOM)")
				 .queryParam("to", "BOM")
				 .queryParam("depart_date", "19/06/2019")
				 .queryParam("return_date", "19/06/2019")
				 .queryParam("adults", "1")
				 .queryParam("childs", "0")
				 .queryParam("infants", "0")
				 .queryParam("class", "Economy")
				 .queryParam("airline", "")
				 .queryParam("carrier", "")
				 .queryParam("ver", "V2")
				 .queryParam("type", "json")
				 .queryParam("intl", "n")
				 .queryParam("sd", "1560591535954")
				 .queryParam("page", "")
				 .queryParam("search_ver", "V2")
				 .queryParam("cc", "1")
				 .queryParam("rhc", "1")
				 .queryParam("timeout", "3000")
				 .get();

		 // First get the JsonPath object instance from the Response interface
		 JsonPath jsonPathEvaluator = response.jsonPath();
		 
		 HashMap responseHashMap = (HashMap) jsonPathEvaluator.getMap("content");


	        // iterating address Map 
	        Iterator<Map.Entry> itr1 = ((Map) responseHashMap).entrySet().iterator(); 
	        while (itr1.hasNext()) { 
	            Map.Entry pair = itr1.next(); 

            	System.out.println("\n"+pair.getKey()+" : "+pair.getValue());
	            Iterator<Map.Entry> itr2 = ((Map)pair.getValue()).entrySet().iterator(); 
		        while (itr2.hasNext()) { 
		            Map.Entry valuePair = itr2.next(); 

	            	System.out.println("\t"+valuePair.getKey()+" : "+valuePair.getValue());
		            if((pair.getKey().equals("v"))&&((String) valuePair.getValue()).equalsIgnoreCase("Mumbai to New Delhi"))
		            {
		            	System.out.println("\t"+pair.getKey()+" : "+pair.getValue());
		            }
		            
		        }
	        } 
	          
		 
		 //System.out.println(responseHashMap.get("228"));
	}

}