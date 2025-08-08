package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);

		quiz.setQuestions(questions);

		quizDao.save(quiz);

		return new ResponseEntity<String>("SUCESS", HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

		Optional<Quiz> quiz = quizDao.findById(id);

		List<Question> questionsFromDB = quiz.get().getQuestions();

		List<QuestionWrapper> questionsForUser = new ArrayList<>();

		for (Question q : questionsFromDB) {

			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());

			questionsForUser.add(qw);

		}

		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);

	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		Quiz quiz = quizDao.findById(id).get();

		List<Question> questions = quiz.getQuestions();

		int resultCount = 0, i = 0;

		for (Response res : responses) {

			if (res.getUserAnswer().equals(questions.get(i).getRightAnswer())) {
				resultCount++;
			}

			i++;

		}

		return new ResponseEntity<Integer>(resultCount, HttpStatus.OK);
	}

}
