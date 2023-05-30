package es.udc.ws.app.client.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;

import es.udc.ws.app.client.service.ClientEventoService;
import es.udc.ws.app.client.service.ClientEventoServiceFactory;
import es.udc.ws.app.dto.RespuestaDto;
import es.udc.ws.app.dto.EventoDto;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class EventoServiceClient {

    public static void main(String[] args) {

        if(args.length == 0) {
        	 System.out.println("Peta aqui what?");
            printUsageAndExit();
        }
        ClientEventoService clientEventoService =
                ClientEventoServiceFactory.getService();
        if("-a".equalsIgnoreCase(args[0])) {
        	System.out.println("llega hasta aqui");
            validateArgs(args, 6, new int[] {4, 5});
            
            // [add] EventoServiceClient -a <nombre> <description> <fechaInicio>
            // <duracion> <aforo>

            try {
            	Calendar cal = Calendar.getInstance();
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
            	cal.setTime(sdf.parse(args[3]));
                Long IdEvento = clientEventoService.addEvento(new EventoDto(args[1], args[2],
                		cal, Integer.valueOf(args[4]), Integer.valueOf(args[5])));

                System.out.println("evento " + IdEvento + " created sucessfully");

            } catch (NumberFormatException | InputValidationException ex) {
                ex.printStackTrace(System.err);
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }

	       }else if ("-f".equalsIgnoreCase(args[0])) {
	        validateArgs(args, 2, new int[] { 1 });
	
	        // [find] EventoServiceClient -f <idEvento>
	
	        try {
	            EventoDto eventoDto = clientEventoService.findEvento(Long
	                    .parseLong(args[1]));
	            System.out.println(" nombre evento: " + eventoDto.getNombre());
	        } catch (NumberFormatException ex) {
	            ex.printStackTrace(System.err);
	        } catch (Exception ex) {
	            ex.printStackTrace(System.err);
	        }
	
	    }else if ("-r".equalsIgnoreCase(args[0])){
	    	// [remove] EventoServiceClient -r <idEvento>
	    	try {
	    		
	    		clientEventoService.removeEvento(Long.parseLong(args[1]));
	    		System.out.println("Id: " + Long.parseLong(args[1]) + "Event removed" );
	    	}catch (NumberFormatException ex){
	    		ex.printStackTrace(System.err);
	    	}catch (Exception ex) {
	    		ex.printStackTrace(System.err);
	    	}
	    }else if ("-u".equalsIgnoreCase(args[0])){
//	    	System.out.println("args 0 " + args[0] + " el 1 " + args[1] + " el 2 " + args[2] + " el 3 " + args[3]
//	    			 + " el 4 " + args[4] + " el 5 " + args[5] );
	        validateArgs(args, 7, new int[] {1, 5, 6});
	
	        // [update] EventoServiceClient -u <idEvento> <nombre> <descripcion> <fechaInicio>
	        // <duracion> <aforo>
	        
	        Calendar cal = Calendar.getInstance();
	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
	        	cal.setTime(sdf.parse(args[4]));
				
			}catch (ParseException e) { 
				System.out.println("cacha unha excepcion");
				printUsageAndExit();
			}
	
	        try {
	            clientEventoService.updateEvento(new EventoDto(Long.valueOf(args[1]),
	                    args[2], args[3], cal, Integer.valueOf(args[5]),
	                    Integer.parseInt(args[6])));
	            
	
	            System.out.println("Evento " + args[1] + " "
	                    + "updated sucessfully");
	
	        }catch (NumberFormatException | InputValidationException ex) {
	            ex.printStackTrace(System.err);
	        }catch (Exception ex) {
	            ex.printStackTrace(System.err);
	        }
	    	}else if("-p".equalsIgnoreCase(args[0])){
	        	// [responder] EventoServiceClient -p <IdUsuario> <IdEvento> <respuesta>
	        	
	        	validateArgs(args, 4, new int[] {1, 2});
            	Calendar cal = Calendar.getInstance();
	            try {
	                Long IdRespuesta = clientEventoService.responder(new RespuestaDto(
	                        Long.parseLong(args[2]), Boolean.parseBoolean(args[3]),cal ,Long.parseLong(args[1])));
	                

	                System.out.println("Response " + IdRespuesta + " "
	                        + "created sucessfully");
	                
	            } catch (NumberFormatException | InputValidationException ex) {
	                ex.printStackTrace(System.err);
	            } catch (Exception ex) {
	                ex.printStackTrace(System.err);
	            }
	        } else if("-fr".equalsIgnoreCase(args[0])){
	        	validateArgs(args, args.length, new int[] { 1 });

	            // [findresponses] EventServiceClient -fr <IdUsuario> <IdEvento> <respuesta>

	            try {
	                List<RespuestaDto> respuestas = null;

//	                if(args.length == 4){
//	                	respuestas = clientEventoService.findResponses(Long.parseLong(args[1]), Long.parseLong(args[2]),
//	                			Boolean.parseBoolean(args[3]));
//	                }
	                if(args.length == 3){
	                	respuestas = clientEventoService.findResponses(null, Long.parseLong(args[1]),
	                			Boolean.parseBoolean(args[2]));
	                }
	                //2 parametros para buscar por usuario
	                if(args.length == 2){
	                	respuestas = clientEventoService.findResponses( Long.parseLong(args[1]), null,
	                			null);
	                }
	                if(args.length == 1){
	                	respuestas = clientEventoService.findResponses(null,Long.parseLong(args[1]), null);
	                }
	                if((args.length != 1) && (args.length != 2) && (args.length != 3)){
	                	System.out.println("peta o meter moir argumentos");
	                	printUsageAndExit();
	                }
	                
	                for (int i = 0; i < respuestas.size(); i++) {
	                        RespuestaDto respuesta = respuestas.get(i);
	                        System.out.println("Id: " + respuesta.getIdRespuesta() +
	                                "\nidEvento: " + respuesta.getIdEvento() +
	                                "\nUsuario: " + respuesta.getIdUsuario() +
	                                "\nRespuesta: " + respuesta.getRespuesta());
	                }
	                
	            } catch (NumberFormatException ex) {
	                ex.printStackTrace(System.err);
	            } catch (Exception ex) {
	                ex.printStackTrace(System.err);
	            }
	        } else if("-fe".equalsIgnoreCase(args[0])) {
	            // [findEvents] EventServiceClient -fe <keywords> <fechaInicio> <fechaFin>
	        	
	        	List<EventoDto> eventos = null;
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        	
	        	if(args.length == 4) {
	        		Calendar f1 = new GregorianCalendar();
	            	try {
						f1.setTime(sdf.parse(args[2]));
					} catch (ParseException e) {
						e.printStackTrace();
					}

	            	Calendar f2 = new GregorianCalendar();
	            	try {
						f2.setTime(sdf.parse(args[3]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
	            	
	            	try {
						eventos = clientEventoService.findEventos(args[1], f1, f2);
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}
	        	} else if(args.length == 2) {
	        		try {
						eventos = clientEventoService.findEventos(args[1], null, null);
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}
	        	} else{
	        		System.out.println("peta al final");
	        		printUsageAndExit();
	        	}
	        	if(eventos != null)
	        	  for (int i = 0; i < eventos.size(); i++) {
	                  EventoDto evento = eventos.get(i);
	                  System.out.println("Id: " + evento.getIdEvento() + 
	                          "\nNombre: " + evento.getNombre() + 
	                          "\nDescripcion: " + evento.getDescripcion() + 
	                          "\nFechaInicio: " + sdf.format(evento.getFechaInicio().getTime()) +
	                          "\nDuracion: " + evento.getDuracion() +
	                          "\nAforo: " + evento.getAforo());
	              }
	    }
    }       

    public static void validateArgs(String[] args, int expectedArgs,
                                    int[] numericArguments) {
        if(expectedArgs != args.length) {
        	System.out.println("peta al validar");
            printUsageAndExit();  
        }
        for(int i = 0 ; i< numericArguments.length ; i++) {
            int position = numericArguments[i];
            try {
                Double.parseDouble(args[position]);
            } catch(NumberFormatException n) {
            	System.out.println("peta al final final");
                printUsageAndExit();     
            }
        }
    }

    public static void printUsageAndExit() {
        printUsage();
        System.exit(-1);
    }

    public static void printUsage() {
        System.err.println("Usage:\n" +
          	"    [add]   	 	EventoServiceClient -a <nombre> <description> <fechaInicio> <duracion> <Aforo> <>\n" +
            "    [remove] 		EventoServiceClient -r <idEvento>\n" +
            "    [update] 		EventoServiceClient -u <idEvento> <nombre> <description> <fechaInicio> <duracion> <Aforo>\n" +
            "    [find]   		EventoServiceClient -f <idEvento>\n" +
            "	 [findeventos]  EventoServiceClient -fe <keywords> <fechaInicio> <fechaFin>" +
            "    [response]    	EventoServiceClient -p <IdUsuario> <IdEvento> <respuesta\n" +
            "    [findresponses]EventoServiceClient -fr <IdUsuario> <IdEvento> <respuesta>\n");
    }
}
