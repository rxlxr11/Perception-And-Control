package com.pac.springboot.serial.Frame;

public class ACKFrame extends FrameStruct{

    public ACKFrame (String frame){
        //decapsulate(frame);
        spiltData();
    }
    public String addr;
    public String comm_Num;
    public String drawerStatus;
    public void spiltData(){
        StringBuilder sb = new StringBuilder(this.Data);
        this.addr = sb.substring(0,2);
        this.comm_Num = sb.substring(2,4);
        this.drawerStatus = sb.substring(4,8);
    }

    public void decapsulate(String frame){

        StringBuilder sb = new StringBuilder(frame);
        this.header = sb.substring(0,4);
        this.Frame_Len = sb.substring(4,6);
        this.Frame_Num = sb.substring(6,8);
        this.Device_Addr = sb.substring(8,10);
        this.Func_Num = sb.substring(10,12);
        this.Data = sb.substring(12,frame.length()-8);
        this.Check = sb.substring(frame.length()-8,frame.length()-4);
        this.end = sb.substring(frame.length()-4,frame.length());


    }


}
