package org.wliu.jsondemos.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonExtendExample1 {
	public static void main(String[] args) {
		

		ObjectMapper mapper = new ObjectMapper();

		//For testing
		DemoUser user = createDummyUser();
		
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

	private static DemoUser createDummyUser(){
		
		User user = new SubUser();
		
		user.setName("mkyong");
		user.setAge(33);
		((SubUser)user).setAddress("Beijing");

		List<String> msg = new ArrayList<String>();
		msg.add("hello jackson 1");
		msg.add("hello jackson 2");
		msg.add("hello jackson 3");

		user.setMessages(msg);
		
		DemoUser demo = new DemoUser();
		demo.setUser(user);
		
		return demo;
		
	}
}