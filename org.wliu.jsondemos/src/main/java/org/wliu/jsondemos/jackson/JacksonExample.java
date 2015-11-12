package org.wliu.jsondemos.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * dependency:jackson-mapper-asl
 * @author Think
 *
 */
public class JacksonExample {
	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
			User user = mapper.readValue(new File("D:\\user.json"), User.class);
			System.out.println(user);

			// Convert JSON string to Object
			String jsonInString = "{\"type\":\"XML\",\"age\":33,\"messages\":[\"msg 1\",\"msg 2\"],\"name\":\"mkyong\"}";
			User user1 = mapper.readValue(jsonInString, User.class);
			System.out.println(user1);
			
			System.out.println(user1.getName());
			System.out.println(user1.getAge());
			for (String temp : user1.getMessages()) {
				System.out.println(temp);
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}