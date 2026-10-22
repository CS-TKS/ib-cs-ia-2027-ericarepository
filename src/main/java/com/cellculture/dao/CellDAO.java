package com.cellculture.dao;

import com.cellculture.model.CellManager;
import com.cellculture.dao.SQLConnection;
import com.cellculture.model.CellPopulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CellDAO {
    //add a new cell population
    public void addCell(CellPopulation cell) throws SQLException{
        String sql = """
    INSERT INTO `Cell_populations`
    (`cell_id`, `user_id`, `ex_name`, `medium`, `growth_factor`, `passage_number`) 
    VALUES (?, ?, ?, ?, ?, ?)
    """;
    //safely prepare connection
    try (Connection con = SQLConnection.getConnection();
         PreparedStatement stmt =
                 con.prepareStatement(
                         sql, Statement.RETURN_GENERATED_KEYS)) {
        //setting values
        stmt.setInt(1, cell.getCellID());
        stmt.setInt(2, cell.getUserID());
        stmt.setString(3, cell.getName());
        stmt.setString(4,cell.getMedium());
        stmt.setInt(5, cell.getCurrentPassage());
        stmt.setDate(6, java.sql.Date.valueOf(cell.getDateStarted()));
        //built in method to convert between local date and sql date
        stmt.executeUpdate();

        //retrieve mySQL id
        try (ResultSet keys = stmt.getGeneratedKeys())
        {
            if (keys.next()) {
                cell.setCellID(keys.getInt(1));
        }
    }

}
}
