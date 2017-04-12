<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link href="${contextPath}/resources/css/bootstrap.min.css"
    rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<div>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <span class="navbar-brand">Привет,
                    ${pageContext.request.userPrincipal.principal.user.firstName}
                    ${pageContext.request.userPrincipal.principal.user.secondName}</span>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${contextPath}/profile/view">Профиль</a></li>
                <li><a href="${contextPath}/profile/edit">Редактировать</a></li>
            </ul>
            <div class="nav navbar-nav navbar-right">
                <form class="navbar-form navbar-left" method="POST"
                    action="${contextPath}/logout">
                    <button type="submit" class="btn btn-danger">Выйти</button>
                    <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </nav>
</div>
<jsp:doBody />
</body>
</html>