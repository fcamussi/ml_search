package mlbuscar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;


public class MLBuscar {
	
	static void mostrar(String texto) throws Exception {
		
		String mlurl = "https://api.mercadolibre.com/sites/MLA/search?q=";
		String producto = "msx talent";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();

		producto = URLEncoder.encode(producto, "UTF-8");
		String url_str = mlurl + producto;
		URL url = new URL(url_str);
		urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept", "");
		
		bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			stringBuffer.append(line);
		}

		JSONObject obj = new JSONObject(stringBuffer.toString());
        //String pageName = obj.getJSONObject("results").getString("results");
//		JSONArray arr = obj.getJSONArray("results");
//		int cantidad = arr.length();
		System.out.print(obj.getJSONObject("paging").get("limit").toString());

	}

}
