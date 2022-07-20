package mlconsulta;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import mlconsulta.MLSitio.IDSitio;


public class MLBuscar {

	public class ListaArticulo extends ArrayList<Articulo> {
		private static final long serialVersionUID = 1L;
	}

	private ListaArticulo articulos;
	private String[] palabrasClave;
	private String sitio;

	public MLBuscar() {
		articulos = new ListaArticulo();
	}

	public void setSitio(IDSitio id) {
		this.sitio = id.name();
	}

	public void setPalabrasClave(String[] palabrasClave) {
		for (int c = 0; c < palabrasClave.length; c++) {
			palabrasClave[c] = palabrasClave[c].toLowerCase();
		}
		this.palabrasClave = palabrasClave;
	}

	public ListaArticulo getArticulos() {
		return articulos;
	}

	public void BuscarProducto() throws Exception {
		String resultado = ConsultaURL.consultar(this.construirURLStr(0));
		JSONObject jsonObj = new JSONObject(resultado);
		int limit = jsonObj.getJSONObject("paging").getInt("limit");
		int total = jsonObj.getJSONObject("paging").getInt("total");

		this.articulos.clear();
		this.cargarRegistros(jsonObj.getJSONArray("results"));
		for (int offset = limit; offset < total; offset += limit) {
			resultado = ConsultaURL.consultar(this.construirURLStr(offset));
			jsonObj = new JSONObject(resultado);
			this.cargarRegistros(jsonObj.getJSONArray("results"));
		}
	}

	private void cargarRegistros(JSONArray jsonArr) throws Exception {
		for (int c = 0; c < jsonArr.length(); c++) {
			String title = jsonArr.getJSONObject(c).get("title").toString().toLowerCase();
			/* Chequea que cada palabra clave esté contenido en el título del árticulo */
			boolean coincide = true;
			for (String pc : this.palabrasClave) {
				if (!title.contains(pc)) {
					coincide = false;
				}
			}
			if (coincide) {
				Articulo articlo = new Articulo();
				articlo.id = jsonArr.getJSONObject(c).get("id").toString();
				articlo.title = title;
				articlo.permalink = jsonArr.getJSONObject(c).get("permalink").toString();
				articulos.add(articlo);
			}
		}
	}

	private String construirURLStr(int offset) throws Exception {
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

}