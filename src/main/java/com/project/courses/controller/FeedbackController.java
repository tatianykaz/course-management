package com.project.courses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.courses.model.Feedback;
import com.project.courses.model.User;
import com.project.courses.service.FeedbackService;
import com.project.courses.service.UserService;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Feedback>> getFeedbacks(){
		List<Feedback> feedbacks = feedbackService.getFeedbacks();
		return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback){
		User user = userService.getAutheticatedUser();
		feedbackService.saveFeedback(feedback, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback updatedFeedback){
		updatedFeedback = feedbackService.updateFeedbackById(id, updatedFeedback);
		return ResponseEntity.status(HttpStatus.OK).body(updatedFeedback);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteFeedback(@PathVariable Long id) {
		Boolean deleted = feedbackService.deleteFeedbackById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
	}	

}
