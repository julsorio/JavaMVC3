<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grupos Musicales</title>

<style>
h1 {
	text-align: center;
}

table {
	border: 1px solid;
	width: 100%
}

table thead {
	background-color: #a18f79;
	text-align: center;
}

table tbody tr:nth-child(odd) {
	background-color: #c9bdac;
}

table tbody tr:nth-child(even) {
	background-color: #e4ded4;
}

table tbody td {
	text-align: center;
}

.contenido {
	margin: auto;
	padding: 10px;
	width: 60%;
}
</style>

</head>

<body>
	<h1>Grupos Musicales</h1>

	<div class="contenido">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gruposMusicales}" var="grupo">
					<tr>
						<td>${grupo.id}</td>
						<td>${grupo.nombre}</td>
						<td><a href="ServletEmisora?accion=detalle&idGrupo=${grupo.id}">Ver m&aacute;s</a> &nbsp; 
							<a href="ServletEmisora?accion=modificar&idGrupo=${grupo.id}">Modificar</a> &nbsp;
							<a href="ServletEmisora?accion=baja&idGrupo=${grupo.id}">Eliminar</a>  
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="ServletEmisora" method="get">
			 <input type="submit" value="Añadir grupo">
			 <input type="hidden" name="accion" id="accion" value="anadir">
		</form>
		
	</div>

</body>

</body>
</html>