package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Feedback;

public interface FeedbackService {
	
	List<Feedback> getFeedbacks();
	
	Feedback saveFeedback(Feedback feedback);

	Feedback getById(Long id);
	
	List<Feedback> getByUserId(Long id);

	Feedback updateFeedbackById(Long id, Feedback updatedFeedback);

	Boolean deleteFeedbackById(Long id);

}
