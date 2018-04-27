package com.sinosoft.core.spring.batch;

import com.sinosoft.core.domain.model.user.User;

public class Message {
	private User user;
	private String content;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
