package com.expense_tracker.Model;
import java.time.LocalDate;
public class Expense{
    private int id;
    private String category;
    private int amount;
    private String description;
    private LocalDate date;
    public Expense(){

    }
    public Expense(String category,int amount,String description){
        this.category=category;
        this.amount=amount;
        this.description=description;
        this.date= LocalDate.now();
    }
    public Expense(int id,String category,int amount,String description,LocalDate date){
        this.id=id;
        this.category=category;
        this.amount=amount;
        this.description=description;
        this.date=date;
    }
    // setter 
    public void setId(int id){
        this.id=id;
    }

    public void setCategory(String category){
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
    public String getCategory(){ return category;}  
    public int getAmount(){ return amount;}
    public String getDescription(){ return description;}
    public LocalDate getDate(){ return date;}

}