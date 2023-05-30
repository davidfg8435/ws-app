package es.udc.ws.app.model.respuesta;

import java.util.ArrayList;
import java.util.Calendar;

public class Respuesta {
	
	private long idRespuesta;
	private long idEvento;
	private Boolean respuesta;
	private String descripcion;
	private Calendar fechaRespuesta;
	private long idUsuario;
	
	public Respuesta(Long idEvento, Boolean respuesta, String descripcion,
			Calendar fechaRespuesta, Long idUsuario){
		this.idEvento = idEvento;
		this.respuesta = respuesta;
        this.descripcion = descripcion;
        this.fechaRespuesta = fechaRespuesta;
        if (fechaRespuesta != null) {
            this.fechaRespuesta.set(Calendar.MILLISECOND, 0);
        }
        this.idUsuario = idUsuario;
    }
	public Respuesta(Long idRespuesta, Long idEvento, Boolean respuesta,
			Calendar fechaRespuesta, Long idUsuario){
		this.idEvento = idEvento;
		this.respuesta = respuesta;
        this.fechaRespuesta = fechaRespuesta;
        if (fechaRespuesta != null) {
            this.fechaRespuesta.set(Calendar.MILLISECOND, 0);
        }
        this.idUsuario = idUsuario;
    }

    public Respuesta(Long idRespuesta, Long idEvento, Boolean respuesta, String descripcion,
			Calendar fechaRespuesta, Long idUsuario) {
        this(idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);
        this.idRespuesta = idRespuesta;
    }

	public long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public Boolean getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Calendar fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fechaRespuesta == null) ? 0 : fechaRespuesta.hashCode());
		result = prime * result + (int) (idEvento ^ (idEvento >>> 32));
		result = prime * result + (int) (idRespuesta ^ (idRespuesta >>> 32));
		result = prime * result + (int) (idUsuario ^ (idUsuario >>> 32));
		result = prime * result
				+ ((respuesta == null) ? 0 : respuesta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechaRespuesta == null) {
			if (other.fechaRespuesta != null)
				return false;
		} else if (!fechaRespuesta.equals(other.fechaRespuesta))
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (idRespuesta != other.idRespuesta)
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (respuesta == null) {
			if (other.respuesta != null)
				return false;
		} else if (!respuesta.equals(other.respuesta))
			return false;
		return true;
	}

}
