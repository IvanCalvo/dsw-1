<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <%String contextPath = request.getContextPath().replace("/", "");%>
        <h1>Autenticação de Usuário</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="doLogin.jsp">
            <table>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="email"
                               value="${param.email}"/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" value="${param.senha}" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
        <a href="/<%=contextPath%>">Menu Principal</a>
    </body>
</html>