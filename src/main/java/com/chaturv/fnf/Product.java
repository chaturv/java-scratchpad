package com.chaturv.fnf;

import java.util.Objects;

public class Product {

    private String id;
    private String name;
    private double salesPrice;
    private double profit;

    public Product(String id, String name, double salesPrice, double profit) {
        this.id = id;
        this.name = name;
        this.salesPrice = salesPrice;
        this.profit = profit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salesPrice=" + salesPrice +
                ", profit=" + profit +
                '}';
    }
}
