package org.wliu.jsondemos.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonExample1 {
	public static void main(String[] args) {
		
		String sourceStr = "{\"name\":\"mkyong\",\"age\":33,\"messages\":[\"hello jackson 1\",\"hello jackson 2\",\"hello jackson 3\"]}";

		ObjectMapper mapper = new ObjectMapper();

		//For testing
		User user = createDummyUser();
		
		try {
			//Convert object to JSON string and save into file directly 
			mapper.writeValue(new File("D:/user.json"), user);
			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(user);
			System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			System.out.println(jsonInString);
			
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static User createDummyUser(){
		
		User user = new User();
		
		user.setName("mkyong");
		user.setAge(33);

		List<String> msg = new ArrayList<String>();
		msg.add("hello jackson 1");
		msg.add("hello jackson 2");
		msg.add("hello jackson 3");

		user.setMessages(msg);
		
		return user;
		
	}
}