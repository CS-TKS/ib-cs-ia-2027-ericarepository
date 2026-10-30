package com.cellculture.dao;

import com.cellculture.model.CellPopulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CellDAO {
    //add a new cell population
    public void addCell(CellPopulation cell) throws SQLException {
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
    public List<CellPopulation> getAllCellsbyUId(int userID)
            throws SQLException{
        String sql = """
                SELECT `cell_id`, `user_id`, `ex_name`, `medium`, `growth_factor`, `passage_number`, `max_passage`, `date_started` FROM `Cell_populations`
                WHERE `user_id` = ?
                ORDER BY `date_started`
                """;
        List<CellPopulation> cells = new ArrayList<>();
        //safely prepare connection
        try (Connection con = SQLConnection.getConnection();
             PreparedStatement stmt =
                     con.prepareStatement(sql))
        {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    //add later
                    cells.add(mapRowToCell(rs));
                }
        }
    }
        return cells;
}
    //retrieve one cell population
    public CellPopulation getCellPopulation(int cellId, int userId)
            throws SQLException {

        String sql = """
                SELECT `cell_id`, `user_id`, `ex_name`, `medium`, `growth_factor`, `passage_number`, `max_passage`, `date_started` FROM `Cell_populations`\s
                WHERE `user_id` = ? AND `cell_id` = ?
                """;
        try (Connection con = SQLConnection.getConnection();
             PreparedStatement stmt =
                     con.prepareStatement(sql)) {
            stmt.setInt(1, cellId);
            stmt.setInt(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCell(rs);
                }

            }
        }
        return null;
    }
    //update a cell population if it belongs to a user
    public boolean updateCellForUser(
            CellPopulation cell, int userId)
            throws SQLException {
        //copy-pasted from mamp
        String sql = """
            
                UPDATE
                `Cell_populations`
            SET
                `ex_name` = ?,
                `medium` = ?,
                `growth_factor` = ?,
                `passage_number` = ?,
                `max_passage` = ?,
                `date_started` = ?
            WHERE
                `cell_id` = ? AND `user_id` = ?
            """;

        try (Connection conn =
                     SQLConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, cell.getName());
            stmt.setString(2, cell.getMedium());
            stmt.setString(3, cell.getGrowthFactor());
            stmt.setInt(4, cell.getCurrentPassage());
            stmt.setInt(5, cell.getMaxPassage());
            stmt.setInt(6, cell.getCellID());
            stmt.setInt(7, userId);

            return stmt.executeUpdate() > 0;
        }
    }
    public boolean deleteCellForUser(
            int cellId, int userId) throws SQLException {

        String sql = """
            
                DELETE FROM Cell_populations
            WHERE cell_id = ? AND user_id = ?
            """;

        try (Connection conn =
                     SQLConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, cellId);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        }
        }
        //convert database row into java object
        private CellPopulation mapRowToCell(ResultSet rs)
                throws SQLException {
        CellPopulation cell = new CellPopulation(
                rs.getInt("cell_id"),
                rs.getInt("user_id"),
                rs.getString("ex_name"),
            rs.getString("medium"),
            rs.getString("growth_factor"),
            rs.getInt("passage_number"),
            rs.getInt("max_passage"),
            rs.getDate("date_started").toLocalDate()
        );
        return cell;
        }
}

