package es.udc.ws.app.test.model;

import static es.udc.ws.app.model.util.ModelConstants.EVENTO_DATA_SOURCE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import es.udc.ws.app.model.evento.Evento;
import es.udc.ws.app.model.respuesta.Respuesta;
//import es.udc.ws.app.model.evento.Jdbc3CcSqlEventoDao;
import es.udc.ws.app.model.evento.SqlEventoDao;
import es.udc.ws.app.model.evento.SqlEventoDaoFactory;
import es.udc.ws.app.model.eventoservice.EventoService;
import es.udc.ws.app.model.eventoservice.EventoServiceFactory;
import es.udc.ws.app.model.eventoservice.EventoServiceImpl;
import es.udc.ws.util.sql.DataSourceLocator;
import es.udc.ws.util.sql.SimpleDataSource;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ServiceTest {
	
	//private static EventoService eventoService = null;

	
	/*EJEMPLO TESTS BUENOS, NO PROBADOS*/
//	
//	private static EventoService eventoService = null;
//	private static SqlEventoDao eventoDao = null;
//	private final long NON_EXISTENT_ID = -1;
//	
//	/**
//	 * Incializamos la instancia del servicio y del dao antes de las pruebas
//	 * empleando patrón factoría y también inicializamos el dataSource.
//	 */	
//	@BeforeClass
//	public static void init() {
//
//		/* Creamos un dataSource empleando una de las implementaciones de 
//		 * sql. La interfaz dataSource nos permite no tener ni que cargar
//		 * el driver JDBC, ni especificar la URL, el usuario y la contraseña 
//		 * para pediro una conexión. */
//		DataSource dataSource = new SimpleDataSource();
//
//		/* Siempre vamos a necesitar obtener una referencia a DataSource estemos
//		  * en un servidor de aplicaciones o en una prueba de integración, por lo
//		  * que vamos a emplear DataSourceLocator que nos mapea los objetos
//		  * dataSource empleados indexados por su nombre */
//
//		DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//
//		/* Empleamos el patrón "Factoría" para obtener referencias al Sercive y 
//		 * al DAO sin necesidad de conocer su clase de implemetación */
//		eventoService = EventoServiceFactory.getService();
//		eventoDao = SqlEventoDaoFactory.getDao();
//	}
//	
//	private Evento getValidEvento(String nombre) {		
//		Calendar initDate = Calendar.getInstance();
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.MINUTE, 30);
//		return new Evento(nombre, "Descripcion Evento", initDate,endDate, 50, 0);
//	}
//	
//	private Evento getValidEventoId(Long idEvento, String nombre) {		
//		Calendar initDate = Calendar.getInstance();
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.MINUTE, 30);
//		return new Evento(idEvento, nombre, "Descripcion Evento", initDate,endDate, 50, 0);
//	}
//	
//	
//	private Evento createEvento(Evento evento) {
//
//		Evento addedEvento = null;
//		try {
//			addedEvento = eventoService.addEvento(evento);
//		} catch (InputValidationException e) {
//			throw new RuntimeException(e);
//		}
//		return addedEvento;
//
//	}
//	private void removeEvento(Long eventoId) {
//
//		try {
//			eventoService.removeEvento(eventoId);
//		} catch (InstanceNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//
//	}
//	
//	/* TESTS */
//	
//	/**
//	 * Testeamos el añadir un evento válido y a la vez el buscar por id
//	 * @throws InputValidationException
//	 * @throws InstanceNotFoundException
//	 */
//	@Test
//	public void testAddAndFindEvento() throws InputValidationException,
//									InstanceNotFoundException {
//
//		Evento evento = getValidEvento("Evento testeo testAddAndFindEvento");
//		Evento addedEvento = null;
//
//		addedEvento = eventoService.addEvento(evento);		
//		Evento foundEvento = eventoService.findEvento(addedEvento.getIdEvento());
//		assertEquals(addedEvento, foundEvento);
//		
//		// Clear Database
//		//removeEvento(addedEvento.getIdEvento());
//	}
//	
//	@Test
//	public void testAddInvalidEvent() {
//
//		Evento evento = getValidEvento("Prueba evento inválido");
//		Evento addedEvento = null;
//		boolean exceptionCatched = false;
//
//		try {
//			// Que el nombre es null sería error
//			evento.setNombre(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//
//			//  Que el nombre esté vacío sería error
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 2");
//			evento.setNombre("");
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			// Que la descripción es null sería error
//			evento.setDescripcion(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//
//			//  Que la descripción esté vacío sería error
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 3");
//			evento.setDescripcion("");
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que la fecha de inicio no sea null
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 4");
//			evento.setFechaInicio(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//
//			//  Que la fecha de inicio no esté en el pasado			
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 5");
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MINUTE, -1); // 1 minuto menos
//			evento.setFechaInicio(calendar);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que la fecha de fin no sea null
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 6");
//			evento.setFechaFin(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que la fecha de fin no esté en el pasado			
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 7");
//			calendar = Calendar.getInstance();
//			calendar.add(Calendar.MINUTE, -1); // 1 minuto menos
//			evento.setFechaFin(calendar);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que la fecha de fin no sea igual o anterior a la fecha 
//			// de inicio
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 8");
//			calendar = Calendar.getInstance();
//			Calendar calendarEnd = Calendar.getInstance();
//			evento.setFechaInicio(calendar);
//			calendarEnd.add(Calendar.MINUTE, -1); // 1 minuto menos
//			evento.setFechaFin(calendarEnd);
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				System.out.println("Capturamos excepcion");
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que el aforo no sea nulo
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 9");
//			evento.setAforo(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que el aforo sea un número negativo
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 9");
//			evento.setAforo(-50);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que el num de registrados no sea nulo
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 9");
//			evento.setNumeroRegistrados(null);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//			//  Que el num de registrados no sea negativo
//			exceptionCatched = false;
//			evento = getValidEvento("Prueba evento inválido 9");
//			evento.setNumeroRegistrados(-50);
//			try {
//				addedEvento = eventoService.addEvento(evento);
//			} catch (InputValidationException e) {
//				exceptionCatched = true;
//			}
//			assertTrue(exceptionCatched);
//			
//		} finally {
//			if (!exceptionCatched) {
//				// Clear Database
//				//removeEvento(addedEvento.getIdEvento());
//			}
//		}
//
//	}
//	*/
//	@Test(expected = InstanceNotFoundException.class)
//	public void testFindNonExistentEvento() throws InstanceNotFoundException {
//
//		eventoService.findEvento(NON_EXISTENT_ID);
//
//	}
//	
//	@Test
//	public void testUpdateEvento() throws InputValidationException,
//			InstanceNotFoundException {
//
//		long aux = 2;
//		Long idEvento = new Long(aux);
//		Evento evento = createEvento(getValidEventoId(idEvento, "Testeamos update correcto"));
//		try {
//			
//			evento.setNombre("Evento actualizado");
//			evento.setAforo(45);
//			evento.setDescripcion("Evento actualizado durante test");
//
//			eventoService.updateEvento(evento);
//
//			Evento updatedEvento = eventoService.findEvento(evento.getIdEvento());
//			assertEquals(evento, updatedEvento);
//
//		} finally {
//			// Clear Database
//			//removeMovie(evento.getIdEvento());
//		}
//
//	}
//	
//	@Test(expected = InstanceNotFoundException.class)
//	public void testUpdateNonExistentMovie() throws InputValidationException,
//			InstanceNotFoundException {
//
//		Evento evento = getValidEvento("Testeamos update correcto");
//		evento.setIdEvento(NON_EXISTENT_ID);
//		eventoService.updateEvento(evento);
//
//	}
//
//	@Test(expected = InstanceNotFoundException.class)
//	public void testRemoveMovie() throws InstanceNotFoundException {
//
//		Evento evento = createEvento(getValidEvento("Testeamos remove correcto"));
//		boolean exceptionCatched = false;
//		try {
//			eventoService.removeEvento(evento.getIdEvento());
//		} catch (InstanceNotFoundException e) {
//			exceptionCatched = true;
//		}
//		assertTrue(!exceptionCatched);
//			// la excepción la lanza find
//		eventoService.findEvento(evento.getIdEvento());
//
//	}
//
//	@Test(expected = InstanceNotFoundException.class)
//	public void testRemoveNonExistentMovie() throws InstanceNotFoundException {		
//		eventoService.removeEvento(NON_EXISTENT_ID);
//	}

	/*@Test
	public void testFindMovies() {

		// Add movies
		List<Movie> movies = new LinkedList<Movie>();
		Movie movie1 = createMovie(getValidMovie("movie title 1"));
		movies.add(movie1);
		Movie movie2 = createMovie(getValidMovie("movie title 2"));
		movies.add(movie2);
		Movie movie3 = createMovie(getValidMovie("movie title 3"));
		movies.add(movie3);

		try {
			List<Movie> foundMovies = movieService.findMovies("moVie Title");
			assertEquals(movies, foundMovies);

			foundMovies = movieService.findMovies("Mo Title 2");
			assertEquals(1, foundMovies.size());
			assertEquals(movies.get(1), foundMovies.get(0));

			foundMovies = movieService.findMovies("title 5");
			assertEquals(0, foundMovies.size());
		} finally {
			// Clear Database
			for (Movie movie : movies) {
				removeMovie(movie.getMovieId());
			}
		}

	}*/

	/*FIN TESTS NO PROBADOS*/
	
