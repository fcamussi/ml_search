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

	
	
	public MLBuscar() {
		registros = new ArrayList<Registro>();
	}
	
	public void setPalabrasClave(String [] palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	
	public void ConsultarProducto() throws Exception {
		
		String mlurl = "https://api.mercadolibre.com/sites/MLA/search?q=";
		String producto = "";
		for (String pc: palabrasClave ) {
			producto = producto + " " + pc;
		}
		producto = URLEncoder.encode(producto, "UTF-8");
		String url_str = mlurl + producto;

		String resultado = ConsultarAPI(url_str);
		
		JSONObject obj = new JSONObject(resultado);
        //String pageName = obj.getJSONObject("results").getString("results");
//		JSONArray arr = obj.getJSONArray("results");
//		int cantidad = arr.length();
		System.out.print(obj.getJSONObject("paging").get("limit").toString());

	}
	
	private String ConsultarAPI(String url_str) throws Exception {
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
