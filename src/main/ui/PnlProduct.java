package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DaoProduct;
import javafx.stage.FileChooser;
import org.jdatepicker.JDatePicker;
import vo.ProductVO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class PnlProduct extends JPanel{
    private JDatePicker dPic;
    private JTextField tfName;
    private JTextField tfDescipt;
    private JTextField tfPrice;
    private JTextField tfFileName;
    private JTable table;
    private JTextField tfSearch;
    private JFileChooser fileChooser;
    private DefaultTableModel model;

    public PnlProduct() {
        setLayout(null);

        model = new DefaultTableModel();
        dPic = new JDatePicker();
        dPic.getFormattedTextField().setLocation(0, 58);
        dPic.setBounds(570, 124, 180, 50);
        add(dPic);

        fileChooser = new JFileChooser();

        JLabel lblNewLabel = new JLabel("\uC81C\uD488 \uB4F1\uB85D");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        lblNewLabel.setBounds(22, 20, 122, 34);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\uC81C\uD488\uBA85");
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewLabel_1.setBounds(31, 78, 50, 15);
        add(lblNewLabel_1);

        tfName = new JTextField();
        tfName.setBounds(93, 75, 132, 21);
        add(tfName);
        tfName.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("\uC81C\uD488 \uC124\uBA85");
        lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(253, 78, 77, 15);
        add(lblNewLabel_1_1);

        tfDescipt = new JTextField();
        tfDescipt.setColumns(10);
        tfDescipt.setBounds(330, 75, 230, 21);
        add(tfDescipt);

        JLabel lblNewLabel_1_1_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
        lblNewLabel_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewLabel_1_1_1.setBounds(584, 78, 77, 15);
        add(lblNewLabel_1_1_1);

        JComboBox cbCate = new JComboBox(new DaoProduct().getCategoryAll());
        cbCate.setBounds(654, 74, 96, 21);
        add(cbCate);

        JLabel lblNewLabel_1_2 = new JLabel("\uAC00\uACA9");
        lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewLabel_1_2.setBounds(31, 124, 50, 15);
        add(lblNewLabel_1_2);

        tfPrice = new JTextField();
        tfPrice.setColumns(10);
        tfPrice.setBounds(93, 124, 132, 21);
        add(tfPrice);

        tfFileName = new JTextField();
        tfFileName.setEditable(true);
        tfFileName.setBounds(253, 124, 96, 21);
        add(tfFileName);
        tfFileName.setColumns(10);

        // 사진 첨부 버튼
        JButton btnAttach = new JButton("\uC0AC\uC9C4 \uCCA8\uBD80");
        btnAttach.setBounds(361, 123, 91, 23);
        add(btnAttach);
        btnAttach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(null);
            }
        });

        JLabel lblNewLabel_1_2_1 = new JLabel("\uCD5C\uC885 \uB4F1\uB85D\uC77C");
        lblNewLabel_1_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewLabel_1_2_1.setBounds(477, 124, 96, 15);
        add(lblNewLabel_1_2_1);

        JLabel lblNewLabel_2 = new JLabel("\uC81C\uD488 \uC870\uD68C");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        lblNewLabel_2.setBounds(22, 193, 122, 34);
        add(lblNewLabel_2);



        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(22, 276, 863, 153);
        add(scrollPane);

        tfSearch = new JTextField();
        tfSearch.setBounds(22, 237, 203, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        // 제품 조회 버튼
        JButton btnSearch = new JButton("\uC81C\uD488 \uC870\uD68C");
        btnSearch.setBounds(237, 237, 91, 23);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model  = new DaoProduct().getProdList(model, tfSearch.getText());
                table.setModel(model);
                model.fireTableDataChanged();
                tfSearch.setText("");
            }
        }); // btnSearch 끝

        // 제품 등록 버튼
        JButton btnRegister = new JButton("\uB4F1\uB85D");
        btnRegister.setBounds(794, 74, 91, 34);
        add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat();
                String name = tfName.getText();
                String descrip = tfDescipt.getText();
                String cate = cbCate.getSelectedItem().toString();
                int price = 0;
                try {
                    price = Integer.parseInt(tfPrice.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"숫자로 된 가격을 입력해주세요.");
                    return;
                }
                String fileName = tfFileName.getText();
//                System.out.println(date);
                new DaoProduct().registerProd(new ProductVO(name,descrip,cate,price,fileName));

            }
        }); // btnRegister 끝

        JButton btnDel = new JButton("\uC0AD\uC81C");
        btnDel.setBounds(346, 237, 91, 23);
        add(btnDel);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DaoProduct().delProd(table);
            }
        });
    }
}
