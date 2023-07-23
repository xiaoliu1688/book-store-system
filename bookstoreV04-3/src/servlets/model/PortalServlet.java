package servlets.model; /**
 * @author 刘翰林
 * @create 2022-10-29 11:31
 */

import bean.Book;
import service.impl.BookServiceImpl;
import servlets.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PortalServlet extends ViewBaseServlet {
    BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> bookList = bookService.getBookList();
            request.setAttribute("bookList",bookList);
            processTemplate("index",request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
