package servelets; /**
 * @author 刘翰林
 * @create 2023-01-05 22:22
 */

import bean.User;
import untils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //将Json封装成对象
        Object o = JsonUtils.parseJsonToBean(request, User.class);
        User user = (User) o;
        System.out.println(user);

        //将Java对象转换成Json格式写出给前端
        JsonUtils.writeResult(response,user);


    }
}
