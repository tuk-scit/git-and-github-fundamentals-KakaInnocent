package com.net.tmi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.net.tmi.entity.Role;
import com.net.tmi.entity.User;
import com.net.tmi.repository.RoleRepository;
import com.net.tmi.repository.userRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private userRepository userrepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user =new User();
		user.setEmail("mash@gmail.com");
		user.setPassword("2022");
		user.setFirstName("Mash");
		user.setLastName("Wasike");
		
		User savedUser= userrepo.save(user);
		
		User existUser= entityManager.find(User.class,savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	
	}
	 @Test
	 public void testFindUserByEmail() {
		 String email="kenc@gmail.com";
		 
		 User user=userrepo.findByEmail(email);
		 
		 assertThat(user).isNotNull();
	 }
	 
	 @Test
	 public void testAddRoleToNewUser() {
			User user =new User();
			user.setEmail("njeri@gmail.com");
			user.setPassword("2022");
			user.setFirstName("Bridget");
			user.setLastName("Njeri");
			
			Role roleUser = roleRepo.findByName("User");
			user.addRole(roleUser);
			
			User savedUser=userrepo.save(user);
			
			assertThat(savedUser.getRoles().size()).isEqualTo(1);
			
	 }
	 
	  @Test
	  public void testAddRolesToExistingUser() {
		  User user=userrepo.findById(1L).get();
		  User user1=userrepo.findById(3L).get();
		  
		  Role roleUser=roleRepo.findByName("User");
		  user.addRole(roleUser);
		  
		  Role roleDoctor=roleRepo.findByName("Doctor");
		  user1.addRole(roleDoctor);
		  
		  //You can also add roles as below
		  Role roleAdmin = new Role(2L);
		  user.addRole(roleAdmin);
		  
		  User savedUser=userrepo.save(user);
		  
		  assertThat(savedUser.getRoles().size()).isEqualTo(2);
	  }
	 
}
