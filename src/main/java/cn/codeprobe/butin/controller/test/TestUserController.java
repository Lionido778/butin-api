package cn.codeprobe.butin.controller.test;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.po.testUser;
import cn.codeprobe.butin.service.TestUserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/test/user")
public class TestUserController {

    // 注入service类
    @Resource
    private TestUserService testUserService;

    // 注入RedisTemplate
    @Resource
    private RedisTemplate<String, Object> redis;

    // 读取用户信息，测试缓存使用：除了首次读取，接下来都应该从缓存中读取
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public testUser getUser(@PathVariable long id) {
        testUser testUser = testUserService.getUserById(id);
        return testUser;
    }

    // 修改用户信息，测试删除缓存
    @RequestMapping(value = "/{id}/change-nick", method = RequestMethod.POST, produces = "application/json")
    public testUser changeNickname(@PathVariable long id) {

        String nick = "abc-" + Math.random();
        int effect = testUserService.updateUserNickname(id, nick);
        if (effect < 0) {
            return null;
        }
        return testUserService.getUserById(id);
    }

    // 使用RedisTemplate访问redis服务器
    @RequestMapping(value = "/redis", method = RequestMethod.GET, produces = "application/json")
    public String redis() {

        // 设置键"project-name"，值"springboot-redis-demo"
        redis.opsForValue().set("project-name", "springboot-redis-demo");
        String value = (String) redis.opsForValue().get("project-name");
        return value;
    }

    // 测试系统时间
    @GetMapping("/time")
    public R getSystemTime() {
        return R.ok(Status.OK.setMsg("系统时间")).put("time", new Date()).put("localTIme",LocalDateTime.now());
    }

    // 测试系统时间
    @GetMapping("/time1")
    public R getTime() {
        return R.ok(Status.OK.setMsg("系统时间")).put("time", new Date()).put("localTIme",LocalDateTime.now());
    }

}


