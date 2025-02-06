package implementations;

import entities.Task;
import entities.Todo;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import services.ITask;

import java.util.List;

@Stateless
@LocalBean
public class TaskImpl implements ITask {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public void createTaskTodoList(Task task) {
        try{
            em.persist(task);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getTasksTodoList(Long todoId) {
        TypedQuery<Task> query = em.createNamedQuery("listTasksTodo", Task.class);
        query.setParameter("todoId", todoId);
        return query.getResultList();
    }

    @Override
    public void deleteTaskTodoList(Long id) {
        try{
            Todo todo = em.find(Todo.class, id);
            em.remove(todo);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void doneTaskTodoList(Long taskId) {
        try {
            Task taskDone = em.find(Task.class, taskId);
            if (taskDone == null) {
                throw new IllegalArgumentException("Task with ID " + taskId + " not found");
            }
            taskDone.setEstFait(true);
            em.persist(taskDone);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
