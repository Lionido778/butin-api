package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.controller.portal.service.ChildService;
import cn.codeprobe.butin.controller.response.R;
import cn.codeprobe.butin.controller.response.Status;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Lionido on 9/2/2022
 */


@RestController
@RequestMapping("/test")
public class TestRootController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChildService childService;

    @GetMapping("/logger")
    public String test() {
        log.info("TestRootController");
        System.out.println(this.getClass().getName());
        childService.childLogPrinter();
        return "hello,world";
    }


    @GetMapping("/R")
    public R R() {
        return R.ok(Status.OK).put("data","data");
    }

}

