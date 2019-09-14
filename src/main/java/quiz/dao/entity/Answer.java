package quiz.dao.entity;

import quiz.dao.enums.AnswerValue;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
public class Answer extends TemplateEntity {

    @Column(nullable = false, name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "value")
    private AnswerValue value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public AnswerValue getValue() {
        return value;
    }

    public void setValue(AnswerValue value) {
        this.value = value;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
