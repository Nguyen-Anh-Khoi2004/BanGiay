package raven.application.form.other;

import DAO.DoanhThuDAO;
import com.formdev.flatlaf.FlatClientProperties;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raven
 */
public class FormDoanhThu extends javax.swing.JPanel {

    DoanhThuDAO dtDAO = new DoanhThuDAO();
    DefaultTableModel defau;

    public FormDoanhThu(String text) {
        initComponents();
        pnDoanhThu.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        loadToday();
    }

    void loadToday() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        lbDoanhThu.setText(dtDAO.DoanhThuToday().toString() + " VND");
        lbDoanhThuThang2.setText(dtDAO.DoanhThuMonth(currentMonth, currentYear).toString() + " VND");
        lbDoanhThuNam.setText(dtDAO.DoanhThuYear(currentYear).toString() + " VND");
        lbTongDoanhThu.setText(dtDAO.TongDoanhThu().toString() + " VND");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        pnDoanhThu = new javax.swing.JPanel();
        lbTongDoanhThu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbDoanhThuNam = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbDoanhThuThang2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbDoanhThu = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTongDoanhThu.setForeground(new java.awt.Color(255, 51, 51));
        lbTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTongDoanhThu.setText("0");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Tổng Doanh Thu:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo năm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        lbDoanhThuNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDoanhThuNam.setForeground(new java.awt.Color(51, 51, 255));
        lbDoanhThuNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDoanhThuNam.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(lbDoanhThuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lbDoanhThuNam)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo tháng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        lbDoanhThuThang2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDoanhThuThang2.setForeground(new java.awt.Color(51, 51, 255));
        lbDoanhThuThang2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDoanhThuThang2.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(lbDoanhThuThang2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lbDoanhThuThang2)
                .addGap(42, 42, 42))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo ngày", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        lbDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDoanhThu.setForeground(new java.awt.Color(51, 51, 255));
        lbDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDoanhThu.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(lbDoanhThu)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout pnDoanhThuLayout = new javax.swing.GroupLayout(pnDoanhThu);
        pnDoanhThu.setLayout(pnDoanhThuLayout);
        pnDoanhThuLayout.setHorizontalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDoanhThuLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDoanhThuLayout.createSequentialGroup()
                        .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(94, 94, 94))
        );
        pnDoanhThuLayout.setVerticalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDoanhThuLayout.createSequentialGroup()
                .addGroup(pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnDoanhThuLayout.createSequentialGroup()
                        .addContainerGap(89, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDoanhThuLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1)
                        .addGap(98, 98, 98)
                        .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(1041, Short.MAX_VALUE)
                .addComponent(lb)
                .addGap(29, 29, 29))
            .addComponent(pnDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbDoanhThu;
    private javax.swing.JLabel lbDoanhThuNam;
    private javax.swing.JLabel lbDoanhThuThang2;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JPanel pnDoanhThu;
    // End of variables declaration//GEN-END:variables
}
