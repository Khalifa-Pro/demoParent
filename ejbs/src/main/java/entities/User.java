package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom", nullable = false, length = 150)
    private String firstName;

    @Column(name = "nom", nullable = false, length = 100)
    private String lastName;

    @Column(name = "nom_utilisateur", nullable = false, length = 200, unique = true)
    private String userName;

    @Column(name = "mot_de_passe", nullable = false, length = 254)
    private String password;

    private String salt;

    @OneToMany(mappedBy="utilisateur", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Todo> todos = new HashSet<>();

    public User(Long id, String firstName, String lastName, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
