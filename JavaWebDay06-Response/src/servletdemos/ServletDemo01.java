package servletdemos; /**
 * @author:刘翰林
 * @Description:
 * @Date: 23/9/2022 09 : 46
 */

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决响应内容的乱码问题
        //response.setContentType("text/html;charset=UTF-8");

        //System.out.println("hello world");
        //response就是服务器端要发送给客户端的响应内容，它里面包含三部分: 响应行、头、体
        //1. 设置响应行的内容：
        //设置响应的状态码，但是一般情况下我们不需要设置状态码，因为服务器会自动设置状态码
        //response.setStatus(404);

        //2. 设置响应头信息: setHeader("name","value");

        //3. 设置响应体的信息: 响应体就是显示在浏览器的数据
        //3.1 通过字符流往浏览器输出文本内容
//        response.getWriter().write("<h1>你好世界...</h1>");

        //3.2 使用字节流往浏览器输出一张图片
        //首先设置响应数据的mime-type: 了解即可
        //根据文件名获取mime-type


        //第一步: 使用字节输入流读取那张图片
        //使用ServletContext获取资源的真实路径
        ServletContext servletContext = getServletContext();
        String path = servletContext.getRealPath("img/赵金麦.jpg");//默认的一开始路径是在WEB-INF下
        FileInputStream ips = new FileInputStream(path);//获取输入流，怼到这张图片

        //第二步: 使用字节输出流，将图片输出到浏览器
        ServletOutputStream op = response.getOutputStream();

        //边读编写
//        int len;
//        byte[] buffer = new byte[1024];
//        while ((len = ips.read(buffer)) != -1){
//            op.write(buffer,0,len);
//        }
        IOUtils.copy(ips,op);
        op.close();
        ips.close();
    }
}
