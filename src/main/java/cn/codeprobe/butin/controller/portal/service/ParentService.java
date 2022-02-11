package cn.codeprobe.butin.controller.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Lionido on 10/2/2022
 */
@Service
public class ParentService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public void parentLogPrinter() {
        System.out.println("ParentService: " + this.getClass());
        log.info("ParentService");
    }
}


//        System.out.println("TestService: "+super.getClass());/**/