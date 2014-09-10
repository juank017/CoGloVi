package co.edu.eafit.coglovi.transversal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoGloViDate {

	public static int calcularEdad(Date fechaNacimiento) {
		int yearsDiff = 0;
		if (fechaNacimiento != null) {
			Calendar calendarFechaNacimiento = new GregorianCalendar();
			calendarFechaNacimiento.setTime(fechaNacimiento);
			Calendar calendarFechaActual = new GregorianCalendar();
			calendarFechaActual.setTime(new Date());
			yearsDiff = Math.abs(calendarFechaActual.get(Calendar.YEAR) - calendarFechaNacimiento.get(Calendar.YEAR));
			if (calendarFechaNacimiento.get(Calendar.MONTH) > calendarFechaActual.get(Calendar.MONTH)
					|| (calendarFechaNacimiento.get(Calendar.MONTH) == calendarFechaActual.get(Calendar.MONTH) && calendarFechaNacimiento
							.get(Calendar.DATE) > calendarFechaActual.get(Calendar.DATE))) {
				yearsDiff--;
			}
		}
		return yearsDiff;
	}

	/**
	 * Suma el numero determinado de dias a la fecha dada.
	 * 
	 * @param fecha
	 * @param numDias
	 * @return
	 */
	public static Date addDaysToDate(Date fecha, int numDias) {
		Calendar fechaDada = new GregorianCalendar();

		fechaDada.setTime(fecha);
		fechaDada.add(Calendar.DATE, numDias);

		return fechaDada.getTime();
	}

	/**
	 * Agrega la fecha exacta a una fecha normal ejemplo 12/08/2013 = 12/08/2013 11:58:24<br>
	 * Creado el 12/08/2013 a las 11:58:24 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fecha
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date addExactDateToDate(Date date, int hour, int minute, int second) {
		Calendar exactDate = new GregorianCalendar();

		exactDate.setTime(date);
		exactDate.set(Calendar.HOUR_OF_DAY, hour);
		exactDate.set(Calendar.MINUTE, minute);
		exactDate.set(Calendar.SECOND, second);

		return exactDate.getTime();
	}

	/**
	 * Suma el numero determinado de minutos a la fecha dada <br>
	 * Creado el 2/10/2013 a las 7:41:22 <br>
	 * 
	 * @param fecha
	 * @param numMinutos
	 * @return
	 */
	public static Date addMinToDate(Date fecha, int numMinutos) {
		Calendar fechaDada = new GregorianCalendar();

		fechaDada.setTime(fecha);
		fechaDada.add(Calendar.MINUTE, numMinutos);

		return fechaDada.getTime();
	}

	/**
	 * Suma el numero determinado de segundos a la fecha dada <br>
	 * Creado el 12/02/2014 a las 16:08:48 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fecha
	 * @param numMinutos
	 * @return
	 */
	public static Date addSecToDate(Date fecha, int numSegundos) {
		Calendar fechaDada = new GregorianCalendar();

		fechaDada.setTime(fecha);
		fechaDada.add(Calendar.SECOND, numSegundos);

		return fechaDada.getTime();
	}

	/**
	 * Suma el n�mero determinado de meses a la fecha dada <br>
	 * Creado el 6/08/2013 a las 10:44:43 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fecha
	 * @param numYears
	 * @return
	 */
	public static Date addMonthToDate(Date fecha, int numMonth) {
		Calendar fechaDada = new GregorianCalendar();

		fechaDada.setTime(fecha);
		fechaDada.add(Calendar.MONTH, numMonth);

		return fechaDada.getTime();
	}

	/**
	 * Suma el número determinado de años a la fecha dada. Creado el 17/04/2013 a las 19:35:26 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fecha
	 * @param numYears
	 * @return
	 */
	public static Date addYearsToDate(Date fecha, int numYears) {
		Calendar fechaDada = new GregorianCalendar();

		fechaDada.setTime(fecha);
		fechaDada.add(Calendar.YEAR, numYears);

		return fechaDada.getTime();
	}

	/**
	 * Metodo encargado de convertir un java.util.Calendar a un XMLGregorianCalendar.
	 * 
	 * @return xmlGregorianCalendar
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertCalendarToXMLGregorianCalendar(Calendar calendar) throws DatatypeConfigurationException {

		if (!Util.isNull(calendar)) {

			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(calendar.getTime());

			XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

			return xmlGregorianCalendar;
		} else {
			return null;
		}
	}

	/**
	 * Metodo encargado de convertir un java.util.date a un Calendar.
	 * 
	 * @return Calendar
	 * @throws DatatypeConfigurationException
	 */
	public static Calendar convertDateToCalendar(Date date) {

		if (!Util.isNull(date)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			return calendar;
		} else {
			return null;
		}
	}

	/**
	 * Metodo encargado de convertir un java.util.date a un GregorianCalendar.
	 * 
	 * @return GregorianCalendar
	 * @throws DatatypeConfigurationException
	 */
	public static GregorianCalendar convertDateToGregorianCalendar(Date date) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);

		return gregorianCalendar;
	}

	/**
	 * Metodo encargado de convertir un java.util.date a un XMLGregorianCalendar.
	 * 
	 * @return xmlGregorianCalendar
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {

		if (!Util.isNull(date)) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);

			XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

			return xmlGregorianCalendar;
		} else {
			return null;
		}
	}

	/**
	 * Metodo encargado de convertir un java.util.date a un XMLGregorianCalendar.
	 * 
	 * @return xmlGregorianCalendar
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertGregorianCalendarToXMLGregorianCalendar(GregorianCalendar gregorianCalendar)
			throws DatatypeConfigurationException {

		if (!Util.isNull(gregorianCalendar)) {

			XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

			return xmlGregorianCalendar;
		} else {
			return null;
		}
	}

	/**
	 * Metodo encargado de convertir un javax.xml.datatype.XMLGregorianCalendar a un java.util.date <br>
	 * Creado el 21/04/2013 a las 19:15:17 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param calendar
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar) throws DatatypeConfigurationException {

		if (!Util.isNull(xmlGregorianCalendar)) {
			return xmlGregorianCalendar.toGregorianCalendar().getTime();
		} else {
			return null;
		}
	}

	/**
	 * Metodo encargado de convertir un javax.xml.datatype.XMLGregorianCalendar a un GregorianCalendar.
	 * 
	 * @return Calendar
	 * @throws DatatypeConfigurationException
	 */
	public static GregorianCalendar convertXMLGregorianCalendarToGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {

		if (!Util.isNull(xmlGregorianCalendar)) {
			return xmlGregorianCalendar.toGregorianCalendar();
		} else {
			return null;
		}

	}

	/**
	 * Da formato a una fecha Date <br>
	 * Creado el 21/05/2013 a las 19:03:17 <br>
	 * 
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static int getDiferenciaEnAnos(Date fechaInicial, Date fechaFinal) {
		int diferenciaAnos = getDiferenciaEnMeses(fechaInicial, fechaFinal) / 12;
		return diferenciaAnos;
	}

	public static int getDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static long getDiferenciaEnHoras(Date fechaInicial, Date fechaFinal) {
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double horas = Math.floor(diferencia / (1000 * 60 * 60));
		return ((long) horas);
	}

	public static int getDiferenciaEnMeses(Date fechaInicial, Date fechaFinal) {
		Calendar calendarFechaInicial = new GregorianCalendar();
		Calendar calendarFechaFinal = new GregorianCalendar();
		calendarFechaInicial.setTime(fechaInicial);
		calendarFechaFinal.setTime(fechaFinal);
		int mothDiff = 0;
		int dayOfMothIni = calendarFechaInicial.get(Calendar.DAY_OF_MONTH);
		while (calendarFechaInicial.before(calendarFechaFinal)) {
			if ((calendarFechaInicial.get(Calendar.YEAR) == calendarFechaFinal.get(Calendar.YEAR) && calendarFechaInicial.get(Calendar.MONTH) == calendarFechaFinal
					.get(Calendar.MONTH))) {
				break;
			}
			calendarFechaInicial.add(Calendar.MONTH, 1);
			calendarFechaInicial.set(Calendar.DAY_OF_MONTH, (dayOfMothIni < 29 ? calendarFechaInicial.get(Calendar.DAY_OF_MONTH)
					: calendarFechaInicial.getActualMaximum(Calendar.DAY_OF_MONTH)));
			mothDiff++;
		}
		if (!Util.isEmptyNull(mothDiff) && (calendarFechaInicial.get(Calendar.DATE) > calendarFechaFinal.get(Calendar.DATE))) {
			mothDiff--;
		}
		return mothDiff;
	}

	public static int getDiferenciaEnMinutos(Date fechaInicial, Date fechaFinal) {
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double minutos = Math.floor(diferencia / (1000 * 60));
		return Math.abs(((int) minutos));
	}

	/**
	 * Retorna la fecha Actual Creado el 6/08/2013 a las 10:40:16 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @return
	 */
	public static Date getFechaActualUtil() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		return new Date(calendar.getTimeInMillis());
	}

	public static int getIndiceFecha(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		return Integer.parseInt(sdf.format(date));

	}

	public static Date getPrimerDiaDelMes(Date fecha) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH), cal.getMinimum(Calendar.HOUR_OF_DAY),
				cal.getMinimum(Calendar.MINUTE), cal.getMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getUltimoDiaDelMes(Date fecha) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), cal.getMaximum(Calendar.HOUR_OF_DAY),
				cal.getMaximum(Calendar.MINUTE), cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * Retorna true si la fecha es posterior a la fecha actual
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isAfterNewDate(Date date) {
		return (date.after(getFechaActualUtil()) ? true : false);
	}

	/**
	 * Retorna true si la fechaInicial es posterior a la fechaFinal
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFechaIniAfterFechaFin(Date fechaIncial, Date fechaFinal) {
		return (fechaIncial.after(fechaFinal) ? true : false);

	}

	/**
	 * Metodo que le pone la hora actual a la fecha hasta que desea consultar</br> Creado el 9/10/2012 a las 11:22:08 </br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fechaFin
	 * @return
	 */
	public static Date setFechaFin(Date fechaFin) {
		GregorianCalendar calendario2 = new GregorianCalendar();
		calendario2.setTime(fechaFin);
		calendario2.set(GregorianCalendar.HOUR_OF_DAY, 23);
		calendario2.set(GregorianCalendar.MINUTE, 59);
		calendario2.set(GregorianCalendar.SECOND, 59);
		calendario2.set(GregorianCalendar.MILLISECOND, 999);
		return calendario2.getTime();

	}

	/**
	 * Metodo que le pone la hora actual a la fecha hasta que desea consultar</br> Creado el 9/10/2012 a las 11:22:08 </br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param fechaFin
	 * @return
	 */
	public static Date setFechaIni(Date fechaIni) {
		GregorianCalendar calendario2 = new GregorianCalendar();
		calendario2.setTime(fechaIni);
		calendario2.set(GregorianCalendar.HOUR_OF_DAY, 00);
		calendario2.set(GregorianCalendar.MINUTE, 00);
		calendario2.set(GregorianCalendar.SECOND, 00);
		calendario2.set(GregorianCalendar.MILLISECOND, 00);
		return calendario2.getTime();

	}

}