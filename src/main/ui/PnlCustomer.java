package ui;

import dao.DaoCustomer;
import vo.CustomerVO;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class PnlCustomer extends JPanel {
    private JTextField tfFName;
    private JTextField tfLName;
    private JTextField tfPhone;
    private JTextField tfEmail;
    private JTextField tfAddress1;
    private JTextField tfAddress2;
    private JTextField tfCity;
    private JTextField tfPostal;
    private JTable table;
    private JTextField tfSearch;
    private DefaultTableModel model;

    public PnlCustomer() {
        setLayout(null);

        DaoCustomer daoC = new DaoCustomer();

        JLabel lblNewLabel = new JLabel("\uACE0\uAC1D \uC815\uBCF4");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel.setBounds(27, 20, 103, 45);
        add(lblNewLabel);

        JLabel lbName = new JLabel("\uC774\uB984");
        lbName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName.setBounds(27, 92, 50, 15);
        add(lbName);

        tfFName = new JTextField();
        tfFName.setBounds(113, 89, 146, 21);
        add(tfFName);
        tfFName.setColumns(10);

        tfLName = new JTextField();
        tfLName.setColumns(10);
        tfLName.setBounds(113, 120, 146, 21);
        add(tfLName);

        JLabel lbName_1 = new JLabel("\uC131");
        lbName_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_1.setBounds(27, 123, 50, 15);
        add(lbName_1);

        JLabel lbName_1_1 = new JLabel("\uC5F0\uB77D\uCC98");
        lbName_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_1_1.setBounds(27, 151, 50, 15);
        add(lbName_1_1);

        tfPhone = new JTextField();
        tfPhone.setColumns(10);
        tfPhone.setBounds(113, 151, 146, 21);
        add(tfPhone);

        JLabel lbName_1_2 = new JLabel("\uC774\uBA54\uC77C");
        lbName_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_1_2.setBounds(27, 179, 50, 15);
        add(lbName_1_2);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(113, 179, 146, 21);
        add(tfEmail);

        JLabel lblNewLabel_1 = new JLabel("\uBC30\uC1A1\uC9C0 \uC815\uBCF4");
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel_1.setBounds(417, 20, 146, 45);
        add(lblNewLabel_1);

        JLabel lbName_2 = new JLabel("\uC8FC\uC18C1");
        lbName_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_2.setBounds(417, 92, 50, 15);
        add(lbName_2);

        tfAddress1 = new JTextField();
        tfAddress1.setColumns(10);
        tfAddress1.setBounds(483, 89, 227, 21);
        add(tfAddress1);

        JLabel lbName_2_1 = new JLabel("\uC8FC\uC18C2");
        lbName_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_2_1.setBounds(417, 123, 50, 15);
        add(lbName_2_1);

        tfAddress2 = new JTextField();
        tfAddress2.setColumns(10);
        tfAddress2.setBounds(483, 120, 227, 21);
        add(tfAddress2);

        JLabel lbName_2_1_1 = new JLabel("\uB3C4\uC2DC");
        lbName_2_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_2_1_1.setBounds(417, 151, 41, 15);
        add(lbName_2_1_1);

        tfCity = new JTextField();
        tfCity.setColumns(10);
        tfCity.setBounds(483, 148, 112, 21);
        add(tfCity);

        JLabel lbName_2_1_1_1 = new JLabel("\uC8FC");
        lbName_2_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_2_1_1_1.setBounds(607, 151, 50, 15);
        add(lbName_2_1_1_1);

        JComboBox cbState = new JComboBox(new DaoCustomer().getState());
        cbState.setBounds(635, 148, 79, 21);
        add(cbState);

        tfPostal = new JTextField();
        tfPostal.setColumns(10);
        tfPostal.setBounds(483, 176, 146, 21);
        add(tfPostal);

        JLabel lbName_1_2_1 = new JLabel("\uC6B0\uD3B8\uBC88\uD638");
        lbName_1_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lbName_1_2_1.setBounds(417, 179, 78, 15);
        add(lbName_1_2_1);

        JLabel lblNewLabel_2 = new JLabel("\uACE0\uAC1D \uC870\uD68C");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel_2.setBounds(27, 222, 103, 45);
        add(lblNewLabel_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 310, 849, 124);
        add(scrollPane);

        table = new JTable();
        scrollPane.setColumnHeaderView(table);

        tfSearch = new JTextField();
        tfSearch.setBounds(27, 279, 197, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        // 회원 조회 버튼
        JButton btnSearch = new JButton("\uACE0\uAC1D \uC870\uD68C");
        btnSearch.setBounds(236, 277, 91, 23);
        add(btnSearch);

        JButton btnRegister = new JButton("\uB4F1\uB85D");
        // 고객 등록 이벤트
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fName = tfFName.getText();
                String lName = tfLName.getText();
                String fAddress = tfAddress1.getText();
                String sAddress = tfAddress2.getText();
                String city = tfCity.getText();
                String state = cbState.getSelectedItem().toString();
                int postal = Integer.parseInt(tfPostal.getText()) ;
                String phone = tfPhone.getText();
                String email = tfEmail.getText();
                daoC.registerCust(new CustomerVO(fName, lName, fAddress, sAddress, city,
                        state, postal, phone, email));
            }
        }); // btnRegister Event (고객 등록)
        btnRegister.setBounds(722, 92, 154, 101);
        add(btnRegister);
    }
}
