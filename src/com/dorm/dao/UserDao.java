package com.dorm.dao;

import java.util.List;

import com.dorm.bean.User;

public interface UserDao {

	User findByStuCodeAndPass(String username, String password);
	
	String findManagerStuCodeMax();
	
	Integer saveManager(User user);
	
	List<User> findManager(String sql);

	User findById(int id);

	void updateManager(User user);

	void updatePassWord(User userCur);

	User findByStuCode(String stuCode);

	void saveStudent(User user);

	List<User> findStudent(String sql);

	Integer findTotalNum(String sql);

	void updateStudent(User studentUpdate);

	User findByUserIdAndManager(String sql);


}
