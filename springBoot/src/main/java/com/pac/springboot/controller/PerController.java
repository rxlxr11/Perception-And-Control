package com.pac.springboot.controller;

import com.pac.springboot.pojo.data;
import com.pac.springboot.pojo.result;
import com.pac.springboot.service.PerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PerController {
    @Autowired
    private PerService perService;
//    private static Logger log = LoggerFactory.getLogger(PerController.class);
    @CrossOrigin
    @RequestMapping("/perception")
    public result all(){
        log.info("获取全部数据");
        result r = new result();
        //data_plus dp = new data_plus();
        //dp = (data_plus) perService.dataHandle();
        r.value = perService.dataHandle();
        r.setting_temp = data.setting_array;
        r.temp = data.temp_array;
        //dp.temp_value = data.temp_array;
        //dp.setting_value = data.setting_array;
        //r.value = dp;
        r.code = 1;
        r.message = "success";
        return r;
    }
    @CrossOrigin
    @RequestMapping("/GetSysPara")
    public result sysPara(){
        log.info("compressor");
        result r = new result();
        r.value = perService.sysHandle();
        r.message = "success";
        r.code = 1;
        return r;
    }

    @RequestMapping("/compressor")
    public result compressor(){
        log.info("compressor");
        result r = new result();
        r.value = perService.compressorHandle();
        r.message = "success";
        r.code = 1;
        return r;
    }

    @RequestMapping("/temp")
    public result temp(){
        log.info("temperature");
        result r = new result();
        r.value = perService.tempHandle();
        r.message = "success";
        r.code = 1;
        return r;
    }

    @RequestMapping("/setting_temp")
    public result Setting_temp(){
        log.info("setting temperature");
        result r = new result();
        r.value = perService.SettingTemp_Handle();
        r.message = "success";
        r.code = 1;
        return r;
    }

    @RequestMapping("/drawer")
    public result drawer(){
        log.info("drawer");
        result r = new result();
        r.value = perService.drawerHandle();
        r.message = "success";
        r.code = 1;
        return r;
    }
    @RequestMapping("/test")
    public result test(){
        result r = perService.test();
        return r;
    }
}
