package com.example.demo.model.dto;

import java.util.Map;

public class ExerciseReportDto {
    private int month;
    private Map<String, Integer> typeCounts;
    private String mostType;
    private int totalDuration;
    private int totalKcal;
    private int avgDuration;
    private int avgKcal;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Map<String, Integer> getTypeCounts() {
        return typeCounts;
    }

    public void setTypeCounts(Map<String, Integer> typeCounts) {
        this.typeCounts = typeCounts;
    }

    public String getMostType() {
        return mostType;
    }

    public void setMostType(String mostType) {
        this.mostType = mostType;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(int totalKcal) {
        this.totalKcal = totalKcal;
    }

    public int getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(int avgDuration) {
        this.avgDuration = avgDuration;
    }

    public int getAvgKcal() {
        return avgKcal;
    }

    public void setAvgKcal(int avgKcal) {
        this.avgKcal = avgKcal;
    }
}
