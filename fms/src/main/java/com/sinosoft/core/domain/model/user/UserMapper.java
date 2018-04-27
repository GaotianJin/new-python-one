package com.sinosoft.core.domain.model.user;

public interface UserMapper {
	public User findByID(int id);
	public int save(User user);
}