//	@Test
//    public void createTest (){
//		
//		
//		String nombre = "prueba1";
//		String descripcion = "probando1";
//		Calendar fechaInicio = Calendar.getInstance();;
//		Calendar fechaFin = Calendar.getInstance();;
//		Integer aforo = 10;
//		Integer numeroRegistrados = 0;
//        
//        DataSource dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaFin.add(Calendar.DAY_OF_MONTH,1);
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = new EventoServiceImpl();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		nombre = "prueba2";
//		descripcion = "probando2";
//		fechaInicio = Calendar.getInstance();;
//		fechaFin = Calendar.getInstance();;
//		aforo = 50;
//		numeroRegistrados = 0;
//        
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaFin.add(Calendar.DAY_OF_MONTH,2);
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = EventoServiceFactory.getService();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		nombre = "prueba3";
//		descripcion = "probando3";
//		fechaInicio = Calendar.getInstance();;
//		fechaFin = Calendar.getInstance();;
//		aforo = 50;
//		numeroRegistrados = 0;
//        
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//        	fechaInicio.add(Calendar.DAY_OF_MONTH,2);
//            fechaFin.add(Calendar.DAY_OF_MONTH,5);
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = EventoServiceFactory.getService();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		nombre = "evento4";
//		descripcion = "probando4";
//		fechaInicio = Calendar.getInstance();;
//		fechaFin = Calendar.getInstance();;
//		aforo = 50;
//		numeroRegistrados = 0;
//        
//        dataSource = new SimpleDataSource();
//        fechaInicio.add(Calendar.DAY_OF_MONTH,6);
//        fechaFin.add(Calendar.DAY_OF_MONTH,8);
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaFin.add(Calendar.DAY_OF_MONTH,1);
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = EventoServiceFactory.getService();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		nombre = "evento5";
//		descripcion = "probando5";
//		fechaInicio = Calendar.getInstance();;
//		fechaFin = Calendar.getInstance();;
//		aforo = 50;
//		numeroRegistrados = 0;
//        dataSource = new SimpleDataSource();
//        fechaInicio.add(Calendar.DAY_OF_MONTH,4);
//        fechaFin.add(Calendar.DAY_OF_MONTH,7);
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = EventoServiceFactory.getService();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//
//		nombre = "evento55";
//		descripcion = "probando55";
//		fechaInicio = Calendar.getInstance();;
//		fechaFin = Calendar.getInstance();;
//		aforo = 800;
//		numeroRegistrados = 0;
//        
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//        	fechaInicio.add(Calendar.DAY_OF_MONTH,5);
//            fechaFin.add(Calendar.DAY_OF_MONTH,6);
//            Evento testEvent = new Evento (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
//            EventoService eventService = EventoServiceFactory.getService();
//            eventService.addEvento(testEvent);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//	}
	
