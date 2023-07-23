package ServletDemos; /**
 * @author:刘翰林
 * @Description:
 * @Date: 18/9/2022 13 : 00
 * Servlet的作用:
 *  1. 接收请求: 获取请求行、头、体的数据，请求的内容是封装在request对象中的
 *     我们要获取请求行、头、体的内容，就只需要调用request对象的getXXX()方法
 *  2. 处理请求: 处理业务逻辑（功能）
 *  3. 做出响应: 把客户端想要知道的数据响应给客户端
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        在获取请求参数之前，设置服务器端请求的字符集
//        request.setCharacterEncoding("UTF-8");

        //System.out.println("hello world");

        //请求行: 请求方式、uri、协议版本
        //1. 获取请求行的内容:
        //1.1 获取请求方式
        String requestMethod = request.getMethod();//post
        //1.2 获取请求的url
        StringBuffer requestURL = request.getRequestURL();//http://localhost:8080/WebDay06/demo01
        //1.3 获取请求的uri
        String requestURI = request.getRequestURI();//   /WebDay06/demo01
        //1.4 获取项目部署名
//        String contextPath = request.getContextPath();//   /WebDay06
//        System.out.println(requestMethod);
//        System.out.println(requestURL);
//        System.out.println(requestURI);
//        System.out.println(contextPath);

        //2. 获取请求头的内容: getHeader("name")
        String header = request.getHeader("user-agent");
//        System.out.println(header);


        //3. 获取请求参数:
        //请求参数的类型: 1. name=value&name=value...    2. {key:value,key:value}
        //而我们今天学习的方法是获取第一种类型的参数
        //3.1 根据一个参数名，获取一个参数值
        /*String username = request.getParameter("username");
        System.out.println(username);*/

        //3.2 根据一个参数名，获取多个参数值,此时拿到的就是一个String[]
        /*String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }*/

        //3.3 一次性获取所有的请求参数，包含参数名和参数值
        //此时得到的请求参数就存放在Map中，Map的key就是请求参数名，Map的值就是请求参数值


        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
//        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        //遍历parameterMap
//        parameterMap.forEach((k,v) -> {
//            for (String s : v) {    //这里的v是个数组，表示参数值，因为一个参数名可能有多个参数值
//                System.out.println(k + ":" + s);
//            }
//        });

        //********************************************************
        System.out.println("ServletDemo01接收到了请求...");
        //使用请求转发跳转到ServletDemo02
        //request.getRequestDispatcher("转发到的路径").forward(request,response);
        //使用绝对路径: 如果不是请求转发那么绝对路径就是目标资源的uri，
        // 如果是请求转发那么绝对路径就是目标资源的uri再省略项目名

//        request.getRequestDispatcher("/demo02").forward(request,response);


        //使用请求转发跳转到WEB-INF里面的a.html
//        request.getRequestDispatcher("/WEB-INF/a.html").forward(request,response);


        //使用请求域对象存储数据,域对象所共享的数据的作用域（也就是存在的范围）是在一次客户端请求当中
        String str = "范海伦";
        request.setAttribute("name",str);
        //要是在浏览器界面访问demo02则会访问不到name：范海伦这个数据，因为不在其作用域内
        //请求转发跳转到demo02
        request.getRequestDispatcher("/demo02").forward(request,response);



    }
}
