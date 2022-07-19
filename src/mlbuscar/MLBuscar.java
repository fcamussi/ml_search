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
	
	public class ListaRegistro extends ArrayList<Registro> {
		private static final long serialVersionUID = 1L;	
	}

	private class Registro {
		public String id;
		public String title;
		public String permalink;
	}
	
	public ListaRegistro listaRegistro;
	private String [] palabrasClave;
	private String sitio;
	

	public MLBuscar() {
		listaRegistro = new ListaRegistro();
	}
	
	public void setPalabrasClave(String [] palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}
	
	public void ConsultarProducto() throws Exception {	
		String resultado = ConsultarURL(this.getURLStr(0));
		JSONObject jsonObj = new JSONObject(resultado);
		int limit = jsonObj.getJSONObject("paging").getInt("limit");
		int total = jsonObj.getJSONObject("paging").getInt("total");

		this.listaRegistro.clear();
		this.agregarRegistros(jsonObj.getJSONArray("results"));
		for (int offset = limit; offset < total; offset += limit) {
			resultado = ConsultarURL(this.getURLStr(offset));
			jsonObj = new JSONObject(resultado);
			this.agregarRegistros(jsonObj.getJSONArray("results"));
		}		
	}
	
	private void agregarRegistros(JSONArray jsonArr) throws Exception {
		for (int c = 0; c < jsonArr.length(); c++) {
			
			// cheackear que cada palabra clave esté en el título
			
			Registro registro = new Registro();
			registro.id = jsonArr.getJSONObject(c).get("id").toString();
			registro.title = jsonArr.getJSONObject(c).get("title").toString();
			registro.permalink = jsonArr.getJSONObject(c).get("permalink").toString();
			listaRegistro.add(registro);
			System.out.print(registro.permalink + "\n");
		}
		
	}

	private String getURLStr(int offset) throws Exception {
		String url_str = "https://api.mercadolibre.com/sites/" + this.sitio + "/search?q=";
		String producto = this.palabrasClave[0];
		for (int c = 1; c < this.palabrasClave.length; c++) {
			producto = producto + " " + this.palabrasClave[c];
		}
		producto = URLEncoder.encode(producto, "UTF-8");
		url_str = url_str + producto;
		url_str = url_str + "&offset=" + offset;
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
