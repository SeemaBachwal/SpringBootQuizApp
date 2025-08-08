package com.example.quizapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT") // Explicit DB type
    private Integer id;
    
    @Column(name="question_title")
    private String questionTitle;
    
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    
    @Column(name="right_answer")
    private String rightAnswer;
    
    @Column(name="difficulty_level")
    private String difficultyLevel;
    private String category;
    
    
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionTitle=" + questionTitle + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", rightAnswer=" + rightAnswer
				+ ", difficultyLevel=" + difficultyLevel + ", category=" + category + "]";
	}
    
    
    

}
