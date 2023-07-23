package service.impl;

import bean.Soldier;
import dao.SoldierDao;
import dao.impl.SoldierDaoImpl;
import service.SoldierService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 9/10/2022 16 : 54
 */
public class SoldierServiceImpl implements SoldierService {
    SoldierDao soldierDao = new SoldierDaoImpl();

    /**
     * 查询所有的士兵信息
     * @return
     * @throws SQLException
     */
    @Override
    //业务逻辑层代码
    public List<Soldier> findAllSoldier() throws SQLException {
        return soldierDao.findAll();
    }

    /**
     * 添加士兵信息
     * @date
     * @author
     * @return
     * @throws
     */
    @Override
    public void addSolder(Soldier soldier) throws SQLException {
        soldierDao.addSolder(soldier);
    }

    @Override
    public void deleteSoldier(int id) throws SQLException {
        soldierDao.deleteSoldier(id);
    }

    @Override
    public Soldier findSoldier(int id) throws SQLException {
        return soldierDao.findSoldier(id);

    }

    @Override
    public void update(Soldier soldier) throws SQLException {
        soldierDao.update(soldier);
    }


}