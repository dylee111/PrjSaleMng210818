package ui;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PnlReport extends JPanel {
    private JTextField tfSearch;
    private JTable table;
    public PnlReport() {
        setLayout(null);

        JCheckBox cbMen = new JCheckBox("Mens");
        cbMen.setVerticalAlignment(SwingConstants.BOTTOM);
        cbMen.setBounds(75, 75, 107, 23);
        add(cbMen);

        JCheckBox cbWomen = new JCheckBox("Womens");
        cbWomen.setBounds(226, 75, 107, 23);
        add(cbWomen);

        JCheckBox cbAcc = new JCheckBox("Accessories");
        cbAcc.setBounds(371, 75, 107, 23);
        add(cbAcc);

        JLabel lblNewLabel = new JLabel("\uBB3C\uD488 \uC870\uD68C");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        lblNewLabel.setBounds(48, 10, 131, 48);
        add(lblNewLabel);

        tfSearch = new JTextField();
        tfSearch.setBounds(511, 76, 205, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        JButton btnSearch = new JButton("\uC870\uD68C");
        btnSearch.setBounds(728, 75, 91, 23);
        add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 131, 780, 278);
        add(scrollPane);

        table = new JTable();
        scrollPane.setColumnHeaderView(table);
    }
}
