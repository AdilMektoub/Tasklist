package com.tasklist.controller;


import java.util.List;





import java.util.List;


import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.tasklist.models.ERole;
import com.tasklist.models.Folder;
import com.tasklist.models.Information;
import com.tasklist.models.Role;
import com.tasklist.models.Task;
import com.tasklist.models.User;
import com.tasklist.repository.FolderRepository;
import com.tasklist.repository.InformationRepository;
import com.tasklist.repository.TaskRepository;
import com.tasklist.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/test")
//@EnableAutoConfiguration
public class TestController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private InformationRepository informationRepository;
	@Autowired
	private FolderRepository folderRepository;
	
	
	
	//==================OK=====================	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	  public List<User> getAllUsers() {
	    return userRepository.findAll();
	  }

	
	//==================OK on REST ONLY==========================
	 @DeleteMapping("/userD/{id}")
//	 @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id_user) {	
		 try {
		     userRepository.deleteById(id_user);

		     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		   } catch (Exception e) {
		     return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		   }	  
		 }

	 
	 
	 
	 
	 
	//==================NOT OK==========================

//	 @PostMapping("/newtask")
//	  public ResponseEntity<Task> addTask( Task task) {
//	    try 
//	    {
//	      Task _task = taskRepository.
//	    		  save(new Task(task.getDescription(), task.getFolder(), task.getUser()));
//
//	      return new ResponseEntity<>(_task, HttpStatus.CREATED);
//	    } 
//	    catch (Exception e) {
//	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }
	 

	 
	//==================OK=====================	 
	 @GetMapping("/tasks/{id}")
		public Optional<Task> findTaskById(@PathVariable("id") Long id_task) {	
			     return taskRepository.findById(id_task);
		}
	 
	 @GetMapping("/informations/{id}")
		public Optional<Information> findInformationById(@PathVariable("id") Long id_information) {	
			     return informationRepository.findById(id_information);
		}
	 
	 @GetMapping("/folders/{id}")
		public Optional<Folder> findFolderById(@PathVariable("id") Long id_folder) {	
			     return folderRepository.findById(id_folder);
		}
	 
	//==================NOT OK==========================
	 
//	 @PostMapping("/newtask")
//	 @ResponseStatus(HttpStatus.CREATED)
//		public Task addTask( Task task) {
//		
//		 System.out.println("newtask"+task.getDescription());
//		return taskRepository.save(task);
//		}
//	 
	 @PostMapping("/newtask")
	  public ResponseEntity<?> addTask(@RequestBody Task task) {
		 System.out.println(task.toString());
		 System.out.println(task.getUser().toString());
		 System.out.println(task.getUser().getRoles().toString());
		 User user = task.getUser();
		 Set<Role> roles = null;
		 Role role = new Role() ; role.setId_role(1); role.setName(ERole.ROLE_USER);
		 roles.add(role);
		 user.setRoles(roles);
		 Task _task=new Task(task.getDescription(),task.getFolder(),user);
		 taskRepository.save(_task);
		 HttpHeaders headers = new HttpHeaders();
		    headers.add("Access-Control-Allow-Origin", "*");
		    headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		    headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
		    headers.add("Access-Control-Allow-Credentials", "true");
		    return new ResponseEntity<>(_task,HttpStatus.CREATED);
	  }

//	  @PostMapping(value = "/newtask",
//		        consumes = MediaType.APPLICATION_JSON_VALUE,
//		        produces = MediaType.APPLICATION_JSON_VALUE)

//	 @RequestMapping(value = "newtask", method =POST ,consumes = MediaType.APPLICATION_JSON_UTF8_bin)
//	  public ResponseEntity<?> addTask(Task task) {
//	    try 
//	    {
//	    	System.out.println(task.getDescription());
//	    Task _task = taskRepository.		  
//	    save(new Task(task.getDescription(), task.getFolder(), task.getUser()));
//	      return new ResponseEntity<>(_task, HttpStatus.CREATED);
//	    } 
//	    catch (Exception e) {
//	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }	
	 
	 
	//==================NOT OK==========================
	 @PostMapping("/{id}/newfolder")
		public Folder addFolder(@PathVariable("id") Long id_user,  Folder folder) {	
			     return folderRepository.
			    		  save(folder);
		}
	

	
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	
	
	
	
//	@GetMapping("/users")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//	public String userAccess() {
//		return "User Content.";
//	}

	@GetMapping("/mod")
//	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}
	


//	@GetMapping("/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String adminAccess() {
//		return "Admin Board.";
//	}
	

	
	
}
