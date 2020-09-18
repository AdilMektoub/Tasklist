package com.tasklist.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.models.Information;
import com.tasklist.models.User;
import com.tasklist.repository.InformationRepository;
import com.tasklist.repository.TaskRepository;
import com.tasklist.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/infos")
public class InformationController {
	
	
	@Autowired
	private InformationRepository iR;

	@Autowired
	private UserRepository uR;


	
	@RequestMapping(value = "/edit" , method=RequestMethod.GET)
	public String edit(Model model, Long id) {
//		List<Information> informations = iR.findAll();
		List<User> users = uR.findAll();
		model.addAttribute("users", users);
//		model.addAttribute("informations", informations);
		
//		Optional<Information> i =  iR.findById(id);
//		Information info = i.get();
		
//		info.setInformation(iR.getOne((long) 1));
//		info.setUser(uR.getOne((long) 1));

//		model.addAttribute("information", info);
		return "EditInformation";
	}

}
