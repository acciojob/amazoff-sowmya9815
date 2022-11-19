package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        String[] arrOfStr = deliveryTime.split(":", 2);
        int h = Integer.parseInt(arrOfStr[0]);
        int m = Integer.parseInt(arrOfStr[1]);
        this.deliveryTime = (h*60)+m;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
