package services;

import entities.Todo;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ITodo {
    public void createTodo(Todo todo);
    public Todo getTodo(Long id);
    public void editTodo(Todo todo, Long idTodoOld);
    public void deleteTodo(Long id);
    public List<Todo> getTodos(Long userId);
}
