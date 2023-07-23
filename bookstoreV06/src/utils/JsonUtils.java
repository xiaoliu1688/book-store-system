package utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author 刘翰林
 * @create 2023-01-05 22:35
 */
public class JsonUtils {

    /**
     * 将json数据转换成字符串封装到对象里
     * @date
     * @author
     * @return
     * @throws
     */
    public static Object parseJsonToBean(HttpServletRequest request,Class<? extends Object> clazz){
        BufferedReader reader = null;

        try {
            // 1.由于请求体数据有可能很大，所以Servlet标准在设计API的时候要求我们通过输入流来读取
            reader = request.getReader();

            // 2.创建StringBuilder对象来累加存储从请求体中读取到的每一行
            StringBuilder stringBuilder = new StringBuilder();
            String body = "";
            while ((body = reader.readLine()) != null){
                stringBuilder = new StringBuilder();
                stringBuilder.append(body);
            }

            // 7.创建Gson对象用于解析JSON字符串
            Gson gson = new Gson();
            Object object = gson.fromJson(stringBuilder.toString(),clazz);
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    public static void writeResult(HttpServletResponse response,Object object){
        try {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(object);
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
