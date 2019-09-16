package com.esop.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class
        }
)
@EnableTransactionManagement   //import  bean注入到容器里 @import导入第三方的@configuration配置类
@ServletComponentScan          //扫描servlet @WebServlet、@WebFilter、@WebListener注解自动注册
@EnableAspectJAutoProxy        //开启Aop代理
//@EnableAutoConfiguration
public class AirportApplication {//

    public static void main(String[] args) {
        SpringApplication.run(AirportApplication.class, args);
    }

}
