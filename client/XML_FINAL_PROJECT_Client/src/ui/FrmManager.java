/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Config.AppConfig;
import bo.AppServices;
import bo.CustomerManagement;
import bo.SystemManagement;
import db.DataAccessObject;
import entities.Customer;
import java.awt.Dialog;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.ir.BreakNode;
import util.Util;

/**
 *
 * @author phamquangkhang
 */
public class FrmManager extends javax.swing.JFrame {

    private SystemManagement sm;
    private CustomerManagement cm;
    private Customer currentCus = null;
    private DefaultTableModel dtm;
    private DataAccessObject dao;

    private final int customerTab = 0;
    private final int staffTab = 2;
    private final int ticketTab = 1;

    private final String[] StaffCol = {"username", "fullname", "gender", "dob", "email", "phone_num", "address", "isBlock", "isManager", "password"};
    private final String[] StaffColRef = {"Tên Tài Khoản", "Họ và Tên", "Giới tính", "Ngày Sinh", "Email", "Số điện thoại", "Địa chỉ", "Tài khoản bị khóa?"};
    private boolean staffIsLoad = false;

    private final String[] CustomerCol = {"cardID", "score", "accountType", "name", "gender", "passport", "dob", "phoneNumber", "email"};
    private final String[] CustomerColRef = {"Mã Thành Viên", "Điểm tích", "Loại Tài Khoản", "Họ và Tên", "Giới tính", "CMND/Passport", "Ngày sinh", "Số điện thoại", "Email"};
    private boolean customerIsLoad = false;

    public String[] currentCol = StaffCol;
    public String[] currentColRef = StaffColRef;
    public String currentTable = "Staff";

    public String currentUser;
    public String currentRole;
    public int currentTab = 0;
    private String currentCardID;
    private HashMap<Integer, ArrayList<String>> map = new HashMap<>();

    /**
     * Creates new form manager_form
     */
    public FrmManager(String username, String role) {
        currentUser = username;
        currentRole = role;
        try {
            dao = new DataAccessObject();
            sm = new SystemManagement();
        } catch (Exception e) {
//            System.out.println("clgv");
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.exit(0);
        }
        initComponents();
        if (currentRole.equalsIgnoreCase("staff")) {
            tabbedPanel.remove(2);
        }
        setLocationRelativeTo(null);
        setResizable(false);
        btnLogedInInfo.setText(role.toUpperCase() + ": " + username);

        setTitle("Hệ thống quản lý rạp CGV");
//        AppServices.wathching();
    }

    public String buildingSQLSelectToBindTable(String[] inArr, String tableName) {
        String ret = "select";
        for (String string : inArr) {
            ret += " " + string + ",";
        }
        ret = ret.substring(0, ret.length() - 1) + " from " + tableName;
        return ret;
    }

