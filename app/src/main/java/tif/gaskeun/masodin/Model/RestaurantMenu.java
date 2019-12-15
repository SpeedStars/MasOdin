package tif.gaskeun.masodin.Model;

public class RestaurantMenu {
    private String restid, name, kategori, desc, harga;

    public RestaurantMenu() {
    }

    public RestaurantMenu(String restid, String name, String kategori, String desc, String harga) {
        this.restid = restid;
        this.name = name;
        this.kategori = kategori;
        this.desc = desc;
    }

    public String getRestid() {
        return restid;
    }

    public void setRestid(String restid) {
        this.restid = restid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
