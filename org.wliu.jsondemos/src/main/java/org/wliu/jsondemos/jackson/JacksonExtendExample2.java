package org.wliu.jsondemos.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * dependency:jackson-mapper-asl
 * @author Think
 *
 */
public class JacksonExtendExample2 {
	
	public static DemoUser test(String strSrc) throws JsonParseException, IOException {
		JsonFactory f = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		JsonParser jp = f.createJsonParser(strSrc);
		DemoUser demoUser = new DemoUser();
		jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		while (jp.nextToken() != JsonToken.END_OBJECT) {
		
		  String fieldname = jp.getCurrentName();
		  jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
		  if ("type".equals(fieldname)) { // contains an object
			  String value = jp.getText();
			  if (Type.PLAIN.name().equals(value)) {
				  demoUser.setType(Type.PLAIN);
			  } else {
				  demoUser.setType(Type.XML);
			  }
		  } else if ("user".equals(fieldname)) {
			  if (jp.getCurrentToken()==JsonToken.START_OBJECT) {
				  if (demoUser.getType()==Type.XML) {
					  demoUser.setUser(mapper.readValue(jp, User.class));
				  } else {
					  demoUser.setUser(mapper.readValue(jp, SubUser.class));
				  }
			  }
		  
		  } else {
		    throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
		  }
		}
		jp.close(); // ensure resources get cleaned up timely and properly
		
		return demoUser;
	}
	
	
	public static void main(String[] args) {

//		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object

			// Convert JSON string to Object
			String jsonInString = "{\"type\":\"PLAIN\",\"user\":{\"name\":\"mkyong\",\"age\":33,\"messages\":[\"hello jackson 1\",\"hello jackson 2\",\"hello jackson 3\"],\"address\":\"Beijing\"}}";
			DemoUser user1 = test(jsonInString);
			System.out.println(user1);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}