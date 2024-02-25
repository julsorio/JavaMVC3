package es.accenture.emisora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author Andrea Ravagli Castillo
 */
public class ModeloGrupo {
	private DataSource poolConexiones;
	
	public ModeloGrupo(DataSource poolConexiones) {
		this.poolConexiones = poolConexiones;
	}
	
	/**
	 * metodo que realiza la consulta a la tabla grupos
	 * @return lista de grupos
	 * @throws Exception
	 */
	public List<Grupo> getGrupos() throws Exception {
		List<Grupo> listaGrupos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet resultado = null;
		
		String query = "SELECT grupoId,nombre,origen,creacion,genero,discograficaIdActual FROM GRUPOS";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.createStatement();
			resultado = miStatement.executeQuery(query);
			
			if (resultado != null) {

				while (resultado.next()) {
					Grupo grupo = new Grupo();
					grupo.setId(resultado.getInt("grupoId"));
					grupo.setNombre(resultado.getString("nombre"));
					grupo.setOrigen(resultado.getString("origen"));
					grupo.setCreacion(resultado.getInt("creacion"));
					grupo.setGenero(resultado.getString("genero"));
					grupo.setIdDiscografica(resultado.getInt("discograficaIdActual"));

					listaGrupos.add(grupo);
				}
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al realizar la consulta " + e.getMessage());
			e.printStackTrace();
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return listaGrupos;
	}
	
	/**
	 * metodo que se encarga de obtener el detalle del grupo de acuerdo al id
	 * @param idGrupo
	 * @return grupo
	 * @throws Exception
	 */
	public Grupo getGrupo(int idGrupo) throws Exception {
		Grupo grupoEncontrado = null;
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		ResultSet resultado = null;
		
		String query = "SELECT grupoId,nombre,origen,creacion,genero,discograficaIdActual FROM grupos WHERE grupoId = ?";
		
		String query2 = "SELECT componenteId,nombre,instrumento FROM componentes WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setInt(1, idGrupo);
			resultado = miStatement.executeQuery();
			
			if (resultado != null) {

				while (resultado.next()) {
					grupoEncontrado = new Grupo();
					grupoEncontrado.setId(resultado.getInt("grupoId"));
					grupoEncontrado.setNombre(resultado.getString("nombre"));
					grupoEncontrado.setOrigen(resultado.getString("origen"));
					grupoEncontrado.setCreacion(resultado.getInt("creacion"));
					grupoEncontrado.setGenero(resultado.getString("genero"));
					grupoEncontrado.setIdDiscografica(resultado.getInt("discograficaIdActual"));

				}
			} else {
				System.out.println("No hay resultados para el grupo con id " + idGrupo);
			}
			
			miStatement = null;
			resultado = null;
			
			miStatement = miConexion.prepareStatement(query2);
			miStatement.setInt(1, idGrupo);
			resultado = miStatement.executeQuery();
			
			if(resultado != null) {
				grupoEncontrado.setListaComponentes(new ArrayList<Componente>());
				
				while(resultado.next()) {
					Componente componente = new Componente();
					componente.setComponenteId(resultado.getInt("componenteId"));
					componente.setGrupoId(idGrupo);
					componente.setInstrumento(resultado.getString("instrumento"));
					componente.setNombre(resultado.getString("nombre"));
					
					grupoEncontrado.getListaComponentes().add(componente);
				}
			} else {
				System.out.println("No hay componentes para el grupo con id " + idGrupo);
			}
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al realizar la consulta " + e.getMessage());
			e.printStackTrace();
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return grupoEncontrado;
	}
	
	/**
	 * metodo encargado de realizar el insert del grupo en la tabla
	 * @param grupo
	 * @throws Exception
	 */
	public void insertarGrupo(Grupo grupo) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "INSERT INTO grupos (nombre, creacion, origen, genero, discograficaIdActual) VALUES (?,?,?,?,?)";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, grupo.getNombre());
			miStatement.setInt(2, grupo.getCreacion());
			miStatement.setString(3, grupo.getOrigen());
			miStatement.setString(4, grupo.getGenero());
			miStatement.setInt(5, grupo.getIdDiscografica());
			miStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al realizar insert en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
	
	/**
	 * metodo encargado de realizar el update del grupo que recibe por parametro
	 * @param grupo
	 * @throws Exception
	 */
	public void actualizarGrupo(Grupo grupo) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "UPDATE grupos SET nombre = ?, creacion = ?, origen = ?, genero = ?, discograficaIdActual = ? WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, grupo.getNombre());
			miStatement.setInt(2, grupo.getCreacion());
			miStatement.setString(3, grupo.getOrigen());
			miStatement.setString(4, grupo.getGenero());
			miStatement.setInt(5, grupo.getIdDiscografica());
			miStatement.setInt(6, grupo.getId());
			miStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error al realizar update en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
	
	/**
	 * metodo encargado de realizar el delete del grupo que recibe por parametro
	 * @param grupo
	 * @throws Exception
	 */
	public void eliminarGrupo(Grupo grupo) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "DELETE FROM grupos WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setInt(1, grupo.getId());
			miStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al realizar delete en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
}
