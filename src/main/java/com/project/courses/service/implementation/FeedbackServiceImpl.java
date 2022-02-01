package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Feedback;
import com.project.courses.model.Student;
import com.project.courses.model.User;
import com.project.courses.repository.FeedbackRepository;
import com.project.courses.service.FeedbackService;
import com.project.courses.service.StudentService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private StudentService studentService;

	@Override
	public List<Feedback> getFeedbacks() {
		List<Feedback> feedbacks = feedbackRepository.findAll();
		return feedbacks;
	}

	@Override
	public Feedback saveFeedback(Feedback feedback, User user) {
		if (feedback.getId() == null) {
			Student student = studentService.getByUser(user);
			feedback.setStudent(student);
		}
		return feedbackRepository.save(feedback);
	}
	
	@Override
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	@Override
	public Feedback getById(Long id) {
		Feedback feedback = feedbackRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Feedback not found."));
		return feedback;
	}

	@Override
	public List<Feedback> getByStudentId(Long studentId) {
		Student student = studentService.getById(studentId);
		List<Feedback> feedbacks = feedbackRepository.findByStudent(student);
		return feedbacks;
	}

	@Override
	public Feedback updateFeedbackById(Long id, Feedback updatedFeedback) {
		Feedback feedback = this.getById(id);
		
		if (updatedFeedback.getName() != null)
			feedback.setName(updatedFeedback.getName());
		
		feedback.setEmail(updatedFeedback.getEmail());
		feedback.setFeedback(updatedFeedback.getFeedback());
		
		feedback = this.saveFeedback(feedback);
		
		return feedback;
	}

	@Override
	public Boolean deleteFeedbackById(Long id) {
		Feedback feedback = this.getById(id);
		
		feedbackRepository.delete(feedback);
		
		if (feedback == null)
			return false;
		
		return true;
	}
}
