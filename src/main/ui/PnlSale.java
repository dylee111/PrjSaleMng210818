package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import dao.DaoCustomer;
import dao.DaoProduct;
import org.jdatepicker.JDatePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlSale extends JPanel {
    private JDatePicker dPic;
    private JTable table;
    private JTextField tfPrice;
    private JTextField tfFirst;
    private JTextField tfSecond;
    private JTextField tfTotal;
    //    private ImageIcon img;
    private JLabel lbProdImg;

    public PnlSale() {
        setLayout(null);
//        img = new ImageIcon();

        DaoCustomer daoCustomer = new DaoCustomer();
        DaoProduct daoProduct = new DaoProduct();
        dPic= new JDatePicker();
        dPic.getFormattedTextField().setLocation(0, 58);
        dPic.setBounds(120, 59, 200, 50);
        add(dPic);


        JComboBox cbCusName = new JComboBox(daoCustomer.getCustAll());
        cbCusName.setBounds(392, 57, 190, 19);
        add(cbCusName);

        JComboBox cbCategory = new JComboBox(daoProduct.getCategoryAll());
        cbCategory.setBounds(30, 86, 95, 23);
        add(cbCategory);

        JComboBox cbProduct = new JComboBox(daoProduct.getProductAll(cbCategory.getSelectedItem().toString()));
        cbProduct.setBounds(130, 86, 162, 23);
        add(cbProduct);

        tfPrice = new JTextField();
        tfPrice.setEditable(false);
        tfPrice.setBounds(303, 87, 96, 23);
        add(tfPrice);
        tfPrice.setColumns(10);
        String pId = cbProduct.getSelectedItem().toString().split(" - ")[0];
        tfPrice.setText(daoProduct.getProdPrice(pId));

        tfFirst = new JTextField();
        tfFirst.setBounds(404, 87, 96, 23);
        add(tfFirst);
        tfFirst.setColumns(10);

        tfSecond = new JTextField();
        tfSecond.setColumns(10);
        tfSecond.setBounds(505, 87, 77, 23);
        add(tfSecond);

        // 제품 이미지 라벨
        lbProdImg = new JLabel("prodimage");
        lbProdImg.setBounds(594, 95, 304, 270);
        ImageIcon img = daoProduct.getProdImg(pId);
        lbProdImg = new JLabel(resizeImg(img));

        // 제품 사진 뒷배경 설정
        JLabel lbProdImgBack = new JLabel("Back");
        lbProdImgBack.setBounds(594, 94, 304, 270);
        lbProdImgBack.setOpaque(true); // setOpaque(false) : background의 알파도를 0으로 만들어서 배경을 투명하게 만드는 함수
        lbProdImgBack.setBackground(Color.WHITE);
        Border bevelBorder = new BevelBorder(BevelBorder.RAISED,
                Color.LIGHT_GRAY,Color.LIGHT_GRAY.darker(),
                Color.LIGHT_GRAY, Color.LIGHT_GRAY.brighter());
        lbProdImgBack.setBorder(bevelBorder);

        add(lbProdImgBack);
        add(lbProdImg);

        JLabel lbTitle = new JLabel("\uB9E4\uCD9C \uCC98\uB9AC");
        lbTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lbTitle.setBounds(30, 10, 95, 23);
        add(lbTitle);

        JLabel lbCreation = new JLabel("\uC804\uD45C \uC791\uC131\uC77C");
        lbCreation.setFont(new Font("굴림", Font.BOLD, 14));
        lbCreation.setBounds(30, 61, 95, 15);
        add(lbCreation);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 119, 552, 245);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lbCustName = new JLabel("\uACE0\uAC1D\uBA85");
        lbCustName.setFont(new Font("굴림", Font.BOLD, 14));
        lbCustName.setBounds(345, 61, 95, 15);
        add(lbCustName);

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
                // 카테고리 선택 시, 해당 카테고리 1번 물품 가격 표시
                // split(" - ")[0] : 상품 번호 , split(" - ")[1] : 상품 이름
                String pId1 = cbProduct.getSelectedItem().toString().split(" - ")[0];
                tfPrice.setText(daoProduct.getProdPrice(pId1));
                ImageIcon img = daoProduct.getProdImg(pId1);
                lbProdImg.setIcon(daoProduct.getProdImg(pId1));
            }

        }); // cbCategory actionPerformed()

        // 제품 선택 시, 가격 및 사진 변경 이벤트
        cbProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cbProduct) {
                    if (cbProduct.getSelectedItem() != null) {
                        String pId2 = cbProduct.getSelectedItem().toString().split(" - ")[0];
                        try {
                            tfPrice.setText(daoProduct.getProdPrice(pId2));
                            ImageIcon img = daoProduct.getProdImg(pId2);
                            lbProdImg.setIcon(daoProduct.getProdImg(pId2));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } // 2번 if
                } // 1번 if
            }
        }); // cbProduct actionPerformed()

        JLabel lbProdTitle = new JLabel("\uC81C\uD488 \uC774\uBBF8\uC9C0");
        lbProdTitle.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lbProdTitle.setBounds(715, 59, 127, 23);
        add(lbProdTitle);

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

    } // 생성자

    private ImageIcon resizeImg (ImageIcon img) {
        int imgW = img.getIconWidth();
        int imgH = img.getIconHeight();
        int dynamicH = imgH * 230 / imgW;
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(230, dynamicH,
                java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
//        System.out.println(img.getIconWidth());
//        System.out.println(img.getIconHeight());
//        return img;
        return img = new ImageIcon(newimg);
    }
}
