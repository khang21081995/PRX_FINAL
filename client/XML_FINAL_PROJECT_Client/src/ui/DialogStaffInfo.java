/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bo.StaffManagement;
import entities.Staff;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import util.Util;

/**
 *
 * @author phamquangkhang
 */
public class DialogStaffInfo extends javax.swing.JDialog {

    /**
     * Creates new form DialogStaffInfo
     */
    private JFrame parent;
    private String[] params;
    
    public static final String MODE_UPDATE = "1";
    public static final String MODE_ADD_NEW = "0";
    private String mode;
    private Staff staff;
    
    private StaffManagement sm;
    
    public DialogStaffInfo(JFrame parent, boolean modal, String... params) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setResizable(false);
        try {
            sm = new StaffManagement();
            setTitle(params[0]);
            this.mode = params[1];
            clearText();
            if (this.mode.equals(MODE_UPDATE)) {
                staff = sm.getStaffByUsername(params[2]);
                txtAcc.setText(staff.getUsername());
                txtAcc.setEnabled(false);
                txtPass.setText(staff.getPassword());
                txtFullName.setText(staff.getName());
                txtDOB.setText(staff.getDob());
                txtAddress.setText(staff.getAddress());
                txtEmail.setText(staff.getEmail());
                txtPhone.setText(staff.getPhoneNumber());
                
                Util.setSelectedButtonText(staff.getGender().trim(), rdbFemale, rdbMale, rdbOthers);
                Util.setSelectedButtonText(staff.getIsBlock() ? "Khóa" : "Mở", rdbUnBlock, rdbBlock);
                Util.setSelectedButtonText(staff.getIsManager() ? "Quản Lý" : "Nhân Viên", rdbStaff, rdbManager);
                try {
                    if (params[3] != null) {
                        rdbBlock.setEnabled(false);
                        rdbUnBlock.setEnabled(false);
                        rdbStaff.setEnabled(false);
                        rdbManager.setEnabled(false);
                    }
                } catch (Exception e) {
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        this.parent = parent;
        this.params = params;
    }
    
    private void clearText() {
        rdbUnBlock.setSelected(true);
        rdbOthers.setSelected(true);
        rdbStaff.setSelected(true);
        txtAcc.setText("");
        txtPass.setText("");
        txtAddress.setText("");
//        txtDOB.setText("dd/MM/yyyy");
        txtEmail.setText("");
        txtFullName.setText("");
        txtPhone.setText("");
    }
    
    private boolean update_staff() {
        try {
            if (!staff.getPassword().equals(txtPass.getText())) {
                staff.setPassword(txtPass.getText());
            }
            staff.setAddress(txtAddress.getText());
            staff.setDob(txtDOB.getText());
            staff.setEmail(txtEmail.getText());
            staff.setGender(Util.getSelectedButtonText(btnGroupGender));
            staff.setIsBlock(Util.getSelectedButtonText(btnGroupIsBlock).equals("Khóa") ? true : false);
            staff.setIsManager(Util.getSelectedButtonText(btnGroupRole).equals("Quản Lý") ? true : false);
            staff.setName(txtFullName.getText());
            staff.setPhoneNumber(txtPhone.getText());
            return sm.update(staff);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return false;
        }
        
    }
    
    private boolean addNewStaff() {
        try {
            staff = new Staff();
            staff.setUsername(txtAcc.getText());
            staff.setPassword(txtPass.getText());
            staff.setAddress(txtAddress.getText());
            staff.setDob(txtDOB.getText());
            staff.setEmail(txtEmail.getText());
            staff.setGender(Util.getSelectedButtonText(btnGroupGender));
            staff.setIsBlock(Util.getSelectedButtonText(btnGroupIsBlock).equals("Khóa") ? true : false);
            staff.setIsManager(Util.getSelectedButtonText(btnGroupRole).equals("Quản Lý") ? true : false);
            staff.setName(txtFullName.getText());
            staff.setPhoneNumber(txtPhone.getText());
            return sm.create(staff);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return false;
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

        btnGroupGender = new javax.swing.ButtonGroup();
        btnGroupIsBlock = new javax.swing.ButtonGroup();
        btnGroupRole = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtAcc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdbMale = new javax.swing.JRadioButton();
        rdbFemale = new javax.swing.JRadioButton();
        rdbOthers = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        rdbBlock = new javax.swing.JRadioButton();
        rdbUnBlock = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        rdbManager = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rdbStaff = new javax.swing.JRadioButton();
        btnAction = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tài Khoản:");

        jLabel2.setText("Mật Khẩu:");

        jLabel3.setText("Họ và Tên:");

        jLabel4.setText("Giới Tính: ");

        btnGroupGender.add(rdbMale);
        rdbMale.setText("Nam");

        btnGroupGender.add(rdbFemale);
        rdbFemale.setText("Nữ");

        btnGroupGender.add(rdbOthers);
        rdbOthers.setSelected(true);
        rdbOthers.setText("Khác");

        jLabel5.setText("Ngày Sinh: ");

        txtDOB.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtDOB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDOBFocusGained(evt);
            }
        });
        txtDOB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtDOBMouseEntered(evt);
            }
        });

        jLabel6.setText("Email:");

        jLabel7.setText("Điện Thoại: ");

        jLabel8.setText("Địa Chỉ: ");

        btnGroupIsBlock.add(rdbBlock);
        rdbBlock.setText("Khóa");

        btnGroupIsBlock.add(rdbUnBlock);
        rdbUnBlock.setSelected(true);
        rdbUnBlock.setText("Mở");

        jLabel9.setText("Khóa TK: ");

        btnGroupRole.add(rdbManager);
        rdbManager.setText("Quản Lý");

        jLabel10.setText("Chức vị:");

        btnGroupRole.add(rdbStaff);
        rdbStaff.setSelected(true);
        rdbStaff.setText("Nhân Viên");
        rdbStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbStaffActionPerformed(evt);
            }
        });

        btnAction.setText("Thực Hiện");
        btnAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionActionPerformed(evt);
            }
        });

        btnCancel.setText("Đóng");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdbMale)
                                .addGap(18, 18, 18)
                                .addComponent(rdbFemale)
                                .addGap(18, 18, 18)
                                .addComponent(rdbOthers)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtAcc)
                            .addComponent(txtFullName)
                            .addComponent(txtPass)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDOB)
                            .addComponent(txtEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAction)
                                .addGap(52, 52, 52)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtAddress)
                            .addComponent(txtPhone)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdbBlock)
                                    .addComponent(rdbManager))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdbStaff)
                                        .addGap(0, 86, Short.MAX_VALUE))
                                    .addComponent(rdbUnBlock))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdbMale)
                    .addComponent(rdbFemale)
                    .addComponent(rdbOthers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbBlock)
                    .addComponent(rdbUnBlock)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbManager)
                    .addComponent(rdbStaff))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAction)
                    .addComponent(btnCancel))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDOBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOBFocusGained
        // TODO add your handling code here:
