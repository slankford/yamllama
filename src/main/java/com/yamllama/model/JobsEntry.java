package com.yamllama.model;

import java.math.BigDecimal;

public class JobsEntry {
    BigDecimal income;
    BigDecimal points;
    BigDecimal experience;

    public JobsEntry() {
    }

    public JobsEntry(BigDecimal income, BigDecimal points, BigDecimal experience) {
        this.income = income;
        this.points = points;
        this.experience = experience;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public BigDecimal getExperience() {
        return experience;
    }

    public void setExperience(BigDecimal experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "income: " + income + "\n" +
                "points: " + points + "\n" +
                "experience: " + experience + "\n";
    }

}
