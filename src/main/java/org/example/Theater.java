package org.example;

import java.io.Serializable;

public class Theater implements Serializable{

    private String name;
    private String direction;
    private int price;

    public Theater (String name, String direction, int price){
        this.name = name;
        this.direction = direction;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

    public int getPrice() {
        return price;
    }
}