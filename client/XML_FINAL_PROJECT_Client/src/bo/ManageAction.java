/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.sql.ResultSet;

/**
 *
 * @author phamquangkhang
 */
public interface ManageAction {

    public boolean create(Object o) throws Exception;

    public boolean update(Object o) throws Exception;

    public ResultSet select(Object o) throws Exception;
}
