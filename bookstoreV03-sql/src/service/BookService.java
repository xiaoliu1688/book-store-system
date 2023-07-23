package service;

import bean.Book;

import java.sql.SQLException;
import java.util.List;


public interface BookService {
    /**
     * 查询所有图书
     * @date
     * @author
     * @return
     * @throws
     */
     List<Book> getBookList() throws SQLException;

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
    void insertBook(Book book) throws SQLException;

    /**
     * 查找图书
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
    void editBook(Book book) throws SQLException;
}
