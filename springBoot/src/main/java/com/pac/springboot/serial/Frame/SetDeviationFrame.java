package com.pac.springboot.serial.Frame;

public class SetDeviationFrame extends FrameStruct{
    public SetDeviationFrame (boolean[] drawer){
        this.Frame_Len    = "0C";
        this.Func_Num     = "03";
        this.Check        = "0000";
        this.Frame_Num    = "01";
        this.Device_Addr  = "00";
        this.Data         = "0";

    }
}
