package com.pac.springboot.service;

import com.pac.springboot.dao.PerDao;
import com.pac.springboot.pojo.data;
import com.pac.springboot.pojo.result;
import com.pac.springboot.pojo.sysPara;
import com.pac.springboot.serial.Frame.StatusFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@Slf4j
@Service
public class PerService {
    @Autowired
    private PerDao perDao ;
    public result test(){
        result r = perDao.test();
        r.code = 2;
        return r;

    }

    public sysPara sysHandle(){
        StatusFrame statusFrame = perDao.getStatusFrame();
        sysPara sp = statusFrame.handleSys();
        return sp;
    }
    public data dataHandle(){
        String frame = perDao.getFrame();
        StatusFrame statusFrame = new StatusFrame();
        statusFrame.decapsulate(frame);
        return statusFrame.getFrame_Data();
    }

    public String compressorHandle(){
        StatusFrame statusFrame = perDao.getStatusFrame();
        //String s = statusFrame.handleCompressor();
        return statusFrame.handleCompressor();
    }
    public Float tempHandle(){
        StatusFrame statusFrame = perDao.getStatusFrame();
        //Float a = statusFrame.handleTemp();
        return statusFrame.handleTemp(54,56);
    }

    public Float SettingTemp_Handle(){
        StatusFrame statusFrame = perDao.getStatusFrame();
        //Float a = statusFrame.handle_SettingTemp();
        return statusFrame.handleTemp(56,58);
    }

    public boolean[] drawerHandle(){
        StatusFrame statusFrame = perDao.getStatusFrame();
        return statusFrame.handleDrawer();
    }
    @Scheduled(fixedRate = 2500) // 每2.5秒执行一次
    public data scheduleUpdate() {
        log.info("update");
        //System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
        data d = dataHandle();
        return d;
    }

//    @Scheduled(fixedRate = 2500)
//    public void compressorUpdate() {
//        log.info("update");
//      //  System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
//        //String result =
//                compressorHandle();
//        //return compressorHandle();
//    }
//    @Scheduled(fixedRate = 2500)
//    public void tempUpdate() {
//        log.info("update");
//        //  System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
//        //Float result =
//                tempHandle();
//        //return result;
//    }
//    @Scheduled(fixedRate = 2500)
//    public void SettingTemp_Update() {
//        log.info("update");
//        //  System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
//        //Float result =
//                SettingTemp_Handle();
//        //return result;
//    }
//    @Scheduled(fixedRate = 2500)
//    public void drawerUpdate() {
//        log.info("update");
//        //  System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
//        //boolean[] result =
//                drawerHandle();
//        //return result;
//    }
//    @Scheduled(fixedRate = 2500)
//    public String compressorUpdate() {
//        log.info("update");
//        //  System.out.println(data.temp_array[0]+" "+data.temp_array[1]);
//        String result = compressorHandle();
//        return result;
//    }
}
