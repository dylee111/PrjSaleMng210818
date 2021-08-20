package vo;

import java.util.Date;

// DataBase에 담긴 column을 불러서 담아주는 역할?
public class UserVO {
    private int userId;
    private String userName;
    private String password;
    private Date createdOn;
    private int quota;
    private String product;
    private Date expiresOn;
    private String adminUser;
    private String id;

    // DEMO_USERS의 모든 정보를 불러올 때.
    public UserVO(int userId, String userName, String password, Date createdOn, int quota,
                  String product, Date expiresOn, String adminUser, String id) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdOn = createdOn;
        this.quota = quota;
        this.product = product;
        this.expiresOn = expiresOn;
        this.adminUser = adminUser;
        this.id = id;
    }

    // DEMO_USERS에 insert할 때.
    public UserVO(String userName, String id, String password) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }
}
