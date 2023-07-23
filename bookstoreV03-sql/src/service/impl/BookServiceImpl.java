package service.impl;

import bean.Book;
import dao.impl.BookDaoImpl;
import service.BookService;

import java.sql.SQLException;
import java.util.List;


public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public List<Book> getBookList() throws SQLException {
        List<bean.Book> bookList = bookDao.selectBookList();
        return bookList;
    }

    @Override
    public void removeBook(Integer id) throws SQLException {
        bookDao.removeBook(id);
    }

    @Override
    public void insertBook(Book book) throws SQLException {
        bookDao.insetBook(book);
    }

    @Override
    public Book findBookById(Integer id) throws SQLException {
        return bookDao.findBookById(id);
    }

    @Override
    public void editBook(Book book) throws SQLException {
        bookDao.upData(book);
    }
}
