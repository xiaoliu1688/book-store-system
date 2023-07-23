package ServletDemos; /**
 * @author:刘翰林
 * @Description:
 * @Date: 22/9/2022 21 : 16
 */

import bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        //我拿到这些参数之后，比如要获取用户名
        //现在的目标：将parameterMap中的数据，封装到user对象中
        System.out.println("被调用l");
        User user = new User();
        //第三方提供了一个工具：BeanUtils，它可以自动将map中的数据存储到JavaBean对象中
        //前提是Map中的key和JavaBean中的属性名是一致的
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
