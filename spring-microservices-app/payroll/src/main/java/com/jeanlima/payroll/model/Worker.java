package com.jeanlima.payroll.model;

import java.io.Serializable;

public class Worker implements Serializable{

    private Long id;
    private String name;
    private Double dailyIncome;

    public Worker(){

    }

    public Worker(Long id, String name, Double dailyIncoome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncoome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDailyIncome(Double dailyIncoome) {
        this.dailyIncome = dailyIncoome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Worker other = (Worker) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

    
    
}
