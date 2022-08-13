package mlsearcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para buscar artículos en Mercado Libre.
 *
 * @author Fernando Camussi
 */
public class MlSearcher {

    private final List<String> wordList;
    private final List<Item> itemList;
    private String siteId;
    private String state;
    private String agent;
    private int maxItemCount;

    /**
     * Constructor
     */
    public MlSearcher() {
        siteId = "";
        state = "";
        agent = "MLSearcher";
        maxItemCount = 1000;
        wordList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    /**
     * Setea el id del sitio donde se hará la búsqueda: MLA, MLB, etc.
     *
     * @param siteId String con el id del sitio.
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * Setea el estado/provincia
     *
     * @param state String con el nombre del estado/provincia.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Setea el agente HTTP
     *
     * @param agent String con el nombre del agente. Por defecto es "MlSearcher".
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * Setea la cantidad máxima de articulos que se obtienen en la búsqueda.
     * Dicha cantidad no tiene porque coincidir con la cantidad de articulos encontrados.
     *
     * @param maxItemCount cantidad máxima de articulos, de tipo int.
     *                     El valor por defecto es 1000 que es la cantidad máxima impuesta por ML.
     * @throws Exception Si maxItemCount es mayor a 1000.
     */
    public void setMaxItemCount(int maxItemCount) throws Exception {
        if (maxItemCount > 1000) {
            throw new Exception("maxItemCount no puede ser mayor a 1000");
        }
        this.maxItemCount = maxItemCount;
    }

    /**
     * Setea las palabras a buscar.
     *
     * @param wordList Una lista de String con las palabras.
     */
    public void setWords(List<String> wordList) {
        this.wordList.clear();
        for (String word : wordList) {
            this.wordList.add(word.toLowerCase());
        }
    }

    /**
     * Realiza la búsqueda.
     *
     * @throws Exception Si falla la búsqueda
     */
    public void searchItems() throws Exception {
        String resultado = resquestURL(buildURLStr(0));
        JSONObject jsonObj = new JSONObject(resultado);
        int limit = jsonObj.getJSONObject("paging").getInt("limit");
        int total = jsonObj.getJSONObject("paging").getInt("total");

        if (total > maxItemCount) {
            total = maxItemCount;
        }
        itemList.clear();
        addItems(jsonObj.getJSONArray("results"));
        for (int offset = limit; offset < total; offset += limit) {
            resultado = resquestURL(buildURLStr(offset));
            jsonObj = new JSONObject(resultado);
            addItems(jsonObj.getJSONArray("results"));
        }
    }

    /**
     * Obtiene la lista de artículos encontrados.
     *
     * @return lista de artículos.
     */
    public List<Item> getFoundItems() {
        return itemList;
    }

    /**
     * Método estático.
     * Convierte una string en una lista de strings.
     *
     * @param string la string de tipo String a convertir.
     * @return lista de strings de tipo List<String>.
     */
    static public List<String> stringToStringList(String string) {
        List<String> stringList = new ArrayList<>();
        String[] stringSplited = string.split("\\s+");
        for (String str : stringSplited) {
            stringList.add(str);
        }
        return stringList;
    }

    /**
     * Método estático.
     * Convierte una lista de strings en una string.
     *
     * @param stringList lista de strings de tipo List<String>.
     * @return una string de tipo String
     */
    static public String stringListToString(List<String> stringList) {
        String string = stringList.get(0);
        for (int c = 1; c < stringList.size(); c++) {
            string = string + " " + stringList.get(c);
        }
        return string;
    }

    /* Métodos privados */

    private void addItems(JSONArray jsonArr) throws Exception {
        for (int c = 0; c < jsonArr.length(); c++) {
            String id = jsonArr.getJSONObject(c).get("id").toString();
            String title = jsonArr.getJSONObject(c).get("title").toString();
            String price = jsonArr.getJSONObject(c).get("price").toString();
            String currency_id = jsonArr.getJSONObject(c).get("currency_id").toString();
            String permalink = jsonArr.getJSONObject(c).get("permalink").toString();
            String thumbnail = jsonArr.getJSONObject(c).get("thumbnail").toString();
            String state_name = jsonArr.getJSONObject(c).getJSONObject("address").get("state_name").toString();
            Item item = new Item();
            item.setId(id);
            item.setTitle(title);
            item.setPrice(price);
            item.setCurrencyId(currency_id);
            item.setPermalink(permalink);
            item.setThumbnail(thumbnail);
            item.setStateName(state_name);
            /* Chequea que cada palabra esté contenida en el título del artículo */
            boolean match = true;
            title = title.toLowerCase();
            for (String word : wordList) {
                if (!title.contains(word)) {
                    match = false;
                    break;
                }
            }
            if (match) itemList.add(item);
        }
    }

    private String buildURLStr(int offset) throws Exception {
        String url_str = "https://api.mercadolibre.com/sites/" + siteId + "/search?q=";
        String word_str = stringListToString(wordList);
        word_str = URLEncoder.encode(word_str, "UTF-8");
        url_str = url_str + word_str;
        url_str = url_str + "&offset=" + offset;
        return url_str;
    }

    private String resquestURL(String url_str) throws Exception {
        URL url = new URL(url_str);
        URLConnection urlConn = url.openConnection();
        urlConn.setRequestProperty("Agent", agent);
        urlConn.setRequestProperty("Accept", "");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

}

// https://api.mercadolibre.com/sites <- paises
// https://api.mercadolibre.com/countries/AR <- estado/provincia