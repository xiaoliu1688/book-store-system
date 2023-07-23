package service;

import bean.Soldier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 9/10/2022 16 : 52
 */
public interface SoldierService {
    /**
     * 查询所有的士兵信息
     * @return
     * @throws SQLException
     */
    List<Soldier> findAllSoldier() throws SQLException;

    /**
     * 添加士兵信息
     * @date
     * @author
     * @return
     * @throws
     */
    void addSolder(Soldier soldier) throws SQLException;

    /**
     * 删除士兵信息
     * @date
     * @author
     * @return
     * @throws
     */
    void deleteSoldier(int id) throws SQLException;

    /**
     * 获取单个士兵信息
     * @date
     * @author
     * @return
     * @throws
     */
    Soldier findSoldier(int id) throws SQLException;

    /**
     * 修改士兵信息
     * @date
     * @author
     * @return
     * @throws
     */
    void update(Soldier soldier) throws SQLException;
}
