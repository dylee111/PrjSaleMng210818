package dao;

import vo.ProductVO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DaoProduct extends DaoSet {
    //
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
        String query = "SELECT PRODUCT_ID, PRODUCT_NAME " +
                "FROM DEMO_PRODUCT_INFO " +
                "WHERE CATEGORY = ? ";
        ArrayList list = new ArrayList();

        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,category);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1) + " - " + rs.getString(2));
            } // while
            // list를 다시 배열로 바꿈.
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
                query = "SELECT * " +
                        "FROM DEMO_PRODUCT_INFO " +
                        "ORDER BY CATEGORY ASC ";
            } else {
                query = "SELECT * " +
                        "FROM DEMO_PRODUCT_INFO " +
                        "WHERE UPPER(PRODUCT_NAME) = UPPER(?) ";
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

    // ComboBox에서 제품 선택 시, 해당 제품 가격 반환
    public String getProdPrice(String pId) {
        String query;
        String result = "";

        try {
            conn = connDB();
            query = "SELECT LIST_PRICE " +
                    "FROM DEMO_PRODUCT_INFO " +
                    "WHERE PRODUCT_ID = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,Integer.parseInt(pId));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                result = rs.getString(1);
            }

        } catch (SQLException e) {}
        return result;
    } // getProdPrice()

    // 물품 사진 출력
    public ImageIcon getProdImg(String pId) {
        ImageIcon result = null;
        System.out.println(pId);
        String query = "SELECT PRODUCT_IMAGE " +
                "FROM DEMO_PRODUCT_INFO " +
                "WHERE PRODUCT_ID = ? ";
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,Integer.parseInt(pId));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                result = new ImageIcon(ImageIO.read(rs.getBinaryStream(1)));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    } // getProdImg()

    // 상품 삭제
    public void delProd(JTable table) {
        boolean result = false;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if(row < 0) return;

        try {
            conn = connDB();
            String query = "DELETE FROM DEMO_PRODUCT_INFO " +
                    "WHERE PRODUCT_NAME = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, (String) model.getValueAt(row, 0));
            int cnt = pstmt.executeUpdate();

            if(cnt > 0) {
                JOptionPane.showMessageDialog(null, "해당 물품을 삭제했습니다.");
                result = true;
            } else {
                JOptionPane.showMessageDialog(null,"해당 물품을 삭제하지 못했습니다.");
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.removeRow(row);
    } // delCust()
} // Main 끝
