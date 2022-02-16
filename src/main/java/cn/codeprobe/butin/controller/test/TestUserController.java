package cn.codeprobe.butin.controller.test;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.service.UserService;
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
    private UserService userService;

    // 注入RedisTemplate
    @Resource
    private RedisTemplate<String, Object> redis;

    // 读取用户信息，测试缓存使用：除了首次读取，接下来都应该从缓存中读取
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        return user;
    }

    // 修改用户信息，测试删除缓存
    @RequestMapping(value = "/{id}/change-nick", method = RequestMethod.POST, produces = "application/json")
    public User changeNickname(@PathVariable long id) {

        String nick = "abc-" + Math.random();
        int effect = userService.updateUserNickname(id, nick);
        if (effect < 0) {
            return null;
        }
        return userService.getUserById(id);
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
}

