
package model;


public class Customers {
    private int id;
    private String fullname;
    private String address;
    private String telephone;
    private String email;
    private String created; 
    private String updated; 

    public Customers() {
    }

    public Customers(int id, String fullname, String address, String telephone, String email, String created, String updated) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    
    
}
