package com.xuecheng.freemarker;

import com.xuecheng.freemarker.entity.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/14 20:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @Test
    public void test1() throws IOException, TemplateException {
        //获得模板配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //模板文件
        String path = this.getClass().getResource("/").getPath();
        //定义模板路径
        configuration.setDirectoryForTemplateLoading(new File(path+"/templates/"));
        //获取模板文件内容
        Template template = configuration.getTemplate("rabbitmq.ftl");

        //模板数据
        Map map = this.getMap();
        //静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream = IOUtils.toInputStream(content);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/rabbitmq.html"));
        IOUtils.copy(inputStream, fileOutputStream);
        inputStream.close();
        fileOutputStream.close();

    }

    // 模板文件的内容是字符串
    @Test
    public void test2() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        String content="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Hello World!</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <#list students as item >\n" +
                "        <tr>\n" +
                "            <td>${item.name}</td>\n" +
                "            <td>${item.age}</td>\n" +
                "            <td>${item.address}</td>\n" +
                "            <td>${(item.birth)!?string(\"yyyy-MM-dd HH:mm:ss\")}</td>\n" +
                "        </tr>\n" +
                "    </#list>\n" +
                "</table>\n" +
                "</body>";
        // 使用模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",content);
        configuration.setTemplateLoader(stringTemplateLoader);
        Template template = configuration.getTemplate("template", "utf-8");
        //模板数据
        Map map = this.getMap();
        //静态化
         String templateContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream = IOUtils.toInputStream(templateContent);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/rabbitmq.html"));
        IOUtils.copy(inputStream, fileOutputStream);
        inputStream.close();
        fileOutputStream.close();
    }



    public Map getMap(){
        HashMap<Object, Object> map = new HashMap<>();
        Student student1 = new Student("乔峰", 12, "丐帮", new Date());
        Student student2 = new Student("虚竹", 12, "逍遥", new Date());
        Student student3 = new Student("段誉", 12, "大理", new Date());
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        map.put("students",students);
        return map;
    }
}
