package ChuongTrinh;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
public class MenuChucNang extends javax.swing.JFrame {

    DefaultTableModel model1;
    DefaultTableModel model2;
    DefaultTableModel model3;
    int i = 0;
    int j = 0;
    int y = 0;
    int suadoi = 0;
    Connection ketNoi;
    String user = DangNhap.Username;
    String chucVu = DangNhap.ChucVu;
    String duongDan;

    public MenuChucNang() throws SQLException {
        initComponents();
        //phanQuyen();
        dongho();
        nameCollumn();
        sanPham();
        colorHoadonTable();
        loadDuLieuNhanVien();
        loadDuLieuSP();
        loadDuLieuHD();
        loadCombobox();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //NhanVien
    ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
    NhanVien nv;
    PreparedStatement cauLenh;

    public void ketNoiCSDL() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=QuanLyQuanCaPhe";
            String user = "sa";
            String pass = "123";
            ketNoi = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối CSDL");
        }

    }

    public void phanQuyen() {

        if (chucVu.equalsIgnoreCase("Nhân viên")) {
            btnSave.setEnabled(false);
            btnSave2.setEnabled(false);
            btnThem.setEnabled(false);
            btnThem2.setEnabled(false);
            btnXoa.setEnabled(false);
            btnXoa2.setEnabled(false);
            btnSua.setEnabled(false);
            btnSua3.setEnabled(false);
            btnReset.setEnabled(false);
            btnReset2.setEnabled(false);
            btnXoaHD.setEnabled(false);
            btnSuaHD.setEnabled(false);

            txtMaNV.setEditable(false);
            txtHoTen.setEditable(false);
            txtEmail.setEditable(false);
            txtDiaChi.setEditable(false);
            txtLuong.setEditable(false);
            txtSoDT.setEditable(false);
            txtMaSP.setEditable(false);
            txtTenSanPham.setEditable(false);
            txtMoTa.setEditable(false);
            txtDonGia.setEditable(false);
            txtSoLuong.setEditable(false);
            txtNamSinh.setEditable(false);

            cboLuong.setEnabled(false);
            cboLoaiSP.setEnabled(false);

            rdoNam.setEnabled(false);
            rdoNu.setEnabled(false);

            lblChao.setText("Chào bạn Nhân viên");
        } else if (chucVu.equalsIgnoreCase("Quản lý")) {
            btnSave.setEnabled(true);
            btnSave2.setEnabled(true);
            btnThem.setEnabled(true);
            btnThem2.setEnabled(true);
            btnXoa.setEnabled(true);
            btnXoa2.setEnabled(true);
            btnSua.setEnabled(true);
            btnSua3.setEnabled(true);
            btnReset.setEnabled(true);
            btnReset2.setEnabled(true);
            btnXoaHD.setEnabled(true);
            btnSuaHD.setEnabled(true);

            txtMaNV.setEditable(true);
            txtHoTen.setEditable(true);
            txtEmail.setEditable(true);
            txtDiaChi.setEditable(true);
            txtLuong.setEditable(true);
            txtSoDT.setEditable(true);
            txtMaSP.setEditable(true);
            txtTenSanPham.setEditable(true);
            txtMoTa.setEditable(true);
            txtDonGia.setEditable(true);
            txtSoLuong.setEditable(true);
            txtNamSinh.setEditable(true);

            cboLuong.setEnabled(true);
            cboLoaiSP.setEnabled(true);

            rdoNam.setEnabled(true);
            rdoNu.setEnabled(true);

            lblChao.setText("Chào bạn Quản lý");

        }

    }

    public void loadDuLieuNhanVien() throws SQLException {
        ketNoiCSDL();
        String sql = "select * from NhanVien";
        cauLenh = ketNoi.prepareStatement(sql);
        ResultSet ketQua = cauLenh.executeQuery();
        listNV.clear();
        while (ketQua.next()) {
            String manv = ketQua.getString(1);
            String hoten = ketQua.getString(2);
            String namsinh = ketQua.getString(3);
            String email = ketQua.getString(4);
            String sdt = ketQua.getString(5);
            String luong = ketQua.getString(6);
            String gioitinh = ketQua.getString(7);
            String diachi = ketQua.getString(8);

            nv = new NhanVien(manv, hoten, namsinh, email, sdt, luong, gioitinh, diachi);
            listNV.add(nv);
            filltoTableNhanVien();

        }
        i = 0;
        showDetail();
        updateRecord();
        ketNoi.close();
    }

    public void filltoTableNhanVien() {
        model1 = (DefaultTableModel) tblNhanVien.getModel();
        model1.setRowCount(0);
        for (NhanVien nv : listNV) {
            Object[] row = {nv.MaNV, nv.hoTen, nv.namSinh, nv.email, nv.sodt, nv.luong, nv.gioiTinh, nv.diaChi};
            model1.addRow(row);
        }
    }

    public void nameCollumn() {
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
            if (txtNamSinh.getText().length() < 4 || txtNamSinh.getText().length() > 5 || namSinh < 1950 || namSinh > 2050) {
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
        String sdt = txtSoDT.getText();
        int count = 0;
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String manv = tblNhanVien.getValueAt(i, 0).toString();

            if (manv.contains(maNV)) {
                JOptionPane.showMessageDialog(rootPane, "Trùng mã nhân viên!");
                txtMaNV.requestFocus();
                return false;
            }
        }
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String CheckSDT = tblNhanVien.getValueAt(i, 4).toString();

            if (sdt.equalsIgnoreCase(CheckSDT)) {
                JOptionPane.showMessageDialog(rootPane, "Trùng số điện thoại!");
                txtSoDT.requestFocus();
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

    public void luuDuLieuNhanVien() throws SQLException {

        if (suadoi > 0) {
            cauLenh.executeUpdate();
            ketNoi.close();
            JOptionPane.showMessageDialog(rootPane, "Đã lưu thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
        }

    }

    public void addNhanVien() throws SQLException {
        ketNoiCSDL();
        String gioitinh;
        String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?)";
        cauLenh = ketNoi.prepareStatement(sql);
        String manv = txtMaNV.getText();
        String hoten = txtHoTen.getText();
        String namsinh = txtNamSinh.getText();
        String email = txtEmail.getText();
        String sdt = txtSoDT.getText();
        String luong = txtLuong.getText();
        if (rdoNam.isSelected()) {

            gioitinh = "Nam";
        } else {
            gioitinh = "Nữ";
        }
        String diachi = txtDiaChi.getText();

        cauLenh.setString(1, manv);
        cauLenh.setString(2, hoten);
        cauLenh.setString(3, namsinh);
        cauLenh.setString(4, email);
        cauLenh.setString(5, sdt);
        cauLenh.setString(6, luong);
        cauLenh.setString(7, gioitinh);
        cauLenh.setString(8, diachi);

        nv = new NhanVien(manv, hoten, namsinh, email, sdt, luong, gioitinh, diachi);
        listNV.add(nv);
        filltoTableNhanVien();

    }

    public void removeNhanVien() throws SQLException {
        int row = tblNhanVien.getSelectedRow();
        if (row >= 0) {
            ketNoiCSDL();
            String sql = "delete from NhanVien where MaNV = ?";
            cauLenh = ketNoi.prepareStatement(sql);
            cauLenh.setString(1, txtMaNV.getText());
            int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa!", "thông báo", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
//                cauLenh.executeUpdate();
                model1.removeRow(row);
                tblNhanVien.setModel(model1);
                listNV.remove(row);
                JOptionPane.showMessageDialog(rootPane, "Đã xóa thành công");

            }

//            ketNoi.close();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng để xóa ");
        }
    }

    public void editTable() throws SQLException {
        int row = tblNhanVien.getSelectedRow();
        if (row >= 0) {
            if (!txtMaNV.getText().equals(tblNhanVien.getValueAt(row, 0))) {
                JOptionPane.showMessageDialog(rootPane, "Sinh viên ko tồn tại vui lòng nhập lại mã sinh viên");
                return;
            }
            ketNoiCSDL();
            String sql = "update NhanVien set HoTen = ?,NamSinh = ?,Email = ?, SDT = ? ,Luong = ?, GioiTinh = ?, DiaChi = ? where MaNV = ?";
            cauLenh = ketNoi.prepareStatement(sql);
            int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn sửa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);

            if (chon == JOptionPane.YES_OPTION) {
                cauLenh.setString(1, txtHoTen.getText());
                cauLenh.setString(2, txtNamSinh.getText());
                cauLenh.setString(3, txtEmail.getText());
                cauLenh.setString(4, txtSoDT.getText());
                cauLenh.setString(5, txtLuong.getText());
                if (rdoNam.isSelected()) {
                    cauLenh.setString(6, "Nam");
                } else {
                    cauLenh.setString(6, "Nữ");
                }

                cauLenh.setString(7, txtDiaChi.getText());
                cauLenh.setString(8, txtMaNV.getText());
                editTable2();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng để cập nhật");
        }

    }

    public void editTable2() {
        int row = tblNhanVien.getSelectedRow();

        String maNV = txtMaNV.getText();
        if (maNV.equals(tblNhanVien.getValueAt(row, 0))) {
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
            lblRecord.setText("Record " + i + " of " + tblNhanVien.getRowCount());
        } else {
            lblRecord.setText("Record " + (i + 1) + " of " + tblNhanVien.getRowCount());
        }
    }

    public void updateRecord2() {
        if (tblSanPham.getRowCount() == 0) {
            j = 0;
            lblRecord2.setText("Record " + j + " of " + tblSanPham.getRowCount());
        } else {
            lblRecord2.setText("Record " + (j + 1) + " of " + tblSanPham.getRowCount());
        }
    }

    public void updateRecord3() {
        if (tblHoaDon.getRowCount() == 0) {
            y = 0;
            lblRecordHD.setText("Record " + y + " of " + tblHoaDon.getRowCount());
        } else {
            lblRecordHD.setText("Record " + (y + 1) + " of " + tblHoaDon.getRowCount());
        }
    }

    public void orderByName() {

        Comparator<NhanVien> com = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                String hoten1 = nv1.hoTen;
                String hoten2 = nv2.hoTen;
                hoten1.trim();
                hoten2.trim();
                int i1 = hoten1.lastIndexOf(' ');
                int i2 = hoten2.lastIndexOf(' ');
                String ten1 = hoten1.substring(i1 + 1);
                String ten2 = hoten2.substring(i2 + 1);
                return ten1.compareTo(ten2);
            }
        };
        Collections.sort(listNV, com);
    }

    public void orderByNamSinh() {

        Comparator<NhanVien> com = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                return nv1.namSinh.compareTo(nv2.namSinh);
            }
        };
        Collections.sort(listNV, com);
    }

    public void orderByLuong() {

        Comparator<NhanVien> com = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                return nv1.luong.compareTo(nv2.luong);
            }
        };
        Collections.sort(listNV, com);
    }

    public void orderBySoLuong() {
        Comparator<SanPham> com2 = new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return sp1.SoLuong.compareTo(sp2.SoLuong);
            }
        };
        Collections.sort(listSP, com2);

    }

    public void orderByDonGia() {

        Comparator<SanPham> com = new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return sp1.DonGia.compareTo(sp2.DonGia);

            }
        };
        Collections.sort(listSP, com);

    }
    //dongho
    Timer thoigian;

    public void dongho() {
        thoigian = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date hientai = new Date();
                SimpleDateFormat dinhdang = new SimpleDateFormat("hh:mm aa");
                lblClock.setText(dinhdang.format(hientai));
            }
        });
        thoigian.start();
    }

    //sanpham
    ArrayList<SanPham> listSP = new ArrayList<SanPham>();
    SanPham sp;

    public void sanPham() {

        JTableHeader tableHeader = tblSanPham.getTableHeader();
        Font HeaderFont = new Font("Tahoma", Font.PLAIN, 16);
        tableHeader.setFont(HeaderFont);
        tableHeader.setForeground(Color.blue);
    }

    public void showDeitail2() {
        txtMaSP.setText(tblSanPham.getValueAt(j, 0).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(j, 1).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(j, 2).toString());
        txtDonGia.setText(tblSanPham.getValueAt(j, 3).toString());
        cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(j, 4));
        txtMoTa.setText(tblSanPham.getValueAt(j, 5).toString());
        duongDan = tblSanPham.getValueAt(j, 6).toString();
        if (duongDan.equals("")) {
            lblHinhAnh.setIcon(null);
        } else {
            ImageIcon hinhAnh = new ImageIcon(new ImageIcon(duongDan).
                    getImage().getScaledInstance(lblHinhAnh.getWidth(),
                            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
            lblHinhAnh.setIcon(hinhAnh);
        }

    }

    public void loadDuLieuSP() throws SQLException {
        ketNoiCSDL();
        String sql = "select MaSP,TenSP,SoLuong,DonGia,TenLoai,MoTa,Hinh from SanPham a inner join \n"
                + "Loai b on a.LoaiSP = b.MaLoai";
        cauLenh = ketNoi.prepareStatement(sql);
        ResultSet ketQua = cauLenh.executeQuery();
        listSP.clear();
        while (ketQua.next()) {
            String masp = ketQua.getString(1);
            String tensp = ketQua.getString(2);
            String soluong = ketQua.getString(3);
            String donGia = ketQua.getString(4);
            String Loai = ketQua.getString(5);
            String MoTa = ketQua.getString(6);
            String hinh = ketQua.getString(7);

            sp = new SanPham(masp, tensp, soluong, donGia, Loai, MoTa, hinh);
            listSP.add(sp);
            filltoTableSanPham();

        }
        j = 0;
        showDeitail2();
        updateRecord2();
        ketNoi.close();

    }

    public void filltoTableSanPham() {
        model2 = (DefaultTableModel) tblSanPham.getModel();
        model2.setRowCount(0);

        for (SanPham sp : listSP) {
            Object[] row = {sp.MaSP, sp.TenSP, sp.SoLuong, sp.DonGia, sp.loaiSP, sp.moTa, sp.hinh};
            model2.addRow(row);
        }
    }

    public void chonHinh() {
        JFileChooser chooser = new JFileChooser("src\\ImagesSanPham\\");
        //chooser.setCurrentDirectory(new File("src\\images\\"));
        chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();
        if (f != null) {
            duongDan = "src\\ImagesSanPham\\" + f.getName();
            ImageIcon hinhAnh = new ImageIcon(new ImageIcon(duongDan).
                    getImage().getScaledInstance(lblHinhAnh.getWidth(),
                            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
            lblHinhAnh.setIcon(hinhAnh);

        }
    }

    public void loadCombobox() throws SQLException {

        try {
            ketNoiCSDL();
            String sql1 = "select TenLoai from Loai";
            Statement stm = ketNoi.createStatement();
            ResultSet ketQua = stm.executeQuery(sql1);
            Vector<String> loaiSP = new Vector<String>();
            cboLoaiSP.removeAllItems();
            while (ketQua.next()) {
                cboLoaiSP.addItem(ketQua.getString(1));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.exit(0);
        }

    }

    public void addSanPham() throws SQLException {
        ketNoiCSDL();
        String sql = "insert into SanPham values(?,?,?,?,?,?,?)";
        cauLenh = ketNoi.prepareStatement(sql);
        String MaSP = txtMaSP.getText().trim();
        String TenSP = txtTenSanPham.getText().trim();
        String soLuong = txtSoLuong.getText().trim();
        String DonGia = txtDonGia.getText().trim();
        String MoTa = txtMoTa.getText().trim();
        String loai = cboLoaiSP.getSelectedItem().toString();
        String hinh = duongDan;
        String maloai;
        if (loai.equalsIgnoreCase("Trà")) {
            maloai = "001";
        } else if (loai.equalsIgnoreCase("Cà phê")) {
            maloai = "002";
        } else if (loai.equalsIgnoreCase("Nước ngọt")) {
            maloai = "003";
        } else if (loai.equalsIgnoreCase("Nước Lọc")) {
            maloai = "004";
        } else {
            maloai = "005";
        }
        cauLenh.setString(1, MaSP);
        cauLenh.setString(2, TenSP);
        cauLenh.setString(3, soLuong);
        cauLenh.setString(4, DonGia);
        cauLenh.setString(5, maloai);
        cauLenh.setString(6, MoTa);
        cauLenh.setString(6, MoTa);
        cauLenh.setString(7, hinh);

        sp = new SanPham(MaSP, TenSP, soLuong, DonGia, loai, MoTa, hinh);
        listSP.add(sp);
        filltoTableSanPham();

    }

    public void removeSanPham() throws SQLException {
        int row = tblSanPham.getSelectedRow();
        if (row >= 0) {
            ketNoiCSDL();
            String sql = "delete from SanPham where MaSP = ?";
            cauLenh = ketNoi.prepareStatement(sql);
            cauLenh.setString(1, txtMaSP.getText());
            int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa!", "thông báo", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
//                cauLenh.executeUpdate();
                listSP.remove(row);
                filltoTableSanPham();
                JOptionPane.showMessageDialog(rootPane, "Đã xóa thành công");

            }

//            ketNoi.close();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng để xóa ");
        }
    }

    public void editSanPham() throws SQLException {
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        String maloai;
        String loai = cboLoaiSP.getSelectedItem().toString();
        if (loai.equalsIgnoreCase("Trà")) {
            maloai = "001";
        } else if (loai.equalsIgnoreCase("Cà phê")) {
            maloai = "002";
        } else if (loai.equalsIgnoreCase("Nước ngọt")) {
            maloai = "003";
        } else if (loai.equalsIgnoreCase("Nước Lọc")) {
            maloai = "004";
        } else {
            maloai = "005";
        }

        ketNoiCSDL();
        String sql = "update SanPham set TenSP = ?,SoLuong = ?,DonGia = ?, LoaiSP = ? , MoTa = ?,Hinh = ? where MaSP = ?";
        cauLenh = ketNoi.prepareStatement(sql);
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn sửa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            cauLenh.setString(1, txtTenSanPham.getText());
            cauLenh.setString(2, txtSoLuong.getText());
            cauLenh.setString(3, txtDonGia.getText());
            cauLenh.setString(4, maloai);
            cauLenh.setString(5, txtMoTa.getText());
            cauLenh.setString(6, duongDan);
            cauLenh.setString(7, txtMaSP.getText());

            editSP();
            JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin sản phẩm thành công!");

        }

    }

    public void editSP() {
        int row = tblSanPham.getSelectedRow();

        tblSanPham.setValueAt(txtMaSP.getText(), row, 0);
        tblSanPham.setValueAt(txtTenSanPham.getText(), row, 1);
        tblSanPham.setValueAt(txtSoLuong.getText(), row, 2);
        tblSanPham.setValueAt(txtDonGia.getText(), row, 3);
        tblSanPham.setValueAt(cboLoaiSP.getSelectedItem(), row, 4);
        tblSanPham.setValueAt(txtMoTa.getText(), row, 5);
        tblSanPham.setValueAt(duongDan, row, 6);

    }

    public void timKiemSP() {

        String TenSanPhamCanTim = txtTimKiem.getText();
        int count = 0;
        if (TenSanPhamCanTim.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sản phẩm cần tìm!");
            txtTimKiem.requestFocus();
            txtTimKiem.setBackground(Color.yellow);
            return;
        } else {
            txtTimKiem.setBackground(Color.white);
        }
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            String tenSP = tblSanPham.getValueAt(i, 1).toString();

            if (tenSP.equalsIgnoreCase(TenSanPhamCanTim)) {
                txtMaSP.setText(tblSanPham.getValueAt(i, 0).toString());
                txtTenSanPham.setText(tblSanPham.getValueAt(i, 1).toString());
                txtSoLuong.setText(tblSanPham.getValueAt(i, 2).toString());
                txtDonGia.setText(tblSanPham.getValueAt(i, 3).toString());
                cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(i, 4));
                txtMoTa.setText(tblSanPham.getValueAt(i, 5).toString());
                duongDan = tblSanPham.getValueAt(i, 6).toString();
                if (duongDan.equals("")) {
                    lblHinhAnh.setText("");
                } else {
                    ImageIcon hinhAnh = new ImageIcon(new ImageIcon(duongDan).
                            getImage().getScaledInstance(lblHinhAnh.getWidth(),
                                    lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
                    lblHinhAnh.setIcon(hinhAnh);
                }
                JOptionPane.showMessageDialog(rootPane, "Đã tìm thấy sản phẩm " + TenSanPhamCanTim + " !");
                count = 0;
                break;
            } else {
                count += 1;
            }
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy sản phẩm " + TenSanPhamCanTim + " !");
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
            if (soLuong <= 0) {
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
            if (giaBan <= 0) {
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

    public void SaveSP() throws SQLException {
        if (suadoi > 0) {
            cauLenh.executeUpdate();
            ketNoi.close();
            JOptionPane.showMessageDialog(rootPane, "Đã lưu thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
        }
    }
    //hoadon
    ArrayList<HoaDon> listHD = new ArrayList<HoaDon>();
    HoaDon hd;
    String donGia;

    public void colorHoadonTable() {

        JTableHeader tableHeader = tblHoaDon.getTableHeader();
        Font HeaderFont = new Font("Tahoma", Font.PLAIN, 16);
        tableHeader.setFont(HeaderFont);
        tableHeader.setForeground(Color.blue);
    }

    public void filltoTableHoaDon() {
        model3 = (DefaultTableModel) tblHoaDon.getModel();
        model3.setRowCount(0);
        for (HoaDon hd : listHD) {
            Object[] row = {hd.MaHD, hd.MaNV, hd.MaSP, hd.tenSP, hd.SoLuong, hd.donGia, hd.thanhTien};
            model3.addRow(row);
        }

    }

    public void loadDuLieuHD() throws SQLException {
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a "
                + "inner join SanPham b "
                + "on a.MaSP = b.MaSP";
        cauLenh = ketNoi.prepareStatement(sql);
        listHD.clear();
        ResultSet ketQua = cauLenh.executeQuery();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
//        showDeitail3();
//        updateRecord3();
        ketNoi.close();

    }

    public void showDeitail3() {
        txtMaNhanVien.setText(tblHoaDon.getValueAt(y, 1).toString());
        txtMaSanPham.setText(tblHoaDon.getValueAt(y, 2).toString());
        timKiemSP_HD();
        String soluong = tblHoaDon.getValueAt(y, 4).toString();
        int sl = 0;
        sl = Integer.parseInt(soluong);
        spnSoLuong.setValue(sl);
    }

    public void addHoaDon() throws SQLException {
        ketNoiCSDL();
        String sql = "insert into HoaDon(MaNV,MaSP,SoLuong,ThanhTien) values(?,?,?,?)";
        cauLenh = ketNoi.prepareStatement(sql);
        String MaSanPham = txtMaSanPham.getText().trim();
        String MaNhanVien = txtMaNhanVien.getText().trim();
        String soLuong = spnSoLuong.getValue().toString();
        String ThanhTien = txtThanhTien.getText().trim();

        cauLenh.setString(1, MaNhanVien);
        cauLenh.setString(2, MaSanPham);
        cauLenh.setString(3, soLuong);
        cauLenh.setString(4, ThanhTien);

        cauLenh.executeUpdate();
        ketNoi.close();

    }

    public boolean checkHD() {
        if (txtMaSanPham.getText().isEmpty()) {
            txtMaSanPham.setBackground(Color.yellow);
        } else {
            txtMaSanPham.setBackground(Color.white);
        }
        if (txtMaNhanVien.getText().isEmpty()) {
            txtMaNhanVien.setBackground(Color.yellow);
        } else {
            txtMaNhanVien.setBackground(Color.white);
        }
        try {
            int soluong = (int) spnSoLuong.getValue();
            if (txtMaSanPham.getText().isEmpty()
                    || txtMaNhanVien.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống");
                return false;
            } else if (soluong <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số lượng lớn hơn 0!");
                spnSoLuong.requestFocus();
                return false;
            }
            if (timKiemSP_HD() == false) {
                return false;
            }
            if (timKiemNhanVien_HD() == false) {

                return false;
            }

        } catch (Exception e) {
            spnSoLuong.requestFocus();
            JOptionPane.showMessageDialog(rootPane, "Lỗi vui lòng nhập số");
            return false;
        }
        return true;
    }

    public boolean timKiemNhanVien_HD() {
        String nhap = txtMaNhanVien.getText().trim();
        int row = tblNhanVien.getRowCount();
        int count = 0;
        if (row <= 0) {

            return false;
        }
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            String MaNV = tblNhanVien.getValueAt(i, 0).toString();

            if (nhap.equalsIgnoreCase(MaNV)) {

                count = 0;
                break;
            } else {
                count += 1;
            }
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(rootPane, "Nhân viên không tồn tại !");
            return false;
        }
        return true;
    }

    public boolean timKiemSP_HD() {
        int row = listSP.size();
        String MaSanPhamCanTim = txtMaSanPham.getText();
        int count = 0;
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng Load dữ liệu sản phẩm!");
            return false;
        }
        if (MaSanPhamCanTim.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sản phẩm cần tìm!");
            txtMaSanPham.requestFocus();
            txtMaSanPham.setBackground(Color.yellow);
            return false;
        } else {
            txtMaSanPham.setBackground(Color.white);
        }

        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            String MaSP = tblSanPham.getValueAt(i, 0).toString();

            if (MaSP.equalsIgnoreCase(MaSanPhamCanTim)) {
                donGia = tblSanPham.getValueAt(i, 3).toString();
                txtTenSP.setText(tblSanPham.getValueAt(i, 1).toString());
                txtDonGiaHD.setText(tblSanPham.getValueAt(i, 3).toString());

                count = 0;
                break;
            } else {
                count += 1;
            }
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(rootPane, "Không tồn tại sản phẩm " + MaSanPhamCanTim + " !");
            return false;
        }
        return true;
    }

    public void removeHoaDon() throws SQLException {
        int row = tblHoaDon.getSelectedRow();
        int row2 = tblHoaDon.getRowCount();
        if (row2 <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        }
        if (row >= 0) {
            ketNoiCSDL();
            String sql = "delete from HoaDon where MaHD = ?";
            cauLenh = ketNoi.prepareStatement(sql);
            cauLenh.setString(1, tblHoaDon.getValueAt(row, 0).toString());
            int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa!", "thông báo", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                cauLenh.executeUpdate();

                JOptionPane.showMessageDialog(rootPane, "Đã xóa thành công");

            }

            ketNoi.close();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng để xóa ");
        }
    }

    public void editHoaDon() throws SQLException {
        int row = tblHoaDon.getSelectedRow();
        int row2 = tblHoaDon.getRowCount();
        if (row2 <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        ketNoiCSDL();
        String sql = "update HoaDon set MaNV = ?,MaSP = ?,SoLuong = ?, ThanhTien = ?  where MaHD = ?";
        cauLenh = ketNoi.prepareStatement(sql);
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn sửa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            cauLenh.setString(1, txtMaNhanVien.getText());
            cauLenh.setString(2, txtMaSanPham.getText());
            cauLenh.setString(3, spnSoLuong.getValue().toString());
            cauLenh.setString(4, txtThanhTien.getText());
            cauLenh.setString(5, tblHoaDon.getValueAt(row, 0).toString());

            JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin hóa đơn thành công!");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng để cập nhật");
        }
        cauLenh.executeUpdate();
        ketNoi.close();

    }

    public void timKiem_HoaDon() throws SQLException {

        ketNoiCSDL();
        String TenSanPham = txtTimkiemHD.getText();
        if (TenSanPham.isEmpty()) {
            loadDuLieuHD();
        } else {
            String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham"
                    + " b on a.MaSP = b.MaSP where b.TenSP like N'%" + TenSanPham + "%'";
            Statement cauLenh = ketNoi.createStatement();
            ResultSet ketQua = cauLenh.executeQuery(sql);
            listHD.clear();
            while (ketQua.next()) {
                String mahd = ketQua.getString(1);
                String manv = ketQua.getString(2);
                String masp = ketQua.getString(3);
                String tenSP = ketQua.getString(4);
                String soluong = ketQua.getString(5);
                String dongia = ketQua.getString(6);
                String thanhTien = ketQua.getString(7);

                hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
                listHD.add(hd);
                filltoTableHoaDon();

            }
            showDeitail3();
            updateRecord3();

            ketNoi.close();
        }

    }

    public void sapXepHD_MaSP() throws SQLException {
        j = 0;
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham b on a.MaSP = b.MaSP order by MaSP ";
        Statement cauLenh = ketNoi.createStatement();
        ResultSet ketQua = cauLenh.executeQuery(sql);
        listHD.clear();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
        showDeitail3();
        updateRecord3();
        ketNoi.close();

    }

    public void sapXepHD_MaNV() throws SQLException {
        j = 0;
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham b on a.MaSP = b.MaSP order by MaNV ";
        Statement cauLenh = ketNoi.createStatement();
        ResultSet ketQua = cauLenh.executeQuery(sql);
        listHD.clear();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
        showDeitail3();
        updateRecord3();
        ketNoi.close();

    }

    public void sapXepHD_DonGia() throws SQLException {
        j = 0;
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham b on a.MaSP = b.MaSP order by DonGia ";
        Statement cauLenh = ketNoi.createStatement();
        ResultSet ketQua = cauLenh.executeQuery(sql);
        listHD.clear();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
        showDeitail3();
        updateRecord3();
        ketNoi.close();

    }

    public void sapXepHD_SoLuong() throws SQLException {
        j = 0;
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham b on a.MaSP = b.MaSP order by SoLuong ";
        Statement cauLenh = ketNoi.createStatement();
        ResultSet ketQua = cauLenh.executeQuery(sql);
        listHD.clear();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
        showDeitail3();
        updateRecord3();
        ketNoi.close();

    }

    public void sapXepHD_ThanhTien() throws SQLException {
        j = 0;
        ketNoiCSDL();
        String sql = "select MaHD,MaNV,a.MaSP,b.TenSP,a.SoLuong,b.DonGia,ThanhTien from HoaDon a  inner join SanPham b on a.MaSP = b.MaSP order by ThanhTien ";
        Statement cauLenh = ketNoi.createStatement();
        ResultSet ketQua = cauLenh.executeQuery(sql);
        listHD.clear();
        while (ketQua.next()) {
            String mahd = ketQua.getString(1);
            String manv = ketQua.getString(2);
            String masp = ketQua.getString(3);
            String tenSP = ketQua.getString(4);
            String soluong = ketQua.getString(5);
            String dongia = ketQua.getString(6);
            String thanhTien = ketQua.getString(7);

            hd = new HoaDon(mahd, manv, masp, tenSP, soluong, dongia, thanhTien);
            listHD.add(hd);
            filltoTableHoaDon();

        }
        showDeitail3();
        updateRecord3();
        ketNoi.close();

    }

    public void tinhTien() {
        int soLuong = (int) spnSoLuong.getValue();
        int donGia = Integer.parseInt(txtDonGiaHD.getText());
        int thanhTien = soLuong * donGia;

        txtThanhTien.setText(thanhTien + "");

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
        pnlChinh = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblNenTrangChu = new javax.swing.JLabel();
        pnlQLSP = new javax.swing.JPanel();
        btnPrev1 = new javax.swing.JButton();
        btnFirst1 = new javax.swing.JButton();
        btnLast1 = new javax.swing.JButton();
        btnNext1 = new javax.swing.JButton();
        plnSP1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        plnSP2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        lbltimKiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem2 = new javax.swing.JButton();
        plnSanPHam3 = new javax.swing.JPanel();
        btnLoad2 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        btnThem2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnSua3 = new javax.swing.JButton();
        btnReset2 = new javax.swing.JButton();
        lblRecord2 = new javax.swing.JLabel();
        btnOderByDonGia = new javax.swing.JButton();
        btnOderBySoluong = new javax.swing.JButton();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblHinhNen = new javax.swing.JLabel();
        pnlQuanLyNhanVien = new javax.swing.JPanel();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnLoad = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnOderByName = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnOderByNamSinh = new javax.swing.JButton();
        btnOderbyLuong = new javax.swing.JButton();
        lblNen = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtDonGiaHD = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        plnSanPHam4 = new javax.swing.JPanel();
        btnThemHD = new javax.swing.JButton();
        btnXoaHD = new javax.swing.JButton();
        btnSuaHD = new javax.swing.JButton();
        btnResetHD = new javax.swing.JButton();
        txtTimkiemHD = new javax.swing.JTextField();
        btnTimKiemHoaDon = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnPrevHD = new javax.swing.JButton();
        btnFirstHD = new javax.swing.JButton();
        btnLastHD = new javax.swing.JButton();
        btnNextHD = new javax.swing.JButton();
        btnTimMaSP = new javax.swing.JButton();
        lblRecordHD = new javax.swing.JLabel();
        btnOderByMaSP = new javax.swing.JButton();
        btnOderByThanhTien = new javax.swing.JButton();
        btnOderByDonGiaHD = new javax.swing.JButton();
        btnOderBySoluongHD = new javax.swing.JButton();
        btnOderByMaNV = new javax.swing.JButton();
        spnSoLuong = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        lblTrangChu = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblBackLogin = new javax.swing.JLabel();
        lblChao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlChinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 2, new java.awt.Color(0, 0, 204)));
        pnlChinh.setLayout(new java.awt.CardLayout());

        pnlMenu.setBackground(new java.awt.Color(0, 255, 255));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Version : 1.0.0.1");
        pnlMenu.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 750, -1, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.PNG"))); // NOI18N
        pnlMenu.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 320));

        lblNenTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffeeBackground.jpg"))); // NOI18N
        pnlMenu.add(lblNenTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 790));

        pnlChinh.add(pnlMenu, "card2");

        pnlQLSP.setPreferredSize(new java.awt.Dimension(881, 628));
        pnlQLSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPrev1.setBackground(new java.awt.Color(51, 102, 255));
        btnPrev1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_rewind_32px.png"))); // NOI18N
        btnPrev1.setMaximumSize(new java.awt.Dimension(65, 31));
        btnPrev1.setMinimumSize(new java.awt.Dimension(65, 31));
        btnPrev1.setPreferredSize(new java.awt.Dimension(100, 35));
        btnPrev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev1ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnPrev1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 640, 120, 50));

        btnFirst1.setBackground(new java.awt.Color(51, 102, 255));
        btnFirst1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFirst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_skip_to_start_32px.png"))); // NOI18N
        btnFirst1.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst1ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnFirst1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 640, 120, 50));

        btnLast1.setBackground(new java.awt.Color(51, 102, 255));
        btnLast1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_end_32px.png"))); // NOI18N
        btnLast1.setPreferredSize(new java.awt.Dimension(100, 35));
        btnLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast1ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnLast1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 640, 120, 50));

        btnNext1.setBackground(new java.awt.Color(51, 102, 255));
        btnNext1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_fast_forward_32px.png"))); // NOI18N
        btnNext1.setMaximumSize(new java.awt.Dimension(65, 31));
        btnNext1.setMinimumSize(new java.awt.Dimension(65, 31));
        btnNext1.setPreferredSize(new java.awt.Dimension(100, 35));
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnNext1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 640, 120, 50));

        plnSP1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 204));
        jLabel10.setText("Mã sản phẩm");

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 204));
        jLabel11.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 204));
        jLabel12.setText("Số lượng");

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("Đơn giá");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 204));
        jLabel14.setText("Loại sản phẩm");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 204));
        jLabel15.setText("Mô tả");

        cboLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cà phê", "Nước ngọt", "Nước suối", "Trà", "" }));

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
                                .addComponent(jLabel10)
                                .addGap(25, 25, 25)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(plnSP1Layout.createSequentialGroup()
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPham)
                                    .addComponent(txtSoLuong))))
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addComponent(jLabel10)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel15))
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(144, 144, 144))
        );

        pnlQLSP.add(plnSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 500, 600));

        plnSP2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Loại sản phẩm", "Mô tả", "Hinh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        javax.swing.GroupLayout plnSP2Layout = new javax.swing.GroupLayout(plnSP2);
        plnSP2.setLayout(plnSP2Layout);
        plnSP2Layout.setHorizontalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
        );
        plnSP2Layout.setVerticalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnSP2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlQLSP.add(plnSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 830, 300));

        lbltimKiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltimKiem.setForeground(new java.awt.Color(51, 51, 255));
        lbltimKiem.setText("Nhập mã sản phẩm cần tìm");
        pnlQLSP.add(lbltimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, 30));
        pnlQLSP.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 340, 40));

        btnTimKiem2.setBackground(new java.awt.Color(51, 51, 255));
        btnTimKiem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem2.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_24px_1.png"))); // NOI18N
        btnTimKiem2.setText("Tìm");
        btnTimKiem2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnTimKiem2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnTimKiem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 130, 40));

        plnSanPHam3.setBackground(new java.awt.Color(51, 102, 255));

        btnLoad2.setBackground(new java.awt.Color(51, 102, 255));
        btnLoad2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLoad2.setForeground(new java.awt.Color(255, 255, 255));
        btnLoad2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_loader_32px.png"))); // NOI18N
        btnLoad2.setText("Load");
        btnLoad2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnLoad2.setOpaque(false);
        btnLoad2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnLoad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoad2ActionPerformed(evt);
            }
        });

        btnSave2.setBackground(new java.awt.Color(51, 102, 255));
        btnSave2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_save_32px.png"))); // NOI18N
        btnSave2.setText("Save");
        btnSave2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave2.setOpaque(false);
        btnSave2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        btnThem2.setBackground(new java.awt.Color(51, 102, 255));
        btnThem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem2.setForeground(new java.awt.Color(255, 255, 255));
        btnThem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_add_32px.png"))); // NOI18N
        btnThem2.setText("Thêm");
        btnThem2.setOpaque(false);
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(51, 102, 255));
        btnXoa2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa2.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_delete_32px.png"))); // NOI18N
        btnXoa2.setText("Xóa");
        btnXoa2.setOpaque(false);
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnSua3.setBackground(new java.awt.Color(51, 102, 255));
        btnSua3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua3.setForeground(new java.awt.Color(255, 255, 255));
        btnSua3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_edit_32px_1.png"))); // NOI18N
        btnSua3.setText("Sửa");
        btnSua3.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSua3.setOpaque(false);
        btnSua3.setPreferredSize(new java.awt.Dimension(200, 50));
        btnSua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua3ActionPerformed(evt);
            }
        });

        btnReset2.setBackground(new java.awt.Color(51, 51, 255));
        btnReset2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset2.setForeground(new java.awt.Color(255, 255, 255));
        btnReset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_reset_32px.png"))); // NOI18N
        btnReset2.setText("Reset");
        btnReset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plnSanPHam3Layout = new javax.swing.GroupLayout(plnSanPHam3);
        plnSanPHam3.setLayout(plnSanPHam3Layout);
        plnSanPHam3Layout.setHorizontalGroup(
            plnSanPHam3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSanPHam3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(btnLoad2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSua3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnReset2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(421, 421, 421))
        );
        plnSanPHam3Layout.setVerticalGroup(
            plnSanPHam3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLoad2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSua3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReset2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlQLSP.add(plnSanPHam3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 70));

        lblRecord2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRecord2.setForeground(new java.awt.Color(0, 0, 204));
        lblRecord2.setText("Record  1 of 10");
        lblRecord2.setToolTipText("");
        pnlQLSP.add(lblRecord2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 640, -1, -1));

        btnOderByDonGia.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByDonGia.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByDonGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_price_24px.png"))); // NOI18N
        btnOderByDonGia.setText("Đơn giá");
        btnOderByDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByDonGiaActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnOderByDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 720, 160, 40));

        btnOderBySoluong.setBackground(new java.awt.Color(51, 102, 255));
        btnOderBySoluong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderBySoluong.setForeground(new java.awt.Color(255, 255, 255));
        btnOderBySoluong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_sort_24px.png"))); // NOI18N
        btnOderBySoluong.setText("Số Lượng");
        btnOderBySoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderBySoluongActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnOderBySoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 720, 160, 40));

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Ảnh sản phẩm");
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        pnlQLSP.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, 290, 240));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 204));
        jLabel22.setText("Sắp xếp theo:");
        pnlQLSP.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 730, -1, -1));

        lblHinhNen.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhNen.setOpaque(true);
        pnlQLSP.add(lblHinhNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1370, 720));

        pnlChinh.add(pnlQLSP, "card3");

        pnlQuanLyNhanVien.setBackground(new java.awt.Color(0, 204, 102));
        pnlQuanLyNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Mã nhân viên");
        pnlQuanLyNhanVien.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 280, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Họ tên ");
        pnlQuanLyNhanVien.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 70, -1));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 280, -1));

        txtSoDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 290, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Số điện thoại");
        pnlQuanLyNhanVien.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 440, 160, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Lương");
        pnlQuanLyNhanVien.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Giới tính");
        pnlQuanLyNhanVien.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Địa chỉ");
        pnlQuanLyNhanVien.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, -1, -1));

        rdoNam.setBackground(new java.awt.Color(255, 153, 153));
        btgGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(51, 0, 255));
        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);
        pnlQuanLyNhanVien.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        btgGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(51, 0, 255));
        rdoNu.setText("Nữ");
        rdoNu.setOpaque(false);
        pnlQuanLyNhanVien.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        pnlQuanLyNhanVien.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 490, 310, 190));

        txtNamSinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 280, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Năm sinh");
        pnlQuanLyNhanVien.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 290, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("Email");
        pnlQuanLyNhanVien.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, -1));

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
        pnlQuanLyNhanVien.add(cboLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 127, -1));

        btnFirst.setBackground(new java.awt.Color(51, 102, 255));
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_skip_to_start_32px.png"))); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 120, 50));

        btnNext.setBackground(new java.awt.Color(51, 102, 255));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_fast_forward_32px.png"))); // NOI18N
        btnNext.setMaximumSize(new java.awt.Dimension(65, 31));
        btnNext.setMinimumSize(new java.awt.Dimension(65, 31));
        btnNext.setPreferredSize(new java.awt.Dimension(100, 35));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 630, 120, 50));

        btnPrev.setBackground(new java.awt.Color(51, 102, 255));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_rewind_32px.png"))); // NOI18N
        btnPrev.setMaximumSize(new java.awt.Dimension(65, 31));
        btnPrev.setMinimumSize(new java.awt.Dimension(65, 31));
        btnPrev.setPreferredSize(new java.awt.Dimension(100, 35));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 630, 120, 50));

        btnLast.setBackground(new java.awt.Color(51, 102, 255));
        btnLast.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_end_32px.png"))); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(100, 35));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 630, 120, 50));

        lblRecord.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRecord.setForeground(new java.awt.Color(0, 0, 204));
        lblRecord.setText("Record  1 of 10");
        lblRecord.setToolTipText("");
        pnlQuanLyNhanVien.add(lblRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 640, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 204))); // NOI18N

        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ tên", "Năm sinh", "Email", "SDT", "Lương", "Giới Tính", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setOpaque(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
        );

        pnlQuanLyNhanVien.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 870, 530));

        jPanel6.setBackground(new java.awt.Color(51, 102, 255));

        btnLoad.setBackground(new java.awt.Color(51, 102, 255));
        btnLoad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLoad.setForeground(new java.awt.Color(255, 255, 255));
        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_loader_32px.png"))); // NOI18N
        btnLoad.setText("Load");
        btnLoad.setBorder(null);
        btnLoad.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnLoad.setOpaque(false);
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(51, 102, 255));
        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_save_32px.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave.setPreferredSize(new java.awt.Dimension(71, 33));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 102, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_add_32px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setOpaque(false);
        btnThem.setPreferredSize(new java.awt.Dimension(71, 33));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(51, 102, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_delete_32px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setOpaque(false);
        btnXoa.setPreferredSize(new java.awt.Dimension(71, 33));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(51, 102, 255));
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_edit_32px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSua.setOpaque(false);
        btnSua.setPreferredSize(new java.awt.Dimension(71, 33));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_32px.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnTimKiem.setOpaque(false);
        btnTimKiem.setPreferredSize(new java.awt.Dimension(71, 33));
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(51, 0, 255));
        btnReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_reset_32px.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setPreferredSize(new java.awt.Dimension(71, 33));
        btnReset.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnResetMouseMoved(evt);
            }
        });
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlQuanLyNhanVien.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 70));

        btnOderByName.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByName.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_name_24px.png"))); // NOI18N
        btnOderByName.setText("Tên");
        btnOderByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByNameActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnOderByName, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 720, 160, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setText("Sắp xếp theo:");
        pnlQuanLyNhanVien.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 730, -1, -1));

        btnOderByNamSinh.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByNamSinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByNamSinh.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByNamSinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_calendar_24px_2.png"))); // NOI18N
        btnOderByNamSinh.setText("Năm sinh");
        btnOderByNamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByNamSinhActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnOderByNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 720, 160, 40));

        btnOderbyLuong.setBackground(new java.awt.Color(51, 102, 255));
        btnOderbyLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderbyLuong.setForeground(new java.awt.Color(255, 255, 255));
        btnOderbyLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_money_24px_1.png"))); // NOI18N
        btnOderbyLuong.setText("Lương");
        btnOderbyLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderbyLuongActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnOderbyLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 720, 160, 40));

        lblNen.setBackground(new java.awt.Color(255, 255, 255));
        lblNen.setForeground(new java.awt.Color(0, 0, 204));
        lblNen.setOpaque(true);
        pnlQuanLyNhanVien.add(lblNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 790));

        pnlChinh.add(pnlQuanLyNhanVien, "card4");

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Mã nhân viên");
        pnlHoaDon.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        txtMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlHoaDon.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 262, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("Mã sản phẩm");
        pnlHoaDon.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        txtMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlHoaDon.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 262, -1));

        txtTenSP.setEditable(false);
        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlHoaDon.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 262, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setText("Tên sản phẩm");
        pnlHoaDon.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setText("Đơn giá");
        pnlHoaDon.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        txtDonGiaHD.setEditable(false);
        txtDonGiaHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlHoaDon.add(txtDonGiaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 262, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("Số lượng ");
        pnlHoaDon.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtThanhTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThanhTienKeyReleased(evt);
            }
        });
        pnlHoaDon.add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 262, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 255));
        jLabel24.setText("Thành tiền");
        pnlHoaDon.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        plnSanPHam4.setBackground(new java.awt.Color(51, 102, 255));

        btnThemHD.setBackground(new java.awt.Color(51, 102, 255));
        btnThemHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemHD.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_add_32px.png"))); // NOI18N
        btnThemHD.setText("Thêm");
        btnThemHD.setOpaque(false);
        btnThemHD.setPreferredSize(new java.awt.Dimension(200, 50));
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        btnXoaHD.setBackground(new java.awt.Color(51, 102, 255));
        btnXoaHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaHD.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_delete_32px.png"))); // NOI18N
        btnXoaHD.setText("Xóa");
        btnXoaHD.setOpaque(false);
        btnXoaHD.setPreferredSize(new java.awt.Dimension(200, 50));
        btnXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDActionPerformed(evt);
            }
        });

        btnSuaHD.setBackground(new java.awt.Color(51, 102, 255));
        btnSuaHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSuaHD.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_edit_32px_1.png"))); // NOI18N
        btnSuaHD.setText("Sửa");
        btnSuaHD.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSuaHD.setOpaque(false);
        btnSuaHD.setPreferredSize(new java.awt.Dimension(200, 50));
        btnSuaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDActionPerformed(evt);
            }
        });

        btnResetHD.setBackground(new java.awt.Color(51, 51, 255));
        btnResetHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetHD.setForeground(new java.awt.Color(255, 255, 255));
        btnResetHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_reset_32px.png"))); // NOI18N
        btnResetHD.setText("Reset");
        btnResetHD.setPreferredSize(new java.awt.Dimension(200, 50));
        btnResetHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetHDActionPerformed(evt);
            }
        });

        btnTimKiemHoaDon.setBackground(new java.awt.Color(51, 102, 255));
        btnTimKiemHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiemHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemHoaDon.setText("Tìm kiếm");
        btnTimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plnSanPHam4Layout = new javax.swing.GroupLayout(plnSanPHam4);
        plnSanPHam4.setLayout(plnSanPHam4Layout);
        plnSanPHam4Layout.setHorizontalGroup(
            plnSanPHam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSanPHam4Layout.createSequentialGroup()
                .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSuaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnResetHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimkiemHD, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        plnSanPHam4Layout.setVerticalGroup(
            plnSanPHam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnSanPHam4Layout.createSequentialGroup()
                .addGroup(plnSanPHam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnSanPHam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSuaHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plnSanPHam4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(plnSanPHam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiemHoaDon)
                            .addComponent(txtTimkiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHoaDon.add(plnSanPHam4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 70));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Mã nhân viên ", "Mã sản phẩm ", "Tên sản phẩm", "Số Lượng ", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        pnlHoaDon.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 730, 390));

        btnPrevHD.setBackground(new java.awt.Color(51, 102, 255));
        btnPrevHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrevHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_rewind_32px.png"))); // NOI18N
        btnPrevHD.setMaximumSize(new java.awt.Dimension(65, 31));
        btnPrevHD.setMinimumSize(new java.awt.Dimension(65, 31));
        btnPrevHD.setPreferredSize(new java.awt.Dimension(100, 35));
        btnPrevHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnPrevHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, 120, 50));

        btnFirstHD.setBackground(new java.awt.Color(51, 102, 255));
        btnFirstHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFirstHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_skip_to_start_32px.png"))); // NOI18N
        btnFirstHD.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFirstHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnFirstHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, 120, 50));

        btnLastHD.setBackground(new java.awt.Color(51, 102, 255));
        btnLastHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLastHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_end_32px.png"))); // NOI18N
        btnLastHD.setPreferredSize(new java.awt.Dimension(100, 35));
        btnLastHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnLastHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 500, 120, 50));

        btnNextHD.setBackground(new java.awt.Color(51, 102, 255));
        btnNextHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNextHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_fast_forward_32px.png"))); // NOI18N
        btnNextHD.setMaximumSize(new java.awt.Dimension(65, 31));
        btnNextHD.setMinimumSize(new java.awt.Dimension(65, 31));
        btnNextHD.setPreferredSize(new java.awt.Dimension(100, 35));
        btnNextHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnNextHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, 120, 50));

        btnTimMaSP.setBackground(new java.awt.Color(51, 51, 255));
        btnTimMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimMaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnTimMaSP.setText("Tìm");
        btnTimMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaSPActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnTimMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 130, -1));

        lblRecordHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRecordHD.setForeground(new java.awt.Color(0, 0, 204));
        lblRecordHD.setText("Record  1 of 10");
        lblRecordHD.setToolTipText("");
        pnlHoaDon.add(lblRecordHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 490, -1, -1));

        btnOderByMaSP.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByMaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByMaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_product_24px.png"))); // NOI18N
        btnOderByMaSP.setText("Mã sản phẩm");
        btnOderByMaSP.setPreferredSize(new java.awt.Dimension(137, 33));
        btnOderByMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByMaSPActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnOderByMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 570, 180, 40));

        btnOderByThanhTien.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByThanhTien.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByThanhTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_money_24px_2.png"))); // NOI18N
        btnOderByThanhTien.setText("Thành tiền");
        btnOderByThanhTien.setPreferredSize(new java.awt.Dimension(137, 33));
        btnOderByThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByThanhTienActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnOderByThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 650, 160, 40));

        btnOderByDonGiaHD.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByDonGiaHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByDonGiaHD.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByDonGiaHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_price_24px.png"))); // NOI18N
        btnOderByDonGiaHD.setText("Đơn giá");
        btnOderByDonGiaHD.setPreferredSize(new java.awt.Dimension(137, 33));
        btnOderByDonGiaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByDonGiaHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnOderByDonGiaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 650, 160, 40));

        btnOderBySoluongHD.setBackground(new java.awt.Color(51, 102, 255));
        btnOderBySoluongHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderBySoluongHD.setForeground(new java.awt.Color(255, 255, 255));
        btnOderBySoluongHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_sort_24px.png"))); // NOI18N
        btnOderBySoluongHD.setText("Số Lượng");
        btnOderBySoluongHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderBySoluongHDActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnOderBySoluongHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 650, 160, 40));

        btnOderByMaNV.setBackground(new java.awt.Color(51, 102, 255));
        btnOderByMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOderByMaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnOderByMaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_24px_1.png"))); // NOI18N
        btnOderByMaNV.setText("Mã nhân viên");
        btnOderByMaNV.setPreferredSize(new java.awt.Dimension(137, 33));
        btnOderByMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOderByMaNVActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnOderByMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 570, 180, 40));

        spnSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        spnSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spnSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSoLuongStateChanged(evt);
            }
        });
        pnlHoaDon.add(spnSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 260, -1));

        pnlChinh.add(pnlHoaDon, "card5");

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(0, 0, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_clock_24px_1.png"))); // NOI18N
        lblClock.setText("10:30 PM");
        jPanel1.add(lblClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 150, 40));

        lblTrangChu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_home_52px.png"))); // NOI18N
        lblTrangChu.setText("Trang chủ");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });
        jPanel1.add(lblTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 80));

        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_52px.png"))); // NOI18N
        lblNhanVien.setText("Nhân viên");
        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
        });
        jPanel1.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 170, 80));

        lblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_notepad_52px.png"))); // NOI18N
        lblHoaDon.setText("Hóa Đơn");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });
        jPanel1.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 170, 80));

        lblSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_product_52px.png"))); // NOI18N
        lblSanPham.setText("Sản Phẩm");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });
        jPanel1.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 170, 80));

        lblThoat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblThoat.setForeground(new java.awt.Color(255, 255, 255));
        lblThoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_exit_52px.png"))); // NOI18N
        lblThoat.setText("Thoát");
        lblThoat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThoatMouseMoved(evt);
            }
        });
        lblThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThoatMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThoatMouseExited(evt);
            }
        });
        jPanel1.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 170, 80));

        lblBackLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBackLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblBackLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_login_52px.png"))); // NOI18N
        lblBackLogin.setText("Đăng Xuất");
        lblBackLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblBackLoginMouseMoved(evt);
            }
        });
        lblBackLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackLoginMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBackLoginMouseExited(evt);
            }
        });
        jPanel1.add(lblBackLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 170, 80));

        lblChao.setBackground(new java.awt.Color(51, 51, 255));
        lblChao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChao.setForeground(new java.awt.Color(255, 255, 255));
        lblChao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_hello_52px.png"))); // NOI18N
        lblChao.setText("Chào bạn ");
        jPanel1.add(lblChao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 220, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    int index = 0;
    private void cboLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLuongItemStateChanged
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongItemStateChanged

    private void cboLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLuongMouseClicked
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
          
            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            
            return;

        } else {

            i = 0;
            showDetail();
            this.updateRecord();
            btnPrev.setEnabled(false);
            btnFirst.setEnabled(false);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            tblNhanVien.setRowSelectionInterval(i, i);
        }

        if (i == 0) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
            tblNhanVien.setRowSelectionInterval(i, i);

        } else {
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            tblNhanVien.setRowSelectionInterval(i, i);
        }
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            return;
        }
        if (i < row) {
            i++;
            this.showDetail();
            this.updateRecord();
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
            tblNhanVien.setRowSelectionInterval(i, i);

        }
        if (i == row - 1) {

            //  JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            return;
        }
        if (i > 0) {
            i--;
            this.showDetail();
            this.updateRecord();
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
            tblNhanVien.setRowSelectionInterval(i, i);

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

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int row = tblNhanVien.getRowCount();
        i = row - 1;
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
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
        tblNhanVien.setRowSelectionInterval(i, i);
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        i = tblNhanVien.getSelectedRow();
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
        showDetail();
        updateRecord();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        try {
            loadDuLieuNhanVien();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi load dữ liệu");
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkBoTrong() && checkTrung()) {
            try {
                addNhanVien();
                i = 0;
                updateRecord();
                suadoi = 1;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi thêm");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            removeNhanVien();
            i = 0;
            updateRecord();
            suadoi = 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (checkBoTrong()) {
            try {
                editTable();
                suadoi = 1;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi cập nhật nhân viên");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtNamSinh.setText("");
        txtSoDT.setText("");
        txtDiaChi.setText("");
        rdoNam.setSelected(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLoad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoad2ActionPerformed
        try {
            loadDuLieuSP();
            suadoi = 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi load sản phẩm");
        }
    }//GEN-LAST:event_btnLoad2ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        if (checkTrungSP() && checkBoTrongSP()) {
            try {
                addSanPham();
                j = 0;
                updateRecord2();
                suadoi = 1;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi thêm sản phẩm");
            }
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        try {
            removeSanPham();
            j = 0;
            updateRecord2();
            suadoi = 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa!");
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        if (checkBoTrongSP()) {
            try {
                editSanPham();
                suadoi = 1;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa");
            }
        }
    }//GEN-LAST:event_btnSua3ActionPerformed

    private void btnReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset2ActionPerformed
        txtMaSP.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtMoTa.setText("");
        cboLoaiSP.setSelectedItem("Cà phê");
    }//GEN-LAST:event_btnReset2ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        j = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getValueAt(j, 0).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(j, 1).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(j, 2).toString());
        txtDonGia.setText(tblSanPham.getValueAt(j, 3).toString());
        cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(j, 4));
        txtMoTa.setText(tblSanPham.getValueAt(j, 5).toString());
        duongDan = tblSanPham.getValueAt(j, 6).toString();
        if (duongDan.equals("")) {
            lblHinhAnh.setText("");
        } else {
            ImageIcon hinhAnh = new ImageIcon(new ImageIcon(duongDan).
                    getImage().getScaledInstance(lblHinhAnh.getWidth(),
                            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
            lblHinhAnh.setIcon(hinhAnh);
        }
        showDeitail2();
        updateRecord2();

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem2ActionPerformed
        timKiemSP();
    }//GEN-LAST:event_btnTimKiem2ActionPerformed

    private void btnResetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseMoved

        btnReset.setBackground(Color.red);
    }//GEN-LAST:event_btnResetMouseMoved

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited

        btnReset.setBackground(new Color(51, 0, 255));
    }//GEN-LAST:event_btnResetMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card2");

        lblTrangChu.setOpaque(true);
        lblTrangChu.setBackground(Color.blue);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(null);
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(null);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(null);


    }//GEN-LAST:event_formWindowOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (suadoi == 0) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa thay đổi dữ liệu !");
                return;
            }
            if (suadoi == 1) {
                luuDuLieuNhanVien();
                loadDuLieuHD();
            }
            suadoi = 2;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi lưu");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnOderByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByNameActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        } else {
            orderByName();
            filltoTableNhanVien();
            showDetail();
        }
    }//GEN-LAST:event_btnOderByNameActionPerformed

    private void btnOderByNamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByNamSinhActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        } else {
            orderByNamSinh();
            filltoTableNhanVien();
            showDetail();
        }
    }//GEN-LAST:event_btnOderByNamSinhActionPerformed

    private void btnOderbyLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderbyLuongActionPerformed
        int row = tblNhanVien.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        } else {
            orderByLuong();
            filltoTableNhanVien();
            showDetail();
        }
    }//GEN-LAST:event_btnOderbyLuongActionPerformed

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        index = 1;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card2");
        if (index == 1) {
            lblTrangChu.setOpaque(true);
            lblTrangChu.setBackground(Color.blue);
            lblNhanVien.setOpaque(false);
            lblNhanVien.setBackground(null);
            lblSanPham.setOpaque(false);
            lblSanPham.setBackground(null);
            lblHoaDon.setOpaque(false);
            lblHoaDon.setBackground(null);
        } else {
            lblTrangChu.setOpaque(false);
        }
        index = 0;
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        index = 2;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card4");
        if (index == 2) {
            lblNhanVien.setOpaque(true);
            lblNhanVien.setBackground(Color.blue);
            lblTrangChu.setOpaque(false);
            lblTrangChu.setBackground(null);
            lblSanPham.setOpaque(false);
            lblSanPham.setBackground(null);
            lblHoaDon.setOpaque(false);
            lblHoaDon.setBackground(null);
        } else {
            lblNhanVien.setOpaque(false);
        }
        index = 0;
    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        index = 5;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card5");
        if (index == 5) {
            lblHoaDon.setOpaque(true);
            lblHoaDon.setBackground(Color.blue);
            lblTrangChu.setOpaque(false);
            lblTrangChu.setBackground(null);
            lblSanPham.setOpaque(false);
            lblSanPham.setBackground(null);
            lblNhanVien.setOpaque(false);
            lblNhanVien.setBackground(null);
        } else {
            lblHoaDon.setOpaque(false);
        }
        index = 0;
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        index = 3;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card3");
        if (index == 3) {
            lblSanPham.setOpaque(true);
            lblSanPham.setBackground(Color.blue);
            lblTrangChu.setOpaque(false);
            lblTrangChu.setBackground(null);
            lblNhanVien.setOpaque(false);
            lblNhanVien.setBackground(null);
            lblHoaDon.setOpaque(false);
            lblHoaDon.setBackground(null);
        } else {
            lblSanPham.setOpaque(false);
            lblSanPham.setBackground(null);
        }
        index = 0;
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn lưu thông tin trước khi thoát ko?", "Thông báo!", JOptionPane.YES_NO_OPTION);

        if (chon == JOptionPane.YES_OPTION) {
            if (suadoi == 0 || suadoi == 2) {
                System.exit(0);
            } else {
                try {
                    luuDuLieuNhanVien();
                    System.exit(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi khi lưu");
                }
                suadoi = 0;
            }

        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_lblThoatMouseClicked

    private void lblBackLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackLoginMouseClicked
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblBackLoginMouseClicked

    private void lblBackLoginMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackLoginMouseMoved
        lblBackLogin.setOpaque(true);
        lblBackLogin.setBackground(new Color(65, 142, 255));
    }//GEN-LAST:event_lblBackLoginMouseMoved

    private void lblBackLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackLoginMouseExited
        lblBackLogin.setOpaque(false);
        lblBackLogin.setBackground(null);
    }//GEN-LAST:event_lblBackLoginMouseExited

    private void lblThoatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseMoved
        lblThoat.setBackground(Color.red);
        lblThoat.setOpaque(true);
    }//GEN-LAST:event_lblThoatMouseMoved

    private void lblThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseExited
        lblThoat.setOpaque(false);
        lblThoat.setBackground(null);
    }//GEN-LAST:event_lblThoatMouseExited

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        try {
            if (suadoi == 0) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa thay đổi dữ liệu !");
                return;
            }
            if (suadoi == 1) {
                SaveSP();
                loadDuLieuHD();
            }
            suadoi = 2;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi lưu");
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void btnFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst1ActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
            

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);

            return;

        } else {

            j = 0;
            showDeitail2();
            this.updateRecord2();
            btnPrev1.setEnabled(false);
            btnFirst1.setEnabled(false);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
        }

        if (j == 0) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirst1.setEnabled(false);
            btnPrev1.setEnabled(false);

        } else {
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
        }
    }//GEN-LAST:event_btnFirst1ActionPerformed

    private void btnPrev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrev1ActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
            return;
        }
        if (j > 0) {
            j--;
            showDeitail2();
            this.updateRecord2();
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);

        }
        if (j == 0) {

            // JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirst1.setEnabled(false);
            btnPrev1.setEnabled(false);

        } else {
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
        }
    }//GEN-LAST:event_btnPrev1ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
            return;
        }
        if (j < row - 1) {
            j++;
            showDeitail2();
            this.updateRecord2();
            btnFirst1.setEnabled(true);
            btnPrev1.setEnabled(true);

        }
        if (j == row - 1) {
            btnFirst1.setEnabled(true);
            btnPrev1.setEnabled(true);
            //  JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLast1.setEnabled(false);
            btnNext1.setEnabled(false);
        } else {
            btnFirst1.setEnabled(true);
            btnPrev1.setEnabled(true);
        }
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast1ActionPerformed
        int row = tblSanPham.getRowCount();
        j = row - 1;
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrev1.setEnabled(true);
            btnFirst1.setEnabled(true);
            btnLast1.setEnabled(true);
            btnNext1.setEnabled(true);
            return;
        }
        if (j == row - 1) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLast1.setEnabled(false);
            btnNext1.setEnabled(false);
            btnFirst1.setEnabled(true);
            btnPrev1.setEnabled(true);
        } else {

            btnFirst1.setEnabled(true);
            btnPrev1.setEnabled(true);
        }

        showDeitail2();
        this.updateRecord2();
    }//GEN-LAST:event_btnLast1ActionPerformed

    private void btnOderByDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByDonGiaActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        } else {
            orderByDonGia();
            filltoTableSanPham();
            showDeitail2();
        }
    }//GEN-LAST:event_btnOderByDonGiaActionPerformed

    private void btnOderBySoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderBySoluongActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu trong bảng");
            return;
        } else {
            orderBySoLuong();
            filltoTableSanPham();
            showDeitail2();
        }
    }//GEN-LAST:event_btnOderBySoluongActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        chonHinh();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        if (checkHD()) {

            try {
                addHoaDon();
                loadDuLieuHD();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi thêm");
            }
        }
    }//GEN-LAST:event_btnThemHDActionPerformed

    private void btnXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDActionPerformed
        try {
            removeHoaDon();
            loadDuLieuHD();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa !");
        }
    }//GEN-LAST:event_btnXoaHDActionPerformed

    private void btnSuaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDActionPerformed
        try {
            if (timKiemNhanVien_HD() && timKiemSP_HD()) {
                editHoaDon();
                loadDuLieuHD();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi sửa !");
        }
    }//GEN-LAST:event_btnSuaHDActionPerformed

    private void btnResetHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetHDActionPerformed
        txtMaNhanVien.setText("");
        txtMaSanPham.setText("");
        txtTenSP.setText("");
        txtDonGiaHD.setText("0");
        int so = 0;
        spnSoLuong.setValue(so);
        txtThanhTien.setText("0");
