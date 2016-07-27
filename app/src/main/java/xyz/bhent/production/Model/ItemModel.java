package xyz.bhent.production.Model;

/**
 * Created by root on 7/22/16.
 */
public class ItemModel {
    private int icon;
    private String title;
    private String price;
    private String image;
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String p) {
        this.price = p;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
