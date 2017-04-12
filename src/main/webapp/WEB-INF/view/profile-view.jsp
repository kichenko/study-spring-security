<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
    <div class="container">
        <form:form modelAttribute="user" class="form-signin">
            <h2 class="form-signin-heading">Профиль</h2>
            <spring:bind path="userName">
                <div class="form-group">
                    <label for="userName">Логин:</label>
                    <form:input id="userName" type="text" path="userName" class="form-control" readonly="true" />
                </div>
            </spring:bind>
            <spring:bind path="firstName">
                <div class="form-group">
                    <label for="firstName">Имя:</label>
                    <form:input id="firstName" type="text" path="firstName" class="form-control" readonly="true" />
                </div>
            </spring:bind>
            <spring:bind path="secondName">
                <div class="form-group">
                    <label for="secondName">Фамилия:</label>
                    <form:input id="secondName" type="text" path="secondName" class="form-control" readonly="true" />
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <form:input id="email" type="text" path="email" class="form-control" readonly="true" />
                </div>
            </spring:bind>
        </form:form>
    </div>
</t:page>
