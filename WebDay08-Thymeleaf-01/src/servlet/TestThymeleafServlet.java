package servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 26/9/2022 20 : 06
 */

import bean.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestThymeleafServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //设置请求域的值
        request.setAttribute("name","奥巴马");
        request.setAttribute("aNotnullList", Arrays.asList("a","b","c"));
        request.setAttribute("aNullList", new ArrayList<>());
        HashMap<String, Teacher> map = new HashMap<>();
        map.put("teacherKey",new Teacher("杨振宁"));
        request.setAttribute("student",new Student("刘翰林",new Subject("java"),Arrays.asList(new School("湖南涉外经济学院"),new School("长沙理工大学")),map));
        request.setAttribute("teacherList",Arrays.asList(new Teacher("liu老师"),new Teacher("yan老师")));

        request.setAttribute("user",new User("lever-A"));
        //设置会话域的值
        request.getSession().setAttribute("session","会话域");
        //设置全局域的值
        request.getServletContext().setAttribute("all","全局域");
        processTemplate("target",request,response);

    }
}
