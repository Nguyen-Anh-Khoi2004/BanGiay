package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import raven.application.Application;
import repo.DbConnect;

/**
 *
 * @author Raven
 */
public class SignUpForm extends javax.swing.JPanel {

    Connection cn;

    public SignUpForm() {
        initComponents();
        init();
    }

  private boolean isUsernameExists(String username) {
    try {
        // Tạo truy vấn SQL để kiểm tra xem tên tài khoản đã tồn tại hay chưa
        String sql = "SELECT COUNT(*) FROM taiKhoan WHERE tenDangNhap = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        
        // Nếu kết quả trả về là 0, có nghĩa là tên tài khoản không tồn tại
        // Ngược lại, tên tài khoản đã tồn tại
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

private void insertAccount() {
    try {
        cn = DbConnect.getConnection(); // Tạo kết nối
    } catch (Exception e) {
        e.printStackTrace();
    }
    String ten = txtUser.getText().trim();
    String matKhau = new String(txtPass.getPassword()).trim();
    // Lấy giá trị từ ComboBox
    String selectedValue = (String) cbbVaiTro.getSelectedItem();
    // Ánh xạ giá trị từ ComboBox thành 0 hoặc 1
    int vaiTro = selectedValue.equals("Admin") ? 1 : 0;

    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên không được để trống");
    } else if (matKhau.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
    } else if (isUsernameExists(ten)) {
        JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
    } else {
        try {
            // Thực hiện chèn dữ liệu vào bảng taiKhoan, bao gồm tên đăng nhập, mật khẩu và vai trò
            String sql = "INSERT INTO taiKhoan (tenDangNhap, matKhau, vaiTro) VALUES (?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, matKhau);
            ps.setInt(3, vaiTro);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không thể thêm tài khoản");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    private void init() {
        setLayout(new LoginFormLayout());
        login.setLayout(new LoginLayout());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        login.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;"
                + "border:30,40,50,30");

        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        cmdSignUp.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        cmdSignUp = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btDN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbbVaiTro = new javax.swing.JComboBox<>();

        cmdSignUp.setText("Đăng kí");
        cmdSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSignUpActionPerformed(evt);
            }
        });

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");

        lbUser.setText("Tài khoản:");

        lbPass.setText("Mật khẩu:");

        btDN.setText("Đăng nhập");
        btDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDNActionPerformed(evt);
            }
        });

        jLabel1.setText("Vai trò:");

        cbbVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Nhân viên" }));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lbUser))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPass)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(loginLayout.createSequentialGroup()
                                        .addComponent(cmdSignUp)
                                        .addGap(18, 18, 18)
                                        .addComponent(btDN, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(10, 10, 10)
                .addComponent(lbUser)
                .addGap(5, 5, 5)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbPass)
                .addGap(5, 5, 5)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSignUp)
                    .addComponent(btDN))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSignUpActionPerformed
        insertAccount();
    }//GEN-LAST:event_cmdSignUpActionPerformed

    private void btDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDNActionPerformed
        Application.showLoginForm();
    }//GEN-LAST:event_btDNActionPerformed

    private class LoginFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(320);
                int loginHeight = login.getPreferredSize().height;
                int x = (width - loginWidth) / 2;
                int y = (height - loginHeight) / 2;
                login.setBounds(x, y, loginWidth, loginHeight);
            }
        }
    }

    private class LoginLayout implements LayoutManager {

        private final int titleGap = 10;
        private final int textGap = 10;
        private final int labelGap = 5;
        private final int buttonGap = 10;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;

                height += lbTitle.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                height += lbUser.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtUser.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtPass.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                height += cbbVaiTro.getPreferredSize().height;
                height += cmdSignUp.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                height += btDN.getPreferredSize().height;
                return new Dimension(0, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);

                lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
                y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

                lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
                y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
                txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
                y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
                txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
                y += txtPass.getPreferredSize().height + UIScale.scale(buttonGap);
                cbbVaiTro.setBounds(x, y, width, cbbVaiTro.getPreferredSize().height);
                y += cbbVaiTro.getPreferredSize().height + UIScale.scale(buttonGap);

                cmdSignUp.setBounds(x, y, width, cmdSignUp.getPreferredSize().height);
                btDN.setBounds(x, y + cmdSignUp.getPreferredSize().height + UIScale.scale(buttonGap), width, btDN.getPreferredSize().height);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDN;
    private javax.swing.JComboBox<String> cbbVaiTro;
    private javax.swing.JButton cmdSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel login;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
