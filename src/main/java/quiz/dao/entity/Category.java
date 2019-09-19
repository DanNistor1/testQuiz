package quiz.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends TemplateEntity {

    @Column(nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setCategory(this);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setCategory(null);
    }

}
