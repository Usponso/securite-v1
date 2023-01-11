<%--
  Created by IntelliJ IDEA.
  User: 20015126
  Date: 09/01/2023
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Signup</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary mb-5">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Sécurite v1</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/securite_v1_war_exploded">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="signup">Signup</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"  href="add">Add a book</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="search">Search user</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <h1>Signup :</h1>

    <form method="post" action="signup">
      <label for="name" class="form-label">Name :</label>
      <input type="text" name="name" id="name" class="form-control" required>

      <label for="firstname" class="form-label">Firstname :</label>
      <input type="text" name="firstname" id="firstname" class="form-control" required>

      <label for="age" class="form-label">Age :</label>
      <input type="number" min="0" max="100" name="age" id="age" class="form-control" required>

      <label for="mail" class="form-label">Mail :</label>
      <input type="text" name="mail" id="mail" class="form-control" required>

      <label for="password" class="form-label">Password :</label>
      <input type="password" name="password" id="password" class="form-control" required>

      <label for="confirmPassword" class="form-label">Confirm password :</label>
      <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required>

      <button type="submit" class="btn btn-success">Signup</button>
    </form>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
