/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import db.DataAccessObject;
import entities.Customer;
import entities.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author phamquangkhang
 */
public class StaffManagement implements ManageAction {

    private final String table_name = "Staff";
    private final DataAccessObject dao;

    public StaffManagement() throws Exception {
        this.dao = new DataAccessObject();
    }

    @Override
    public boolean create(Object o) throws SQLException, Exception {
        String sqlInsert = "INSERT INTO " + table_name
                + " ( [username],[password],[fullname],[dob],[email],[address],[phone_num],[isManager],[gender],[isBlock]) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?) ";

        Staff s = o instanceof Staff ? (Staff) o : null;
        if (s == null) {
            return false;
        }
        return dao.executeSQLwithParams(sqlInsert, DataAccessObject.MODE_UPDATE.SMALL_UPDATE,
                s.getUsername(),
                s.getPassword(),
                s.getName(),
                s.getDob(),
                s.getEmail(),
                s.getAddress(),
                s.getPhoneNumber(), s.getIsManager() ? "1" : "0",
                s.getGender(),
                s.getIsBlock() ? "1" : "0"
        );
    }

    @Override
    public boolean update(Object o) throws SQLException, Exception {
        String sqlUpdate = "UPDATE " + table_name
                + " Set  [password] = ?,[fullname]= ?,[dob]=?,[email]=?,[address]=?,[phone_num]=?,[isManager]=?,[gender]=?,[isBlock]=? "
                + " where  [username] = ? ";

        Staff s = o instanceof Staff ? (Staff) o : null;
        if (s == null) {
            return false;
        }
        return dao.executeSQLwithParams(sqlUpdate, DataAccessObject.MODE_UPDATE.SMALL_UPDATE,
                s.getPassword(),
                s.getName(),
                s.getDob(),
                s.getEmail(),
                s.getAddress(),
                s.getPhoneNumber(), s.getIsManager() ? "1" : "0",
                s.getGender(),
                s.getIsBlock() ? "1" : "0",
                s.getUsername()
        );
    }

    @Override
    public ResultSet select(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Staff getStaffByUsername(String username) throws Exception {
        Staff staff = null;
        String sqlSelect = "Select [username],[password],[fullname],[dob],[email],[address],[phone_num],[isManager],[gender],[isBlock] from staff where username = ?";
        ResultSet rs = dao.getResulSet(sqlSelect, username);
        if (rs.next()) {
            return new Staff(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("dob"), rs.getString("email"), rs.getString("address"),
                    rs.getString("phone_num"), rs.getString("isManager").equalsIgnoreCase("1") ? true : false, rs.getString("gender"), rs.getString("isBlock").equalsIgnoreCase("1") ? true : false);
        }
        return staff;
    }

//    public static void main(String[] args) {
//        try {
//            Staff c = new Staff();
//            c.setAddress("Hà Nội");
//            c.setDob("21/08/1995");
//            c.setEmail("KhangPQ.vn@gmail.com");
//            c.setIsManager(false);
//            c.setName("KhangPQ");
//            c.setPassword("Nh0cdaicA");
//            c.setPhoneNumber("0981604050");
//            c.setUsername("Khang21081995");
//            c.setIsBlock(true);
//            new StaffManagement().create(c);
//            System.out.println("OK");
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
}
