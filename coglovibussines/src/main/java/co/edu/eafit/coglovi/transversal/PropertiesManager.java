package co.edu.eafit.coglovi.transversal;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesManager{
	private static Map<String, ResourceBundle> table = new Hashtable<String, ResourceBundle>();

	public static String obtenerCadena(String nombreArchivo, String nombreClave) {
		// Intento obtener el archivo del hashtable
		ResourceBundle bundle = (ResourceBundle) table.get(nombreArchivo);

		// Si no logró obtener el archivo de la tabla, entonces lo cargo
		bundle = ResourceBundle.getBundle(nombreArchivo);

		// Retorno el string asociado a la cadena especificada
		return bundle.getString(nombreClave);
	}
	
	public static String getTextArgs(String key, Object[] args) {
		String message;
		try {
			message = getBundle().getString(key);
			for (int i = 0; i < args.length; i++) {
				message = message.replace("{" + i + "}", args[i].toString());
			}
		} catch (java.util.MissingResourceException mre) {
			return "???" + key + "???";
		}
		return message;
	}
	
	public static ResourceBundle getBundle() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return ResourceBundle.getBundle("i18n/messages", new Locale("es_CO"), classLoader);
	}
	
	public static String getText(String key) {
		String message;
		try {
			message = getBundle().getString(key);
		} catch (java.util.MissingResourceException mre) {
			return "???" + key + "???";
		}
		return message;
	}
	/**
	 * Obtiene una propiedad de un archivo almacenado en disco
	 * 
	 * @param urlFile
	 * @param nombreClave
	 * @return
	 */
	public static String obtenerCadenaExterna(String urlFile, String nombreClave) {
		String propertie = "";
		// Intento obtener el archivo del hashtable
		ResourceBundle bundle = (ResourceBundle) table.get(urlFile);
		// Si no logró obtener el archivo de la tabla, entonces lo cargo
		try {
			if (bundle == null) {

				FileInputStream fis = new FileInputStream(urlFile);
				try {
					bundle = new PropertyResourceBundle(fis);
				} finally {
					fis.close();
				}
				table.put(urlFile, bundle);
			}
			// Retorno el string asociado a la cadena especificada
			if (bundle.containsKey(nombreClave)) {
				propertie = bundle.getString(nombreClave);
			}else{
				propertie="Propiedad " + nombreClave + " no encontrada en "+urlFile;
			}
		} catch (Exception e) {
			return "???" + nombreClave + "???";
		}
		return propertie;
	}



}

