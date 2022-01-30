package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Feedback;
import com.project.courses.model.User;

public interface FeedbackService {
	
	List<Feedback> getFeedbacks();
	
	Feedback saveFeedback(Feedback feedback);
	
	Feedback saveFeedback(Feedback feedback, User user);

	Feedback getById(Long id);
	
	List<Feedback> getByStudentId(Long studentId);

	Feedback updateFeedbackById(Long id, Feedback updatedFeedback);

	Boolean deleteFeedbackById(Long id);

}
