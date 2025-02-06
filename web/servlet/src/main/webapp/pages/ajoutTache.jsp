<%--
  Created by IntelliJ IDEA.
  User: FINAPPS
  Date: 06/02/2025
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.Task" %>
<%@ page import="entities.Todo" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Nouvelle Tâche</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Ajouter une nouvelle tâche</h2>

    <%
        Long todoId = Long.valueOf(request.getParameter("todoId"));
        //Todo todo = ITodo.getTodoById(todoId);

    %>

    <form action="ajout-task" method="post">
        <input type="hidden" name="todoId" value="<%= todoId %>">
        <div class="mb-3">
            <label for="libelle" class="form-label">Libelle de la tâche</label>
            <input type="text" class="form-control" id="libelle" name="libelle" required>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
