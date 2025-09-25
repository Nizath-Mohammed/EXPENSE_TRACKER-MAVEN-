package com.expense_tracker.Model;
public class Catagory{
    String name;
    private Catagory(String name){
        this.name=name;
    }
    // Getter
    public String getName(){
        return this.name;
    } 
    // Setter
    public void setName(String name){
        this.name=name;
    }

}