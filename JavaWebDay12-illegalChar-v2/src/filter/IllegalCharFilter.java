package filter; /**
 * @author 刘翰林
 * @create 2022-12-04 23:47
 */

import javax.servlet.*;
import java.io.*;
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
        String commentValue = request.getParameter("commentValue");

        if(commentValue != null){
            for(String illegal : listIllegal){
                if(commentValue.contains(illegal)){//如果包含敏感词
                    response.getWriter().write("评论失败，包含敏感词");
                    return;
                }
            }
        }

        chain.doFilter(request, response);


    }
}
