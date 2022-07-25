<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CarDealership</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Carros</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Carros</caption>
			<tr>
				<th>ID</th>
				<th>modelo</th>
				<th>Loja</th>
				<th>placa</th>
				<th>Ano</th>
				<th>Quilometragem</th>
				<th>Descrição</th>
				<th>Valor</th>
			</tr>
			<c:forEach var="carro" items="${requestScope.listaCarros}">
				<tr>
					<td>${carro.id}</td>
					<td>${carro.modelo}</td>
					<td>${carro.loja.nome}</td>
					<td>${carro.placa}</td>
					<td>${carro.ano}</td>
					<td>${carro.quilometragem}</td>
					<td>${carro.descricao}</td>
					<td>${carro.valor}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>