//        txtDOB.setText("");
//        DatePicker picker = new DatePicker(LocalDate.now());
//        picker.setVisible(true);
//        picker.ad
    }//GEN-LAST:event_txtDOBFocusGained

    private void rdbStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbStaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbStaffActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionActionPerformed
        // TODO add your handling code here:
        if (mode == MODE_UPDATE) {
            if (update_staff()) {
                ((FrmManager) parent).loadData();
                btnCancelActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!!!");
            } else {
//                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
        if (mode == MODE_ADD_NEW) {
            if (addNewStaff()) {
                ((FrmManager) parent).loadData();
                btnCancelActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!!!");
            } else {
//                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
        

    }//GEN-LAST:event_btnActionActionPerformed

    private void txtDOBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDOBMouseEntered
        // TODO add your handling code here:
//        txtDOB.setText("");
    }//GEN-LAST:event_txtDOBMouseEntered

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
            java.util.logging.Logger.getLogger(DialogStaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogStaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogStaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogStaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogStaffInfo dialog = new DialogStaffInfo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup btnGroupGender;
    private javax.swing.ButtonGroup btnGroupIsBlock;
    private javax.swing.ButtonGroup btnGroupRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rdbBlock;
    private javax.swing.JRadioButton rdbFemale;
    private javax.swing.JRadioButton rdbMale;
    private javax.swing.JRadioButton rdbManager;
    private javax.swing.JRadioButton rdbOthers;
    private javax.swing.JRadioButton rdbStaff;
    private javax.swing.JRadioButton rdbUnBlock;
    private javax.swing.JTextField txtAcc;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JFormattedTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables

}
