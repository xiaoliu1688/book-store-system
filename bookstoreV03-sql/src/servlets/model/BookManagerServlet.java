package servlets.model;


import bean.Book;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.BookServiceImpl;
import servlets.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookManagerServlet extends ModelBaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 跳转回系统管理界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toManagerPage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        processTemplate("manager/manager",request,response);
    }



    /**
     * 跳转到图书管理界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toBookManagerPage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        //显示所有的图书
        List<Book> bookList = bookService.getBookList();
        //将所有的图书存储到请求域对象中
        request.setAttribute("bookList",bookList);

        processTemplate("manager/book_manager",request,response);
    }

    /**
     * 删除图书
     * @date
     * @author
     * @return
     * @throws
     */
    public void removeBook(HttpServletRequest request, HttpServletResponse response){
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        try {
            bookService.removeBook(id);
            //删除成功重新跳转到图书管理界面
            response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到添加图书
     * @date
     * @author
     * @return
     * @throws
     */
    public void toAddPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("manager/book_edit",request,response);
    }

    /**
     * 添加图书
     * @date
     * @author
     * @return
     * @throws
     */

    public void insertOrUpDataBook(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1、获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Book book = new Book();
        try {
            //将map的数据封装到book中
            BeanUtils.populate(book,parameterMap);
            if(book.getBookId() !=null && !"".equals(book.getBookId())){
                //修改图书
                bookService.editBook(book);
            }else {
                //添加图书
                book.setImgPath("static/uploads/xiaowangzi.jpg");
                bookService.insertBook(book);
            }

            //插入成功跳转到显示图书界面,使用重定向
            response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void change(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1、先查出该书的信息
        Integer id = Integer.valueOf(request.getParameter("id"));
        try {
            //得到book
            Book book = bookService.findBookById(id);
            //把book对象存入请求域中
            request.setAttribute("book",book);
            //再跳转到图书编辑界面中
            processTemplate("manager/book_edit",request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
