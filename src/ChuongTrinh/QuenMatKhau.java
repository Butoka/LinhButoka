package ChuongTrinh;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class QuenMatKhau extends javax.swing.JFrame {

    Connection ketNoi;
    ArrayList<NguoiDung> listND = new ArrayList<NguoiDung>();
    String username;
    String pass, chucvu;
    NguoiDung nd;

    public QuenMatKhau() throws SQLException {
        initComponents();
        loadDuLieuNguoiDung();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void ketNoiCSDL() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=QuanLyQuanCaPhe";
            String user = "sa";
            String pass = "123";
            ketNoi = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL");
        }

    }

    public void loadDuLieuNguoiDung() throws SQLException {
        ketNoiCSDL();
        String sql = "select * from NguoiDung";
        PreparedStatement cauLenh = ketNoi.prepareStatement(sql);
        ResultSet ketQua = cauLenh.executeQuery();
        while (ketQua.next()) {
            username = ketQua.getString(1);
            pass = ketQua.getString(2);
            chucvu = ketQua.getString(3);
            nd = new NguoiDung(username, pass, chucvu);
            listND.add(nd);

        }

    }
    int dem = 0;

    public void checkUserNamePassWord() {

        for (int i = 0; i < listND.size(); i++) {
            username = listND.get(i).username;
            pass = listND.get(i).password;
            if (username.equals(txtUsername.getText())) {
                lblCheckUsername.setText("");
                dem = 0;
                break;
            }
            if (!username.equals(txtUsername.getText())) {
                dem++;
            }

        }

//        if (dem > 0) {
//            lblCheckUsername.setForeground(new Color(255, 255, 135));
//            lblCheckUsername.setText("Username không tồn tại");
//            return;
//        }
    }

    public void doiMatKhau() throws SQLException {

        ketNoiCSDL();
        String sql = "update NguoiDung set Password = ? where Username = ?";
        PreparedStatement cauLenh = ketNoi.prepareStatement(sql);
        cauLenh.setString(1, pswNewPass.getText());
        cauLenh.setString(2, txtUsername.getText());
        cauLenh.executeUpdate();

    }

    public boolean check() throws SQLException {
        int loi = 0;
        checkUserNamePassWord();
        if (txtUsername.getText().equals("Username") || txtUsername.getText().isEmpty()) {
            lblCheckUsername.setForeground(new Color(84, 255, 118));
            lblCheckUsername.setText("Bạn chưa nhập username!");
            loi++;
        } else {
            lblCheckUsername.setText("");
        }

        if (pswNewPass.getText().equals("00000000") || pswNewPass.getText().isEmpty()) {
            lblCheckNewPass.setForeground(new Color(84, 255, 118));
            lblCheckNewPass.setText("Bạn chưa nhập new password!");
            loi++;
        } else if (pswNewPass.getText().length() < 3) {
            lblCheckNewPass.setForeground(new Color(84, 255, 118));
            lblCheckNewPass.setText("Password phải có ít nhất trên 3 ký tự!");
        } else {
            lblCheckNewPass.setText("");
        }
        if (pswComfirmPass.getText().equals("00000000") || pswComfirmPass.getText().isEmpty()) {
            lblCheckComfirmPass.setForeground(new Color(84, 255, 118));
            lblCheckComfirmPass.setText("Bạn chưa nhập lại new password!");
            loi++;
        } else if (pswComfirmPass.getText().length() < 3) {
            lblCheckComfirmPass.setForeground(new Color(84, 255, 118));
            lblCheckComfirmPass.setText("Password phải có ít nhất trên 3 ký tự!");
            loi++;
        } else if (!pswComfirmPass.getText().equals(pswNewPass.getText())) {
            lblCheckComfirmPass.setForeground(new Color(84, 255, 118));
            lblCheckComfirmPass.setText("<html>Mật khẩu không trùng khớp ! Vui lòng nhập lại<br> mật khẩu !</html>");
            loi++;
        } else {
            lblCheckComfirmPass.setText("");
        }
        if (loi > 0) {
            return false;
        }
        if (dem > 0) {
            lblCheckUsername.setForeground(new Color(84, 255, 118));
            lblCheckUsername.setText("Username không tồn tại");
            return false;
        } else {
            lblCheckUsername.setText("");
        }
        if (pswComfirmPass.getText().equals(pswNewPass.getText())) {
            int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn đổi mật khẩu", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                doiMatKhau();
                JOptionPane.showMessageDialog(rootPane, "Đổi mật khẩu thành công");
                new DangNhap().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Mật khẩu chưa thay đổi");
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

        lblCheckUsername = new javax.swing.JLabel();
        lblCheckNewPass = new javax.swing.JLabel();
        lblCheckComfirmPass = new javax.swing.JLabel();
        lblHienComfirmPass = new javax.swing.JLabel();
        lblHienNewPass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        pswNewPass = new javax.swing.JPasswordField();
        pswComfirmPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        lblBackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCheckUsername.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(lblCheckUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 410, 20));

        lblCheckNewPass.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(lblCheckNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 410, 20));

        lblCheckComfirmPass.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(lblCheckComfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 410, 50));

        lblHienComfirmPass.setForeground(new java.awt.Color(38, 97, 215));
        lblHienComfirmPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        lblHienComfirmPass.setText("Hien");
        lblHienComfirmPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHienComfirmPassMouseClicked(evt);
            }
        });
        getContentPane().add(lblHienComfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 30, -1));

        lblHienNewPass.setBackground(new java.awt.Color(255, 255, 255));
        lblHienNewPass.setForeground(new java.awt.Color(38, 97, 215));
        lblHienNewPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        lblHienNewPass.setText("Hien");
        lblHienNewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHienNewPassMouseClicked(evt);
            }
        });
        getContentPane().add(lblHienNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 30, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_24px.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_password_24px.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_password_1_24px.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        btnCancel.setBackground(new java.awt.Color(0, 204, 102));
        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_cancel_32px.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setMaximumSize(new java.awt.Dimension(69, 31));
        btnCancel.setMinimumSize(new java.awt.Dimension(69, 31));
        btnCancel.setPreferredSize(new java.awt.Dimension(69, 31));
        btnCancel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCancelMouseMoved(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 130, 40));

        btnDoiMatKhau.setBackground(new java.awt.Color(255, 84, 21));
        btnDoiMatKhau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseMoved(evt);
            }
        });
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseExited(evt);
            }
        });
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        getContentPane().add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 150, 40));

        btnBack.setBackground(new java.awt.Color(37, 110, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorder(null);
        btnBack.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBackMouseMoved(evt);
            }
        });
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 130, 40));

        txtUsername.setBackground(new java.awt.Color(38, 97, 215));
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setText("Username");
        txtUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 410, -1));

        pswNewPass.setBackground(new java.awt.Color(38, 97, 215));
        pswNewPass.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pswNewPass.setForeground(new java.awt.Color(255, 255, 255));
        pswNewPass.setText("00000000");
        pswNewPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        pswNewPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pswNewPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswNewPassFocusLost(evt);
            }
        });
        getContentPane().add(pswNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 410, -1));

        pswComfirmPass.setBackground(new java.awt.Color(38, 97, 215));
        pswComfirmPass.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pswComfirmPass.setForeground(new java.awt.Color(255, 255, 255));
        pswComfirmPass.setText("00000000");
        pswComfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        pswComfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pswComfirmPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswComfirmPassFocusLost(evt);
            }
        });
        getContentPane().add(pswComfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 410, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 147));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoForgetPass.jpg"))); // NOI18N
        jLabel4.setText("CHANGE PASSWORD");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 40));

        lblBackGround.setBackground(new java.awt.Color(38, 97, 215));
        lblBackGround.setOpaque(true);
        getContentPane().add(lblBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        try {
            if (check()) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi load csdl");
        }
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void lblHienNewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHienNewPassMouseClicked
        if (lblHienNewPass.getText().equals("Hien")) {
            pswNewPass.setEchoChar((char) 0);
            lblHienNewPass.setText("An");
            lblHienNewPass.setIcon(new ImageIcon(getClass().getResource("/images/hidePass.png")));

        } else if (lblHienNewPass.getText().equals("An")) {
            pswNewPass.setEchoChar('*');
            lblHienNewPass.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
            lblHienNewPass.setText("Hien");
        }
    }//GEN-LAST:event_lblHienNewPassMouseClicked

    private void lblHienComfirmPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHienComfirmPassMouseClicked
        if (lblHienComfirmPass.getText().equals("Hien")) {
            pswComfirmPass.setEchoChar((char) 0);
            lblHienComfirmPass.setText("An");
            lblHienComfirmPass.setIcon(new ImageIcon(getClass().getResource("/images/hidePass.png")));

        } else if (lblHienComfirmPass.getText().equals("An")) {
            pswComfirmPass.setEchoChar('*');
            lblHienComfirmPass.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
            lblHienComfirmPass.setText("Hien");
        }
    }//GEN-LAST:event_lblHienComfirmPassMouseClicked

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        if (txtUsername.getText().equals("Username")) {
            txtUsername.setText("");
            txtUsername.requestFocus();

        }
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        if (txtUsername.getText().isEmpty()) {
            txtUsername.setText("Username");

        }
    }//GEN-LAST:event_txtUsernameFocusLost

    private void pswNewPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswNewPassFocusGained
        if (pswNewPass.getText().equals("00000000")) {
            pswNewPass.setText("");
            pswNewPass.requestFocus();

        }
    }//GEN-LAST:event_pswNewPassFocusGained

    private void pswNewPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswNewPassFocusLost
        if (pswNewPass.getText().isEmpty()) {
            pswNewPass.setText("00000000");
        }

    }//GEN-LAST:event_pswNewPassFocusLost

    private void pswComfirmPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswComfirmPassFocusGained
        if (pswComfirmPass.getText().equals("00000000")) {
            pswComfirmPass.setText("");
            pswComfirmPass.requestFocus();

        }
    }//GEN-LAST:event_pswComfirmPassFocusGained

    private void pswComfirmPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswComfirmPassFocusLost
        if (pswComfirmPass.getText().isEmpty()) {
            pswComfirmPass.setText("00000000");
        }
    }//GEN-LAST:event_pswComfirmPassFocusLost

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBackMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseMoved
        btnBack.setBackground(new Color(0, 85, 255));
    }//GEN-LAST:event_btnBackMouseMoved

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        btnBack.setBackground(new Color(37, 110, 255));
    }//GEN-LAST:event_btnBackMouseExited

    private void btnDoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseExited
        btnDoiMatKhau.setBackground(new Color(255, 84, 21));
    }//GEN-LAST:event_btnDoiMatKhauMouseExited

    private void btnDoiMatKhauMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseMoved
        btnDoiMatKhau.setBackground(new Color(255, 63, 0));
    }//GEN-LAST:event_btnDoiMatKhauMouseMoved

    private void btnCancelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseMoved
        btnCancel.setBackground(new Color(0, 182, 91));
    }//GEN-LAST:event_btnCancelMouseMoved

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QuenMatKhau().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBackGround;
    private javax.swing.JLabel lblCheckComfirmPass;
    private javax.swing.JLabel lblCheckNewPass;
    private javax.swing.JLabel lblCheckUsername;
    private javax.swing.JLabel lblHienComfirmPass;
    private javax.swing.JLabel lblHienNewPass;
    private javax.swing.JPasswordField pswComfirmPass;
    private javax.swing.JPasswordField pswNewPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
