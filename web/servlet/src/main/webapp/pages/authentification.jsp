<%--
  Created by IntelliJ IDEA.
  User: FINAPPS
  Date: 02/02/2025
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion & Inscription</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 400px;
            margin: 5% auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<div class="form-container">
    <ul class="nav nav-tabs" id="authTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="login-tab" data-bs-toggle="tab" data-bs-target="#login" type="button" role="tab" aria-controls="login" aria-selected="true">
                Connexion
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="register-tab" data-bs-toggle="tab" data-bs-target="#register" type="button" role="tab" aria-controls="register" aria-selected="false">
                Inscription
            </button>
        </li>
    </ul>
    <div class="tab-content mt-3" id="authTabsContent">
        <!-- Connexion -->
        <div class="tab-pane fade show active" id="login" role="tabpanel">
            <form action="<%= request.getContextPath() %>/authentification" method="POST">
                <input type="hidden" name="action" value="login">
                <div class="mb-3">
                    <input type="email" name="username" class="form-control" placeholder="Email" required>
                </div>
                <div class="mb-3">
                    <input type="password" name="password" class="form-control" placeholder="Mot de passe" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Se connecter</button>
            </form>
        </div>

        <!-- Inscription -->
        <div class="tab-pane fade" id="register" role="tabpanel">
            <form action="<%= request.getContextPath() %>/authentification" method="POST">
                <input type="hidden" name="action" value="register">
                <div class="mb-3">
                    <input type="text" class="form-control" name="firstname" placeholder="Prénom" required>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" name="lastname" placeholder="Nom" required>
                </div>
                <div class="mb-3">
                    <input type="email" class="form-control" name="username" placeholder="Email" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" name="password" placeholder="Mot de passe" required>
                </div>
                <button type="submit" class="btn btn-success w-100">S'inscrire</button>
            </form>
        </div>

    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

