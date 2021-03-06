package co.edu.eafit.coglovi.util.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Se encarga del envio de la alerta a parse para su posterior envio al usuario movil<br>
 * Creado el 4/07/2014 a las 11:49:16 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public class SendDataPush {

	private final Log logger = LogFactory.getLog(getClass());

	private String applicationID="p47hOCcKnwZZBwkDOwVPL9eIR8Zqiyh596sd1VKd";
	private String restApiKey="zL6YRd4r48KM0IpFmqTfb6HqSzNsaK1ig14Mytki";
	private String urlPush="https://api.parse.com/1";
	private String type;
	private String[] channel;
	private String alert;
	private String title;
	private String fechaExpira;

	/**
	 * Se encarga de enviar las notificaciones a parse para que este las envie a los moviles inscritos<br>
	 * Creado el 8/07/2014 a las 17:30:11 <br>
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean enviarPush() throws ClientProtocolException, IOException {

		JSONObject jo = new JSONObject();
		jo.put("channels", channel);
		jo.put("expiration_time", fechaExpira);
		Map<String, String> data = new HashMap<String, String>();
		data.put("alert", alert);
		data.put("title", title);
		// badge: (iOS only) the value indicated in the top right corner of the app icon. This can be set to a value or to Increment in order to
		// increment the current value by 1.
		data.put("badge", "Increment");
		jo.put("data", data);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = null;
		HttpPost httpost = new HttpPost(urlPush);
		httpost.addHeader("X-Parse-Application-Id", applicationID);
		httpost.addHeader("X-Parse-REST-API-Key", restApiKey);
		httpost.addHeader("Content-Type", "application/json");
		StringEntity reqEntity = new StringEntity(jo.toString());
		httpost.setEntity(reqEntity);
		response = httpclient.execute(httpost);
		entity = response.getEntity();
		if (entity != null) {
			responseString = EntityUtils.toString(response.getEntity());
		}

		try {
			return (new JSONObject(responseString)).getBoolean("result");
		} catch (JSONException e) {
			logger.error(e.getMessage() + (new JSONObject(responseString)).getString("error"), e);
		}
		return false;
	}

	public String getAlert() {
		return alert;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public String[] getChannel() {
		return channel;
	}

	public String getFechaExpira() {
		return fechaExpira;
	}

	public String getRestApiKey() {
		return restApiKey;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getUrlPush() {
		return urlPush;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public void setChannel(String[] channel) {
		this.channel = channel;
	}

	public void setFechaExpira(String fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public void setRestApiKey(String restApiKey) {
		this.restApiKey = restApiKey;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrlPush(String urlPush) {
		this.urlPush = urlPush;
	}
}