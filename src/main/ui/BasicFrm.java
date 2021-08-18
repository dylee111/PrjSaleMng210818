package ui;

import javax.swing.*;
import java.awt.*;

public abstract class BasicFrm extends JFrame {
    private int width;
    private int height;
    private String title;

    public BasicFrm(String title,int width, int height) {
        super(title); // JFrame에 title을 받는 생성자가 있기 때문에 title은 JFrame에서 받아 줌.
        this.width = width;
        this.height = height;
        init();    // 초기화
        arrange(); // 추가
        inflate(); // 새 창 출력
    }
    public abstract void init();
    public abstract void arrange();

    // 자손 클래스에서는 생성자는 상속이 되지 않기 때문에
    public void inflate() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // DO_NOTHING_ON_CLOSE : 창이 안닫히게 설정
        setSize(width, height);
//        setBounds(100,100,500,300); // x,y 축의 위치에서 새 창이 열림.(창 크기도 같이 설정)
        setLocationRelativeTo(this); // 새 창이 화면 가운데 열림.
        setVisible(true); // 항상 제일 마지막에 초기화
    }
}
