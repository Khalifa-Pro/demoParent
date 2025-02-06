package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tache")
@NamedQuery(name = "listTasksTodo", query = "SELECT u FROM Task u WHERE u.todo.id = :todoId ORDER BY u.libelle ASC")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "tache")
    private String libelle;

    @Column(nullable = false, length = 100, name = "fait")
    private boolean estFait;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
    @JoinColumn(name="todo_id")
    protected  Todo todo;

    public Task() {}
    public Task(String libelle, Todo todo) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public boolean isEstFait() {
        return estFait;
    }

    public void setEstFait(boolean estFait) {
        this.estFait = estFait;
    }
}
