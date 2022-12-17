package com.net.tmi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.net.tmi.entity.Role;
import com.net.tmi.entity.User;
import com.net.tmi.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String viewHomePage() {
		return "tmi/admin";
	}
	
	//Method handler to handle list_users request from index.html page navigation bar
	@GetMapping("/list_users")
	public String viewUserList(Model model) {

		return findPaginated(1,"roles","asc",model);
	}
	
	
	  @GetMapping("/edit/{id}") public String editUser(@PathVariable("id") Long
	  id,Model model) { User user= userService.get(id); List<Role> listRoles=
	  userService.getRoles(); model.addAttribute("user", user);
	  model.addAttribute("listRoles", listRoles); return "user_form"; }
	 
	  

	
	  @PostMapping("/save") public String saveUser(User user) {
	  userService.save(user); return "redirect:/admin/list_users"; }
	
	  @GetMapping("/delete/{id}")
	  public String deleteUser(@PathVariable(value="id") Long id) {
		 //call delete employee method from service layer
		  userService.deleteUserById(id);
		  return "redirect:/admin/list_users";
	  }
	  
	  @GetMapping("/showNewUserForm")
	  public String showNewUserForm(Model model) {
		  //Create a model attribute to bind form data
		  User user=new User();
		  List<Role> listRoles=userService.getRoles();
		  model.addAttribute("user", user);
		  model.addAttribute("listRoles", listRoles);
		  return "new_user";
		  
	  }
		@GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable(value="pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			int pageSize=5;
			
			Page<User> page=userService.findPaginated(pageNo, pageSize,sortField,sortDir);
			List<User> listUsers=page.getContent();
			
			model.addAttribute("currentPage",pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField",sortField);
			model.addAttribute("sortDir",sortDir);
			model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc":"asc");
			
			model.addAttribute("listUsers", listUsers);
			
			return "users";	
		}
}
