package tif.gaskeun.masodin.Model;

import java.io.Serializable;

public class Account implements Serializable {

    private String namastaff;
    private String username;
    private String password;
    private String key;

    public Account(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamastaff() {
        return namastaff;
    }

    public void setNamastaff(String namastaff) {
        this.namastaff = namastaff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return " "+namastaff+"\n" +
                " "+username+"\n" +
                " "+password;
    }

    public Account(String pnamastaff, String pusername, String ppassword){
        namastaff = pnamastaff;
        username = pusername;
        password = ppassword;
    }
}
