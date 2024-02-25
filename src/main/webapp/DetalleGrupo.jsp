<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle</title>

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

	<h1>Detalle del grupo</h1>

	<div class="contenido">
		<table>
			<tbody>
				<tr>
					<td>Id del grupo:</td>
					<td>${detalleGrupo.id}</td>
				</tr>
				<tr>
					<td>Nombre del grupo:</td>
					<td>${detalleGrupo.nombre}</td>
				</tr>
				<tr>
					<td>Año de creacion del grupo:</td>
					<td>${detalleGrupo.creacion}</td>
				</tr>
				<tr>
					<td>Lugar de origen del grupo:</td>
					<td>${detalleGrupo.origen}</td>
				</tr>
				<tr>
					<td>Genero musical del grupo:</td>
					<td>${detalleGrupo.genero}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<h1>Componentes del grupo</h1>
	
	<div class="contenido">
		<table>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Instrumento</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${detalleGrupo.listaComponentes}" var="componente" >
					<tr>
						<td>${componente.nombre}</td>
						<td>${componente.instrumento}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<p>
		<a href="ServletEmisora?accion=cargar">Volver atr&aacute;s</a>
	</p>
</body>
</html>