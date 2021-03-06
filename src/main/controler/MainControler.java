package controler;

import ui.LoginFrmT;
import ui.MainFrm;
import vo.UserVO;

public class MainControler {
    public static void main(String[] args) {
//        new LoginFrm();
        MainControler.forwardControl("Login"); // new LoginFrm();와 동일한 역할.
    } // main()

    // Singleton Pattern : 오직 1개의 인스턴스만 생성( 값을 저장해야하기 때문에)
    private static MainControler mainControler; // 자기 자신을 멤버변수로 가짐
    private UserVO session; // UserVO에서 넘어온 User에 대한 정보를 담는 인스턴스

    private MainControler() {} // 메서드를 통해서만 인스턴스 생성가능.

    public static MainControler getInstance() {  // 인스턴스 생성없이 사용되는 메서드이기 때문에 static
        if(mainControler == null) mainControler = new MainControler();
        return mainControler;
    } // getInstance()

    public UserVO getSession() { return session; } // getSession()
    public void setSession(UserVO session) { this.session = session; } // 매개변수로 들어온 UserVO의 모든 정보를 초기화.

    public static void forwardControl(String cmd) {
        if(cmd.equals("Login")) {
            new LoginFrmT();
        } else if(cmd.equals("Main")) {
            new MainFrm();
        }
    } // forwardControl()
}
