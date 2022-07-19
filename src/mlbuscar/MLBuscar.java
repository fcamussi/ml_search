package mlbuscar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class MLBuscar {
	
	private class Registro {
		public String id;
		public String title;
		public String permalink;
	}
	
	private String [] palabrasClave;
	private ArrayList<Registro> registros;
	private String sitio;

	
	
	public MLBuscar() {
		registros = new ArrayList<Registro>();
	}
	
	public void setPalabrasClave(String [] palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}
	
	public void ConsultarProducto() throws Exception {
		
		String resultado = ConsultarURL(this.getURLStr());
		
		JSONObject obj = new JSONObject(resultado);
        //String pageName = obj.getJSONObject("results").getString("results");
//		JSONArray arr = obj.getJSONArray("results");
//		int cantidad = arr.length();
		System.out.print(obj.getJSONObject("paging").get("limit").toString());

	}
	
	private String getURLStr() throws Exception {
		String url_str = "https://api.mercadolibre.com/sites/" + this.sitio + "/search?q=";
		String producto = "";
		for (String pc: this.palabrasClave) {
			producto = producto + " " + pc;
		}
		producto = URLEncoder.encode(producto, "UTF-8");
		url_str = url_str + producto;
		return url_str;
	}
	
	private String ConsultarURL(String url_str) throws Exception {
		URL url = new URL(url_str);
		URLConnection urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept", "");	
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String line;

		while ((line = bufferedReader.readLine()) != null)
		{
			stringBuffer.append(line);
		}
		
		return stringBuffer.toString();
	}

}
