package com.example.edai;

public class Inventory {
    private int Product_Id;
    private String Product_name;
    private String Product_company;
    private String Product_color;
    private int Product_quantity;


    public Inventory(int product_Id,String product_name, String product_company, String product_color, int product_quantity) {
        Product_Id = product_Id;
        Product_name = product_name;
        Product_company = product_company;
        Product_color = product_color;
        Product_quantity = product_quantity;
    }
    public Inventory(){

    }
    public void product_display(){
        System.out.println(Product_Id);
        System.out.println(Product_name);
        System.out.println(Product_company);
        System.out.println(Product_color);
        System.out.println(Product_quantity);
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
