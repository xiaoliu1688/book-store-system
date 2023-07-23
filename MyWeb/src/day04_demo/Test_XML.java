package day04_demo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 11/9/2022 21 : 02
 * 使用DOM4J解析xml
 * 步骤:
 *  1. 引入jar包
 *  2. 创建解析器对象: new SAXReader()
 *  3. 使用解析器对象读取xml配置文件，从而得到一个Document对象
 *  4. 使用Document对象获取根标签
 *  5. 获取根标签里面的子标签
 */
public class Test_XML {
    @Test
    public void test1() throws DocumentException {
        //目标: 获取demo01.xml文件的根标签的标签名
        //1. 创建解析器对象
        SAXReader saxReader = new SAXReader();
        //2. 使用解析器对象读取xml配置文件，从而得到一个Document对象
        //2.1 将demo01.xml文件转成字节输入流
        InputStream ipm = Test_XML.class.getClassLoader().getResourceAsStream("resources/demo01.xml");
        //2.2 使用解析器读取流
        Document document = saxReader.read(ipm);
        //3. 使用document对象获取当前xml文档的根标签
        Element rootElement = document.getRootElement();
        //4. 使用根标签获取标签名
        String name = rootElement.getName();
        System.out.println(name);

    }

    //获取根标签下的所有子标签
    @Test
    public void test2() throws DocumentException {
        //1. 创建解析器对象
        SAXReader saxReader = new SAXReader();
        //2. 使用解析器对象读取xml配置文件，从而得到一个Document对象
        //2.1 将demo01.xml文件转成字节输入流
        InputStream ipm = Test_XML.class.getClassLoader().getResourceAsStream("resources/bookstore.xml");
        //2.2 使用解析器读取流
        Document document = saxReader.read(ipm);
        //3. 使用document对象获取当前xml文档的根标签
        Element rootElement = document.getRootElement();
        //4. 使用根标签获取其所有的子标签
        List<Element> elements = rootElement.elements();
        //遍历输出
        for(Element element : elements){
            System.out.println(element.getName());
        }
    }

    //获取根标签下的所有book子标签，并且获取每个book标签下的所有子标签
    @Test
    public void test3() throws DocumentException {
        //1. 创建解析器对象
        SAXReader saxReader = new SAXReader();
        //2. 使用解析器对象读取xml配置文件，从而得到一个Document对象
        //2.1 将demo01.xml文件转成字节输入流
        InputStream ipm = Test_XML.class.getClassLoader().getResourceAsStream("resources/bookstore.xml");
        //2.2 使用解析器读取流
        Document document = saxReader.read(ipm);
        //3. 使用document对象获取当前xml文档的根标签
        Element rootElement = document.getRootElement();
        //4. 使用根标签获取标签名为book的所有子标签
        List<Element> elementsBook = rootElement.elements("book");
        //遍历
        for(Element element : elementsBook){
            //获取每个book标签的所有子标签
            List<Element> subElements = element.elements();
            System.out.println(element.getName());  //获取标签的名字
            //遍历输出
            for(Element element1 : subElements){
                System.out.println(element1.getName());
            }
            System.out.println("*******************************");
        }
    }

    @Test
    public void test4() throws DocumentException {
        //目标：获取每一份报纸的id属性值，以及每份报纸的子标签及其子标签的内容

        //解析器的实质就是里面封装了各种解析xml文件的方法
        SAXReader saxReader = new SAXReader();
        InputStream ipm = Test_XML.class.getClassLoader().getResourceAsStream("resources/bookstore.xml");
        Document document = saxReader.read(ipm);//得到了整个文档
        Element rootElement = document.getRootElement();//拿到了根标签
        List<Element> paper = rootElement.elements("paper"); //拿到了所有的报纸标签
        for(Element element : paper){
            System.out.println("--------------今日头条-------------");
            String id = element.attributeValue("id");   //获取标签element id的属性值
            System.out.println("报纸的id是：" + id);
            List<Element> subElements = element.elements(); //获取了所有报纸标签的子标签
            for(Element element1 : subElements){    //这里的element 是报纸的子标签
                String text = element1.getText();   //获取标签内部的文本内容，还有一个是getTextTrim这个是自动去除首尾空格
                System.out.println("报纸内容是：" + text);

            }
        }
    }

    //获取第三本书的作者
    @Test
    public void test5() throws DocumentException {
        //1. 创建解析器对象
        SAXReader saxReader = new SAXReader();
        //2. 使用解析器对象读取xml配置文件，从而得到一个Document对象
        //2.1 将demo01.xml文件转成字节输入流
        InputStream ipm = Test_XML.class.getClassLoader().getResourceAsStream("resources/bookstore.xml");
        //2.2 使用解析器读取流
        Document document = saxReader.read(ipm);

        Element node = (Element) document.selectSingleNode("books/book[3]/author");//得到author这个标签
        String text = node.getText();//获取author标签的文本内容
        System.out.println(text);

    }
}