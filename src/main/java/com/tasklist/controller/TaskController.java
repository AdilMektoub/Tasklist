package com.tasklist.controller;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tasklist.models.Task;
import com.tasklist.models.User;
import com.tasklist.repository.TaskRepository;
import com.tasklist.repository.UserRepository;
import com.tasklist.services.ImplTaskController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/")
public class TaskController implements ImplTaskController {
	
	@Autowired
	  TaskRepository taskRepository;

	  @GetMapping("listTask")
	 List<Task> all() {
		    return taskRepository.findAll();
		  }
	  
//	  @Override
//	  @PostMapping("newtask")
//		public Task addTask(Task task) {
//		  
//			return taskRepository.save(task);
//		}
	  
//	  @Override
//		public  addTask(Task task){
//		        List<Task> list = taskRepository.findAll(); 	
//	                if (list.size() > 0) {
//	    	           return false;
//	                } else {
//	    	        taskRepository.save(task);
//	    	        return true;
//	       }
//	  }
	  
//	  @Override
//	 @PostMapping("newtask")
//	  public ResponseEntity<?> addTask(Task task) {
//	    try 
//	    {
//	      Task _task = taskRepository.
//	    		  
//	    		  save(new Task(task.getDescription(), task.getFolder(), task.getUser()));
//	      return new ResponseEntity<>(_task, HttpStatus.CREATED);
//	    } 
//	    catch (Exception e) {
//	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }	  

	  

// public ResponseEntity <Task> getTask( @RequestParam(required = false) Task task) {
//	  List<Task> tasks=taskRepository.findAll();
//		  taskRepository.findAll();
//		  taskRepository.findAll().forEach(tasks::add);
//		  
//		  return new ResponseEntity<>(tasks, HttpStatus.OK);
		  
//	    try {
//	      List<Task> tasks = new ArrayList<Task>();
//
//	      if (description == null)
//	        taskRepository.findAll().forEach(tasks::add);
//	      else
//	        taskRepository.findByTitleContaining(title).forEach(tasks::add);
//
//	      if (tasks.isEmpty()) {
//	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	      }
//
//	      return new ResponseEntity<>(tasks, HttpStatus.OK);
//	    } catch (Exception e) {
//	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	  }
	
	
	
	  @GetMapping("/usertask/{id_user}")
//	  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<?> findByUser(@PathVariable("id_user") @RequestParam(required = false) Task task) {
//		  List<Task> tasks = new ArrayList<Task>();
//			List<Task> taskU=taskRepository.findAll();
		
          task.getDescription();
          
          
		  return new ResponseEntity<>(task.getDescription(), HttpStatus.OK);
	  }

	
	  
//	  @RequestMapping(value = "/addInformation" , method=RequestMethod.GET)
//		public String addInformation(Model model) {
//			List<Category> categories = cR.findAll();
//			List<User> users = uR.findAll();
//			Information info = new Information();
//			info.setCategory(cR.getOne((long) 1));
//			info.setUser(uR.getOne((long) 1));
//			model.addAttribute("users", users);
//			model.addAttribute("categories", categories);
//			model.addAttribute("information", info);
//			return "AddInformation";
//		}
	  
	  
	  
	  

//	  @GetMapping("/{id_task}")
//	  public ResponseEntity<Task> getTaskById(@PathVariable("id_task") long id_task) {
//	    Optional<Task> tasksData = taskRepository.findById(id_task);
//	    return new ResponseEntity<>(tasksData.get(), HttpStatus.OK);
//	    if (tasksData.isPresent()) {
//	      return new ResponseEntity<>(tasksData.get(), HttpStatus.OK);
//	    } 
//	    else {
//	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
	  

//	  @PostMapping("newtask")
//	  public ResponseEntity<?> createTask(Task task) {
//	    try 
//	    {
//	      Task _task = taskRepository.
//	    		  save(new Task(task.getDescription(), task.getFolder(), task.getUser()));
//	      return new ResponseEntity<>(_task, HttpStatus.CREATED);
//	    } 
//	    catch (Exception e) {
//	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }
//
//	  @PutMapping("/tasksupdate/{id}")
//	  public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
//	    Optional<Task> taskData = taskRepository.findById(id);
//
//	    if (taskData.isPresent()) {
//	      Task _task = taskData.get();
//	      
//	      _task.setDescription(task.getDescription());
//	      
//	      return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
//	    } else {
//	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	  }
//
//	  @DeleteMapping("/tasks/{id}")
//	  public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
//	    try {
//	      taskRepository.deleteById(id);
//	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    } catch (Exception e) {
//	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }
//
//	  @DeleteMapping("/tasks")
//	  public ResponseEntity<HttpStatus> deleteAllTask() {
//	    try {
//	      taskRepository.deleteAll();
//	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    } catch (Exception e) {
//	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//	    }
//
//	  }
}
	 

