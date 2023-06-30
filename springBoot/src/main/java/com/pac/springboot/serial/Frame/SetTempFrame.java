package com.pac.springboot.serial.Frame;
import lombok.extern.slf4j.Slf4j;

import java.lang.Math;
@Slf4j
public class SetTempFrame extends FrameStruct{
    public SetTempFrame (float set_temp){
        this.Frame_Len    =  "0B"  ;
        this.Func_Num     =  "04"  ;
        this.Check        =  "0000";
        this.Frame_Num    =  "10"  ;
        this.Device_Addr  =  "01"  ;
        this.Data         =  setTemp(set_temp)  ;

    }
    private String setTemp(float f){
        StringBuilder sb = new StringBuilder();
        int   a  = (int) f;
        float b  = f-(float) a;
        if ( Math.abs(b) < 0.5){
            sb.append("0");
        }else {
            sb.append("1");
        }
        String s = Tool.decTObin(Integer.toString(Math.abs(a)));
        log.error("砍我一击灭之"+s);
        if (s.length() < 6){
            for (int i = 0; i < 6 - s.length(); i++) {
                sb.append("0");
            }
        }
        sb.append(s);
        if (f < 0){
            sb.append("1");
        }else {
            sb.append("0");
        }
        //s = ;
        return Tool.bin2hex(sb.toString());
    }
}
