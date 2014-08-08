<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hi there</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="" method="post">
  User Name：<input type="text" name="username"><br/>
    Password：<input type="password" name="password"><br/>
    <input type="submit" value="Submit">
</form>

</body>
</html>