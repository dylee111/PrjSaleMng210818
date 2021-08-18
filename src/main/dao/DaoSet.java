package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoSet {
    Connection conn; // 네트워크 통한 DB 연결 객체
    PreparedStatement pstmt;// SQL을 사용할 수 있게 해주는 객체
    Statement stmt;
    ResultSet rs;// 쿼리를 응답받을 때 결과값을 담기위한 객체

    public Connection connDB() throws SQLException {
        String driver = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "db7", pass = "1234";
        conn = DriverManager.getConnection(driver,user,pass);
        return conn;
    }

    public void closeDB() {
        try {
            if(conn!=null)conn.close();if(stmt != null) stmt.close();
            if(pstmt!=null)pstmt.close();if(rs != null) rs.close();
        } catch (SQLException e) {e.printStackTrace();}
    }
}