package my.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DemoJTable extends JFrame {

    private JTable tblSanPham;
    private JButton btThem, btXoa;
    private JTextField txtMa, txtTen, txtGia;
    private DefaultTableModel model;

    public DemoJTable(String title) {
        super(title);
        createGUI();
        processEvent();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DemoJTable frm = new DemoJTable("Demo JTable");
        frm.setVisible(true);
    }

    private void createGUI() {
        //Cách 1
        Object[][] data = {
            {"001", "Gạo", "450000"},
            {"002", "Đường", "470000"},
        };
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán"};
        
        // Initialize DefaultTableModel
        model = new DefaultTableModel(data, columnNames);
        
        // Create JTable with model
        tblSanPham = new JTable(model);
        
        // Create JScrollPane to hold JTable
        JScrollPane scrollTable = new JScrollPane(tblSanPham);

        JPanel p = new JPanel();
        p.add(new JLabel("Mã"));
        p.add(txtMa = new JTextField(5));
        p.add(new JLabel("Tên"));
        p.add(txtTen = new JTextField(10));
        p.add(new JLabel("Giá"));
        p.add(txtGia = new JTextField(10));
        p.add(btThem = new JButton("Thêm"));
        p.add(btXoa = new JButton("Xóa"));

        // Add JTable to JFrame
        add(scrollTable, BorderLayout.CENTER);
        add(p, BorderLayout.NORTH);
    }

    private void processEvent() {
        btThem.addActionListener((e) -> {
            String error = "";
            try {
                String masp = txtMa.getText();
                String tensp = txtTen.getText();
                if (masp.length() == 0) {
                    error = "Bạn chưa nhập mã sản phẩm";
                }
                if (tensp.length() == 0) {
                    error = "Bạn chưa nhập tên sản phẩm";
                }

                double gia = Double.parseDouble(txtGia.getText());

                if (error.length() > 0) {
                    JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add new row to the table
                model.addRow(new Object[]{masp, tensp, gia});
            } catch (NumberFormatException ex) {
                error = "Nhập sai kiểu giá bán";
JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btXoa.addActionListener((e) -> {
            // Get selected row from JTable
            int selectedIndex = tblSanPham.getSelectedRow();
            
            // Check if a row is selected
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng đang chọn?") == JOptionPane.OK_OPTION) {
                    // Remove the selected row
                    model.removeRow(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}