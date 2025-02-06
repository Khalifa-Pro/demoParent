<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Todo" %>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Tâches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Bienvenue, <%= user.getFirstName() %>!</h2>
        <a href="authentification.jsp" class="btn btn-danger">Déconnexion</a>
    </div>

    <!-- Bouton d'ajout qui redirige vers la page d'ajout -->
    <a href="ajout-todo" class="btn btn-primary mb-3">Nouveau</a>

    <h3>Vos Todos</h3>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Titre</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Todo> todos = (List<Todo>) request.getAttribute("todos");
            if (todos != null && !todos.isEmpty()) {
                int i = 1;
                for (Todo todo : todos) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= todo.getTitle() %></td>
            <td><%= todo.getDescription() %></td>
            <td>
                <a href="TaskServlet?todoId=<%= todo.getId() %>" class="btn btn-secondary">Voir les tâches</a>
                <a href="#" class="btn btn-primary">Modifier</a>
                <a href="#" class="btn btn-danger">Supprimer</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" class="text-center">Aucun todo disponible.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
