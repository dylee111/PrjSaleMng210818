package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCustomer extends DaoSet{
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
    }

}
