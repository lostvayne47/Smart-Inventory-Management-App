package com.example.edai;

public class Inventory {
    private int Product_Id;
    private String Product_name;
    private String Product_company;
    private String Product_color;
    private int Product_quantity;

    public Inventory() {
    }

    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int product_Id) {
        Product_Id = product_Id;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getProduct_company() {
        return Product_company;
    }

    public void setProduct_company(String product_company) {
        Product_company = product_company;
    }

    public String getProduct_color() {
        return Product_color;
    }

    public void setProduct_color(String product_color) {
        Product_color = product_color;
    }

    public int getProduct_quantity() {
        return Product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        Product_quantity = product_quantity;
    }
}
