package quiz.dao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test")
public class TestSet extends TemplateEntity {

    @Column(nullable = false, name = "created_date")
    private String date;

    @ManyToMany
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();

    public TestSet() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        this.date = formatter.format(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.getTests().add(this);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.getTests().remove(this);
    }
}
