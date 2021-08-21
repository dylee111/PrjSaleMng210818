package dao;

import vo.ProductVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

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

    public ProductVO registerProd(ProductVO vo) {
        boolean result = false;

        try {
            conn = connDB();
            String query = "INSERT INTO DEMO_PRODUCT_INFO(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,CATEGORY,PRODUCT_AVAIL,LIST_PRICE,PRODUCT_IMAGE,MIMETYPE,FILENAME,IMAGE_LAST_UPDATE) " +
                    "VALUES(DEMO_PROD_SEQ.nextVal, ?,?,?,'Y',?,null,'image/jpeg',?,sysdate) ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,vo.getProductName());
            pstmt.setString(2,vo.getProductDescription());
            pstmt.setString(3,vo.getCategory());
            pstmt.setInt(4,vo.getListPrice());
            pstmt.setString(5,vo.getFileName());
//            pstmt.setString(6,vo.getImageLastUpdate());

            int cnt = pstmt.executeUpdate();

            if(cnt > 0) {
                JOptionPane.showMessageDialog(null, "제품이 등록되었습니다.");
                result = true;
            } else {
                JOptionPane.showMessageDialog(null, "제품이 등록되지 않았습니다. 다시 확인해주세요.");
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vo;
    } // registerProd()

    public DefaultTableModel getProdList(DefaultTableModel model, String srch) {
        String query;
        model = new DefaultTableModel(new String[]{"이름","카테고리","가격","재고 유무","업데이트 일자"},0);

        try {
            conn = connDB();
            if(srch.equals("")){
                query = "SELECT * FROM DEMO_PRODUCT_INFO ";
            } else {
                query = "SELECT * FROM DEMO_PRODUCT_INFO WHERE UPPER(PRODUCT_NAME) = UPPER(?) ";
            }
            pstmt = conn.prepareStatement(query);
            if(!srch.equals("")) pstmt.setString(1, srch);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] arr = {rs.getString(2),rs.getString(4), "$ "+ rs.getString(6),
                        rs.getString(5), rs.getString(10)};
            model.addRow(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
} // Main 끝
