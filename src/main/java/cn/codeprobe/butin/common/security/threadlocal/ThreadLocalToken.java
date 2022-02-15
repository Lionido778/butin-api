package cn.codeprobe.butin.common.security.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改
 */
@Slf4j
@Component
public class ThreadLocalToken {

    private final ThreadLocal<String> local = new ThreadLocal<>();
    private final String name = Thread.currentThread().getName();
    private final Long id = Thread.currentThread().getId();

    public void saveToken(String token) {
        log.debug(name + id + "保存token");
        local.set(token);
    }


    public String getToken() {
        log.debug(name + id + "获取token:{}", local.get());
        return local.get();
    }

    public void clear() {
        log.debug(name + id + "清空");
        local.remove();
    }
}
