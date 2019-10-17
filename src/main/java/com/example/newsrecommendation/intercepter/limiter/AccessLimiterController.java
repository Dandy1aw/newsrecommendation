package com.example.newsrecommendation.intercepter.limiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
@RestController
@RequestMapping("/limit")
public class AccessLimiterController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(120);
    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping(value="/ratelimit",method = RequestMethod.GET)
    @ResponseBody
    public void tryForVisit() {
        for(int i=0;i<20;i++) {
            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    String str=visitAccess();
                    System.out.println("output:"+str);
                }
            });
        }
    }



    public String visitAccess() {
        if(accessLimitService.tryAcquire()) {
//            try {
////                Thread.sleep(2000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            return "aceess success [" + sdf.format(new Date()) + "]";
        }else {
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }
}
