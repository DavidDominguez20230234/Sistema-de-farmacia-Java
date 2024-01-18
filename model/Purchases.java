
package model;


public class Purchases {
    private int id;
    private int code;
    private String product_name;
    private int purcahse_amount;
    private double purchase_price;
    private double pruchase_subtotal;
    private double total;
    private String created;
    private String supplier_name_product;
    private String purchaser;

    public Purchases() {
    }

    public Purchases(int id, int code, String product_name, int purcahse_amount, double purchase_price, double pruchase_subtotal, double total, String created, String supplier_name_product, String purchaser) {
        this.id = id;
        this.code = code;
        this.product_name = product_name;
        this.purcahse_amount = purcahse_amount;
        this.purchase_price = purchase_price;
        this.pruchase_subtotal = pruchase_subtotal;
        this.total = total;
        this.created = created;
        this.supplier_name_product = supplier_name_product;
        this.purchaser = purchaser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPurcahse_amount() {
        return purcahse_amount;
    }

    public void setPurcahse_amount(int purcahse_amount) {
        this.purcahse_amount = purcahse_amount;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public double getPruchase_subtotal() {
        return pruchase_subtotal;
    }

    public void setPruchase_subtotal(double pruchase_subtotal) {
        this.pruchase_subtotal = pruchase_subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSupplier_name_product() {
        return supplier_name_product;
    }

    public void setSupplier_name_product(String supplier_name_product) {
        this.supplier_name_product = supplier_name_product;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }
    
    
}
