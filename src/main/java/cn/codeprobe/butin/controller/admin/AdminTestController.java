package cn.codeprobe.butin.controller.admin;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
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
@RequestMapping("/admin")
public class AdminTestController {
    @GetMapping("/hello")
    @ApiOperation("测试方法")
    public R test() {
        return R.ok(Status.OK).put("data", "hello,world");
    }


}