//	@Test
//	public void find_update_remove_Test (){
//		long aux = 3;
//		Long idEvento = new Long(aux);
//		
//        DataSource dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            EventoService eventService = EventoServiceFactory.getService();
//            Evento testEvent = eventService.findEvento(idEvento);
//            Evento EventoAux = new Evento(idEvento,
//            		"prueba2Updated",testEvent.getDescripcion(),
//            		testEvent.getFechaInicio(),testEvent.getFechaFin(),testEvent.getAforo(),
//            		testEvent.getNumeroRegistrados());
//            eventService.updateEvento(EventoAux);
//
//            long aux2 = 1;
//            idEvento = new Long(aux2);
//            eventService.removeEvento(idEvento);
//            
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
        //findbytests
		
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            EventoService eventService = EventoServiceFactory.getService();;
//            List<Evento> testList = new ArrayList<Evento>();
//            Calendar fecha1 = Calendar.getInstance();
//            Calendar fecha2 = Calendar.getInstance();
//            fecha1.add(Calendar.DAY_OF_MONTH,3);
//            fecha2.add(Calendar.DAY_OF_MONTH,5);
//            testList = eventService.findEventos("evento", fecha1, fecha2);
//            System.out.println("keys size " + testList.size());
//            for (Evento event : testList){
//                Evento EventoAux = new Evento(event.getIdEvento(),
//                		event.getNombre(),"UpdatedByKeys",
//                		event.getFechaInicio(),event.getFechaFin(),event.getAforo(),
//                		event.getNumeroRegistrados());
//                eventService.updateEvento(EventoAux);
//            }
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//	}
	//TESTS DE RESPONSE
	
