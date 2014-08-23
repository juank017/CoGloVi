package co.edu.eafit.coglovi.gestionapp.model;

public class Constantes {
	public static class Activo {
		public static final String SI = "S";
		public static final String NO = "N";
	}

	public static class Alertas {
		public static class TipoDirectorioAlerta {
			public static final int BANDEJA_ENTRADA = 1;
			public static final int ELIMINADO = 4;
			public static final int GUARDADO = 3;
			public static final int LEIDO = 2;
		}

		public static class TipoEstadoAlerta {
			public static final int PENDIENTE_ENVIO = 1;
			public static final int ENVIADA = 2;
			public static final int ENTREGADA = 3;
			public static final int SIN_ENTREGAR = 4;
		}

		public static class TipoRespuesta {
			public static final int SI = 100;
			public static final int NO = 200;
			public static final int MEDIANAMENTE = 300;
		}

		public static class TipoMotivoResultado {
			public static final int USUARIO_NO_REGISTRADO = 1;
			public static final int ERROR = 2;
		}
	}

	public static class ClaveMensaje {

		/* Claves de mensajes para las vistas */
		public static final String COMMON_VALIDATE_ERRORCAMPOSNULL = "common.validate.errorCamposNull";

		/* Claves de mensajes para el proceso de validacion de estructura de carga de archivos. */
		public static final String GESTIONDOCUMENTAL_ERROR_TIPODATO_M413 = "gestiondocumental.error.tipoDato.M413";
		public static final String GESTIONDOCUMENTAL_ERROR_LONGITUDNOVALIDA_M414 = "gestiondocumental.error.longitudNoValida.M414";
		public static final String GESTIONDOCUMENTAL_ERROR_TABLACAMPONOEXISTE_M415 = "gestiondocumental.error.tablaCampoNoExiste.M415";
		public static final String GESTIONDOCUMENTAL_ERROR_CAMPOSINSUFICIENTES_M416 = "gestiondocumental.error.camposInsuficientes.M416";
		public static final String GESTIONDOCUMENTAL_ERROR_CAMPOOBLIGATORIO_M428 = "gestiondocumental.error.campoObligatorio.M428";

		/* Claves de mensajes para el proceso de validacion de reglas de negocio de procesos de carga de archivos. */
		public static final String GESTIONDOCUMENTAL_ERROR_PERSONANOEXISTE_M383 = "validacionProcesosCarga.error.M383";
		public static final String GESTIONDOCUMENTAL_ERROR_PERSONANOEXISTE_M478 = "validacionProcesosCarga.error.M478";
		public static final String GESTIONDOCUMENTAL_ERROR_ESTADO_NO_VALIDO_M515 = "validacionProcesosCarga.error.M515";
		public static final String GESTIONDOCUMENTAL_ERROR_ESTADO_NO_VALIDO_M522 = "validacionProcesosCarga.error.M522";
		public static final String GESTIONDOCUMENTAL_ERROR_ESTADO_NO_VALIDO_M523 = "validacionProcesosCarga.error.M523";
		public static final String GESTIONDOCUMENTAL_ERROR_ESTADO_NO_VALIDO_M527 = "validacionProcesosCarga.error.M527";

		/* Claves de mensajes para el proceso de validacion de consumo en WebService. */
		public static final String WEBSERVICE_RESULTADO_EXITOSO_M349 = "common.m349.procesoExitoso";
		public static final String WEBSERVICE_EXITOSO_SIN_RESULTADO_M540 = "common.service.respuesta.M540";
		public static final String WEBSERVICE_ERROR_COMUNICACION_M8 = "common.comunicacion.error";
		public static final String WEBSERVICE_ERROR_DATOS_M539 = "common.service.validacion.M539";
		public static final String WEBSERVICE_ERROR_AUTENTICACION_M480 = "common.service.autenticacion.M480";
	}

	public static class CodigoEstadoComucacion {
		/** Constante cuando el resultado es exitoso desde el WS. */
		public static final int RESULTADO_EXITOSO = 1;
		/** Constante cuando el proceso es exitoso pero no genera resultados desde el WS. */
		public static final int EXITOSO_SIN_RESULTADO = 2;
		/** Constante cuando sucede un error en la la ejecucion del caso de uso desde el WS. */
		public static final int ERROR_COMUNICACION = 3; // No importa
		/** Constante cuando sucede un error en la la ejecucion del caso de uso, por datos desde el WS. */
		public static final int ERROR_DATOS = 4;
		/** Constante cuando sucede un error de autenticacion. */
		public static final int ERROR_AUTENTICACION = 5; // Para la parte de seguridad
		/** Constante cuando sucede un error tecnico */
		public static final int ERROR_TECNICO = 6; // Excepcion por cualquier cosa
	}

