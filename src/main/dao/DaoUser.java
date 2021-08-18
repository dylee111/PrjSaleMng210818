package dao;

import controler.MainControler;
import vo.UserVO;

import java.sql.SQLException;

public class DaoUser extends DaoSet {
    UserVO user = null;

    public UserVO loginCheck(String id, String pw) {
        try {
            conn = connDB();
            // query문 안에 ';'은 생략.
            String query = "select * from demo_users where id='" + id + "' and password ='" + pw + "'";

            pstmt = conn.prepareStatement(query); // SQL을 사용할 수 있게 해주는 객체
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
    } // loginCheck
}
