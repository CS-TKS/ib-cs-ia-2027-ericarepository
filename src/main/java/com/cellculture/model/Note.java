package com.cellculture.model;
import java.time.LocalDate;

public class Note {
    private int noteID;
    private int cellPopulationID;
    private String text;
    private LocalDate dateLogged;

    public Note(int id, int cellPopulationID, String text, LocalDate dateLogged)
    {
        id = this.noteID;
        cellPopulationID = this.cellPopulationID;
        text = this.text;
        dateLogged = this.dateLogged;
    }

    public int getNoteID()
    {
        return noteID;
    }
    public int getCellPopulationID()
    {
        return cellPopulationID;
    }
    public String getText()
    {
        return text;
    }
    public LocalDate getDateLogged()
    {
        return dateLogged;
    }
}
