<%--
  Created by IntelliJ IDEA.
  User: 20015126
  Date: 09/01/2023
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add book</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary mb-5">
    <div class="container-fluid">
      <a class="navbar-brand" href="/securite_v1_war_exploded">Sécurite v1</a>
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
            <a class="nav-link" href="signup">Signup</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="add">Add a book</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="search">Search user</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <h1>Add a book :</h1>

    <form method="post" action="add" class="form">
      <label for="ownerName" class="form-label">Owner name :</label>
      <input type="text" name="ownerName" id="ownerName" class="form-control" required>

      <label for="bookId" class="form-label">Book id :</label>
      <input type="text" name="idBook" id="bookId" class="form-control" required>

      <label for="bookName" class="form-label">Book name :</label>
      <input type="number" name="nameBook" id="bookName" class="form-control" required>

      <label for="author" class="form-label">Author :</label>
      <input type="text" name="author" id="author" class="form-control" required>

      <button type="submit" class="btn btn-success">Add</button>
    </form>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
