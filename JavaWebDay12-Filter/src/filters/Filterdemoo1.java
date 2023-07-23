package filters; /**
 * @author 刘翰林
 * @create 2022-11-27 18:43
 * 编写过滤器的步骤:
 * 1. 写一个类实现Filter接口，并且重写方法
 * 2. 在web.xml中配置该过滤器的拦截路径
 *
 * 过滤器的生命周期和生命周期方法:
 * 1. 过滤器在服务器启动（项目部署）的时候创建，在服务器关闭的时候销毁
 *
 * 生命周期方法有仨:
 * 1. init()它会在过滤器对象创建出来之后执行，在它里面可以做一些初始化操作
 * 2. doFilter()它会在过滤器每次过滤请求的时候执行
 * 3. destroy()它会在过滤器对象销毁之前执行
 */

import javax.servlet.*;
import java.io.IOException;

public class Filterdemoo1 implements Filter {
    //1. init()它会在过滤器对象创建出来之后执行，在它里面可以做一些初始化操作
    @Override
    public void init(FilterConfig config) throws ServletException {
    }


    //3. destroy()它会在过滤器对象销毁之前执行
    @Override
    public void destroy() {
    }

    //2. doFilter()它会在过滤器每次过滤请求的时候执行
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //可以使用过滤器解决乱码的问题
        request.setCharacterEncoding("UTF-8");

        //有请求被filter接收到的时候就会执行该方法进行过滤处理
        System.out.println("filter接受到了请求");

        //这句代码表示放行
        chain.doFilter(request, response);
    }
}
