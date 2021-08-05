package ChuongTrinh;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class QuanLyNhanVien extends javax.swing.JFrame {

    DefaultTableModel model;
    int i = 0;

    public QuanLyNhanVien() {
        initComponents();
        nameCollumn();
        showDetail();
        updateRecord();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void nameCollumn() {
        String[] header = {"Mã nhân viên", "Họ tên", "Năm sinh", "Email", "Số điện thoại", "Lương", "Giới tính", "Địa chỉ"};
        String[][] data = {
            {"NV01", "Lê Long Linh", "2000", "linhlelong@gmail.com", "0944694449", "5000000", "Nam", "Cần Thơ"},
            {"NV02", "Trần Khôn", "1999", "khontk@gmail.com", "0944444444", "4000000", "Nam", "An Giang"},
            {"NV03", "Ngô Tất Tố", "2001", "tonntt@gmail.com", "0944444445", "5000000", "Nữ", "Cà Mau"},
            {"NV04", "Hà Hồng", "1997", "hahonghh@gmail.com", "0944444446", "6000000", "Nữ", "Cần Thơ"},
            {"NV05", "Ngưu Thế Đạt", "1993", "datntd97@gmail.com", "0944444447", "5500000", "Nam", "Bạc Liêu"}};
        model = new DefaultTableModel(data, header);
        tblNhanVien.setModel(model);
        JTableHeader tableHeader = tblNhanVien.getTableHeader();
        Font HeaderFont = new Font("Tahoma", Font.PLAIN, 18);
        tableHeader.setFont(HeaderFont);
        tableHeader.setForeground(Color.blue);

    }

    public boolean checkBoTrong() {
        if (txtMaNV.getText().isEmpty()) {
            txtMaNV.setBackground(Color.yellow);
        } else {
            txtMaNV.setBackground(Color.white);
        }
        if (txtHoTen.getText().isEmpty()) {
            txtHoTen.setBackground(Color.yellow);
        } else {
            txtHoTen.setBackground(Color.white);
        }
        if (txtNamSinh.getText().isEmpty()) {
            txtNamSinh.setBackground(Color.yellow);
        } else {
            txtNamSinh.setBackground(Color.white);
        }
        if (txtEmail.getText().isEmpty()) {
            txtEmail.setBackground(Color.yellow);
        } else {
            txtEmail.setBackground(Color.white);
        }
        if (txtLuong.getText().isEmpty()) {
            txtLuong.setBackground(Color.yellow);
        } else {
            txtLuong.setBackground(Color.white);
        }
        if (txtSoDT.getText().isEmpty()) {
            txtSoDT.setBackground(Color.yellow);
        } else {
            txtSoDT.setBackground(Color.white);
        }
        if (txtDiaChi.getText().isEmpty()) {
            txtDiaChi.setBackground(Color.yellow);
        } else {
            txtDiaChi.setBackground(Color.white);
        }
        if (txtMaNV.getText().isEmpty()
                || txtHoTen.getText().isEmpty()
                || txtNamSinh.getText().isEmpty()
                || txtEmail.getText().isEmpty()
                || txtLuong.getText().isEmpty()
                || txtDiaChi.getText().isEmpty()
                || txtSoDT.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống");
            return false;
        }
        try {
            int namSinh = Integer.parseInt(txtNamSinh.getText());
            if (namSinh < 0) {
                JOptionPane.showMessageDialog(rootPane, "Năm sinh không hợp lệ !\nVui lòng nhập số dương");
                txtNamSinh.requestFocus();
                return false;
            }
            if (txtNamSinh.getText().length() < 4 || txtNamSinh.getText().length() > 5 || namSinh < 1950) {
                JOptionPane.showMessageDialog(rootPane, "Năm sinh không hợp lệ !\nVui lòng nhập lại");
                txtNamSinh.requestFocus();
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Năm sinh không hợp lệ !\nVui lòng nhập số");
            txtNamSinh.requestFocus();
            return false;
        }
        try {
            int luong = Integer.parseInt(txtLuong.getText());
            if (luong < 0) {
                JOptionPane.showMessageDialog(rootPane, "Lương không hợp lệ !\nVui lòng nhập số dương");
                txtLuong.requestFocus();
                return false;
            }
            if (luong < 5000000) {
                JOptionPane.showMessageDialog(rootPane, "Lương không hợp lệ !\nLương nhân viên phải từ 5 triệu trở lên");
                txtLuong.requestFocus();
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lương không hợp lệ !\nVui lòng nhập số");
            txtLuong.requestFocus();
            return false;
        }
        String dinhdangGmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "gmail+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String dinhdangFpt = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "fpt+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (txtEmail.getText().matches(dinhdangGmail) || txtEmail.getText().matches(dinhdangFpt)) {
            txtEmail.setBackground(Color.white);
        } else {

            JOptionPane.showMessageDialog(rootPane, "Định dạng email không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            txtEmail.setBackground(Color.orange);
            txtEmail.requestFocus();
            return false;
        }
        String sdt = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (txtSoDT.getText().matches(sdt)) {
            txtSoDT.setBackground(Color.white);
        } else {

            JOptionPane.showMessageDialog(rootPane, "Định dạng số điện thoại không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            txtSoDT.setBackground(Color.orange);
            txtSoDT.requestFocus();
            return false;
        }

        return true;
    }

    public boolean checkTrung() {
        String maNV = txtMaNV.getText();
        String email = txtEmail.getText();
        int count = 0;
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String manv = tblNhanVien.getValueAt(i, 0).toString();

            if (maNV.equalsIgnoreCase(manv)) {
                JOptionPane.showMessageDialog(rootPane, "Trùng mã nhân viên!");
                txtMaNV.requestFocus();
                return false;
            }
        }
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String CheckEmail = tblNhanVien.getValueAt(i, 3).toString();

            if (email.equalsIgnoreCase(CheckEmail)) {
                JOptionPane.showMessageDialog(rootPane, "Trùng email!");
                txtEmail.requestFocus();
                return false;
            }
        }
        return true;

    }

    public void showDetail() {
        txtMaNV.setText(tblNhanVien.getValueAt(i, 0).toString());
        txtHoTen.setText(tblNhanVien.getValueAt(i, 1).toString());
        txtNamSinh.setText(tblNhanVien.getValueAt(i, 2).toString());
        txtEmail.setText(tblNhanVien.getValueAt(i, 3).toString());
        txtSoDT.setText(tblNhanVien.getValueAt(i, 4).toString());
        txtLuong.setText(tblNhanVien.getValueAt(i, 5).toString());
        if (tblNhanVien.getValueAt(i, 6).toString().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(tblNhanVien.getValueAt(i, 7).toString());
    }

    public void addNhanVien() {
        String MaNV = txtMaNV.getText().trim();
        String HoTen = txtHoTen.getText().trim();
        String NamSinh = txtNamSinh.getText().trim();
        String Email = txtEmail.getText().trim();
        String SDT = txtSoDT.getText().trim();
        String Luong = txtLuong.getText().trim();
        String DiaChi = txtDiaChi.getText().trim();
        Vector dataRow = new Vector();
        dataRow.add(MaNV);
        dataRow.add(HoTen);
        dataRow.add(NamSinh);
        dataRow.add(Email);
        dataRow.add(SDT);
        dataRow.add(Luong);
        if (rdoNam.isSelected()) {
            dataRow.add("Nam");
        } else {
            dataRow.add("Nữ");
        }
        dataRow.add(DiaChi);
        model.addRow(dataRow);

    }

    public void removeNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            model.removeRow(row);
            JOptionPane.showMessageDialog(rootPane, "Đã xóa thành công!");
        }
    }

    public void editTable() {
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn sửa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            tblNhanVien.setValueAt(txtMaNV.getText(), row, 0);
            tblNhanVien.setValueAt(txtHoTen.getText(), row, 1);
            tblNhanVien.setValueAt(txtNamSinh.getText(), row, 2);
            tblNhanVien.setValueAt(txtEmail.getText(), row, 3);
            tblNhanVien.setValueAt(txtSoDT.getText(), row, 4);
            tblNhanVien.setValueAt(txtLuong.getText(), row, 5);
            if (rdoNam.isSelected()) {
                tblNhanVien.setValueAt("Nam", row, 6);
            } else {
                tblNhanVien.setValueAt("Nữ", row, 6);
            }

            tblNhanVien.setValueAt(txtDiaChi.getText(), row, 7);
            JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin thành công!");
        }
    }

    public void timKiem() {
        String nhap = JOptionPane.showInputDialog(this, "Vui lòng nhập mã nhân viên cần tìm", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        int count = 0;
        if (nhap == null) {
            JOptionPane.showMessageDialog(rootPane, "Đã thoát chức năng tìm kiếm!");
            return;
        }
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String MaNV = tblNhanVien.getValueAt(i, 0).toString();

            if (nhap.equalsIgnoreCase(MaNV)) {
                txtMaNV.setText(tblNhanVien.getValueAt(i, 0).toString());
                txtHoTen.setText(tblNhanVien.getValueAt(i, 1).toString());
                txtNamSinh.setText(tblNhanVien.getValueAt(i, 2).toString());
                txtEmail.setText(tblNhanVien.getValueAt(i, 3).toString());
                txtSoDT.setText(tblNhanVien.getValueAt(i, 4).toString());
                txtLuong.setText(tblNhanVien.getValueAt(i, 5).toString());
                if (tblNhanVien.getValueAt(i, 6).toString().equalsIgnoreCase("Nam")) {
                    rdoNam.setSelected(true);
                } else {
                    rdoNu.setSelected(true);
                }
                txtDiaChi.setText(tblNhanVien.getValueAt(i, 7).toString());
                JOptionPane.showMessageDialog(rootPane, "Tìm thấy nhân viên " + nhap + " !");
                count = 0;
                break;
            } else {
                count += 1;
            }
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy nhân viên " + nhap + " !");
        }
    }

    public void updateRecord() {
        if (tblNhanVien.getRowCount() == 0) {
            i = 0;
        } else {
            lblRecord.setText("Record " + (i + 1) + " of " + tblNhanVien.getRowCount());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        txtNamSinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboLuong = new javax.swing.JComboBox<>();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblRecord = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnLoad = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblVeMenu = new javax.swing.JLabel();
        lblNen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 16, 1137, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mã nhân viên");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 88, -1, -1));

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 85, 264, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Họ tên ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 88, -1, -1));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 85, 306, -1));

        txtSoDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 206, 265, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Số điện thoại");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 209, -1, -1));

        txtLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 206, 161, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Lương");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 209, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Giới tính");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 261, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Địa chỉ:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 261, -1, -1));

        btgGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rdoNam.setText("Nam");
        getContentPane().add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 257, -1, -1));

        btgGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rdoNu.setText("Nữ");
        getContentPane().add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 257, -1, -1));

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 257, 306, -1));

        txtNamSinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 264, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Năm sinh");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 153, -1, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 150, 306, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Email");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 153, -1, -1));

        cboLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboLuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5000000", "5500000", "6000000", "6500000", "7000000" }));
        cboLuong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLuongItemStateChanged(evt);
            }
        });
        cboLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLuongMouseClicked(evt);
            }
        });
        getContentPane().add(cboLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 206, 127, -1));

        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFirst.setText("First");
        btnFirst.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        getContentPane().add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 369, -1, -1));

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext.setText("Next");
        btnNext.setMaximumSize(new java.awt.Dimension(65, 31));
        btnNext.setMinimumSize(new java.awt.Dimension(65, 31));
        btnNext.setPreferredSize(new java.awt.Dimension(100, 35));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 369, -1, -1));

        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrev.setText("Prev");
        btnPrev.setMaximumSize(new java.awt.Dimension(65, 31));
        btnPrev.setMinimumSize(new java.awt.Dimension(65, 31));
        btnPrev.setPreferredSize(new java.awt.Dimension(100, 35));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 369, -1, -1));

        btnLast.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLast.setText("Last");
        btnLast.setPreferredSize(new java.awt.Dimension(100, 35));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        getContentPane().add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 369, -1, -1));

        lblRecord.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRecord.setForeground(new java.awt.Color(255, 0, 51));
        lblRecord.setText("Record  1 of 10");
        lblRecord.setToolTipText("");
        getContentPane().add(lblRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 422, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 204))); // NOI18N

        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 444, -1, -1));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        btnLoad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLoad.setText("Load");
        btnLoad.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnLoad.setPreferredSize(new java.awt.Dimension(200, 50));
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoad);

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(btnSave);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoa);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSua.setPreferredSize(new java.awt.Dimension(200, 50));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua);

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnTimKiem.setPreferredSize(new java.awt.Dimension(200, 50));
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel2.add(btnTimKiem);

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel2.add(btnReset);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(912, 85, -1, -1));

        lblVeMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVeMenu.setText("Trở về menu");
        lblVeMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblVeMenuMouseMoved(evt);
            }
        });
        lblVeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVeMenuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblVeMenuMouseExited(evt);
            }
        });
        getContentPane().add(lblVeMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 789, -1, -1));

        lblNen.setOpaque(true);
        getContentPane().add(lblNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed

    }//GEN-LAST:event_btnLoadActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int i = tblNhanVien.getSelectedRow();
        txtMaNV.setText(tblNhanVien.getValueAt(i, 0).toString());
        txtHoTen.setText(tblNhanVien.getValueAt(i, 1).toString());
        txtNamSinh.setText(tblNhanVien.getValueAt(i, 2).toString());
        txtEmail.setText(tblNhanVien.getValueAt(i, 3).toString());
        txtSoDT.setText(tblNhanVien.getValueAt(i, 4).toString());
        txtLuong.setText(tblNhanVien.getValueAt(i, 5).toString());
        if (tblNhanVien.getValueAt(i, 6).toString().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(tblNhanVien.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrung() && checkBoTrong()) {
            addNhanVien();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        removeNhanVien();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        editTable();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLuongItemStateChanged
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongItemStateChanged

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;

        } else {

            i = 0;
            showDetail();
            this.updateRecord();
            btnPrev.setEnabled(false);
            btnFirst.setEnabled(false);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
        }

        if (i == 0) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);

        } else {
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
        }
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int row = tblNhanVien.getRowCount();
        if (i > 0) {
            i--;
            this.showDetail();
            this.updateRecord();
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (i == 0) {

            // JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);

        } else {
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int row = tblNhanVien.getRowCount();
        if (i < row) {
            i++;
            this.showDetail();
            this.updateRecord();
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);

        }
        if (i == row - 1) {

            //  JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int row = tblNhanVien.getRowCount();
        i = row - 1;
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (i == row - 1) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        } else {

            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }

        this.showDetail();
        this.updateRecord();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtNamSinh.setText("");
        txtSoDT.setText("");
        txtDiaChi.setText("");
        rdoNam.isSelected();
    }//GEN-LAST:event_btnResetActionPerformed

    private void lblVeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenuMouseClicked
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblVeMenuMouseClicked

    private void lblVeMenuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenuMouseMoved
        lblVeMenu.setForeground(Color.blue);
        lblVeMenu.setText("<html><u>Trở về menu</u></html>");
    }//GEN-LAST:event_lblVeMenuMouseMoved

    private void lblVeMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenuMouseExited
        lblVeMenu.setForeground(Color.black);
        lblVeMenu.setText("<html>Trở về menu</html>");
    }//GEN-LAST:event_lblVeMenuMouseExited

    private void cboLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLuongMouseClicked
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLuong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNen;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JLabel lblVeMenu;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSoDT;
    // End of variables declaration//GEN-END:variables
}
