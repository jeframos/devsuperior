package com.devsuperior.dslearn.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


//Foi adicionado a anotação @Inheritance(strategy = InheritanceType.JOINED) para indicar que
// a classe Lesson é uma superclasse de outras classes (Content e Task) e que o mapeamento
// das subclasses será feito em tabelas separadas no banco de dados.
@Entity
@Table(name = "tb_lesson")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lesson  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    //Foi adicionado a anotação @ManyToMany para indicar que existe um relacionamento muitos-para-muitos
    // entre a entidade Lesson e a entidade Enrollment.
    // O relacionamento é mapeado por meio de uma tabela intermediária chamada "tb_lesson_done",
    // que possui duas chaves estrangeiras: "lesson_id" e "user_id".
    // Como a entidade Enrollment possui chave composta (user_id, offer_id),
    // é necessário mapear as duas colunas da chave estrangeira na anotação @JoinColumn.
    @ManyToMany
    @JoinTable(name = "tb_lesson_done",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = {
                @JoinColumn(name = "user_id"),
                @JoinColumn(name = "offer_id")
            }
    )
    private Set<Enrollment> enrollmentsDone = new HashSet<>();

    public Lesson() {}

    public Lesson(Long id, String title, Integer position, Section section) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Enrollment> getEnrollmentsDone() {
        return enrollmentsDone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
