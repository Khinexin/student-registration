package com.demo.studentregistration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.studentregistration.entity.User;
import com.demo.studentregistration.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;


	public User create(User user) {
		return userRepository.save(user);
	}


	public User update(User user) {
		return userRepository.saveAndFlush( user);
	}


	public void delete(int id) {
		userRepository.deleteById(id);
	}


	public List<User> findAll() {
		return userRepository.findAll();
	}


	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
}
