package implementations;

import entities.Todo;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import services.ITodo;

import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class TodoImpl implements ITodo {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public void createTodo(Todo todo) {
        try {
            em.persist(todo);
            em.flush(); // S'assure que les données sont insérées immédiatement
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du Todo", e);
        }
    }


    @Override
    public Todo getTodo(Long id) {
        try {
            // Utilisation de EntityManager pour récupérer un Todo par son ID
            Todo todo = em.find(Todo.class, id);
            return todo;
        } catch (Exception e) {
            // Gérer les exceptions si nécessaire (par exemple, si l'ID n'existe pas)
            throw new RuntimeException("Erreur lors de la récupération du Todo", e);
        }
    }

    @Override
    public void editTodo(Todo todo, Long idTodoOld) {
        try {
            Todo existTodo = em.find(Todo.class, idTodoOld);
            if (existTodo == null) {
                throw new IllegalArgumentException("Todo with ID " + idTodoOld + " not found");
            }
            existTodo.setTitle(todo.getTitle());
            existTodo.setDescription(todo.getDescription());
            existTodo.setUtilisateur(todo.getUtilisateur());
            em.persist(existTodo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public void deleteTodo(Long id) {
        try{
            Todo todo = em.find(Todo.class, id);
            em.remove(todo);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Todo> getTodos(Long userId) {
        System.out.println("Récupération des Todos pour userId : " + userId);

        List<Todo> todos = em.createNamedQuery("listTodos", Todo.class)
                .setParameter("userId", userId)
                .getResultList();

        System.out.println("Résultat de la requête : " + todos.size() + " todos trouvés.");

        return todos;
    }

}
