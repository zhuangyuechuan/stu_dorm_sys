package com.dorm.service;

import java.util.List;

import com.dorm.bean.User;
import com.dorm.util.PageModel;

public interface UserService {

	User findByStuCodeAndPass(String username, String password);
	
	void saveManager(User user,String[] dormBuildIds);
	
	List<User> findManager(String searchType, String keyword);

	User findById(int id);

	void updateManager(User user);

	void updatePassWord(User userCur);

	User findByStuCode(String stuCode);

	void saveStudent(User user);

	List<User> findStudent(String dormBuildId, String searchType, String keyword, User user, PageModel pageModel);

	Integer findTotalNum(String dormBuildId, String searchType, String keyword, User user);

	void updateStudent(User studentUpdate);

	User findByUserIdAndManager(Integer id, User user);

}
