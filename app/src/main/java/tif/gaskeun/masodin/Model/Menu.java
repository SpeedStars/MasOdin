package tif.gaskeun.masodin.Model;

import java.io.Serializable;

public class Menu implements Serializable {

    private String nama;
    private String kategori;
    private String deskripsi;
    private String harga;
    private String key;

    public Menu() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return " " + nama + "\n" +
                " " + kategori + "\n" +
                " " + deskripsi + "\n" +
                " " + harga;
    }

    public Menu(String nmmakanan,String katmakanan, String ketmakanan, String hrgmakanan) {
        nama = nmmakanan;
        kategori = katmakanan;
        deskripsi = ketmakanan;
        harga = hrgmakanan;
    }
}