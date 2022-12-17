package com.net.tmi.service;

import org.springframework.data.domain.Page;

import com.net.tmi.entity.User;

public interface UserServiceInterface {

	void deleteUserById(Long id);
	void saveUser(User user);
	Page<User> findPaginated(int pageNo ,int pageSize,String sortField,String sortDirection);
}
