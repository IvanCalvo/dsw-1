<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${proposta != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${proposta != null}">
		<input type="hidden" name="id" value="${proposta.id}" />
	</c:if>
	<tr>
		<td><label for="valor">Valor</label></td>
		<td><input type="number" id="valor" name="valor" size="20" min="0.01" step="any"
			required value="${proposta.valor}" /></td>
	</tr>
	<tr>
		<td><label for="condPagamento">Condição de Pagamento</label></td>
		<td><input type="text" id="condPagamento" name="condPagamento" size="45" required
			value="${proposta.condPagamento}" /></td>
	</tr>
	<tr>
		<td><label for="dataAtual">Data Atual</label></td>
		<td><input type="number" id="dataAtual" name="dataAtual" size="8" required
			min="1500" value="${proposta.dataAtual}" /></td>
	</tr>
	<tr>
		<td><label for="status">Status da Compra</label></td>
		<td><input type="text" id="status" name="status" required size="45" value="${proposta.status}" /></td>
	</tr>
	<tr>
		<td><label for="cliente">Cliente</label></td>
		<td><select id="cliente" name="cliente">
				<c:forEach items="${clientes}" var="cliente">
					<option value="${cliente.key}"
						${proposta.cliente.nome==cliente.value ? 'selected' : '' }>
						${cliente.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td><label for="carro">Carro</label></td>
		<td><select id="carro" name="carro">
				<c:forEach items="${carros}" var="carro">
					<option value="${carro.key}"
						${proposta.carro.nome==carro.value ? 'selected' : '' }>
						${carro.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>