package com.example.demo.services.mapservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.services.crudservices.UserService;
import com.example.demo.services.security.EncryptionService;

@Service
@Profile("map")
public class UserServiceImpl implements UserService {

	private EncryptionService encryptionService;

	private Map<Integer, User> users = new HashMap<Integer, User>();

	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public UserServiceImpl() {
		// loadProducts();
	}

	private void loadUsers() {
		User u1 = new User();
		u1.setId(1);
		u1.setUsername("admin@MyBilling.com");
		u1.setPassword("password");
		u1.setEncryptedpassword("password");
		u1.setVersion(1);
		users.put(1, u1);
	}

	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return new ArrayList<>(users.values());
	}

	@Override
	public User getUserById(Integer id) {
		return users.get(id);
	}

	@Override
	public User saveOrUpdateUser(User user) {
		if (user != null) {
			if (user.getId() == null) {
				user.setId(getMaxId());
			}
			if (null != user && null != user.getPassword()) {
				user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
			}
			users.put(user.getId(), user);
			return user;
		} else {
			throw new RuntimeException("User can't be null");
		}

	}

	private Integer getMaxId() {
		if (null != users && !users.isEmpty()) {
			return Collections.max(users.keySet()) + 1;
		} else {
			return 1;
		}
	}

	@Override
	public void deleteUser(Integer id) {
		users.remove(id);
	}

}
