package tif.gaskeun.masodin.Model;

public class RestaurantData {
    private String address, email, name, contact;

    public RestaurantData() {
    }

    public RestaurantData(String address, String email, String name, String contact) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
