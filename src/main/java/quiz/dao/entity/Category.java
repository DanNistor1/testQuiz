package quiz.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category extends TemplateEntity {

    @Column(nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
