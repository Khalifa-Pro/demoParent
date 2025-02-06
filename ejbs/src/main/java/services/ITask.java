package services;

import entities.Task;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ITask {
    public void createTaskTodoList(Task task);
    public List<Task> getTasksTodoList(Long todoId);
    public void deleteTaskTodoList(Long id);
    public void doneTaskTodoList(Long taskId);
}
