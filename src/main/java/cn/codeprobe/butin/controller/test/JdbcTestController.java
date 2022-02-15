package cn.codeprobe.butin.controller.test;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Lionido on 13/2/2022
 */
@RestController
@RequestMapping("/test")
public class JdbcTestController {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    UserService userService;

    @RequestMapping(value = "/jdbc", method = RequestMethod.GET)
    public String index() {

        String sql = "SELECT mobile FROM user WHERE id = ?";

        // 通过jdbcTemplate查询数据库
        String mobile = jdbcTemplate.queryForObject(
                sql, new Object[]{1}, String.class);

        return "Hello " + mobile;
    }

    @PostMapping("/mybatis")
    public R mybatis(Long userId) {
        return R.ok(Status.OK).put("data", userService.getUserById(userId));
    }
}

