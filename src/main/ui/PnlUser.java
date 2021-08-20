package ui;

import dao.DaoUser;
import vo.UserVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlUser extends JPanel {
    private JTextField tfName;
    private JPasswordField pfPw;
    private JPasswordField pfRePw;
    private JTextField tfId;
    private JTextField tfSearch;
    private JTable table;
    private DefaultTableModel model;

    public PnlUser() {
        setLayout(null);
        DaoUser dao = new DaoUser();
        // DefualtTableModel로 복수개의 데이터를 불러오기 위함.
        model = new DefaultTableModel(
                new String[]{"USER_ID","ID","이름","입사일","QUOTA","상품유무","퇴사일","관리자 여부"},0);
        // 사용자 등록
        JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uB4F1\uB85D");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        lblNewLabel.setBounds(12, 10, 161, 52);
        add(lblNewLabel);

        JLabel lbName = new JLabel("\uC774\uB984");
        lbName.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lbName.setBounds(53, 74, 104, 36);
        add(lbName);

        JLabel lbId = new JLabel("\uC544\uC774\uB514");
        lbId.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lbId.setBounds(53, 130, 104, 36);
        add(lbId);

        JLabel lbRePw = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
        lbRePw.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lbRePw.setBounds(53, 248, 114, 36);
        add(lbRePw);

        JLabel lbPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
        lbPw.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lbPw.setBounds(53, 186, 104, 36);
        add(lbPw);
        // 사용자 등록 TextField
        tfName = new JTextField();
        tfName.setBounds(210, 82, 149, 21);
        add(tfName);
        tfName.setColumns(10);

        pfPw = new JPasswordField();
        pfPw.setBounds(210, 194, 149, 21);
        add(pfPw);

        pfRePw = new JPasswordField();
        pfRePw.setBounds(210, 256, 149, 21);
        add(pfRePw);

        tfId = new JTextField();
        tfId.setBounds(210, 138, 149, 21);
        add(tfId);
        tfId.setColumns(10);
        // 사용자 등록 버튼
        JButton btnRegister = new JButton("\uB4F1\uB85D");
        btnRegister.setFont(new Font("굴림", Font.BOLD, 18));
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String id = tfId.getText();
                String pw = new String(pfPw.getPassword());
                String rePw = new String(pfRePw.getPassword());
                if (name == null || name.equals("")) {
                    JOptionPane.showMessageDialog(null,"이름을 입력해주세요.");
                    tfName.requestFocus();
                    return;
                } else if (id == null || id.equals("")) {
                    JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.");
                    tfId.requestFocus();
                    return;
                } else if (pw == null || pw.equals("")) {
                    JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.");
                    pfPw.requestFocus();
                    return;
                } else if (rePw == null || rePw.equals("")) {
                    JOptionPane.showMessageDialog(null,"비밀번호를 다시 확인해주세요.");
                    pfRePw.requestFocus();
                    return;
                }
                if(!pw.equals(rePw)) {
                    JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
                    pfPw.setText("");
                    pfRePw.setText("");
                    pfPw.requestFocus();
                    return;
                }
//                dao.duplicateId(tfId.getText());
                dao.registerUser(new UserVO(name, id, pw));
            }
        }); // 등록 버튼 이벤트 끝

        btnRegister.setBounds(53, 308, 306, 52);
        add(btnRegister);

        // 사용자 조회
        JLabel lbUserList = new JLabel("\uC0AC\uC6A9\uC790 \uBAA9\uB85D");
        lbUserList.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        lbUserList.setBounds(387, 10, 161, 52);
        add(lbUserList);

        tfSearch = new JTextField();
        tfSearch.setBounds(445, 82, 250, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        JButton btnSearch = new JButton("\uC0AC\uC6A9\uC790 \uC870\uD68C");

        btnSearch.setFont(new Font("굴림", Font.BOLD, 12));
        btnSearch.setBounds(716, 81, 118, 23);
        add(btnSearch);




        table = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(445, 121, 389, 239);
        add(scrollPane);
        // 사용자 정보 검색 버튼
        btnSearch.addActionListener(e -> {
//            DefaultTableModel model = (DefaultTableModel) table.getModel(); // DefaultTableModel : 복수개의 데이터를 다루기 위한 객체
            // 다음 검색 시, 테이블 내용 초기화
            while (table.getRowCount() > 0) {
                model.removeRow(0);
            }
            String srch = tfSearch.getText();
            model = dao.getUserList(model, srch);
            table.setModel(model);
            model.fireTableDataChanged();
        }); // 사용자 정보 검색 버튼 끝

    }
}
