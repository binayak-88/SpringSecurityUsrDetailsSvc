/**
 * 
 */
package edu.binayak.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.binayak.springsecurity.model.User;
import edu.binayak.springsecurity.service.UserService;

/**
 * @author HP
 *
 */
@RestController
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String getHome() {
		return "Home";
	}
	
	@GetMapping("/welcome")
	public String getWelcome() {
		return "Welcome";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Admin";
	}
	
	@GetMapping("/emp")
	public String getEmp() {
		return "Emp";
	}
	
	@GetMapping("/student")
	public String getStudent() {
		return "Student";
	}
	
	@GetMapping("/denied")
	public String getDenied() {
		
		return "Forbidden";
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		User user2 = userService.registerUser(user);
		if(user2!=null) {
			return  ResponseEntity.ok("User registration successfully");
		}
		else {
			return ResponseEntity.ok("User registration failed");
		}
	}
	
}

