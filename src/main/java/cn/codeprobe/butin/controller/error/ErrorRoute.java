package cn.codeprobe.butin.controller.error;

import cn.codeprobe.butin.response.R;
import cn.codeprobe.butin.response.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lionido on 12/2/2022
 */
@RestController
public class ErrorRoute {

    @GetMapping("/404")
    public R NotFound() {
        return R.error(Status.NOT_FOUND);
    }

    @GetMapping("/405")
    public R MethodNotMatch() {
        return R.error(Status.METHOD_NOT_MATCHED);
    }
}
