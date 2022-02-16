package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.controller.portal.service.ChildService;
import cn.codeprobe.butin.model.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lionido on 9/2/2022
 */


@RestController
@RequestMapping("/test")
public class TestRootController {

    @Resource
    private JwtUtil jwtUtil;

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
        return R.ok(Status.OK).put("data", "data");
    }

    @GetMapping("/exception")
    public R E() {
        throw new ButinException(Status.TEST);
    }

    @PostMapping("/testVO")
    public R vo(@Valid @RequestBody TestVO testVO) {
        System.out.println(testVO);
        return R.ok(Status.OK);
    }

    @GetMapping("/createToken")
    public R createToken() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        return R.ok(Status.LOGIN_SUCCESS).put("token", jwtUtil.createToken(map));
    }

    @GetMapping("/decodeToken")
    public R decodeToken(String token) {
        Map<String, Object> map = jwtUtil.decodeToken(token);
        return R.ok(Status.LOGIN_SUCCESS).put((HashMap<String, Object>) map);
    }

    @GetMapping("/verifyToken")
    public R verifyToken(String token) {
        jwtUtil.verifyToken(token);
        return R.ok(Status.OK).put("data", 123123);
    }
}


