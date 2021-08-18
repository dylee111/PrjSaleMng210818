package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends BasicFrm {
    private JPanel pnlId;
    private JPanel pnlPw;
    private JPanel pnlBtn;
    private JPanel pnlimg;

    private JLabel lbLogin;
    private JLabel lbpw;
    private JLabel lbimg;

    private JTextField tfId;
    private JPasswordField pw;

    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnCancel;


    public LoginFrm() {
        super("LOGIN", 300, 150);
    }

    @Override
    public void init() {
        pnlId = new JPanel();
        pnlPw = new JPanel();
        pnlBtn = new JPanel();

        lbLogin = new JLabel("ID ");
        lbLogin.setFont(new Font("맑은 고딕",Font.BOLD,16));
        lbpw = new JLabel("PW");
        lbpw.setFont(new Font("맑은 고딕",Font.BOLD,15));
        tfId = new JTextField(10);
        pw = new JPasswordField(10);
        btnLogin = new JButton("로그인");
        btnLogin.addActionListener(e -> {
            String id = tfId.getText();
            String pw2 = new String(pw.getPassword());

            if(id.equals("")) {
                JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.");
                tfId.requestFocus();
                return;
            }
            if(pw2.equals("")) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                pw.requestFocus();
                return;
            }
        });
        btnRegister = new JButton("회원가입");
//        btnRegister.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new RegisterFrm();
//                System.exit(0);
//            }
//        });
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfId.setText("");
                pw.setText("");
//                System.exit(0);
            }
        });
        setResizable(false);
    }

    @Override
    public void arrange() {
        pnlId.add(lbLogin);
        pnlId.add(tfId);
        pnlPw.add(lbpw);
        pnlPw.add(pw);

        pnlBtn.add(btnLogin);
        pnlBtn.add(btnCancel);
        pnlBtn.add(btnRegister);

        add(pnlId,"North");
        add(pnlPw,"Center");
        add(pnlBtn,"South");
//        setLayout(new GridLayout(3,0));
    }
}

