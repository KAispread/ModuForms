package com.modu.ModuForm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(19);
        helloLombok.setName("adsf");

        helloLombok.getAge();
    }
}
