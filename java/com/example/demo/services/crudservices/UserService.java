package com.example.demo.services.crudservices;

import java.util.List;

import com.example.demo.domain.User;

public interface UserService {
	List<User> listAllUser();

	User getUserById(Integer id);

	User saveOrUpdateUser(User user);

	void deleteUser(Integer id);
}
