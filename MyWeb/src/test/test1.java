package test;

import org.junit.Test;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 23/9/2022 15 : 29
 */
public class test1 {
    @Test
    public void test001(){
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = test1.class.getClassLoader();
        if(systemClassLoader.equals(classLoader)){
            System.out.println("是的");
        }
    }
}