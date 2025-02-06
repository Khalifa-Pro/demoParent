package Servlets;

import entities.Todo;
import implementations.TodoImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entities.User;
import implementations.UserImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AuthServlet", value = "/authentification")
public class AuthServlet extends HttpServlet {

    @EJB
    private UserImpl userService;

    @EJB
    private TodoImpl todoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        userService.sInscrire(firstname, lastname, username, password);
        response.sendRedirect("index.jsp?success=1");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.seConnecter(username, password);

        if (user != null) {
            System.out.println("Utilisateur connecté : " + user.getId() + " - " + user.getUserName());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Récupérer la liste des todos
            List<Todo> todos = todoService.getTodos(user.getId());

            // Envoyer les todos à la page JSP
            request.setAttribute("todos", todos);

            // Rediriger vers la page todo.jsp
            request.getRequestDispatcher("/pages/todo.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp?error=1");
        }
    }

}
