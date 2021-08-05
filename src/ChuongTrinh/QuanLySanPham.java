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
public class QuanLySanPham extends javax.swing.JFrame {

    DefaultTableModel model;

    public QuanLySanPham() {
        initComponents();
        sanPham();
    }

    public void sanPham() {
        String[] headerSP = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn Giá", "Loại sản phẩm", "Mô tả"};
        String[][] dataSP = {{"SP001", "Trà ô long TeaPlus", "20", "10000", "Nước ngọt", "Chai 50ml"},
        {"SP002", "Trà ô long TeaPlus 2", "20", "10000", "Trà", "Chai 50ml"},
        {"SP003", "Cà phê TeaPlus 3", "30", "12000", "Cà phê", "Gói 200g"},
        {"SP004", "Trà ô long TeaPlus 4", "40", "13000", "Trà", "Chai 150ml"},
        {"SP005", "Nước suối TeaPlus 5", "10", "15000", "Nước suối", "Chai 50ml"}};
        model = new DefaultTableModel(dataSP, headerSP);
        tblSanPham.setModel(model);
        JTableHeader tableHeader = tblSanPham.getTableHeader();
        Font HeaderFont = new Font("Tahoma", Font.PLAIN, 16);
        tableHeader.setFont(HeaderFont);
        tableHeader.setForeground(Color.blue);
    }

    public void addSanPham() {
        String MaSP = txtMaSP.getText().trim();
        String TenSP = txtTenSanPham.getText().trim();
        String soLuong = txtSoLuong.getText().trim();
        String DonGia = txtDonGia.getText().trim();
        String MoTa = txtMoTa.getText().trim();
        Vector dataRow = new Vector();
        dataRow.add(MaSP);
        dataRow.add(TenSP);
        dataRow.add(soLuong);
        dataRow.add(DonGia);
        dataRow.add(cboLoaiSP.getSelectedItem());
        dataRow.add(MoTa);
        model.addRow(dataRow);

    }