	public static class ExpresionesRegulares {

		/* Esta expresi�n regular valida que la cadena ingresada contenga solo caracteres alfanum�ricos, sin saltos de linea. */
		public static final String RE_ALFANUMERICOS_CEROMUCHOS = "^(\\w)*$";
		/* Esta expresi�n regular valida que la cadena ingresada contenga solo caracteres num�ricos, sin saltos de linea. */
		public static final String RE_NUMERICOS_CEROMUCHOS = "^([0-9])*$";

		/*
		 * Esta expresi�n regular valida que la cadena ingresada contenga solo caracteres alfanum�ricos, sin saltos de linea, adem�s debe
		 * contener como m�nimo 1 caracter.
		 */
		public static final String RE_ALFANUMERICOS_UNOMUCHOS = "^(\\w)+$";
		/*
		 * Esta expresi�n regular valida que la cadena ingresada contenga solo caracteres num�ricos, sin saltos de linea, adem�s debe contener
		 * como m�nimo 1 caracter.
		 */
		public static final String RE_NUMERICOS_UNOMUCHOS = "^([0-9])+$";
	}

	public static class FiltroBusqueda {
		public static final int CRITERIO_PLACA = 1;
		public static final int CRITERIO_IDENTIFICACION = 2;
		public static final int CRITERIO_PERSONAS_REGISTRADAS = 3;
		public static final int CRITERIO_ESTADISTICAS_ALERTAS_ENVIADAS = 4;
		public static final int CRITERIO_VEHICULOS_REGISTRADOS_MES = 5;
		public static final int CRITERIO_PLACAS_MAYOR_COMPARENDOS_MES = 6;
		public static final int CRITERIO_RESULTADO_ENCUESTA_EXPERIENCIA_USUARIO = 7;
		public static final int CRITERIO_PREGUNTAS_FRECUENTES = 8;
		public static final int CRITERIO_TERMINOS_CONDICIONES = 9;
		public static final int CRITERIO_MANUAL_USUARIO = 10;
		public static final int CRITERIO_ENCUESTA_EXPERIENCIA_USUARIO = 11;

	}

	public class TipoDocumentoIdentidad {

		public static final int NN = 1;
		public static final int CEDULA = 2;
		public static final int NIT = 3;
		public static final int CEDULA_EXTRANJERIA = 4;
		public static final int PASAPORTE = 6;

	}
	
	public class TipoClasePregunta {

		public static final int PROCESO = 1;
		public static final int APLICATIVO = 2;
	}

	public static class TipoParametro {
		public static final int VALOR_APLICATION_ID_PARSE = 296;
		public static final int VALOR_REST_API_KEY_PARSE = 297;
		public static final int VALOR_PUSH_URL_PARSE = 298;
		public static final int DIAS_EXPIRA_ALERTA = 299;
		public static final int TIEMPO_EJECUCIONES_PROCESO_ENVIO_ALERTAS = 312;

		public static final int CUENTA_ORIGEN_CORREO_ELECTRONICO_APP_FOTODETECCION = 307;
		public static final int CONTRASENA_CUENTA_CORREO_ELECTRONICO_APP_FOTODETECCION = 308;
		public static final int CORREO_ELECTRONICO_HOST_APP_FOTODETECCION = 309;
		public static final int CORREO_ELECTRONICO_PORT_APP_FOTODETECCION = 310;
		public static final int MENSAJE_CORREO_ELECTRONICO_REGISTRARSE = 313;
		public static final int MENSAJE_CORREO_ELECTRONICO_RECUPERAR_CLAVE = 314;
		public static final int IMAGEN_CAMPANHA_CORREO_ELECTRONICO = 315;
		public static final int NOMBRE_APLICATIVO_APP_FOTODETECCION = 316;
		public static final int MAYOR_NRO_COMPARENDOS_MES_FOTODETECCION = 321;
		public static final int URL_MANUAL_APP_FOTODETECCION = 328;
	}

	public static class ConfiguracionContenido {
		public static class TipoOpcion {
			public static final int PREGUNTAS_FRECUENTES = 1;
		}

	}
}
