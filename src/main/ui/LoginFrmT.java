package ui;

import controler.MainControler;
import dao.DaoSet;
import dao.DaoUser;
import vo.UserVO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrmT extends BasicFrm implements ActionListener {
    JLabel lbId, lbPw;
    JTextField tfId;
    JPasswordField pfPw;
    JButton btnLogin, btnCancel, btnJoin;
    JPanel pnlN, pnlC, pnlS;

    public LoginFrmT() {
        super("Login",300,150);
    }

    @Override
    public void init() {
        setResizable(false);
        pnlN = new JPanel();
        pnlC = new JPanel();
        pnlS = new JPanel();
        lbId = new JLabel("ID");
        lbPw = new JLabel("PW");

        tfId = new JTextField(10);
        tfId.setText("ADMIN");
        pfPw = new JPasswordField(10);
        pfPw.setText("1");
        btnLogin = new JButton("로그인");
        btnCancel = new JButton("취소");
        btnJoin = new JButton("가입");
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        btnJoin.addActionListener(this);
    }
    @Override
    public void arrange() {
        pnlN.add(lbId);
        pnlN.add(tfId);
        pnlC.add(lbPw);
        pnlC.add(pfPw);
        pnlS.add(btnLogin);
        pnlS.add(btnCancel);
        pnlS.add(btnJoin);
        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlS, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = tfId.getText();
        String pw = new String(pfPw.getPassword());
        if (e.getSource() == btnLogin) {
            if(id.equals("")) {
                JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                tfId.requestFocus(); // 해당 텍스트 상자로 커서 이동.
                return;
            } else if (pw.equals("")) {
                JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.");
                pfPw.requestFocus();
                return;
            }
            UserVO userVO = new DaoUser().loginCheck(id, pw);
            if(userVO == null) {
                JOptionPane.showMessageDialog(null, "아이디와 패스워드를 다시 확인해주세요.");
                tfId.requestFocus(); // 해당 텍스트 상자로 커서 이동.
                return;
            } else {
                JOptionPane.showMessageDialog(null, userVO.getUserName()+ "님 환영합니다.");
                dispose();
                MainControler.forwardControl("Main");
            }
            dispose();
        } // btnLogin
        if(e.getSource() == btnCancel) dispose();
//        if(e.getSource() == btnJoin) new RegisterFrm();
    }
}