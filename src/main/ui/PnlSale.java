package ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DaoCustomer;
import dao.DaoProduct;
import org.jdatepicker.JDatePicker;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlSale extends JPanel {
    private JDatePicker dPic;
    private JTable table;
    private JTextField tfPrice;
    private JTextField tfFirst;
    private JTextField tfSecond;
    private JTextField tfTotal;

    public PnlSale() {
        setLayout(null);
        DaoCustomer daoCustomer = new DaoCustomer();
        DaoProduct daoProduct = new DaoProduct();
        dPic= new JDatePicker();
        dPic.getFormattedTextField().setLocation(0, 58);
        dPic.setBounds(120, 59, 200, 50);
        add(dPic);


        JLabel lblNewLabel = new JLabel("\uB9E4\uCD9C \uCC98\uB9AC");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 10, 95, 23);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\uC804\uD45C \uC791\uC131\uC77C");
        lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
        lblNewLabel_1.setBounds(30, 61, 95, 15);
        add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 119, 552, 245);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1_1 = new JLabel("\uACE0\uAC1D\uBA85");
        lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(345, 61, 95, 15);
        add(lblNewLabel_1_1);

        tfPrice = new JTextField();
        tfPrice.setEditable(false);
        tfPrice.setBounds(303, 87, 96, 23);
        add(tfPrice);
        tfPrice.setColumns(10);

        tfFirst = new JTextField();
        tfFirst.setBounds(404, 87, 96, 23);
        add(tfFirst);
        tfFirst.setColumns(10);

        tfSecond = new JTextField();
        tfSecond.setColumns(10);
        tfSecond.setBounds(505, 87, 77, 23);
        add(tfSecond);

        JComboBox cbCusName = new JComboBox(daoCustomer.getCustAll());
        cbCusName.setBounds(392, 57, 190, 19);
        add(cbCusName);

        JComboBox cbCategory = new JComboBox(daoProduct.getCategoryAll());
        cbCategory.setBounds(30, 86, 95, 23);
        add(cbCategory);


        JComboBox cbProduct = new JComboBox(daoProduct.getProductAll(cbCategory.getSelectedItem().toString()));
        cbProduct.setBounds(130, 86, 162, 23);
        add(cbProduct);
        // 카테고리 별 물품을 정렬 이벤트
        cbCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cateW = cbCategory.getSelectedItem().toString();
                Object[] oArr = null;
                cbProduct.removeAllItems();
                try {
                    oArr = daoProduct.getProductAll(cateW);
//                    oArr = new DaoProduct().getProductAll(cateW);
                } catch (Exception e1) {}
                if(oArr.length > 0) {
                    for (int i = 0; i < oArr.length; i++) {
                        cbProduct.addItem(oArr[i]);
                    }
                }
            } // actionPerformed()
        });

        JLabel lblNewLabel_2 = new JLabel("\uC81C\uD488 \uC774\uBBF8\uC9C0");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblNewLabel_2.setBounds(715, 59, 127, 23);
        add(lblNewLabel_2);

        JButton btnAdd = new JButton("\uCD94\uAC00");
        btnAdd.setBounds(283, 381, 91, 51);
        add(btnAdd);

        JButton btnDel = new JButton("\uC0AD\uC81C");
        btnDel.setBounds(386, 381, 91, 51);
        add(btnDel);

        JButton btnPay = new JButton("\uACB0\uC81C");
        btnPay.setBounds(491, 381, 91, 51);
        add(btnPay);

        tfTotal = new JTextField();
        tfTotal.setForeground(Color.WHITE);
        tfTotal.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        tfTotal.setBackground(SystemColor.activeCaption);
        tfTotal.setEditable(false);
        tfTotal.setBounds(29, 382, 213, 50);
        tfTotal.setText("      합계 : ");
        add(tfTotal);
        tfTotal.setColumns(10);

        JLabel lbImage = new JLabel("");
        lbImage.setBounds(594, 95, 304, 270);
        add(lbImage);
    }

}
