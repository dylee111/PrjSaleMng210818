package ui;

import controler.MainControler;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrm extends BasicFrm {
    private JPanel pnlNorth, pnlCenter;
    private CardLayout cardLayout;
    private String[] menus;
    private JLabel[] lbArr;
    private ImageIcon[] icons;
    private JPanel[] pnls;

    public MainFrm() {
        super("", 900, 600);
        // MainControler의 인스턴스를 getInstance() 메서드를 통해 생성하고 초기화된 값을 가져와서 UserName만 따로 불러옴.
        String title = MainControler.getInstance().getSession().getUserName() + "님 환영합니다.";
        setTitle(title);
    }

    @Override
    public void init() {
        menus = new String[]{"Sale", "Report", "Product", "Customer", "User"};
//        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        lbArr = new JLabel[5];
        icons = new ImageIcon[5];
        cardLayout = new CardLayout(10,10); // (수평마진, 수직마진)

        pnlNorth = new JPanel();
        pnlCenter = new JPanel(cardLayout); // pnlCenter가 cardLayout을 포함.
        pnls = new JPanel[]{new PnlSale(), new PnlReport(), new PnlProduct(), new PnlCustomer(), new PnlUser()};
        pnlNorth.setBackground(Color.WHITE);
        pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬
//        pnlCenter.setBackground(Color.PINK); // 생략 가능 코드

        for (int i = 0; i < lbArr.length; i++) {
            icons[i] = new ImageIcon("images/"+menus[i]+".png");
            lbArr[i] = new JLabel(icons[i]);
            pnlNorth.add(lbArr[i]);
            pnlCenter.add(menus[i], pnls[i]); // pnlCenter에 menus[i]의 이름으로 pnls[i]을 쌓음 (Panel이름, Panel)
            // CardLayout Setting
//            pnls[i].setBackground(colors[i]);
            final int tmp = i; // 지역변수인 i를 상수로 사용
            lbArr[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cardLayout.show(pnlCenter, menus[tmp]); // menus[tmp]이름으로 된 cardLayout을 pnlCenter에 보여줌
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    lbArr[tmp].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            });
        }
    }

    @Override
    public void arrange() {
        add(pnlNorth,"North");
        add(pnlCenter,"Center");
    }

}
