package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.MainController;
import org.jdatepicker.JDatePicker;

import dao.DaoCustomer;
import dao.DaoOrder;
import dao.DaoProduct;

public class PnlSale extends JPanel implements ActionListener {
  private DaoCustomer daoCustomer = new DaoCustomer();
  private DaoProduct daoProduct = new DaoProduct();

  private JLabel lbWriteDate, lbCustomer, lbTotal, lbTotTitle,
          lbProdTitle, lbProdImg, lbProdImgBack;
  private int vTotal = 0;
  private JComboBox cbCustomer, cbCate, cbProd;
  private JTextField tfSaleDate, tfCustomerId, tfProdId,
          tfPrice, tfAmount, tfTotal;
  private JButton btnAdd, btnDel, btnPublish;
  private JPanel pnlTitle;
  private DefaultTableModel model;
  private JTable table;
  private JScrollPane pane;
  private JDatePicker dPic;

  public PnlSale() {
    init();
    arrange();
    event();
  }


  private void init() {
    setLayout(null);
    setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1)," �� ��  ó �� "));
    lbWriteDate = new JLabel("��ǥ �ۼ���");
    lbCustomer = new JLabel("����");
    lbTotal = new JLabel("");
    lbTotTitle = new JLabel("��    �� : ");
    lbTotal.setForeground(Color.WHITE);
    lbTotTitle.setForeground(Color.WHITE);
    lbProdTitle = new JLabel("��ǰ �̹���");
    lbProdImgBack = new JLabel();
    lbProdImgBack.setOpaque(true);
    lbProdImgBack.setBackground(Color.WHITE);
    Border bevelBorder = new BevelBorder(BevelBorder.RAISED,
            Color.LIGHT_GRAY, Color.LIGHT_GRAY.darker(),
            Color.LIGHT_GRAY, Color.LIGHT_GRAY.brighter());
    lbProdImgBack.setBorder(bevelBorder);
    dPic = new JDatePicker();
    dPic.setTextEditable(false);
    dPic.setShowYearButtons(true);
    cbCustomer = new JComboBox(daoCustomer.getCustAll());
    cbCate = new JComboBox(daoProduct.getCate());
    cbProd = new JComboBox(daoProduct.getProdList(cbCate.getSelectedItem().toString()));
    SimpleDateFormat mSimpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    Date currentTime = new Date();
    String mTime = mSimpleDateFormat.format(currentTime);
    tfSaleDate = new JTextField(mTime);
    tfSaleDate.setEditable(false);
    tfCustomerId = new JTextField(20);
    tfProdId = new JTextField(10);
    String pId = (cbProd.getSelectedItem().toString()).split("/")[0];
    tfPrice = new JTextField(daoProduct.getProdPrice(pId));
    ImageIcon img = daoProduct.getProdImg(pId);
    lbProdImg = new JLabel(resizeImg(img));

    tfPrice.setEditable(false);
    tfAmount = new JTextField(5);
    class LimitKeyNum extends PlainDocument {
      public void insertString(int offset, String str,
                               AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if (str.charAt(0) >= '0' && str.charAt(0) <= '9')
          super.insertString(offset, str, attr);
      }
    }
    tfAmount.setDocument(new LimitKeyNum());
    tfTotal = new JTextField(20);
    btnAdd = new JButton("�߰�");
    btnDel = new JButton("����");
    btnPublish = new JButton("��  ��");
    pnlTitle = new JPanel(null);
    pnlTitle.setOpaque(true);
    pnlTitle.setBackground(new Color(128, 147, 161));
    model = new DefaultTableModel(
            new String[]{"����","��ǰ��","�ܰ�","����","�հ�"}, 0);
    table = new JTable(model);
    pane = new JScrollPane(table);
  }

  private void arrange() {
    lbWriteDate.setBounds(12, 45, 100, 20);
    lbCustomer.setBounds(350, 45, 100, 20);
    lbTotal.setBounds(140, 2, 100, 20);
    lbTotTitle.setBounds(40, 2, 100, 20);
    lbProdTitle.setBounds(736, 78, 70, 25);
    lbProdImgBack.setBounds(642, 105, 250, 300);
    lbProdImg.setBounds(654, 105, 230, 300);
    pnlTitle.setBounds(12, 410, 250, 25);
    cbCustomer.setBounds(390, 45, 230, 20);
    cbCate.setBounds(12, 80, 120, 20);
    cbProd.setBounds(134, 80, 278, 20);
    dPic.setBounds(90, 43, 250, 50);
    tfProdId.setBounds(525, 155, 100, 23);
    tfPrice.setBounds(414, 80, 70, 22);
    tfAmount.setBounds(485, 80, 58, 22);
    tfTotal.setBounds(543, 80, 90, 22);
    btnAdd.setBounds(408, 410, 60, 23);
    btnDel.setBounds(469, 410, 60, 23);
    btnPublish.setBounds(531, 410, 100, 23);
    TableColumn column = null; // 1��° column�� ���� TableColumn
    for (int i = 0; i < table.getColumnCount(); i++) {
      column = table.getColumnModel().getColumn(i);
      if (i == 0)
        column.setPreferredWidth(110);
        // default width is 75.
      else if (i == 1)
        column.setPreferredWidth(270);
      else if (i == 2)
        column.setPreferredWidth(60);
      else if (i == 3)
        column.setPreferredWidth(50);
      else if (i == 4)
        column.setPreferredWidth(110);
    } // ���̺� �÷��� ���� �����ϴ� �� ��
    pane.setBounds(12, 105, 620, 300);

    add(lbWriteDate);
    add(lbCustomer);
    pnlTitle.add(lbTotal);
    pnlTitle.add(lbTotTitle);
    add(pnlTitle);
    add(cbCustomer);
    add(cbCate);
    add(cbProd);
    add(dPic);
    add(tfCustomerId);
    add(tfPrice);
    add(tfAmount);
    add(tfTotal);
    add(btnAdd);
    add(btnDel);
    add(btnPublish);
    add(pane);
    add(lbProdTitle);
    add(lbProdImg);
    add(lbProdImgBack);
  }

  private void event() {
    cbCate.addActionListener(this);
    cbProd.addActionListener(this);
    btnAdd.addActionListener(this);
    btnDel.addActionListener(this);
    btnPublish.addActionListener(this);
    tfAmount.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (!tfPrice.getText().equals("") &&
                !tfAmount.getText().equals("")) {
          int calcPrice = Integer.parseInt(
                  tfPrice.getText()) *
                  Integer.parseInt(tfAmount.getText());
          tfTotal.setText(Integer.toString(calcPrice));
        } else {
          tfTotal.setText("");
        }
      }
      @Override
      public void keyReleased(KeyEvent e) {
        keyPressed(e);
      }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String[] arr  = null;
    Object[] oArr = null;
    ArrayList list = new ArrayList();
    if (e.getSource() == btnAdd) {
      if(tfAmount.getText().equals("") ||
              tfTotal.getText().equals("")) return;
      String arrTxt[] = new String[5];
      arrTxt[0] = cbCate.getSelectedItem().toString();
      arrTxt[1] = cbProd.getSelectedItem().toString();
      arrTxt[2] = tfPrice.getText();
      arrTxt[3] = tfAmount.getText();
      arrTxt[4] = tfTotal.getText();
      DefaultTableModel model =
              (DefaultTableModel) table.getModel();
      model.addRow(arrTxt);
      vTotal += Integer.parseInt(arrTxt[4]);
      lbTotal.setText(Integer.toString(vTotal));
      tfAmount.setText("");
      tfTotal.setText("");
      tfAmount.requestFocus();
    } else if (e.getSource() == btnDel) {
      int row = table.getSelectedRow();
      if (row == -1) return;
      vTotal -= Integer.parseInt(table.getValueAt(row, 4).toString());
      lbTotal.setText(Integer.toString(vTotal));
      DefaultTableModel model=(DefaultTableModel) table.getModel();
      model.removeRow(row);
      tfAmount.setText("");
      tfTotal.setText("");
    } else if (e.getSource() == btnPublish) {
      Calendar cal = (Calendar) dPic.getModel().getValue();
      SimpleDateFormat dateFormatter = new SimpleDateFormat("yy/MM/dd");
      String saleDate = dateFormatter.format(cal.getTime());

      int total = Integer.parseInt(lbTotal.getText());
      int custId = Integer.valueOf((cbCustomer.getSelectedItem().toString())
              .split("/")[0]);
      int userId = MainController.getInstance().getSession().getUserId();
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      String notice="";
      if(new DaoOrder().addOrders(custId, total,saleDate,userId,model)){
        notice = "�Է��� �Ϸ�Ǿ����ϴ�.";
        while (table.getRowCount() > 0) {
          ((DefaultTableModel) table.getModel()).removeRow(0);
        }
        tfAmount.setText("");tfTotal.setText("");vTotal = 0;
      } else {
        notice = "�Է��� �����߽��ϴ�.";
      }
      JOptionPane.showMessageDialog(null, notice);
    } else if (e.getSource() == cbCate) {
      String cateW = cbCate.getSelectedItem().toString();
      cbProd.removeAllItems();
      try {
        oArr = daoProduct.getProdList(cateW);
      } catch (Exception e1) {}
      if(oArr.length>0)
        for (int i = 0; i < oArr.length; i++)
          cbProd.addItem(oArr[i]);
      String pId1 = cbProd.getSelectedItem().toString().split("/")[0];
      try {
        tfPrice.setText(daoProduct.getProdPrice(pId1));
        ImageIcon img = daoProduct.getProdImg(pId1);
        lbProdImg.setIcon(resizeImg(img));
      } catch (Exception e2) { }
      tfAmount.setText("");
      tfTotal.setText("");
    } else if (e.getSource() == cbProd) {
      if (cbProd.getSelectedItem() != null) {
        String pId2 = cbProd.getSelectedItem().toString().split("/")[0];
        try {
          tfPrice.setText(daoProduct.getProdPrice(pId2));
          ImageIcon img = daoProduct.getProdImg(pId2);
          lbProdImg.setIcon(resizeImg(img));
        } catch (Exception e2) { }
      }
      tfAmount.setText("");
      tfTotal.setText("");
      tfAmount.requestFocus();
    }
  }
  private ImageIcon resizeImg(ImageIcon img){
    int imgW = img.getIconWidth();
    int imgH = img.getIconHeight();
    int dynamicH = imgH*230/imgW;
    Image image = img.getImage();
    Image newimg = image.getScaledInstance(230, dynamicH,
            Image.SCALE_SMOOTH);
    img = new ImageIcon(newimg);
    System.out.println(img.getIconWidth());
    return img;
  }
}
