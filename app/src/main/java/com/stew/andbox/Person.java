package com.stew.andbox;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String gender;
    private double height;

    Person(String name, String gender, double height) {
        this.name = name;
        this.gender = gender;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Gender:   " + this.gender + ",  Height: " + this.height;
    }
}
