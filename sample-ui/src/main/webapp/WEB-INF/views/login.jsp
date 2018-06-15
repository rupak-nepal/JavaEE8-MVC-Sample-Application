<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
        <link rel="stylesheet" href="${mvc.contextPath}/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${appConfig.applicationName} | Log In</title>
    </head>
    <body>

        <form action="${mvc.contextPath}/app/login" method="post">
            <div class="container">
                <c:if test="${ERROR_MSG != null}">
                   <div class="${ERROR_MSG != null ? 'alert alert-danger':''}">${ERROR_MSG}</div>
                </c:if>
                <h4>Login</h4>

                <input type="text" placeholder="Username" name="username" requied>
                <input type="password" placeholder="Password" name="password" required>
                <button type="submit">Login</button>
            </div>
        </form>
    </body>
</html>
