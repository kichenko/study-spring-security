<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:page>
    <div class="container">
        <form:form modelAttribute="user" method="POST"
            action="${contextPath}/profile/edit" class="form-signin">
            <h2 class="form-signin-heading">Профиль</h2>
            <spring:bind path="firstName">
                <div
                    class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="firstName">Имя:</label>
                    <form:input id="firstName" type="text" path="firstName" class="form-control" autofocus="true" />
                    <form:errors path="firstName"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="secondName">
                <div
                    class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="secondName">Фамилия:</label>
                    <form:input id="secondName" type="text" path="secondName" class="form-control" />
                    <form:errors path="secondName"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div
                    class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="secondName">Email:</label>
                    <form:input id="email" type="email" path="email" class="form-control" />
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>
            <button class="btn btn-lg btn-primary btn-block"
                type="submit">Сохранить</button>
        </form:form>
    </div>
</t:page>
