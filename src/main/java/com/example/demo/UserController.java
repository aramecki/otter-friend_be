package com.example.demo;

import java.util.*;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create users rest api
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		System.out.println("Received user: " + user);

		if (!user.getPassword().equals(user.getSecPassword())) {
			return ResponseEntity.badRequest().body("La password di conferma non corrisponde.");
		}

		User savedUser = userRepository.save(user);
		return ResponseEntity.ok(savedUser);
	}

//	public User createUser(@RequestBody User user) {
//		if (!user.getPassword().equals(user.getSecPassword())) {
//			System.out.println("La password di conferma non corrisponde.);
//			return null;
//		}
//
//		System.out.println("Dati ricevuti: " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getBirth() + ", " + user.getCardType() + ", " + user.getEmail() + ", " + user.getPassword() + ", " + user.getSecPassword());
//		return userRepository.save(user);
//	}

	// get user by id rest api
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user with id :" + id));
		return ResponseEntity.ok(user);
	}

	// update user rest api
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user with id :" + id));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setBirth(userDetails.getBirth());
		user.setCardType(userDetails.getCardType());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setSecPassword(userDetails.getSecPassword());

		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	// delete user rest api
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user with id :" + id));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
