package es.accenture.emisora;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Andrea Ravagli Castillo 
 * Servlet implementation class ServletEmisora
 */
public class ServletEmisora extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloGrupo modelo;
	
	@Resource(name="jdbc/emisora")
	private DataSource poolConexiones;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEmisora() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			modelo = new ModeloGrupo(poolConexiones);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/**
	 * metodo que procesa las peticiones get y post
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion == null) {
			accion = "cargar";
		}

		switch (accion) {
		case "cargar":
			obtenerGrupos(request, response);
			break;
		case "detalle":
			detalleGrupo(request, response);
			break;
		case "anadir":
			altaGrupo(request, response);
			break;
		case "alta":
			insertarGrupo(request, response);
			break;
		case "modificar":
			modificarGrupo(request, response);
			break;
		case "actualizar":
			actualizarGrupo(request, response);
			break;
		case "baja":
			bajaGrupo(request, response);
			break;
		default:
			obtenerGrupos(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * metodo encargado de obtener el listado de grupos para ponerlo en un atributo del request
	 * @param request
	 * @param response
	 */
	private void obtenerGrupos(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Grupo> listaGrupo  = modelo.getGrupos();
			request.setAttribute("gruposMusicales", listaGrupo);
			
			RequestDispatcher dispatcher =  request.getRequestDispatcher("GruposMusicales.jsp");
			dispatcher.forward(request , response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo encargado de obtener el detalle del grupo segun el id que viene en la peticion
	 * el detalle se pone en un atributo del request
	 * @param request
	 * @param response
	 */
	private void detalleGrupo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idGrupo"));
		Grupo grupo = null;
		try {
			grupo = modelo.getGrupo(id);
			request.setAttribute("detalleGrupo", grupo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("DetalleGrupo.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo encargado de redireccionar al jsp de alta de nuevo grupo
	 * @param request
	 * @param response
	 */
	private void altaGrupo(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("NuevoGrupo.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo encargado de recibir los datos enviados desde el formulario de alta
	 * y luego invocar el metodo de insertar en el modelo
	 * @param request
	 * @param response
	 */
	private void insertarGrupo(HttpServletRequest request, HttpServletResponse response) {
		String nombre = request.getParameter("nombre");
		String origen = request.getParameter("origen");
		int creacion = Integer.parseInt(request.getParameter("creacion"));
		String genero = request.getParameter("genero");
		int idDiscografica = Integer.parseInt(request.getParameter("discografica"));
		
		Grupo grupo = new Grupo();
		grupo.setNombre(nombre);
		grupo.setOrigen(origen);
		grupo.setCreacion(creacion);
		grupo.setGenero(genero);
		grupo.setIdDiscografica(idDiscografica);
		
		try {
			modelo.insertarGrupo(grupo);
			response.sendRedirect("ServletEmisora?accion=cargar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo encargado de redireccionar al jsp que muestra los datos del grupo
	 * que se va a actualizar
	 * @param request
	 * @param response
	 */
	private void modificarGrupo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idGrupo"));
		Grupo grupo = null;
		try {
			grupo = modelo.getGrupo(id);
			request.setAttribute("datosGrupo", grupo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ActualizarGrupo.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo encargado de recibir los datos enviados desde el formulario de actualizacion
	 * y luego invocar el metodo de actualizacion en el modelo
	 * @param request
	 * @param response
	 */
	private void actualizarGrupo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String origen = request.getParameter("origen");
		int creacion = Integer.parseInt(request.getParameter("creacion"));
		String genero = request.getParameter("genero");
		int idDiscografica = Integer.parseInt(request.getParameter("discografica"));
		
		
		Grupo grupo = new Grupo();
		grupo.setId(id);
		grupo.setNombre(nombre);
		grupo.setOrigen(origen);
		grupo.setCreacion(creacion);
		grupo.setGenero(genero);
		grupo.setIdDiscografica(idDiscografica);
		
		try {
			modelo.actualizarGrupo(grupo);
			response.sendRedirect("ServletEmisora?accion=cargar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * metodo encargado de pasar al modelo el grupo que se va a eliminar
	 * @param request
	 * @param response
	 */
	private void bajaGrupo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idGrupo"));
		Grupo grupo = null;
		try {
			grupo = modelo.getGrupo(id);
			modelo.eliminarGrupo(grupo);
			response.sendRedirect("ServletEmisora?accion=cargar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
