package cn.codeprobe.butin.controller.portal.service;

import org.springframework.stereotype.Service;

/**
 * Created by Lionido on 10/2/2022
 */
@Service
public class ChildService extends ParentService {

    public void childLogPrinter() {
        super.parentLogPrinter(); // parentLogPrinter()
        System.out.println("ChildService: " + this.getClass());
        this.log.info("ChildService");
    }
}


//                      System.out.println("Test2Service: "+this.getClass());
