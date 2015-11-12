package org.wliu.jsondemos.jackson;

public class DemoUser {
	
	private Type type = Type.XML;
	private User user = null;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
