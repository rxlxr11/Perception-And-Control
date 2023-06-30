package com.pac.springboot.serial.Frame;

import com.sun.org.apache.bcel.internal.generic.PUSH;

public class CompressorConFrame extends FrameStruct{
    public CompressorConFrame(boolean b){
        this.Frame_Len   = "0B";
        this.Func_Num    = "02";
        //this.Check     = CRC_Func();
        this.Check       = "0000";
        this.Frame_Num   = "04";
        this.Device_Addr = "00";
        if (b){
            this.Data    = "01";
        }else {
            this.Data    = "00";
        }
    }

}
