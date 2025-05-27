package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.StaffAccessLog;


public class StaffAccessLogDBManager {
    private Connection conn;

    public StaffAccessLogDBManager(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void addLog(String logID, String staffID, String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO StaffAccessLog VALUES (?,?,?,CURRENT_TIMESTAMP, null)");
        ps.setString(1, logID);
        ps.setString(2, staffID);
        ps.setString(3, username);
        ps.executeUpdate();
    }

    public StaffAccessLog findStaffAccessLog(String staffID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM StaffAccessLog WHERE STAFFID=? ORDER BY LOGINTIME DESC LIMIT 1");
        ps.setString(1, staffID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String logID = rs.getString("logid");
            String username = rs.getString("username");
            Timestamp loginTime = rs.getTimestamp("logintime");
            Timestamp logoutTime = rs.getTimestamp("logouttime");
            return new StaffAccessLog(logID, staffID, username, loginTime, logoutTime);
        }
        return null;
    }

    public void updateStaffAccessLogout(String staffID, String logID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE StaffAccessLog SET LOGOUTTIME=CURRENT_TIMESTAMP WHERE STAFFID=? AND LOGID=?");
        ps.setString(1, staffID);
        ps.setString(2, logID);
        ps.executeUpdate();
    }

    public ArrayList<StaffAccessLog> findAllLog(String staffID) throws SQLException {
        ArrayList<StaffAccessLog> StaffAccessLogs = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM StaffAccessLog WHERE STAFFID=? ORDER BY LOGINTIME DESC LIMIT 5");
        ps.setString(1, staffID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String logID = rs.getString("logID");
            String username = rs.getString("username");
            Timestamp loginTime = rs.getTimestamp("logintime");
            Timestamp logoutTime = rs.getTimestamp("logouttime");
            StaffAccessLog StaffAccessLog = new StaffAccessLog(logID, staffID, username, loginTime, logoutTime);
            StaffAccessLogs.add(StaffAccessLog);
        }
        return StaffAccessLogs;
    }
}
