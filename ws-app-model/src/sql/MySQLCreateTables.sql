-- ----------------------------------------------------------------------------
-- Model
-------------------------------------------------------------------------------

DROP TABLE Respuesta;
DROP TABLE Evento;

-- --------------------------------- Evento ------------------------------------
CREATE TABLE Evento ( idEvento BIGINT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(255) COLLATE latin1_bin NOT NULL,
	descripcion VARCHAR(1024) COLLATE latin1_bin NOT NULL,
	fechaInicio TIMESTAMP DEFAULT 0 NOT NULL,
	fechaFin TIMESTAMP DEFAULT 0 NOT NULL,
	aforo INTEGER(10),
	numeroRegistrados INTEGER(10),
	CONSTRAINT EventoPK PRIMARY KEY(idEvento),
	CONSTRAINT validarFecha CHECK (fechaInicio <= fechaFin) );

CREATE INDEX EventIndexByEventId ON Evento (idEvento);

-- --------------------------------- Respuesta ------------------------------------
CREATE TABLE Respuesta ( idRespuesta BIGINT NOT NULL AUTO_INCREMENT,
	idEvento BIGINT NOT NULL,
	respuesta BOOLEAN NOT NULL,
	descripcion VARCHAR(1024) COLLATE latin1_bin NOT NULL,
	fechaRespuesta TIMESTAMP DEFAULT 0 NOT NULL,
	idUsuario BIGINT NOT NULL,
	CONSTRAINT RespuestaPK PRIMARY KEY (idRespuesta),
	CONSTRAINT RespuestaFK FOREIGN KEY (idEvento) REFERENCES Evento (idEvento) );

CREATE INDEX AnswerIndexByAnswerId ON Respuesta (idRespuesta);
