package com.hska.eshop.userservice.service;

import com.hska.eshop.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String getHello() {
		return "Hello from user-service";
	}
}
