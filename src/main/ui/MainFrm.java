package ui;

import controler.MainControler;
import vo.UserVO;

public class MainFrm extends BasicFrm {
    public MainFrm() {
        super("", 800, 700);
        // MainControler의 인스턴스를 getInstance() 메서드를 통해 생성하고 초기화된 값을 가져와서 UserName만 따로 불러옴.
        String title = MainControler.getInstance().getSession().getUserName() + "님 환영합니다.";
        setTitle(title);
    }
    @Override
    public void init() {

    }

    @Override
    public void arrange() {

    }

}
