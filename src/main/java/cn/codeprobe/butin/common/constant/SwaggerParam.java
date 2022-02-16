package cn.codeprobe.butin.common.constant;

/**
 * Created by Lionido on 15/2/2022
 */
public interface SwaggerParam {

    interface title{
        String ADMIN = "Butin后台管理接口文档";
        String PORTAL = "Butin门户接口文档";
        String USER = "Butin用户管理接口文档";
    }

    interface enable{
        boolean OPEN = true;
        boolean CLOSE = false;
    }

    interface group{
        String ADMIN = "后台管理";
        String PORTAL = "前端门户";
        String USER = "用户管理";
    }

    interface description{
        String ADMIN = "后台管理可调用的API";
        String PORTAL = "门户可调用的API";
        String USER = "用户可调用的API";
    }

    interface contact{
        String NAME = "Lionido";
        String EMAIL = "codeprobe@163.com";
        String URL = "www.codeprobe.cn";
    }

}
