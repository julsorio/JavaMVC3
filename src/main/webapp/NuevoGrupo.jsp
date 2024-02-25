<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
body {
	background-color: #D46A6A;
}

h1 {
	font-weight:bold;
	text-align:center;
}

h3 {
	font-weight:bold;
	text-align:center;
}

.marco {
	border: 1pt solid #fbb;
	width:100%;
}

.tabla {
	border: 1pt solid;
	width:50%;
	margin: auto;
}

.cabecera {
	font-weight:bold;
	background-color:#801515;
	width:30%;
}

.datos {
	background-color:#FFAAAA;
	width:70%;
}

</style>

<title>Grupos Musicales - Nuevo grupo musical</title>
</head>
<body>
	<h1>Grupos Musicales</h1>

	<div class="marco">

		<h3>Nuevo grupo musical</h3>

		<form action="ServletEmisora" method="get">
			<input type="hidden" id="accion" name="accion" value="alta">
			<table class="tabla"  >
				<tr>
					<td class="cabecera"><label for="nombre">Nombre del grupo: </label></td>
					<td class="datos"><input type="text" name="nombre" id="nombre"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="apellido">Año creación: </label></td>
					<td class="datos"><input type="text" name="creacion" id="creacion"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="origen">Origen: </label></td>
					<td class="datos"><select name="origen" id="origen">
							<option value="Estados Unidos">Estados Unidos</option>
							<option value="Reino Unido">Reino Unido</option>
							<option value="Australia">Australia</option>
							<option value="España">España</option>
							<option value="Alemania">Alemania</option>
					</select></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="genero">Género: </label></td>
					<td class="datos"><select name="genero" id="genero">
						<option value="Heavy Metal">Heavy Metal</option>
						<option value="Hard rock">Hard rock</option>
						<option value="Rock">Rock</option>
						<option value="Folk metal">Folk metal</option>
						<option value="Rock andaluz">Rock andaluz</option>
					</select></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="discografica">Discografica: </label></td>
					<td class="datos"><select name="discografica" id="discografica">
						<option value="1">Metal Blade Records</option>
						<option value="2">Megaforce Records</option>
						<option value="3">EMI Records</option>
						<option value="4">Epic Records</option>
						<option value="5">Sony Music</option>
						<option value="6">Columbia Records</option>
						<option value="7">Capitol Records</option>
						<option value="8">Warner Music</option>
						<option value="9">RCA Records</option>
						<option value="10">Universal Music</option>

					</select></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
					<input type="submit" name="guardar" id="guardar" value="Guardar">
					<input type="reset" name="borrar" id="borrar" value="Cancelar" onclick="history.back();" />
					</td>
				</tr>

			</table>
			<br><br>
		</form>

	</div>
</body>
</html>
