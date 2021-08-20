package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoProduct extends DaoSet {
    public Object[] getCategoryAll() {
        Object[] result = null;
        String query = "SELECT DISTINCT CATEGORY FROM DEMO_PRODUCT_INFO ";
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            } // while
            result = list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // getCategory()

    public Object[] getProductAll(String category) {
        Object[] result = null;
        String query = "SELECT PRODUCT_ID, PRODUCT_NAME FROM DEMO_PRODUCT_INFO WHERE CATEGORY = ? ";
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,category);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1) + " - " + rs.getString(2));
            } // while
            result = list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // getProductAll()
} // Main 끝