    public boolean loadData(String... params) {
        dtm.setNumRows(0);
        dtm.setColumnCount(0);
//        tbStaff.removeAll();
        String sqlSelect = buildingSQLSelectToBindTable(currentCol, currentTable);
        if (currentTab == staffTab) {
            sqlSelect += " where isManager='0'";
        }
        if (params.length != 0) {
            sqlSelect += params[0];
        }
        System.out.println(sqlSelect);
        for (String string : currentColRef) {
            dtm.addColumn(string);
        }
        ArrayList<String> listString = new ArrayList<>();
        try {
//            dao = new DataAccessObject();
            ResultSet rs = dao.getResulSet(sqlSelect);

            while (rs.next()) {
                int count = 0;
                Object[] obj = new Object[currentCol.length];
                for (String string : currentCol) {
                    if (string.toLowerCase().startsWith("is")) {
                        obj[count++] = rs.getString(string).equalsIgnoreCase("0") ? "false" : "true";
                    } else {
                        obj[count++] = rs.getString(string);
                    }
                }
                dtm.addRow(obj);
                listString.add((String) obj[0]);
            }
            map.put(currentTab, listString);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi trong quá trình lấy dữ liệu!!!");
//            JOptionPane.showMessageDialog(this, ex.toString());
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

        tabbedPanel = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCustomer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnIncrease = new javax.swing.JButton();
        btnDecrease = new javax.swing.JButton();
        btnRef = new javax.swing.JButton();
        txtCardID = new javax.swing.JTextField();
        txtScore = new javax.swing.JTextField();
        lblScore = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStaff = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnLogedInInfo = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPanelStateChanged(evt);
            }
        });

        tbCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCustomer.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCustomer.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(tbCustomer);

        tabbedPanel.addTab("Khách Hàng", jScrollPane2);

        btnIncrease.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnIncrease.setText("Tích điểm");
        btnIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncreaseActionPerformed(evt);
            }
        });

        btnDecrease.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDecrease.setText("Đổi điểm");
        btnDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecreaseActionPerformed(evt);
            }
        });

        btnRef.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnRef.setText("Đối mã Khách Hàng");
        btnRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefActionPerformed(evt);
            }
        });

        txtCardID.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCardID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardIDActionPerformed(evt);
            }
        });

        txtScore.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lblScore.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblScore.setText("Phạm Quang Khang: 0 điểm");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Điểm tích/đổi:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnRef, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtScore, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(175, 175, 175))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRef, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtScore, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        tabbedPanel.addTab("Ticket", jPanel1);

        tbStaff.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbStaff.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tbStaff);

        tabbedPanel.addTab("Nhân viên", jScrollPane1);

        btnLogout.setText("Đăng Xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnLogedInInfo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogedInInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogedInInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogedInInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogedInInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        sm.logout(this);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:\
        switch (currentTab) {
            case staffTab:
                DialogStaffInfo staffInfo = new DialogStaffInfo(this, true, "Thêm nhân viên", DialogStaffInfo.MODE_ADD_NEW);
                staffInfo.setVisible(true);
                break;
            case customerTab:
                DialogCustomerInfo customerInfo = new DialogCustomerInfo(this, true, "Thêm Khách Hàng", DialogStaffInfo.MODE_ADD_NEW);
                customerInfo.setVisible(true);
                break;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private int btnShow(int caze) {
        switch (caze) {
            case customerTab:
            case staffTab:
                btnAdd.setVisible(true);
                btnUpdate.setVisible(true);
                btnSearch.setVisible(true);
                txtSearch.setVisible(true);
                break;
            case ticketTab:
                btnAdd.setVisible(false);
                btnUpdate.setVisible(false);
                btnSearch.setVisible(false);
                txtSearch.setVisible(false);
                break;

        }
        return caze;
    }

    private void tabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPanelStateChanged
        // TODO add your handling code here:
        switch ((currentTab = btnShow(tabbedPanel.getSelectedIndex()))) {
            case staffTab:
                dtm = (DefaultTableModel) tbStaff.getModel();
                currentCol = StaffCol;
                currentColRef = StaffColRef;
                currentTable = "Staff";
//                if (!staffIsLoad) {
                loadData();
//                    staffIsLoad = true;
//                }
                break;
            case customerTab:
                dtm = (DefaultTableModel) tbCustomer.getModel();
                currentCol = CustomerCol;
                currentColRef = CustomerColRef;
                currentTable = "Customer";
//                if (!customerIsLoad) {
                loadData();
//                    customerIsLoad = true;
//                }
                break;
            case ticketTab:
                btnRef.setEnabled(true);
                btnDecrease.setEnabled(false);
                btnIncrease.setEnabled(false);
                txtCardID.setText("");
                txtScore.setText("");
                lblScore.setText("");
                break;
        }
    }//GEN-LAST:event_tabbedPanelStateChanged

    private void btnLogedInInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogedInInfoActionPerformed
        // TODO add your handling code here:
        DialogStaffInfo staffInfo = new DialogStaffInfo(this, true, "Cập nhật thông tin cá nhân", DialogStaffInfo.MODE_UPDATE, currentUser, currentRole);
        staffInfo.setVisible(true);
    }//GEN-LAST:event_btnLogedInInfoActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (dtm.getRowCount() > 0) {
            int selectedIndex;
            switch (currentTab) {
                case staffTab:
                    selectedIndex = tbStaff.getSelectedRow();
                    if (selectedIndex == -1) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên cần cập nhật!!!");
                        return;
                    }
                    String username = map.get(currentTab).get(selectedIndex);
                    System.out.println(username);
                    DialogStaffInfo staffInfo = new DialogStaffInfo(this, true, "Cập nhật thông tin nhân viên", DialogStaffInfo.MODE_UPDATE, username);
                    staffInfo.setVisible(true);
                    break;
                case customerTab:
                    selectedIndex = tbCustomer.getSelectedRow();
                    if (selectedIndex == -1) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng cần cập nhật!!!");
                        return;
                    }
                    String customerID = map.get(currentTab).get(selectedIndex);
                    DialogCustomerInfo customerInfo = new DialogCustomerInfo(this, true, "Cập nhật thông tin Khách Hàng", DialogStaffInfo.MODE_UPDATE, customerID);
                    customerInfo.setVisible(true);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hiện tại không tồn tại bản ghi nào để cập nhật!!!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearch.getText();
        String condition = "";
        switch (currentTab) {
            case staffTab:
                condition += " and ( username LIKE '%" + searchText + "%' or fullname like '%" + searchText + "%')";
                break;
            case customerTab:
                condition += " where ( name LIKE '%" + searchText + "%' or cardID like '%" + searchText + "%')";
                break;
        }
        loadData(condition);
    }//GEN-LAST:event_btnSearchActionPerformed


    private void btnRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefActionPerformed
        // TODO add your handling code here:
        currentCardID = txtCardID.getText().trim();
        if (currentCardID.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Mã Khách Hàng không được để trống!!!");
            return;
        }
        try {
            String temp[] = {};
            String temp1[] = {};

            cm = new CustomerManagement();
            cm.makeXMLQuery(currentCardID, "GETDATA", temp, temp1);
            currentCus = cm.getCustomerByCardID(currentCardID);

            if (currentCus != null) {
                btnDecrease.setEnabled(true);
                btnIncrease.setEnabled(true);
                lblScore.setText("Tên Khách Hàng: " + currentCus.getName() + " - " + currentCus.getScore() + " Điểm");
            } else {
//                btnDecrease.setEnabled(true);
                btnIncrease.setEnabled(true);
                lblScore.setText("");
                JOptionPane.showMessageDialog(this, "Hiện tại khách hàng không được tìm thấy tại cơ sở hiện tại. Hãy đợi 10s và tìm lại hoặc tích điểm nếu như hệ thống không thể đồng bộ dữ liệu về máy");
                currentCus = new Customer();
                currentCus.setCardID(currentCardID);
//                currentCus.setName("Chờ update");
//                cm.create(currentCus);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnRefActionPerformed

    private void txtCardIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCardIDActionPerformed

    private void btnIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncreaseActionPerformed
        // TODO add your handling code here:
        try {
            int score = Integer.parseInt(txtScore.getText().trim());
            if (cm.inceaseScore(currentCardID, score)) {
                txtCardID.setText(currentCardID);
                btnRefActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Thành công");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnIncreaseActionPerformed

    private void btnDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecreaseActionPerformed
        // TODO add your handling code here:
        try {
            int score = Integer.parseInt(txtScore.getText().trim());
            if (cm.deceaseScore(currentCardID, score)) {
                txtCardID.setText(currentCardID);
                btnRefActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Thành công");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDecreaseActionPerformed

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
            java.util.logging.Logger.getLogger(FrmManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmManager("abc", "staff").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDecrease;
    private javax.swing.JButton btnIncrease;
    private javax.swing.JButton btnLogedInInfo;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRef;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblScore;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JTable tbCustomer;
    private javax.swing.JTable tbStaff;
    private javax.swing.JTextField txtCardID;
    private javax.swing.JTextField txtScore;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
