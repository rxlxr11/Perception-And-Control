package com.pac.springboot.controller;

import com.pac.springboot.pojo.*;
import com.pac.springboot.service.ConService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class ConController {
    @Autowired
    private ConService conService;

    @CrossOrigin
    @RequestMapping ("/setCompressor")
    public result setCompressor(@RequestBody compressor compre){

        if (compre.getCompressor().equals("1"))
            conService.sendCompressor(true);
        else if (compre.getCompressor().equals("2")) {
            conService.sendCompressor(false);
        }
        result r2 =  new result();
        r2.code   =  1;
        r2.message  =  "success";
        return r2;
    }
    @CrossOrigin
    @RequestMapping("/setDrawer")
    public result SetDrawer(@RequestBody drawer d){
        log.error("这里有错吗" +
                "收到请回答"+d);
        boolean[] drawer = d.getDrawers();
        conService.sendDrawer(drawer);
        log.error("lalaldemaxi" +
                "收到请回答");
        result r =  new result();
        r.code   =  1;
        r.message  =  "success";
        return r;
    }

    @CrossOrigin
    @RequestMapping("/setTemp")
    public result setTemp(@RequestBody temp t){
        log.error("发生甚魔事了"+t);
        conService.sendTemp(t.getTemperature());
        result r =  new result();
        r.code   =  1;
        r.message  =  "success";
        return r;
    }
    @CrossOrigin
    @RequestMapping ("/setSysPara")
    public result SetPara(@RequestBody sysPara sp){
        log.error("在你眼中我是谁"+sp);
        conService.sendPara(sp);
        result r =  new result();
        r.code   =  1;
        r.message  =  "success";
        return r;
    }
}
