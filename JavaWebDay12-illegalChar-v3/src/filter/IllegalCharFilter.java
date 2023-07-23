package filter; /**
 * @author 刘翰林
 * @create 2022-12-04 23:47
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class IllegalCharFilter implements Filter {

    private List<String> listIllegal = new ArrayList<>();
    public void init(FilterConfig config) throws ServletException {
        //1、先通过类加载器获取流
        InputStream is = IllegalCharFilter.class.getClassLoader().getResourceAsStream("illegal.txt");

        //2、缓冲流
        try {
            String str = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while ((str = bufferedReader.readLine()) != null){        //通过循环读取所有的铭感词
                listIllegal.add(str);//将遍历得到的铭感词存入集合中
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //要求：将敏感词替换成*输出
        HttpServletRequest req = (HttpServletRequest) request;
        ClassLoader classLoader = req.getClass().getClassLoader();
        //返回一个request对象的代理类对象
        HttpServletRequest proxyInstance = (HttpServletRequest) Proxy.newProxyInstance(classLoader, req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("getParameter")){
                    //如果调用的是getParameter方法
                    String comment = (String) method.invoke(req, args);//此处获得了评论的内容
                    for(String illegal : listIllegal){
                        if (comment.contains(illegal)) {
                            String str = "";
                            for(int i = 0; i < illegal.length(); i++){
                                str = str + "*";
                            }
                            comment = comment.replace(illegal, str);
                        }
                    }
                    return comment;
                }
                return method.invoke(req,args);//其他方法正常调用
            }
        });
        chain.doFilter(proxyInstance, response);
    }
}
