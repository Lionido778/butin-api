package cn.codeprobe.butin.controller.user;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.service.UserService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lionido on 14/2/2022
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${butin.jwt.header}")
    private String header;

    @Value("${butin.jwt.expireTime}")
    private Long expireTimeCache;


    @GetMapping("/{id}")
    public R getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return R.ok(Status.OK).put("data", user);
    }

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginVO loginVO, HttpServletRequest request) {
        //防止用户多次登录，加剧缓存
        String oldToken = request.getHeader(this.header);
        if (StrUtil.isNotBlank(oldToken)) {
            redisTemplate.delete(oldToken);
        }
        UserDTO userDTO = userService.login(loginVO);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userDTO.getId());
        // Jwt claim 支持的类型 map list 其他基础类型， 如果是其他自定义，需要转换为json
        map.put("user", JSONUtil.parse(userDTO));
        String token = jwtUtil.createToken(map);
        redisTemplate.opsForValue().set(token, map, expireTimeCache, TimeUnit.DAYS);
        return R.ok(Status.LOGIN_SUCCESS).put(this.header, token);
    }


}
