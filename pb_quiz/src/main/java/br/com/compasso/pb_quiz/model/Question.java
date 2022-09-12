package br.com.compasso.pb_quiz.model;


import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private boolean answer;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public Question setAnswer(boolean answer) {
        this.answer = answer;
        return this;
    }

}
