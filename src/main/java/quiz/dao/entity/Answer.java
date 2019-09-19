package quiz.dao.entity;

import quiz.dao.enums.AnswerValue;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer extends TemplateEntity {

    @Column(nullable = false, name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "value")
    private AnswerValue value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {
    }

    public Answer(String text, AnswerValue value, Question question) {
        this.text = text;
        this.value = value;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerValue getValue() {
        return value;
    }

    public void setValue(AnswerValue value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
