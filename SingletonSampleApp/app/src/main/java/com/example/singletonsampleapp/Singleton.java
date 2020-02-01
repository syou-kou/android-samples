package com.example.singletonsampleapp;

public class Singleton {

    private static Singleton singleton = new Singleton();

    private String name;

    private Singleton() {}

    public static Singleton getInstance() {
        return singleton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
