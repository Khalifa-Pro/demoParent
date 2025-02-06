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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TodoServlet", value = "/TodoServlet")
public class TodoServlet extends HttpServlet {

    @EJB
    private TodoImpl todoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            System.out.println("Aucun utilisateur trouvé en session !");
            response.sendRedirect("index.jsp?error=2");
            return;
        }

        System.out.println("Utilisateur trouvé : " + user.getId());

        List<Todo> todos = todoService.getTodos(user.getId());
        System.out.println("Nombre de todos récupérés : " + todos.size());

        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/pages/todo.jsp").forward(request, response);
    }


}
