package com.modu.ModuForm.singleton;

public class StatefulService {
    public int order(String name, int price) {
        System.out.println("name = " + name + "/ price = " +price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
