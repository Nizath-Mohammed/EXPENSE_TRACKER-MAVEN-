package com.expense_tracker.Model;
import java.time.LocalDate;
public class Expense{
    private int id;
    private String name;
    private String category;
    private int amount;
    private String description;
    private LocalDate date;
    public Expense(String name,String e,int amount,String description){
        this.name=name;
        this.category=category;
        this.amount=amount;
        this.description=description;
        this.date= LocalDate.now();
    }
    public Expense(int id,String name,String category,int amount,String description,LocalDate date){
        this.id=id;
        this.name=name;
        this.category=category;
        this.amount=amount;
        this.description=description;
        this.date=date;
    }
    // setter 
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCategory(String catagory){
        this.category=category;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    // getter
    public int getId(){ return id;}
    public String getName(){ return name;}
    public String getCategory(){ return category;}  
    public int getAmount(){ return amount;}
    public String getDescription(){ return description;}
    public LocalDate getDate(){ return date;}

}