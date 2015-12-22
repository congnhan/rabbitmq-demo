package org.mq.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BinBo on 12/22/2015.
 */
public class Dog {
    private String color;
    private String name;
    private int age;
    private String nation;
    private String birthDay;


    public Dog(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.birthDay = sdf.format(new Date());
        this.color = "black";
        this.name = "Morning";
        this.age = 0;
        this.nation = "vietnam";
    }

    public String getColor() {
        return color;
    }

    public Dog setColor(String color) {
        this.color = color;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dog setAge(int age) {
        this.age = age;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public Dog setNation(String nation) {
        this.nation = nation;
        return this;
    }
}
