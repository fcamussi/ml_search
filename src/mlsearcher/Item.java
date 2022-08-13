package mlsearcher;

/**
 * Clase para almacenar los valores de los artículos.
 *
 * @author Fernando Camussi
 */
public class Item {

    private String id;
    private String title;
    private String price;
    private String currencyId;
    private String permalink;
    private String thumbnail;
    private String stateName;

    public Item() {
        id = "";
        title = "";
        price = "";
        currencyId = "";
        permalink = "";
        thumbnail = "";
        stateName = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
