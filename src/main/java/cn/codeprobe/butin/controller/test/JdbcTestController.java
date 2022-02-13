package cn.codeprobe.butin.controller.test;

import org.springframework.jdbc.core.JdbcTemplate;
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

    @RequestMapping(value = "/jdbc", method = RequestMethod.GET)
    public String index() {

        String sql = "SELECT mobile FROM user WHERE id = ?";

        // 通过jdbcTemplate查询数据库
        String mobile = (String) jdbcTemplate.queryForObject(
                sql, new Object[]{1}, String.class);

        return "Hello " + mobile;
    }
}

