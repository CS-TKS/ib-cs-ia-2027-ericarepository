package com.cellculture.service;

import com.cellculture.dao.CellDAO;
import com.cellculture.model.CellPopulation;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public class CellManager {

    private final CellDAO cellDAO;

    public CellManager(CellDAO cellDAO) {
        this.cellDAO = cellDAO;
    }
    //retrieve all cells
    public List<CellPopulation> getAllCells(int userId)
            throws SQLException {
        return cellDAO.getAllCellsbyUId(userId);
    }
    //retrieve one cell population
    public void getCellPopulation(int cellId, int userId)
            throws SQLException {
            if(cellId <=0  || userId <= 0)
            {
                throw new IllegalArgumentException("Cell id and user id cannot be blank");
            }

    }
    //add a cell to database
    public void addCell(CellPopulation cell)
        throws SQLException {
        if (cell == null)
        {
            throw new IllegalArgumentException("cell population cannot be null");
        }
        if(cell.getName() == null || cell.getName().isBlank())
        {
            throw new IllegalArgumentException("Exeriment name cannot be blank");
        }
        if(cell.getCurrentPassage() <= 0)
        {
            throw new IllegalArgumentException("Passage number is required");
        }
    }
    //update population
    public void updateCellForUser(
            CellPopulation cell, int userId)
            throws SQLException {
        if(cell.getCellID() <=0  || userId <= 0)
        {
            throw new IllegalArgumentException("Cell id and user id invalid");
        }
    }
    //delete population
    public void deleteCellForUser(
            int cellId, int userId) throws SQLException {
        if(cellId <=0  || userId <= 0)
        {
            throw new IllegalArgumentException("Cell id and user id invalid");
        }
    }
    private void validateCell(CellPopulation cell)
    {
        if(cell == null)
        {
            throw new IllegalArgumentException(
                    "cell population cannot be null."
            );
        }

        if(cell.getName() == null)
        {
            throw new IllegalArgumentException(
                    "experiment name cannot be null."
            );
        }

        if(cell.getMedium() == null)
        {
                throw new IllegalArgumentException(
                        "Medium cannot be null"
                );
        }
        if(cell.getGrowthFactor() == null)
        {
            throw new IllegalArgumentException(
                    "Growth factor cannot be null"
            );
        }

        if(cell.getCurrentPassage() <= 0)
        {
            throw new IllegalArgumentException(
                    "Current passage must be more than zero"
            );
        }

        if(cell.getMaxPassage() <= 0)
        {
            throw new IllegalArgumentException(
                    "Max passage must be more than zero"
            );
        }

        if(cell.getMaxPassage() < cell.getCurrentPassage())
        {
            throw new IllegalArgumentException(
                    "Maximum passage cannot be less than the current passage"
            );
        }

        LocalDate dateStarted = cell.getDateStarted();
        if(dateStarted == null)
        {
            throw new IllegalArgumentException(
                    "Start date cannot be null"
            );
        }
        if(dateStarted.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException(
                    "Start date cannot be after current date"
            );
        }
    }
    }

