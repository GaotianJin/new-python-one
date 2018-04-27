package com.sinosoft.core.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sinosoft.core.domain.model.user.User;
import com.sinosoft.core.domain.model.user.dao.UserDAO;

@Service
public class UserCacheService {
	@Autowired
	UserDAO userDAO;
	@Cacheable(value = "userCache")
	// 使用了一个缓存名叫 accountCache
	public User getUserByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account." + userName);
		System.out.println("real querying db..." + userName);
		return userDAO.load(1);
	}

	@CacheEvict(value = "userCache", key = "#user.getUsername()")
	// 清空 accountCache 缓存
	public void updateUser(User user) {
		System.out.println("real update db..." + user.getUsername());
	}

	@CacheEvict(value = "userCache", allEntries = true)
	// 清空 accountCache 缓存
	public void reload() {
	}

}
