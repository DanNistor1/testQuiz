package quiz.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
public class Answer extends TemplateEntity {

    @Column(nullable = false, name = "text")
    private String text;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
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
