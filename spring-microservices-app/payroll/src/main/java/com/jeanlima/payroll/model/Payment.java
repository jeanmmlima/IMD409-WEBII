package com.jeanlima.payroll.model;

import java.io.Serializable;

public class Payment implements Serializable{

    private String name;
    private Double dailyIncome;
    private Integer days;

    public Payment(){

    }

    public Payment(String name, Double dailyIncome, Integer days) {
        this.name = name;
        this.dailyIncome = dailyIncome;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(Double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public double getTotal(){
        return days * dailyIncome;
    }
    

    

    
}