//	@Test
//    public void createTest2 (){
//		long aux = 3;
//		Long idEvento = new Long(aux);
//		Boolean respuesta = true;
//		String descripcion = "respuesta1";
//		Calendar fechaRespuesta = Calendar.getInstance();
//		Long idUsuario = new Long(1);
//		
//        DataSource dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            EventoService eventoService = new EventoServiceImpl();
//            eventoService.responder(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//		idEvento = new Long(1);
//		respuesta = true;
//		descripcion = "respuesta1";
//		fechaRespuesta = Calendar.getInstance();
//		idUsuario = new Long(1);
//		fechaRespuesta.add(Calendar.DAY_OF_MONTH,-1);
//		
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            EventoService eventoService = new EventoServiceImpl();
//            eventoService.responder(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		idEvento = new Long(1);
//		respuesta = false;
//		descripcion = "respuesta2";
//		fechaRespuesta = Calendar.getInstance();
//		idUsuario = new Long(2);
//		fechaRespuesta.add(Calendar.DAY_OF_MONTH,-1);
//		
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            RespuestaService respuestaService = new RespuestaServiceImpl();
//            respuestaService.addRespuesta(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		idEvento = new Long(2);
//		respuesta = true;
//		descripcion = "respuesta3";
//		fechaRespuesta = Calendar.getInstance();
//		idUsuario = new Long(3);
//		fechaRespuesta.add(Calendar.DAY_OF_MONTH,-1);
//		
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            RespuestaService respuestaService = new RespuestaServiceImpl();
//            respuestaService.addRespuesta(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		idEvento = new Long(2);
//		respuesta = true;
//		descripcion = "respuesta4";
//		fechaRespuesta = Calendar.getInstance();
//		idUsuario = new Long(1);
//		fechaRespuesta.add(Calendar.DAY_OF_MONTH,-1);
//		
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            RespuestaService respuestaService = new RespuestaServiceImpl();
//            respuestaService.addRespuesta(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
//        
//		idEvento = new Long(3);
//		respuesta = false;
//		descripcion = "respuesta5";
//		fechaRespuesta = Calendar.getInstance();
//		idUsuario = new Long(3);
//		fechaRespuesta.add(Calendar.DAY_OF_MONTH,-1);
//		
//        dataSource = new SimpleDataSource();
//        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
//        try (Connection c = dataSource.getConnection()) {
//            fechaRespuesta.add(Calendar.DAY_OF_MONTH,1);
//            Respuesta testRespuesta = new Respuesta (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
//            RespuestaService respuestaService = new RespuestaServiceImpl();
//            respuestaService.addRespuesta(testRespuesta);
//        }catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
	}
	
	/*public void find_update_remove_2 (){
		long aux = 2;
		Long idEvento = new Long(aux);
		
        DataSource dataSource = new SimpleDataSource();
        DataSourceLocator.addDataSource(EVENTO_DATA_SOURCE, dataSource);
        try (Connection c = dataSource.getConnection()) {
            EventoService eventService = EventoServiceFactory.getService();
            Evento testEvent = eventService.findEvento(idEvento);
            Evento EventoAux = new Evento(idEvento,
            		"prueba2Updated",testEvent.getDescripcion(),
            		testEvent.getFechaInicio(),testEvent.getFechaFin(),testEvent.getAforo(),
            		testEvent.getNumeroRegistrados());
            eventService.updateEvento(EventoAux);

            long aux2 = 1;
            idEvento = new Long(aux2);
            eventService.removeEvento(idEvento);
            
        }catch (Exception e) {
            e.printStackTrace(System.err);
        }
	}
	
	/*COSAS QUE FALTAN*/
	//no poder aceptar un evento con aforo completo
	//si un usuario responde varias veces tener en cuenta solo la ultima respuesta
	//cambiar los tests
//}
