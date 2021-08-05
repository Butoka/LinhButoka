package ChuongTrinh;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
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
    int i = 0;

    public MenuChucNang() {
        initComponents();
        dongho();
        nameCollumn();
        showDetail();
        updateRecord();
        sanPham();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //NhanVien
    public void nameCollumn() {
        String[] header = {"Mã NV", "Họ tên", "Năm sinh", "Email", "SDT", "Lương", "Giới tính", "Địa chỉ"};
        String[][] data = {
            {"NV01", "Lê Long Linh", "2000", "linhlelong@gmail.com", "0944694449", "5000000", "Nam", "Cần Thơ"},
            {"NV02", "Trần Khôn", "1999", "khontk@gmail.com", "0944444444", "4000000", "Nam", "An Giang"},
            {"NV03", "Ngô Tất Tố", "2001", "tonntt@gmail.com", "0944444445", "5000000", "Nữ", "Cà Mau"},
            {"NV04", "Hà Hồng", "1997", "hahonghh@gmail.com", "0944444446", "6000000", "Nữ", "Cần Thơ"},
            {"NV05", "Ngưu Thế Đạt", "1993", "datntd97@gmail.com", "0944444447", "5500000", "Nam", "Bạc Liêu"}};
        model1 = new DefaultTableModel(data, header);
        tblNhanVien.setModel(model1);
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

            if (maNV.contains(manv)) {
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
        model1.addRow(dataRow);

    }

    public void removeNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            model1.removeRow(row);
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
                JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin thành công!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Lỗi !!! \n Trùng mã nhân viên!");
            }

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
    //dongho
    Timer thoigian;

    public void dongho() {
        thoigian = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date hientai = new Date();
                SimpleDateFormat dinhdang = new SimpleDateFormat("hh:mm:ss aa");
                lblClock.setText(dinhdang.format(hientai));
            }
        });
        thoigian.start();
    }

    //sanpham
    public void sanPham() {
        String[] headerSP = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn Giá", "Loại sản phẩm", "Mô tả"};
        String[][] dataSP = {{"SP001", "Trà ô long TeaPlus", "20", "10000", "Nước ngọt", "Chai 50ml"},
        {"SP002", "Trà ô long TeaPlus 2", "20", "10000", "Trà", "Chai 50ml"},
        {"SP003", "Cà phê TeaPlus 3", "30", "12000", "Cà phê", "Gói 200g"},
        {"SP004", "Trà ô long TeaPlus 4", "40", "13000", "Trà", "Chai 150ml"},
        {"SP005", "Nước suối TeaPlus 5", "10", "15000", "Nước suối", "Chai 50ml"}};
        model2 = new DefaultTableModel(dataSP, headerSP);
        tblSanPham.setModel(model2);
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
        model2.addRow(dataRow);

    }

    public void removeSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dòng nào trong bảng!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xóa dòng này!", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            model2.removeRow(row);
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
            String maSP = txtMaSP.getText();
            if (maSP.equals(tblSanPham.getValueAt(row, 0))) {
                tblSanPham.setValueAt(txtMaSP.getText(), row, 0);
                tblSanPham.setValueAt(txtTenSanPham.getText(), row, 1);
                tblSanPham.setValueAt(txtSoLuong.getText(), row, 2);
                tblSanPham.setValueAt(txtDonGia.getText(), row, 3);
                tblSanPham.setValueAt(cboLoaiSP.getSelectedItem(), row, 4);
                tblSanPham.setValueAt(txtMoTa.getText(), row, 5);

                JOptionPane.showMessageDialog(rootPane, "Đã sửa thông tin sản phẩm thành công!");
            }else
            {
                JOptionPane.showMessageDialog(rootPane, "Lỗi !\nTrùng mã sản phẩm");
            }

        }
    }

    public void timKiemSP() {

        String MaSanPhamCanTim = txtTimKiem.getText();
        int count = 0;
        if (MaSanPhamCanTim.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sản phẩm cần tìm!");
            txtTimKiem.requestFocus();
            txtTimKiem.setBackground(Color.yellow);
            return;
        } else {
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
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblLogo2 = new javax.swing.JLabel();
        lblNenTrangChu = new javax.swing.JLabel();
        pnlQLSP = new javax.swing.JPanel();
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
        lblNen = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnBackLogin = new javax.swing.JButton();
        lblClock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlChinh.setLayout(new java.awt.CardLayout());

        pnlMenu.setBackground(new java.awt.Color(0, 255, 255));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Phần mềm quản lý quán cà phê");
        pnlMenu.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 550, 1370, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Coffee Management");
        pnlMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 490, 1370, -1));

        lblLogo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffeelogo2.png"))); // NOI18N
        pnlMenu.add(lblLogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1370, 480));

        lblNenTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffeeBackground2.png"))); // NOI18N
        pnlMenu.add(lblNenTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 790));

        pnlChinh.add(pnlMenu, "card2");

        pnlQLSP.setPreferredSize(new java.awt.Dimension(881, 628));
        pnlQLSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        plnSP1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Mã sản phẩm");

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaSP.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenSanPham.setToolTipText("");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSoLuong.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Số lượng");

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDonGia.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Đơn giá");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Loại sản phẩm");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPham)
                                    .addComponent(txtSoLuong))))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
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
                .addGap(32, 32, 32)
                .addGroup(plnSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnSP1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)))
        );

        pnlQLSP.add(plnSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 690));

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
        jScrollPane4.setViewportView(tblSanPham);

        javax.swing.GroupLayout plnSP2Layout = new javax.swing.GroupLayout(plnSP2);
        plnSP2.setLayout(plnSP2Layout);
        plnSP2Layout.setHorizontalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        plnSP2Layout.setVerticalGroup(
            plnSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        pnlQLSP.add(plnSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, 630));

        lbltimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltimKiem.setText("Nhập mã sản phẩm cần tìm");
        pnlQLSP.add(lbltimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });
        pnlQLSP.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 502, 30));

        btnTimKiem2.setBackground(new java.awt.Color(51, 51, 255));
        btnTimKiem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_32px.png"))); // NOI18N
        btnTimKiem2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnTimKiem2.setPreferredSize(new java.awt.Dimension(200, 50));
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });
        pnlQLSP.add(btnTimKiem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 90, 120, 30));

        plnSanPHam3.setBackground(new java.awt.Color(0, 153, 153));

        btnLoad2.setBackground(null);
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

        btnSave2.setBackground(null);
        btnSave2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_save_32px.png"))); // NOI18N
        btnSave2.setText("Save");
        btnSave2.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave2.setOpaque(false);
        btnSave2.setPreferredSize(new java.awt.Dimension(200, 50));

        btnThem2.setBackground(null);
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

        btnXoa2.setBackground(null);
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

        btnSua3.setBackground(null);
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
                .addComponent(btnReset2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        lblHinhNen.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhNen.setOpaque(true);
        pnlQLSP.add(lblHinhNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1370, 720));

        pnlChinh.add(pnlQLSP, "card3");

        pnlQuanLyNhanVien.setBackground(new java.awt.Color(0, 204, 102));
        pnlQuanLyNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Mã nhân viên");
        pnlQuanLyNhanVien.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 88, -1, -1));

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 85, 264, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Họ tên ");
        pnlQuanLyNhanVien.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 70, -1));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 270, -1));

        txtSoDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 280, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Số điện thoại");
        pnlQuanLyNhanVien.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 161, -1));

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
        jLabel7.setText("Địa chỉ:");
        pnlQuanLyNhanVien.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        rdoNam.setBackground(new java.awt.Color(255, 153, 153));
        btgGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(51, 0, 255));
        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);
        pnlQuanLyNhanVien.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        btgGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(51, 0, 255));
        rdoNu.setText("Nữ");
        rdoNu.setOpaque(false);
        pnlQuanLyNhanVien.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        pnlQuanLyNhanVien.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 490, 310, -1));

        txtNamSinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 264, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Năm sinh");
        pnlQuanLyNhanVien.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlQuanLyNhanVien.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 306, -1));

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

        btnFirst.setBackground(new java.awt.Color(153, 153, 153));
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_skip_to_start_32px.png"))); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlQuanLyNhanVien.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 120, 50));

        btnNext.setBackground(new java.awt.Color(153, 153, 153));
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

        btnPrev.setBackground(new java.awt.Color(153, 153, 153));
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

        btnLast.setBackground(new java.awt.Color(153, 153, 153));
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien.setOpaque(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

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

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        btnLoad.setBackground(null);
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

        btnSave.setBackground(null);
        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_save_32px.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 14, 5, 14));
        btnSave.setPreferredSize(new java.awt.Dimension(71, 33));

        btnThem.setBackground(null);
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

        btnXoa.setBackground(null);
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

        btnSua.setBackground(null);
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

        btnTimKiem.setBackground(null);
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

        lblNen.setBackground(new java.awt.Color(255, 255, 255));
        lblNen.setForeground(new java.awt.Color(0, 0, 204));
        lblNen.setOpaque(true);
        pnlQuanLyNhanVien.add(lblNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1370, 810));

        pnlChinh.add(pnlQuanLyNhanVien, "card4");

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Chức năng này đang cập nhật ! :)))");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Lỗi !!!");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/opp.png"))); // NOI18N

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGap(506, 506, 506)
                .addComponent(jLabel19)
                .addContainerGap(544, Short.MAX_VALUE))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel18)
                .addGap(70, 70, 70)
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        pnlChinh.add(pnlHoaDon, "card5");

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(0, 0, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_product_52px.png"))); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        jPanel1.add(btnSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 180, 83));

        btnHome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_home_52px.png"))); // NOI18N
        btnHome.setText("Trang chủ");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 83));

        btnNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_52px.png"))); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 180, 83));

        btnHoaDon.setBackground(null);
        btnHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_notepad_52px.png"))); // NOI18N
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(btnHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 178, 83));

        btnThoat.setBackground(new java.awt.Color(0, 0, 204));
        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_exit_52px.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setBorder(null);
        btnThoat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThoat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnThoatMouseMoved(evt);
            }
        });
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThoatMouseExited(evt);
            }
        });
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 150, 80));

        btnBackLogin.setBackground(null);
        btnBackLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBackLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnBackLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_login_52px.png"))); // NOI18N
        btnBackLogin.setText("Trở lại Form đang nhập");
        btnBackLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBackLoginMouseMoved(evt);
            }
        });
        btnBackLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackLoginMouseExited(evt);
            }
        });
        btnBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnBackLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 280, 80));

        lblClock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_clock_24px_1.png"))); // NOI18N
        lblClock.setText("10:30 PM");
        jPanel1.add(lblClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 160, 40));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    int index = 0;
    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        index = 2;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card4");
        if (index == 2) {
            btnNhanVien.setOpaque(true);
            btnNhanVien.setBackground(Color.blue);
            btnHome.setOpaque(false);
            btnHome.setBackground(null);
            btnSanPham.setOpaque(false);
            btnSanPham.setBackground(null);
            btnHoaDon.setOpaque(false);
            btnHoaDon.setBackground(null);

        } else {
            btnNhanVien.setOpaque(false);
            btnNhanVien.setBackground(null);
        }
        index = 0;


    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        index = 3;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card3");
        if (index == 3) {
            btnSanPham.setOpaque(true);
            btnSanPham.setBackground(Color.blue);
            btnHome.setOpaque(false);
            btnHome.setBackground(null);
            btnNhanVien.setOpaque(false);
            btnNhanVien.setBackground(null);
            btnHoaDon.setOpaque(false);
            btnHoaDon.setBackground(null);
        } else {
            btnSanPham.setOpaque(false);
            btnSanPham.setBackground(null);
        }
        index = 0;
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        index = 1;
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card2");
        if (index == 1) {
            btnHome.setOpaque(true);
            btnHome.setBackground(Color.blue);
            btnNhanVien.setOpaque(false);
            btnNhanVien.setBackground(null);
            btnSanPham.setOpaque(false);
            btnSanPham.setBackground(null);
            btnHoaDon.setOpaque(false);
            btnHoaDon.setBackground(null);
        } else {
            btnHome.setOpaque(false);
            btnHome.setBackground(null);
        }
        index = 0;


    }//GEN-LAST:event_btnHomeActionPerformed

    private void cboLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLuongItemStateChanged
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongItemStateChanged

    private void cboLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLuongMouseClicked
        txtLuong.setText(cboLuong.getSelectedItem().toString());
    }//GEN-LAST:event_cboLuongMouseClicked

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

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed

    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrung() && checkBoTrong()) {
            addNhanVien();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        removeNhanVien();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (checkBoTrong()) {
            editTable();
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
        rdoNam.isSelected();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLoad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoad2ActionPerformed

    }//GEN-LAST:event_btnLoad2ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        if (checkTrungSP()&&checkBoTrongSP()) {
            addSanPham();
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        removeSanPham();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        if (checkBoTrongSP()) {
            editSanPham();
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
        int i = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getValueAt(i, 0).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(i, 1).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(i, 2).toString());
        txtDonGia.setText(tblSanPham.getValueAt(i, 3).toString());
        cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(i, 4));
        txtMoTa.setText(tblSanPham.getValueAt(i, 5).toString());
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

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnThoatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseMoved

        btnThoat.setBackground(Color.red);
    }//GEN-LAST:event_btnThoatMouseMoved

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited

        btnThoat.setBackground(new Color(0, 0, 204));
    }//GEN-LAST:event_btnThoatMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        CardLayout cd = (CardLayout) pnlChinh.getLayout();
        cd.show(pnlChinh, "card2");

        btnHome.setOpaque(true);
        btnHome.setBackground(Color.blue);
        btnNhanVien.setOpaque(false);
        btnNhanVien.setBackground(null);
        btnSanPham.setOpaque(false);
        btnSanPham.setBackground(null);

    }//GEN-LAST:event_formWindowOpened

    private void btnBackLoginMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackLoginMouseMoved
        btnBackLogin.setOpaque(true);
        btnBackLogin.setBackground(Color.green);

    }//GEN-LAST:event_btnBackLoginMouseMoved

    private void btnBackLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackLoginMouseExited
        btnBackLogin.setOpaque(false);
        btnBackLogin.setBackground(null);
    }//GEN-LAST:event_btnBackLoginMouseExited

    private void btnBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackLoginActionPerformed
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackLoginActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        timKiemSP();
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        index = 5;
        CardLayout cl = (CardLayout) pnlChinh.getLayout();
        cl.show(pnlChinh, "card5");
        if (index == 5) {
            btnHoaDon.setOpaque(true);
            btnHoaDon.setBackground(Color.blue);
            btnSanPham.setOpaque(false);
            btnSanPham.setBackground(null);
            btnHome.setOpaque(false);
            btnHome.setBackground(null);
            btnNhanVien.setOpaque(false);
            btnNhanVien.setBackground(null);
        } else {
            btnHoaDon.setOpaque(false);
            btnHoaDon.setBackground(null);
        }

    }//GEN-LAST:event_btnHoaDonActionPerformed

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
            java.util.logging.Logger.getLogger(MenuChucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuChucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuChucNang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.JButton btnBackLogin;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoad2;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset2;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem2;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblHinhNen;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblNen;
    private javax.swing.JLabel lblNenTrangChu;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JLabel lbltimKiem;
    private javax.swing.JPanel plnSP1;
    private javax.swing.JPanel plnSP2;
    private javax.swing.JPanel plnSanPHam3;
    private javax.swing.JPanel pnlChinh;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlQLSP;
    private javax.swing.JPanel pnlQuanLyNhanVien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
