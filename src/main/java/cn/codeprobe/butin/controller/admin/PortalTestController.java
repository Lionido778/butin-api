package cn.codeprobe.butin.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lionido on 9/2/2022
 */

@Api(tags = "后端测试接口")
@RestController
@RequestMapping("/admin/test")
public class PortalTestController {
    @GetMapping("/hello")
    @ApiOperation("测试方法")
    public String test(){
        return "hello,world";
    }
}
