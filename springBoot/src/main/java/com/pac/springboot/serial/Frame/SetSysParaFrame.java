package com.pac.springboot.serial.Frame;

import com.pac.springboot.pojo.sysPara;

public class SetSysParaFrame extends FrameStruct{
    public SetSysParaFrame (sysPara sys){
        this.Frame_Len    = "1C";
        this.Func_Num     = "05";
        this.Check        = "0000";
        this.Frame_Num    = "01";
        this.Device_Addr  = "00";
        this.Data         = sys(sys);

    }

    private String sys(sysPara s){
        StringBuilder sb = new StringBuilder();
        sb.append("FFFFFFFFFF");
        sb.append(s.getDev_Addr());
        sb.append("0001");
        sb.append(s.getDelay());
        sb.append("0000");
        sb.append("04");
        System.out.println("全体目光想这看其"+s.getTemp_deviation());
        sb.append(s.getTemp_deviation());
        sb.append("FFFFFFFF00");
        return sb.toString();
    }
}
