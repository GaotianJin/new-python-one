package com.sinosoft.core.spring.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import com.sinosoft.core.domain.model.user.User;

public class MessagesItemProcessor implements ItemProcessor<User, Message> {

	public Message process(User user) throws Exception {
		if (!StringUtils.hasText(user.getUsername())) {
			throw new RuntimeException("The user name is required!");
		}
		Message m = new Message();// Message是user一个简单的包装
		m.setUser(user);
		m.setContent("Hello " + user.getUsername() + ",please pay promptly at end of this month.");
		return m;
	}
}