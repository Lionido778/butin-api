package cn.codeprobe.butin;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@MapperScan("cn.codeprobe.butin.repository")  //指定要扫描的 mybatis映射类的路径
@SpringBootApplication
public class ButinApiApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(ButinApiApplication.class, args);
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StrUtil.isBlank(path)) {
            path = " ";
        }
        log.info(
                "\n------------------------------------------------------------------\n\t" +
                        "Started successfully. application is running! \n\t" +
                        "Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
                        "External访问网址: \thttp://" + ip + ":" + port + path + "\n" +
                        "------------------------------------------------------------------\n");
    }


}
