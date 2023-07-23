package dao.impl;

import bean.Soldier;
import dao.BaseDao;
import dao.SoldierDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 9/10/2022 16 : 31
 */
public class SoldierDaoImpl extends BaseDao implements SoldierDao {
    @Override
    public List<Soldier> findAll() throws SQLException {
        //持久层代码
        String sql = "select soldier_id soldierId,soldier_name soldierName,soldier_weapon soldierWeapon from t_soldier";
        return getBeanList(Soldier.class, sql);
    }

    @Override
    public void addSolder(Soldier soldier) throws SQLException {
        String sql = "insert into t_soldier (soldier_name,soldier_weapon)  values(?,?)";
        update(sql,soldier.getSoldierName(),soldier.getSoldierWeapon());
    }

    @Override
    public void deleteSoldier(int id) throws SQLException {
        String sql = "delete from t_soldier where soldier_Id = ?";
        update(sql,id);
    }

    @Override
    public Soldier findSoldier(int id) throws SQLException {
        String sql = "select soldier_id soldierId,soldier_name soldierName,soldier_weapon soldierWeapon from t_soldier where soldier_id =?";
        Soldier soldier = (Soldier) getBean(Soldier.class,sql,id);
        return soldier;
    }

    @Override
    public void update(Soldier soldier) throws SQLException {
        String sql = "update t_soldier set soldier_id = ?, soldier_name = ?, soldier_weapon = ? where soldier_id = ?";
        update(sql,soldier.getSoldierId(),soldier.getSoldierName(),soldier.getSoldierWeapon(),soldier.getSoldierId());
    }
}