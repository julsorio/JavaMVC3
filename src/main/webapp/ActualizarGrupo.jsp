<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
body {
	background-color: #D46A6A;
}

h1 {
	font-weight: bold;
	text-align: center;
}

h3 {
	font-weight: bold;
	text-align: center;
}

.marco {
	border: 1pt solid #fbb;
	width: 100%;
}

.tabla {
	border: 1pt solid;
	width: 50%;
	margin: auto;
}

.cabecera {
	font-weight: bold;
	background-color: #801515;
	width: 30%;
}

.datos {
	background-color: #FFAAAA;
	width: 70%;
}
</style>

<title>Grupos Musicales - Actualizar grupo musical</title>
</head>
<body>
	<h1>Grupos Musicales</h1>

	<div class="marco">

		<h3>Actualizar grupo musical</h3>

		<form action="ServletEmisora" method="get">

			<table class="tabla" >
				<tr>
					<td class="cabecera"><label for="id">Id del grupo: </label></td>
					<td class="datos">${datosGrupo.id}</td>
					<td><input type="hidden" name="id" id="id" value="${datosGrupo.id}"></td>
					<td><input type="hidden" name="accion" id="accion" value="actualizar"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="nombre">Nombre del grupo: </label></td>
					<td class="datos"><input type="text" name="nombre" id="nombre" value="${datosGrupo.nombre}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="apellido">Año creación: </label></td>
					<td class="datos"><input type="text" name="creacion" id="creacion" value="${datosGrupo.creacion}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="origen">Origen: </label></td>
						<td class="datos"><select name="origen" id="origen">
							<option value="Estados Unidos" <c:if test="${datosGrupo.origen eq 'Estados Unidos'}"> selected="selected"</c:if> >Estados Unidos</option>
							<option value="Reino Unido" <c:if test="${datosGrupo.origen eq 'Reino Unido'}"> selected="selected"</c:if> >Reino Unido</option>
							<option value="Australia" <c:if test="${datosGrupo.origen eq 'Australia'}"> selected="selected"</c:if> >Australia</option>
							<option value="España" <c:if test="${datosGrupo.origen eq 'España'}"> selected="selected"</c:if> >España</option>
							<option value="Alemania" <c:if test="${datosGrupo.origen eq 'Alemania'}"> selected="selected"</c:if> >Alemania</option>
					</select></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="genero">Género: </label></td>
					<td class="datos"><select name="genero" id="genero">
						<option value="Heavy Metal" <c:if test="${datosGrupo.genero eq 'Heavy Metal'}"> selected="selected" </c:if> >Heavy Metal</option>
						<option value="Hard rock" <c:if test="${datosGrupo.genero eq 'Hard rock'}"> selected="selected" </c:if> >Hard rock</option>
						<option value="Rock" <c:if test="${datosGrupo.genero eq 'Rock'}"> selected="selected" </c:if> >Rock</option>
						<option value="Folk metal" <c:if test="${datosGrupo.genero eq 'Folk metal'}"> selected="selected" </c:if> >Folk metal</option>
						<option value="Rock andaluz" <c:if test="${datosGrupo.genero eq 'Rock andaluz'}"> selected="selected" </c:if> >Rock andaluz</option>
					</select></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="discografica">Discografica: </label></td>
					<td class="datos"><select name="discografica" id="discografica">
						<option value="1" <c:if test="${datosGrupo.idDiscografica eq 1}"> selected="selected" </c:if>  >Metal Blade Records</option>
						<option value="2" <c:if test="${datosGrupo.idDiscografica eq 2}"> selected="selected" </c:if> >Megaforce Records</option>
						<option value="3" <c:if test="${datosGrupo.idDiscografica eq 3}"> selected="selected" </c:if> >EMI Records</option>
						<option value="4" <c:if test="${datosGrupo.idDiscografica eq 4}"> selected="selected" </c:if> >Epic Records</option>
						<option value="5" <c:if test="${datosGrupo.idDiscografica eq 5}"> selected="selected" </c:if> >Sony Music</option>
						<option value="6" <c:if test="${datosGrupo.idDiscografica eq 6}"> selected="selected" </c:if> >Columbia Records</option>
						<option value="7" <c:if test="${datosGrupo.idDiscografica eq 7}"> selected="selected" </c:if> >Capitol Records</option>
						<option value="8" <c:if test="${datosGrupo.idDiscografica eq 8}"> selected="selected" </c:if> >Warner Music</option>
						<option value="9" <c:if test="${datosGrupo.idDiscografica eq 9}"> selected="selected" </c:if> >RCA Records</option>
						<option value="10" <c:if test="${datosGrupo.idDiscografica eq 10}"> selected="selected" </c:if> >Universal Music</option>

					</select></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Guardar" /> 
						<input type="reset" name="borrar" id="borrar" value="Cancelar" onclick="history.back();" />
					</td>
				</tr>

			</table>

		</form>

	</div>
</body>
</html>
