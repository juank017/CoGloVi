create database coglovi;
use coglovi;

DROP TABLE COGLOVI.USUARIO;

CREATE TABLE COGLOVI.Usuario
(
	IDUSUARIO MEDIUMINT NOT NULL AUTO_INCREMENT,
	NRODOCUMENTO VARCHAR(20) NOT NULL,
	NOMBRES VARCHAR(200) NOT NULL,
	APELLIDOS VARCHAR(200),
	CELULAR VARCHAR(20),
	CORREOELECTRONICO VARCHAR(200) NOT NULL,
	CLAVE VARCHAR(200) NOT NULL,
	FECHAREGISTRO DATE NOT NULL,
	FECHACAMBIOCLAVE DATE NOT NULL,
	PRIMARY KEY (IDUSUARIO)
) 
;

CREATE TABLE COGLOVI.GrupoInteres
(
	IDUSUARIO INT NOT NULL,
	GRUPOINTERES VARCHAR(200) 
	);
	
	
CREATE TABLE COGLOVI.Preguntas(
	IDPREGUNTA MEDIUMINT NOT NULL AUTO_INCREMENT,
	FECHAREGISTRO DATE NOT NULL,
	PREGUNTA VARCHAR(200),
	ESTADO INT,
	GRUPOINTERES VARCHAR(200),
	IDUSUARIO INT NOT NULL,
	PRIMARY KEY (IDPREGUNTA)
	);

INSERT INTO COGLOVI.PREGUNTAS(FECHAREGISTRO,PREGUNTA,ESTADO,GRUPOINTERES,IDUSUARIO) VALUES(?,?,?,?,?)

INSERT INTO COGLOVI.PREGUNTAS(FECHAREGISTRO,PREGUNTA,ESTADO,GRUPOINTERES,IDUSUARIO) VALUES(curdate(),'CUAL ES MI EDAD',1,'Z500',1);