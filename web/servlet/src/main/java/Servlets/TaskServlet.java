package Servlets;

import entities.Task;
import entities.Todo;
import implementations.TaskImpl;
import implementations.TodoImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskServlet", value = "/TaskServlet")
public class TaskServlet extends HttpServlet {

    @EJB
    private TaskImpl taskService;

    @EJB
    private TodoImpl todoService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todoIdParam = request.getParameter("todoId");

        if (todoIdParam == null || todoIdParam.isEmpty()) {
            System.out.println("ID Todo manquant !");
            response.getWriter().write("ID Todo manquant");
            return;
        }

        Long todoId = Long.parseLong(todoIdParam);
        System.out.println("Récupération des tâches pour Todo ID : " + todoId);

        // Vérifier que le Todo existe
        Todo todo = todoService.getTodo(todoId);
        if (todo == null) {
            System.out.println("Todo introuvable !");
            response.getWriter().write("Todo introuvable");
            return;
        }

        // Récupérer les tâches du Todo
        List<Task> tasks = taskService.getTasksTodoList(todo.getId());
        System.out.println("Nombre de tâches récupérées : " + (tasks != null ? tasks.size() : "null"));

        // Envoyer les données à la JSP
        request.setAttribute("todo", todo);
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/pages/task.jsp").forward(request, response);
    }
}
