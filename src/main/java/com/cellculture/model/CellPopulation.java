package com.cellculture.model;

import java.time.LocalDate;

public class CellPopulation {
    private int experimentID;
    private int userID;
    private String experimentName;
    private String medium;
    private String growthFactor;
    private int currentPassage;
    private int maxPassage;
    private LocalDate dateStarted;

    public CellPopulation(int cellID, int userID, String name, String medium,
                          String growthFactor, int currentPassage, int maxPassage,
                          LocalDate dateStarted)
    {
        cellID = this.experimentID;
        userID =  this.userID;
        name = this.experimentName;
        medium = this.medium;
        growthFactor = this.growthFactor;
        currentPassage = this.currentPassage;
        maxPassage = this.maxPassage;
        dateStarted = this.dateStarted;
    }

    //getter methods
    public int getCellID()
    {
        return experimentID;
    }

    public int getUserID()
    {
        return userID;
    }

    public String getName()
    {
        return experimentName;
    }

    public String getMedium()
    {
        return medium;
    }

    public String getGrowthFactor()
    {
        return growthFactor;
    }

    public int getCurrentPassage()
    {
        return currentPassage;
    }

    public int getMaxPassage()
    {
        return maxPassage;
    }

    public LocalDate getDateStarted()
    {
        return dateStarted;
    }

    //setter methods
    public void setCellID(int x)
    {
        experimentID = x;
    }

    public void setUserID(int x)
    {
        userID = x;
    }

    public void setName(String x)
    {
        experimentName = x;
    }

    public void setMedium(String x)
    {
        medium = x;
    }

    public void setGrowthFactor(String x)
    {
        growthFactor = x;
    }

    public void setCurrentPassage(int x)
    {
        currentPassage = x;
    }

    public void setMaxPassage(int x)
    {
        maxPassage = x;
    }

    public void setDateStarted(LocalDate x)
    {
        dateStarted = x;
    }
}


