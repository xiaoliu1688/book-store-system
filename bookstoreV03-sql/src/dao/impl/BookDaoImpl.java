package dao.impl;

import bean.Book;
import dao.BaseDao;
import dao.BookDao;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> selectBookList() throws SQLException {
        String sql = "select book.book_id bookId,book.book_name bookName,book.author,book.price,other.sales,other.stock,book.img_path imgPath" +
                " from book, other" +
                " where book.book_id = other.b_id" +
                " order by other.sales desc";
        List<Book> list = getList(Book.class, sql);
        return list;
    }

    @Override
    public void removeBook(Integer id) throws SQLException {
        // 存在主从表关系，需先删除other从表，才能删除book主表
        String otherSQL = "delete from other where b_id = ?";
        update(otherSQL, id);

        String bookSQL = "delete from book where book_id = ?";
        update(bookSQL,id);
    }

    @Override
    public void insetBook(Book book) throws SQLException {
        String bookSQL = "insert into book (book_name,author,price,img_path) values (?,?,?,?)";
        update(bookSQL,book.getBookName(),book.getAuthor(),book.getPrice(),book.getImgPath());

        String selectSQL = "select max(book_id) from book";
        //getValue()返回为BigInteger类型，无法直接赋值给Integer类型
        Integer book_id = Integer.valueOf(getValue(selectSQL).toString());

        String otherSQL = "insert into other(b_id, sales, stock) values (?,?,?)";
        update(otherSQL, book_id, book.getSales(), book.getStock());
    }

    @Override
    public Book findBookById(Integer id) throws SQLException {
        String sql = "select book.book_id bookId,book.book_name bookName,book.author,book.price,other.sales,other.stock,book.img_path imgPath" +
                " from book, other" +
                " where book.book_id = other.b_id" +
                " and book.book_id = ?";
        Book book = getBean(Book.class, sql, id);
        return book;
    }
    

    @Override
    public void upData(Book book) throws SQLException {
        String bookSQL = "update book set book_name = ?,author = ?,price = ? where book_id = ?";
        update(bookSQL,book.getBookName(),book.getAuthor(),book.getPrice(),book.getBookId());

        String otherSQL = "update other set sales = ?,stock = ? where b_id = ?";
        update(otherSQL, book.getSales(),book.getStock(), book.getBookId());
    }
}

