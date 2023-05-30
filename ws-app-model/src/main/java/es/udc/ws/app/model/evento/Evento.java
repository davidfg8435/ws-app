package es.udc.ws.app.model.evento;

import java.util.Calendar;

public class Evento {
	
	private long idEvento;
	private String nombre;
	private String descripcion;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private Integer aforo;
	private Integer numeroRegistrados;
	
	public Evento(String nombre, String descripcion, Calendar fechaInicio,
			Calendar fechaFin, Integer aforo, Integer numeroRegistrados){
		this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        if (fechaInicio != null) {
            this.fechaInicio.set(Calendar.MILLISECOND, 0);
        }
        this.fechaFin = fechaFin;
        if (fechaFin != null) {
            this.fechaFin.set(Calendar.MILLISECOND, 0);
        }
        this.aforo = aforo;
        this.numeroRegistrados = numeroRegistrados;
    }

    public Evento(Long idEvento, String nombre, String descripcion, Calendar fechaInicio,
			Calendar fechaFin, Integer aforo, Integer numeroRegistrados) {
        this(nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);
        this.idEvento = idEvento;
    }

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
		if (fechaInicio != null) {
            this.fechaInicio.set(Calendar.MILLISECOND, 0);
        }
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
		if (fechaFin != null) {
            this.fechaFin.set(Calendar.MILLISECOND, 0);
        }
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public Integer getNumeroRegistrados() {
		return numeroRegistrados;
	}

	public void setNumeroRegistrados(Integer numeroRegistrados) {
		this.numeroRegistrados = numeroRegistrados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aforo == null) ? 0 : aforo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + (int) (idEvento ^ (idEvento >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime
				* result
				+ ((numeroRegistrados == null) ? 0 : numeroRegistrados
						.hashCode());
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
		Evento other = (Evento) obj;
		if (aforo == null) {
			if (other.aforo != null)
				return false;
		} else if (!aforo.equals(other.aforo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numeroRegistrados == null) {
			if (other.numeroRegistrados != null)
				return false;
		} else if (!numeroRegistrados.equals(other.numeroRegistrados))
			return false;
		return true;
	}
    
}
