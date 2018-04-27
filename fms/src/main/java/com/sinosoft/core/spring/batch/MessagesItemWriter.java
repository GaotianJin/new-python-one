package com.sinosoft.core.spring.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MessagesItemWriter implements ItemWriter<Message> {

	public void write(List<? extends Message> messages) throws Exception {
		System.out.println("write results");
		for (Message m : messages) {
			System.out.println(m.getContent()); // 只做输出
		}
	}
}
