package com.targettech.springboot.crud.billionaireslist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class Billionaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "NetWorth cannot be null")
    private Double networth;
    @NotBlank(message = "companiesowned cannot be empty")
    private String companiesowned;

   public Billionaire(){}

    public Billionaire(long id, @NotBlank(message = "Name is mandatory") String name,@NotNull(message = "NetWorth cannot be null") Double networth, @NotBlank(message = "companiesowned cannot be empty") String compainesowned) {
        this.id = id;
        this.name = name;
        this.networth = networth;
        this.companiesowned = compainesowned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNetworth() {
        return networth;
    }

    public void setNetworth(Double networth) {
        this.networth = networth;
    }

    public String getCompaniesowned() {
        return companiesowned;
    }

    public void setCompaniesowned(String companiesowned) {
        this.companiesowned = companiesowned;
    }

    @Override
    public String toString() {
        return "Billionaires{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", networth=" + networth +
                ", compainesowned='" + companiesowned + '\'' +
                '}';
    }
}