//        tinhTien();
    }//GEN-LAST:event_btnResetHDActionPerformed

    private void btnPrevHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevHDActionPerformed
        int row = tblSanPham.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
            return;
        }
        if (y > 0) {
            y--;
            showDeitail3();
            this.updateRecord3();
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

        }
        if (y == 0) {

            // JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirstHD.setEnabled(false);
            btnPrevHD.setEnabled(false);

        } else {
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
        }
    }//GEN-LAST:event_btnPrevHDActionPerformed

    private void btnFirstHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstHDActionPerformed
        int row = tblHoaDon.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

            return;

        } else {

            y = 0;
            showDeitail3();
            this.updateRecord3();
            btnPrevHD.setEnabled(false);
            btnFirstHD.setEnabled(false);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
        }

        if (y == 0) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới đầu danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnFirstHD.setEnabled(false);
            btnPrevHD.setEnabled(false);

        } else {
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
        }
    }//GEN-LAST:event_btnFirstHDActionPerformed

    private void btnLastHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHDActionPerformed
        int row = tblHoaDon.getRowCount();
        y = row - 1;
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
            return;
        }
        if (y == row - 1) {

            //JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLastHD.setEnabled(false);
            btnNextHD.setEnabled(false);
            btnFirstHD.setEnabled(true);
            btnPrevHD.setEnabled(true);
        } else {

            btnFirstHD.setEnabled(true);
            btnPrevHD.setEnabled(true);
        }

        showDeitail3();
        this.updateRecord3();
    }//GEN-LAST:event_btnLastHDActionPerformed

    private void btnNextHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextHDActionPerformed
        int row = tblHoaDon.getRowCount();
        if (row == 1) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);

            return;

        }
        if (row == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dữ liệu nào trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnPrevHD.setEnabled(true);
            btnFirstHD.setEnabled(true);
            btnLastHD.setEnabled(true);
            btnNextHD.setEnabled(true);
            return;
        }
        if (y < row) {
            y++;
            showDeitail3();
            this.updateRecord3();
            btnFirstHD.setEnabled(true);
            btnPrevHD.setEnabled(true);

        }
        if (y == row - 1) {

            //  JOptionPane.showMessageDialog(rootPane, "Đã tới cuối danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnLastHD.setEnabled(false);
            btnNextHD.setEnabled(false);
        } else {
            btnFirstHD.setEnabled(true);
            btnPrevHD.setEnabled(true);
        }
    }//GEN-LAST:event_btnNextHDActionPerformed

    private void btnOderByMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByMaSPActionPerformed
        try {
            sapXepHD_MaSP();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOderByMaSPActionPerformed

    private void btnOderByThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByThanhTienActionPerformed
        try {
            sapXepHD_ThanhTien();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOderByThanhTienActionPerformed

    private void btnOderByDonGiaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByDonGiaHDActionPerformed
        try {
            sapXepHD_DonGia();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOderByDonGiaHDActionPerformed

    private void btnOderBySoluongHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderBySoluongHDActionPerformed
        try {
            sapXepHD_SoLuong();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOderBySoluongHDActionPerformed

    private void btnOderByMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOderByMaNVActionPerformed
        try {
            sapXepHD_MaNV();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOderByMaNVActionPerformed

    private void btnTimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemHoaDonActionPerformed

        try {
            timKiem_HoaDon();

        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNang.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTimKiemHoaDonActionPerformed

    private void btnTimMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaSPActionPerformed
        timKiemSP_HD();
        tinhTien();
    }//GEN-LAST:event_btnTimMaSPActionPerformed

    private void spnSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSoLuongStateChanged
        tinhTien();
    }//GEN-LAST:event_spnSoLuongStateChanged

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        y = tblHoaDon.getSelectedRow();
        txtMaNhanVien.setText(tblHoaDon.getValueAt(y, 1).toString());
        txtMaSanPham.setText(tblHoaDon.getValueAt(y, 2).toString());
        timKiemSP_HD();
        String soluong = tblHoaDon.getValueAt(y, 4).toString();
        int sl = 0;
        sl = Integer.parseInt(soluong);
        spnSoLuong.setValue(sl);
        showDeitail3();
        updateRecord3();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtThanhTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThanhTienKeyReleased
        tinhTien();
    }//GEN-LAST:event_txtThanhTienKeyReleased

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
            java.util.logging.Logger.getLogger(MenuChucNang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuChucNang().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(MenuChucNang.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst1;
    private javax.swing.JButton btnFirstHD;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast1;
    private javax.swing.JButton btnLastHD;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoad2;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNextHD;
    private javax.swing.JButton btnOderByDonGia;
    private javax.swing.JButton btnOderByDonGiaHD;
    private javax.swing.JButton btnOderByMaNV;
    private javax.swing.JButton btnOderByMaSP;
    private javax.swing.JButton btnOderByNamSinh;
    private javax.swing.JButton btnOderByName;
    private javax.swing.JButton btnOderBySoluong;
    private javax.swing.JButton btnOderBySoluongHD;
    private javax.swing.JButton btnOderByThanhTien;
    private javax.swing.JButton btnOderbyLuong;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrev1;
    private javax.swing.JButton btnPrevHD;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset2;
    private javax.swing.JButton btnResetHD;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnSuaHD;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem2;
    private javax.swing.JButton btnTimKiemHoaDon;
    private javax.swing.JButton btnTimMaSP;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoaHD;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboLuong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblBackLogin;
    private javax.swing.JLabel lblChao;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHinhNen;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblNen;
    private javax.swing.JLabel lblNenTrangChu;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JLabel lblRecord2;
    private javax.swing.JLabel lblRecordHD;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lbltimKiem;
    private javax.swing.JPanel plnSP1;
    private javax.swing.JPanel plnSP2;
    private javax.swing.JPanel plnSanPHam3;
    private javax.swing.JPanel plnSanPHam4;
    private javax.swing.JPanel pnlChinh;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlQLSP;
    private javax.swing.JPanel pnlQuanLyNhanVien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGiaHD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimkiemHD;
    // End of variables declaration//GEN-END:variables
}
