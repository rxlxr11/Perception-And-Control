package com.pac.springboot.serial.Frame;

public class SetDeviceAddrFrame extends FrameStruct{
    public SetDeviceAddrFrame (String Addr){
        this.Frame_Len    = "0C";
        this.Func_Num     = "03";
        this.Check        = "0000";
        this.Frame_Num    = "01";
        this.Device_Addr  = "00";
        this.Data         = "0";

    }
}
