package com.sinosoft.core.domain.model.user.ibatis;

import java.io.Serializable;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.user.User;


@Repository
public class UserSQL extends SqlSessionDaoSupport implements Serializable{
  
	public User findByID(int id) {
		return (User) this.getSqlSession().selectOne("com.sinosoft.core.domain.model.user.UserMapper.findByID", id);
    }  

	public User save(User user) {
		return (User) this.getSqlSession().selectOne("com.sinosoft.core.domain.model.user.UserMapper.save", user);
	}
}  