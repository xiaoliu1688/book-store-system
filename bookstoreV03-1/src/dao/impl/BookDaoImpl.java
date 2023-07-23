package dao.impl;

import bean.Book;
import dao.BaseDao;
import dao.BookDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 刘翰林
 * @create 2022-10-31 9:37
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> selectBookList() throws SQLException {
        String sql = "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from t_book";
        List<Book> list = getList(Book.class, sql);
        return list;
    }

    @Override
    public void removeBook(Integer id) throws SQLException {
        String sql = "delete from t_book where book_id = ?";
        update(sql,id);
    }

    @Override
    public void insetBook(Book book) throws SQLException {
        String sql = "insert into t_book (book_name,author,price,sales,stock,img_path) values (?,?,?,?,?,?)";
        update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public Book findBookById(Integer id) throws SQLException {
        String sql = "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from t_book where book_id = ?";
        Book book = getBean(Book.class, sql, id);
        return book;
    }

    @Override
    public void upData(Book book) throws SQLException {
        String sql = "update t_book set book_name = ?,author = ?,price = ?,sales = ?,stock = ? where book_id = ?";
        update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getBookId());
    }
}
