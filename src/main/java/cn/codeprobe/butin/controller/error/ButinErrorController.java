package cn.codeprobe.butin.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lionido on 12/2/2022
 */

@RestController
public class ButinErrorController implements ErrorController {

    @RequestMapping("/error")
    public void disPatchError(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object status_code = req.getAttribute("javax.servlet.error.status_code");
        String destination = null;
        switch (String.valueOf(status_code)) {
            case "404":
                destination = "/404";
                break;
            case "405":
                destination = "/405";
                break;
        }
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + destination);
    }
}