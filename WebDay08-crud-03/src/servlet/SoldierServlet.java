package servlet; /**
 * @author 刘翰林
 * @create 2022-10-25 10:50
 */

import bean.Soldier;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.SoldierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SoldierServlet extends ViewBaseServlet {
    SoldierServiceImpl soldierService = new SoldierServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、设置字符编码集
        request.setCharacterEncoding("UTF-8");
        //2、获取请求参数
        String method = request.getParameter("method");
//        if(method.equals("showAll")){
//            showAll(request,response);
//        }
//
//        if(method.equals("toAdd")){
//            toAdd(request,response);
//        }
//
//        if(method.equals("add")){
//            add(request,response);
//        }
//
//        if(method.equals("toDelete")){
//            toDelete(request,response);
//        }
//
//
//        if(method.equals("toUpDate")){
//            toUpdate(request,response);
//        }
//
//        if(method.equals("update")){
//            upDate(request,response);
//        }
        try {
            //利用反射传入方法名和参数获得方法
            Method declaredMethod = getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //通过得到的方法传入对象和参数调用
            declaredMethod.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示所有士兵
     * @date
     * @author
     * @return
     * @throws
     */
    void showAll(HttpServletRequest request,HttpServletResponse response){
        try {
            List<Soldier> allSoldier = soldierService.findAllSoldier();
            Iterator<Soldier> iterator = allSoldier.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
//            System.out.println(allSoldier);
            request.setAttribute("SoldierList",allSoldier);
            processTemplate("list",request,response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到添加士兵界面
     * @date
     * @author
     * @return
     * @throws
     */
    void toAdd(HttpServletRequest request,HttpServletResponse response) throws IOException {
        processTemplate("add",request,response);
    }

    /**
     * 添加士兵
     * @date
     * @author
     * @return
     * @throws
     */
    void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //1、获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2、将请求参数封装到一个soldier对象中
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3、调用逻辑业务层
            soldierService.addSolder(soldier);
            //4、跳转到士兵显示界面
            response.sendRedirect( request.getContextPath()+ "/soldier?method=showAll");//重定向由浏览器重新发请求到showAllServlet访问showall页面
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 跳转到删除
     * @date
     * @author
     * @return
     * @throws
     */
    void toDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //1、获取请求参数，得到需要删除的id
        Integer soldierId = Integer.valueOf(request.getParameter("id"));
        //2、调用业务逻辑层删除
        try {
            soldierService.deleteSoldier(soldierId);
            //3、删除后跳转到显示界面
            response.sendRedirect( request.getContextPath()+ "/soldier?method=showAll");//重定向由浏览器重新发请求到showAllServlet访问showall页面
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到修改界面
     * @date
     * @author
     * @return
     * @throws
     */
    void toUpDate(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //1、获取获取参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2、通过id查出对应的士兵信息
        try {
            System.out.println(id);
            Soldier soldier = soldierService.findSoldier(id);
            //3、将士兵信息存储到请求域中
            request.setAttribute("soldierInfo",soldier);
            //4、跳转到修改界面
            processTemplate("update",request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改士兵
     * @date
     * @author
     * @return
     * @throws
     */
    void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //1、获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2、将map封装到一个soldier对象里
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3、调用业务逻辑层进行修改
            System.out.println(soldier);
            soldierService.update(soldier);
            //4、跳转到显示页面
            response.sendRedirect( request.getContextPath()+ "/soldier?method=showAll");//重定向由浏览器重新发请求到showAllServlet访问showall页面
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
