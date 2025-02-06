<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Task" %>
<%@ page import="entities.Todo" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Tâches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <%
        // Vérification de la validité de todoId
        Long todoId = null;
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage == null) {
            try {
                todoId = Long.valueOf(request.getParameter("todoId"));
            } catch (NumberFormatException e) {
                errorMessage = "ID de Todo invalide.";
            }
        }

        // Récupération du Todo et des tâches
        Todo todo = (Todo) request.getAttribute("todo");
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    %>

    <!-- Affichage du message d'erreur si nécessaire -->
    <%
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger"><%= errorMessage %></div>
    <%
        }
    %>

    <%-- Afficher le titre de la Todo si todo est trouvé --%>
    <% if (todo != null) { %>
    <h2>Tâches pour: <%= todo.getTitle() %></h2>
    <% } else { %>
    <div class="alert alert-warning">Aucune Todo trouvé pour cet ID.</div>
    <% } %>

    <!-- Bouton d'ajout de nouvelle tâche -->
    <% if (todo != null) { %>
    <a href="ajout-task?todoId=<%= todoId %>" class="btn btn-primary mb-3">Nouvelle Tâche</a>
    <% } %>

    <!-- Table des tâches -->
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Libelle</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (tasks != null && !tasks.isEmpty()) {
                int i = 1;
                for (Task task : tasks) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= task.getLibelle() %></td>
            <td>
                <a href="#" class="btn btn-primary">Modifier</a>
                <a href="#" class="btn btn-success">Terminer</a>
                <a href="#" class="btn btn-danger">Supprimer</a>
            </td>

        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2" class="text-center">Aucune tâche disponible.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
