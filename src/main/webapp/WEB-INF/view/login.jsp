<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Вход в систему</title>        
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Вход</h2>
        <div class="form-group ${error != null ? 'has-error' : ''}">             
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <c:if test="${not empty logout}">
                <div class="alert alert-info">${logout}</div>
            </c:if>           
            <input name="username" type="text" class="form-control" placeholder="Введите логин..." autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Введите пароль..."/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="checkbox">
                <label><input type="checkbox" name="remember-me">Запомнить меня</label>
            </div>                       
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </div>
    </form>
</div>
</body>
</html>
