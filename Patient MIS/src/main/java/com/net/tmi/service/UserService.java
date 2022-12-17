
package com.net.tmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.net.tmi.entity.Role;
import com.net.tmi.entity.User;
import com.net.tmi.repository.RoleRepository;
import com.net.tmi.repository.userRepository;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public void saveUserWithDefaultRole(User user) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String encodedPassword=encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		
		userRepo.save(user);
	}
	
	//Service method to save the updated user information including updated roles
	public void save(User user){
//		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
//		String encodedPassword=encoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
		
//		Role roleUser = roleRepo.findByName("User");
//		user.addRole(roleUser);
		
		userRepo.save(user);
	}
	
	
	public List<User> listAll(){
		return userRepo.findAll();
	}
	
	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<Role> getRoles(){
		return roleRepo.findAll();
	}

	@Override
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
		
	}
	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			   Sort.by(sortField).descending();
		
		Pageable pageable=PageRequest.of(pageNo-1, pageSize,sort);
		
		return this.userRepo.findAll(pageable);
	}
	


	
	
}
