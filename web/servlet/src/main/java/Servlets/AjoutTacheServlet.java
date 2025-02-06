package Servlets;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entities.Task;
import entities.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import implementations.TodoImpl;
import implementations.TaskImpl;
import entities.User;

import java.io.IOException;

@WebServlet(name = "AjoutTacheServlet", value = "/ajout-task")
public class AjoutTacheServlet extends HttpServlet {

    @EJB
    private TaskImpl taskService;

    @EJB
    private TodoImpl todoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp?error=2");
            return;
        }

        Long todoId = Long.valueOf(request.getParameter("todoId"));
        String description = request.getParameter("libelle");

        if (description == null || description.isEmpty()) {
            request.setAttribute("error", "Veuillez remplir la description.");
            request.getRequestDispatcher("ajout-task.jsp").forward(request, response);
            return;
        }

        Todo todo = todoService.getTodo(todoId);
        if (todo == null) {
            response.sendRedirect("todo.jsp?error=TodoNotFound");
            return;
        }

        Task newTask = new Task();
        newTask.setLibelle(description);
        newTask.setTodo(todo);

        try {
            taskService.createTaskTodoList(newTask);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("TaskServlet?todoId=" + todoId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp?error=2");
            return;
        }

        String todoIdParam = request.getParameter("todoId");

        if (todoIdParam == null || todoIdParam.isEmpty()) {
            response.sendRedirect("todos.jsp?error=MissingTodoId");
            return;
        }

        Long todoId = Long.parseLong(todoIdParam);
        Todo todo = todoService.getTodo(todoId);

        if (todo == null) {
            response.sendRedirect("todos.jsp?error=TodoNotFound");
            return;
        }

        // Ajouter le todo à la requête pour l'affichage dans la JSP
        request.setAttribute("todo", todo);
        request.getRequestDispatcher("/pages/ajoutTache.jsp").forward(request, response);
    }

}
