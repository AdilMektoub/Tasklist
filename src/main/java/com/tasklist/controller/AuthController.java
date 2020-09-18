package com.tasklist.controller;

import java.util.Date;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.models.ERole;
import com.tasklist.models.Role;
import com.tasklist.models.Task;
import com.tasklist.models.User;
import com.tasklist.models.Folder;
import com.tasklist.repository.RoleRepository;
import com.tasklist.repository.TaskRepository;
import com.tasklist.repository.UserRepository;
import com.tasklist.security.jwt.JwtUtils;
import com.tasklist.security.services.UserDetailsImplement;
import com.tasklist.ship.request.LoginRequest;
import com.tasklist.ship.request.SignupRequest;
import com.tasklist.ship.response.JwtResponse;
import com.tasklist.ship.response.MessageResponse;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
		@Autowired
		AuthenticationManager authenticationManager;

		@Autowired
		UserRepository userRepository;
		@Autowired
		TaskRepository taskRepository;

		@Autowired
		RoleRepository roleRepository;

		@Autowired
		PasswordEncoder encoder;

		@Autowired
		JwtUtils jwtUtils;

		@PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
			// Accumulate names into a List
//		     List<Task> tasks = userDetails.getAuthTask().stream()
//		    		 .map(userDetails::getTasks).collect(Collectors.toList());
			
//			  Accumulate names into a List
//		     List<String> taskslist = userDetails.getTasks().stream()
//						.map(taskslist->tasks.getDescription())
//						.collect(Collectors.toList());



			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId_user(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(),
													 userDetails.getPhone(),
													 userDetails.getList(),
													 userDetails.getInfTitle(),
													 userDetails.getInfDescription(),
													 userDetails.getFoldTitle(),
													 userDetails.getCat(),
													 roles
													 ));
		}
		
		@GetMapping("/users")
		  public List<User> getAllUsers() {
		    return userRepository.findAll();
		  }
		
		
//		 @PostMapping("/newtask")
//		  public ResponseEntity<Task> createTask(@RequestBody Task task) {
//			 UserDetailsImplement userDetails;
//			
//			 
//		    try {
//		      Task _task = taskRepository.
//		    		  save(new Task(task.getDescription(), task.getFolder(), task.getUser()));
//		      return new ResponseEntity<>(_task, HttpStatus.CREATED);
//		    } catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//		    }
//		  }
		
		
		
		

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			User user = new User(signUpRequest.getUsername(), 
								 signUpRequest.getEmail(),
								 encoder.encode(signUpRequest.getPassword()),
								 signUpRequest.getPhone(),
								 signUpRequest.getDateOfBirth()

					);

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}

			user.setRoles(roles);
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
	}

