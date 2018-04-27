package com.sinosoft.core.spring.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sinosoft.core.domain.model.user.User;

public class UserMapper implements RowMapper<User> {

	// public User mapFieldSet(FieldSet fs) {
	// User u = new User();
	// u.setId(Integer.valueOf(fs.readString(0)));
	// u.setUsercode(fs.readString(1));
	// u.setUsername(fs.readString(2));
	// return u;
	// }

	public User mapRow(ResultSet rs, int i) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("ID"));
		u.setUsercode(rs.getString("USERCODE"));
		u.setUsername(rs.getString("USERNAME"));
		return u;
	}
	
	public void save(User user){
		
	}
}
