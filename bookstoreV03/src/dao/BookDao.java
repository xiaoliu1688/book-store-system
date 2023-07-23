package dao;

import bean.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 刘翰林
 * @create 2022-10-31 9:33
 */
public interface BookDao {
    /**
     * 查询所有图书
     * @date
     * @author
     * @return
     * @throws
     */
    List<Book> selectBookList() throws SQLException;

    /**
     * 删除图书
     * @date
     * @author
     * @return
     * @throws
     */
    void removeBook(Integer id) throws SQLException;

    /**
     * 添加图书
     * @date
     * @author
     * @return
     * @throws
     */
    void insetBook(Book book) throws SQLException;

    /**
     * 根据图书id查找图书
     * @date
     * @author
     * @return
     * @throws
     */
    Book findBookById(Integer id) throws SQLException;

    /**
     * 修改图书
     * @date
     * @author
     * @return
     * @throws
     */
    void upData(Book book) throws SQLException;

}
