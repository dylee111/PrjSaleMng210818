package dao;

import vo.CustomerVO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCustomer extends DaoSet{
    // 매출 처리 탭 고객 이름
    public Object[] getCustAll() {
        Object[] result = null;
        String query = "SELECT CUSTOMER_ID,CUST_FIRST_NAME,CUST_LAST_NAME " +
                "FROM DEMO_CUSTOMERS ORDER BY CUSTOMER_ID ";
        // 고객 정보에 대한 정확한 행의 수를 알 수 없기 때문에 ArrayList 사용.
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(rs.getInt(1)+" - "+rs.getString(2)+" "+rs.getString(3));
            }
            // toArray : list에 담긴 값을 배열로 바꿈.
            result = list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // getCustAll()

    // 고객 등록
    public void registerCust(CustomerVO vo) {
        boolean result = false;
        try {
            conn = connDB();
            if(duplicatePhone(vo.getPhoneNumber1())) return;
            String query = "INSERT INTO DEMO_CUSTOMERS(CUSTOMER_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_STREET_ADDRESS1, CUST_STREET_ADDRESS2, CUST_CITY, CUST_STATE, CUST_POSTAL_CODE, PHONE_NUMBER1, PHONE_NUMBER2, CREDIT_LIMIT, CUST_EMAIL) " +
                    "VALUES(DEMO_CUST_SEQ.nextval,?, ?, ?, ?, ? ,? ,? ,? ,null,1000,?)" ;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,vo.getCustFirstName());
            pstmt.setString(2,vo.getCustLastName());
            pstmt.setString(3,vo.getStreetAddress1());
            pstmt.setString(4,vo.getStreetAddress2());
            pstmt.setString(5,vo.getCustCity());
            pstmt.setString(6,vo.getCustState());
            pstmt.setInt(7,vo.getCustPostalCode());
            pstmt.setString(8,vo.getPhoneNumber1());
            pstmt.setString(9,vo.getCustEmail());
            int cnt = pstmt.executeUpdate();

            if(cnt > 0 ) {
                JOptionPane.showMessageDialog(null,"등록되었습니다.");
                result = true;
            } else {
                JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // registerCust();

    // 고객 등록 시 핸드폰 번호 중복 체크
    public boolean duplicatePhone(String phoneNum) {
        boolean result = false;
        try {
            conn = connDB();
            String query = "SELECT PHONE_NUMBER1 FROM DEMO_CUSTOMERS WHERE PHONE_NUMBER1 = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,phoneNum);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "중복되는 연락처입니다.");
                result = true;
            } else {
                JOptionPane.showMessageDialog(null, "사용가능한 연락처입니다.");
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // duplicatePhone()

    // state ComboBox 출력
    public Object[] getState() {
        Object[] result = null;
        String query = "SELECT * FROM DEMO_STATES ORDER BY STATE_NAME ASC ";
        ArrayList list = new ArrayList();

        try {
            conn = connDB();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(rs.getString(1));
            }
            result = list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // getState()
}
