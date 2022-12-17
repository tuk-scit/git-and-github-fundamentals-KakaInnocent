package com.net.tmi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.net.tmi.entity.Role;
import com.net.tmi.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	@Autowired RoleRepository repo;
	
	@Test
	public void testCreateRoles() {
		Role user= new Role("User");
		Role admin=new Role("Admin");
		Role doctor= new Role("Doctor");
		
		repo.saveAll(List.of(user,admin,doctor));
		
		List<Role> listRoles=repo.findAll();
		assertThat(listRoles.size()).isEqualTo(3);
	}
}