    public void removeSanPham() {
        int row = tblSanPham.getSelectedRow();
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

    public void editSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn sửa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            tblSanPham.setValueAt(txtMaSP.getText(), row, 0);
            tblSanPham.setValueAt(txtTenSanPham.getText(), row, 1);
            tblSanPham.setValueAt(txtSoLuong.getText(), row, 2);
            tblSanPham.setValueAt(txtDonGia.getText(), row, 3);
            tblSanPham.setValueAt(cboLoaiSP.getSelectedItem(), row, 4);
            tblSanPham.setValueAt(txtMoTa.getText(), row, 5);

            JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin sản phẩm thành công!");
        }
    }

    public void timKiemSP() {

        String MaSanPhamCanTim = txtTimKiem.getText();
        int count = 0;
        if (MaSanPhamCanTim.isEmpty()) 
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sản phẩm cần tìm!");
            txtTimKiem.requestFocus();
            txtTimKiem.setBackground(Color.yellow);
            return;
        }else
        {
            txtTimKiem.setBackground(Color.white);
        }
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            String MaSP = tblSanPham.getValueAt(i, 0).toString();

            if (MaSP.equalsIgnoreCase(MaSanPhamCanTim)) {
                txtMaSP.setText(tblSanPham.getValueAt(i, 0).toString());
                txtTenSanPham.setText(tblSanPham.getValueAt(i, 1).toString());
                txtSoLuong.setText(tblSanPham.getValueAt(i, 2).toString());
                txtDonGia.setText(tblSanPham.getValueAt(i, 3).toString());
                cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(i, 4));
                txtMoTa.setText(tblSanPham.getValueAt(i, 5).toString());
                JOptionPane.showMessageDialog(rootPane, "Đã tìm thấy sản phẩm " + MaSanPhamCanTim + " !");
                count = 0;
                break;
            } else {
                count += 1;
            }
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy nhân viên " + MaSanPhamCanTim + " !");
        }
    }

    public boolean checkBoTrongSP() {
        if (txtMaSP.getText().isEmpty()) {
            txtMaSP.setBackground(Color.yellow);
        } else {
            txtMaSP.setBackground(Color.white);
        }
        if (txtTenSanPham.getText().isEmpty()) {
            txtTenSanPham.setBackground(Color.yellow);
        } else {
            txtTenSanPham.setBackground(Color.white);
        }
        if (txtSoLuong.getText().isEmpty()) {
            txtSoLuong.setBackground(Color.yellow);
        } else {
            txtSoLuong.setBackground(Color.white);
        }
        if (txtMoTa.getText().isEmpty()) {
            txtMoTa.setBackground(Color.yellow);
        } else {
            txtMoTa.setBackground(Color.white);
        }
        if (txtDonGia.getText().isEmpty()) {
            txtDonGia.setBackground(Color.yellow);
        } else {
            txtDonGia.setBackground(Color.white);
        }

        if (txtMaSP.getText().isEmpty()
                || txtTenSanPham.getText().isEmpty()
                || txtSoLuong.getText().isEmpty()
                || txtDonGia.getText().isEmpty()
                || txtMoTa.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống");
            return false;
        }
        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ !\nVui lòng nhập số dương");
                txtSoLuong.requestFocus();
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ !\nVui lòng nhập số");
            txtSoLuong.requestFocus();
            return false;
        }
        try {
            int giaBan = Integer.parseInt(txtDonGia.getText());
            if (giaBan < 0) {
                JOptionPane.showMessageDialog(rootPane, "Đơn giá không hợp lệ !\nVui lòng nhập số dương");
                txtDonGia.requestFocus();
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Đơn giá không hợp lệ !\nVui lòng nhập số");
            txtDonGia.requestFocus();
            return false;
        }

        return true;
    }

    public boolean checkTrungSP() {
        String maNV = txtMaSP.getText();
        int count = 0;
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            String manv = tblSanPham.getValueAt(i, 0).toString();

            if (maNV.equalsIgnoreCase(manv)) {
                JOptionPane.showMessageDialog(rootPane, "Trùng mã sản phẩm!");
                txtMaSP.requestFocus();
                return false;
            }
        }
        return true;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQLSP = new javax.swing.JLabel();
        plnSP1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        plnSP2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        plnSanPHam3 = new javax.swing.JPanel();
        btnLoad2 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        btnThem2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnSua3 = new javax.swing.JButton();
        btnReset2 = new javax.swing.JButton();
        btnTimKiem2 = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lbltimKiem = new javax.swing.JLabel();
        lblVeMenu2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblQLSP.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblQLSP.setForeground(new java.awt.Color(0, 51, 204));
        lblQLSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLSP.setText("QUẢN LÝ SẢN PHẨM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaSP.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenSanPham.setToolTipText("");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSoLuong.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Số lượng");

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDonGia.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Đơn giá");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Loại sản phẩm");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Mô tả");

        cboLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cà  phê", "Nước ngọt", "Nước suối", "Trà", " " }));

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(5);
        txtMoTa.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtMoTa);

        javax.swing.GroupLayout plnSP1Layout = new javax.swing.GroupLayout(plnSP1);
        plnSP1.setLayout(plnSP1Layout);
        plnSP1Layout.setHorizontalGroup(
            plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSP1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(plnSP1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(plnSP1Layout.createSequentialGroup()
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPham)
                                    .addComponent(txtSoLuong))))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDonGia)
                            .addComponent(cboLoaiSP, 0, 317, Short.MAX_VALUE)
                            .addGroup(plnSP1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        plnSP1Layout.setVerticalGroup(
            plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSP1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
        );

        plnSP2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout plnSP2Layout = new javax.swing.GroupLayout(plnSP2);
        plnSP2.setLayout(plnSP2Layout);
        plnSP2Layout.setHorizontalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        plnSP2Layout.setVerticalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        plnSanPHam3.setLayout(new java.awt.GridLayout());

        btnLoad2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLoad2.setText("Load");
        btnLoad2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnLoad2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnLoad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoad2ActionPerformed(evt);
            }
        });
        plnSanPHam3.add(btnLoad2);

        btnSave2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave2.setText("Save");
        btnSave2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave2.setPreferredSize(new java.awt.Dimension(200, 50));
        plnSanPHam3.add(btnSave2);

        btnThem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });
        plnSanPHam3.add(btnThem2);

        btnXoa2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa2.setText("Xóa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });
        plnSanPHam3.add(btnXoa2);

        btnSua3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua3.setText("Sửa");
        btnSua3.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSua3.setPreferredSize(new java.awt.Dimension(200, 50));
        btnSua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua3ActionPerformed(evt);
            }
        });
        plnSanPHam3.add(btnSua3);

        btnReset2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset2.setText("Reset");
        btnReset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset2ActionPerformed(evt);
            }
        });
        plnSanPHam3.add(btnReset2);

        btnTimKiem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem2.setText("Tìm kiếm");
        btnTimKiem2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnTimKiem2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        lbltimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltimKiem.setText("Nhập mã sản phẩm cần tìm");

        lblVeMenu2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVeMenu2.setText("Trở về menu");
        lblVeMenu2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblVeMenu2MouseMoved(evt);
            }
        });
        lblVeMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVeMenu2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblVeMenu2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plnSanPHam3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1235, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(plnSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plnSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbltimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblVeMenu2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQLSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plnSanPHam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltimKiem)
                            .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plnSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(plnSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblVeMenu2)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        if (checkTrungSP() && checkBoTrongSP()) {
            addSanPham();
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        removeSanPham();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnLoad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoad2ActionPerformed

    }//GEN-LAST:event_btnLoad2ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        editSanPham();
    }//GEN-LAST:event_btnSua3ActionPerformed

    private void btnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem2ActionPerformed
        timKiemSP();
    }//GEN-LAST:event_btnTimKiem2ActionPerformed

    private void btnReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset2ActionPerformed
        txtMaSP.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtMoTa.setText("");
        cboLoaiSP.setSelectedItem("Cà phê");

    }//GEN-LAST:event_btnReset2ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int i = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getValueAt(i, 0).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(i, 1).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(i, 2).toString());
        txtDonGia.setText(tblSanPham.getValueAt(i, 3).toString());
        cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(i, 4));
        txtMoTa.setText(tblSanPham.getValueAt(i, 5).toString());

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        timKiemSP();
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void lblVeMenu2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenu2MouseMoved
        lblVeMenu2.setForeground(Color.blue);
        lblVeMenu2.setText("<html><u>Trở về menu</u></html>");
    }//GEN-LAST:event_lblVeMenu2MouseMoved

    private void lblVeMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenu2MouseClicked
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblVeMenu2MouseClicked

    private void lblVeMenu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVeMenu2MouseExited
        lblVeMenu2.setForeground(Color.black);
        lblVeMenu2.setText("<html>Trở về menu</html>");
    }//GEN-LAST:event_lblVeMenu2MouseExited

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
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad2;
    private javax.swing.JButton btnReset2;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnTimKiem2;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblQLSP;
    private javax.swing.JLabel lblVeMenu2;
    private javax.swing.JLabel lbltimKiem;
    private javax.swing.JPanel plnSP1;
    private javax.swing.JPanel plnSP2;
    private javax.swing.JPanel plnSanPHam3;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
