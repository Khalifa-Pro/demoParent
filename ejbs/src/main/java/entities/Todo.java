package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todo")
@NamedQuery(name = "listTodos", query = "SELECT t FROM Todo t WHERE t.utilisateur.id = :userId ORDER BY t.title ASC")
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50,name = "titre")
    private String title;

    @Column(nullable = false, length = 254,name = "description")
    private String description;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    protected  User utilisateur;

    @OneToMany(mappedBy="todo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public Todo(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Todo() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }
}
