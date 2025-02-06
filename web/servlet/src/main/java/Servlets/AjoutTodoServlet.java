package Servlets;

import entities.Todo;
import entities.User;
import implementations.TodoImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ajoutTodo", value = "/ajout-todo")
public class AjoutTodoServlet extends HttpServlet {

    @EJB
    private TodoImpl todoService;

    // Affiche la page de formulaire ajoutTodo.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp?error=2");
            return;
        }

        request.getRequestDispatcher("/pages/ajoutTodo.jsp").forward(request, response);
    }

    // Traite l'ajout d'un Todo
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp?error=2");
            return;
        }

        // Récupération des valeurs du formulaire
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.isEmpty() || description == null || description.isEmpty()) {
            request.setAttribute("error", "Veuillez remplir tous les champs.");
            request.getRequestDispatcher("ajoutTodo.jsp").forward(request, response);
            return;
        }

        // Création et enregistrement du Todo
        Todo newTodo = new Todo();
        newTodo.setTitle(title);
        newTodo.setDescription(description);
        newTodo.setUtilisateur(user);

        try {
            todoService.createTodo(newTodo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔥 Solution : Rediriger vers TodoServlet pour recharger la liste des tâches
        response.sendRedirect("TodoServlet");
    }

}
