package dao;

import controler.MainControler;
import vo.UserVO;

import javax.swing.*;
import java.sql.SQLException;

public class DaoUser extends DaoSet {
    UserVO user = null;

    public UserVO loginCheck(String id, String pw) {
        try {
            conn = connDB();
            // query문 안에 ';'은 생략.
            // String query = "select * from demo_users where id='" + id + "' and password ='" + pw + "'";
            String query = "select * from demo_users where id=? and password =?";

            pstmt = conn.prepareStatement(query); // SQL을 사용할 수 있게 해주는 객체
            pstmt.setString(1, id); // query 1번 물음표
            pstmt.setString(2, pw); // query 2번 물음표
            rs = pstmt.executeQuery(); // 쿼리를 응답받을 때 결과값을 담기위한 객체
            if (rs.next()) {
                user = new UserVO(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getDate(7), rs.getString(8), rs.getString(9)
                );
                // MainControler의 인스턴스를 getInstance() 메서드를 통해 생성하고 setSession으로 user(UserVO)를 초기화.
                MainControler.getInstance().setSession(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    } // loginCheck()

    public boolean registerUser(UserVO vo) {
        boolean result = false;

        try {
            conn = connDB();
            String sql = "INSERT INTO DEMO_USERS(USER_ID, USER_NAME, PASSWORD, CREATED_ON, QUOTA,PRODUCTS, EXPIRES_ON, ADMIN_USER, ID) " +
                    "VALUES(demo_users_seq_nextval,?,?,sysdate, null, 'Y',null,'N',?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getUserId());      // sql 1번 물음표
            pstmt.setString(2, vo.getUserName()); // sql 2번 물음표
            pstmt.setString(3, vo.getId());       // sql 3번 물음표
            int cnt = pstmt.executeUpdate(); // 테이블에 업데이트할 때.
            result = cnt > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    } // registerUser()

    public boolean duplicateId(String id) {
        boolean result = false;

        try {
            conn = connDB();
            String sql = "SELECT ID FROM DEMO_USERS WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery(); // 쿼리를 응답받을 때 결과값을 담기위한 객체

            if(rs.next()) {
                    JOptionPane.showMessageDialog(null,"중복되는 아이디입니다.");
                    result = true;
            } else {
                    JOptionPane.showMessageDialog(null,"사용가능한 아이디입니다.");
                    result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
