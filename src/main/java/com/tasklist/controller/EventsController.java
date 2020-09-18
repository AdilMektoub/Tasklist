package com.tasklist.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tasklist.models.Events;
import com.tasklist.models.User;
import com.tasklist.repository.EventsRepository;
import com.tasklist.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test/events")
public class EventsController {

	
	@Autowired
    EventsRepository er;
	@Autowired
	UserRepository userRepository;

   

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Welcome!";
    }
    @GetMapping("/all")
    public List<Events> getAllEvents() {
	    return er.findAll();
	  }

//    @GetMapping("/all")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    Iterable<Events> events(@RequestParam("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
//        return er.findBetween(from, to);
//    }

    @PostMapping("/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
  Events createEvent(@RequestBody EventsCreateParams params) {

    	User u = userRepository.findById(params.id_user).get();

        Events e = new Events();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
    

        er.save(e);

        return e;
    }
//
//    @PostMapping("/api/events/move")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @Transactional
//    Events moveEvent(@RequestBody EventsMoveParams params) {
//
//        Events e = er.findById(params.id).get();
//        
//
//        e.setStart(params.start);
//        e.setEnd(params.end);
//       
//
//        er.save(e);
//
//        return e;
//    }

    @PostMapping("/delete")
    @Transactional
    void deleteEvent(@RequestBody EventsDeleteParams params) {
        er.deleteById(params.id);
    }

    public static class EventsCreateParams {
        public Long id_user;
		public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        
    }

    public static class EventsMoveParams {
        public Long id_event;
        public LocalDateTime start;
        public LocalDateTime end;
       
    }

    public static class EventsDeleteParams {
        public Long id;
    }
}
