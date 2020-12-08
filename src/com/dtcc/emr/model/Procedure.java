package com.dtcc.emr.model;

public class Procedure {
    private int procedureId;
    private String cpt;
    private String description;
    private String name;
    private double cost;

    public Procedure() {
    }

    public Procedure(int procedureId, String cpt, String description, String name, double cost) {
        this.procedureId = procedureId;
        this.cpt = cpt;
        this.description = description;
        this.name = name;
        this.cost = cost;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public String getCpt() {
        return cpt;
    }

    public void setCpt(String cpt) {
        this.cpt = cpt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "procedureId=" + procedureId +
                ", cpt=" + cpt +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
