package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	
	 /* Part 1
	  public List<Question> getAllQuestions() {
	  
	  List<Question> list = questionDao.findAll();
	  System.out.println("DB Returned: " + list); return list; 
	  }*/

	public ResponseEntity<List<Question>> getAllQuestions() {

		try {

			List<Question> list = questionDao.findAll();
			System.out.println("DB Returned: " + list);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	/* Part 1
	 public List<Question> getQuestionsByCategory(String category) {

		return questionDao.findByCategory(category);

	}*/
		
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

		try {

			List<Question> list = questionDao.findAll();
			System.out.println("DB Returned: " + list);
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	/* Part 1
	public String addQuestion(Question question) {

		questionDao.save(question);
		return "Successfully added the question to DB..!!";
	}*/
	
	public ResponseEntity<String> addQuestion(Question question) {

		try {

			questionDao.save(question);
			return new ResponseEntity<String>("Successfully added the question to DB..!!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new String(), HttpStatus.BAD_REQUEST);
	}
	
	

}
