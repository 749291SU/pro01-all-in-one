package com.siwen;

import com.siwen.imperial.court.dao.BaseDao;
import com.siwen.imperial.court.entity.Emp;
import com.siwen.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @projectName: pro01-all-in-one-demo
 * @package: com.siwen
 * @className: ImperialCourtTest
 * @author: 749291
 * @description: TODO
 * @date: 4/10/2023 7:27 PM
 * @version: 1.0
 */

public class ImperialCourtTest {
//    @Test
//    public void testGetConnection() {
//        Connection connection = JDBCUtils.getConnection();
////        System.out.println("connection = " + connection);
//        JDBCUtils.releaseConnection(connection);
//    }
    private BaseDao<Emp> baseDao = new BaseDao<>();

    @Test
    public void testGetSingleBean() {

        String sql = "SELECT emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword FROM t_emp WHERE emp_id=?";

        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);

        System.out.println("emp = " + emp);
    }

    @Test
    public void testGetBeanList() {

        String sql = "SELECT emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword FROM t_emp";

        List<Emp> empList = baseDao.getBeanList(sql, Emp.class);

        for (Emp emp : empList) {
            System.out.println("emp = " + emp);
        }

    }

    @Test
    public void testUpdate() {

        String sql = "UPDATE t_emp set emp_position=? WHERE emp_id=?";

        String empPosition = "minister";
        String empId = "3";

        int affectedRowNumber = baseDao.update(sql, empPosition, empId);

        System.out.println("affectedRowNumber = " + affectedRowNumber);

    }
}
