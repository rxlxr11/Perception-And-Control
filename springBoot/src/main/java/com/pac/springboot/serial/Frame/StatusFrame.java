package com.pac.springboot.serial.Frame;

import com.pac.springboot.pojo.data;
import com.pac.springboot.pojo.sysPara;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.pow;
@Slf4j
public class StatusFrame extends FrameStruct{
    public data getFrame_Data(){
        System.out.println(this.Data);
        data d = new data();
        //StringBuilder sb = new StringBuilder(this.Data);
        d.setCompressor(handleCompressor());
        d.setDrawer(handleDrawer());
        d.setTemp(handleTemp(54,56));
        d.setSetting_temp(handleTemp(52,54));
        if (data.temp_array.length < 20){
            data.temp_array[data.temp_array.length-1]=d.getTemp();
        }else {
            for (int i = 0 ; i<19 ; i ++){
                data.temp_array[i] = data.temp_array[i+1];
            }
            data.temp_array[19] = d.getTemp();
        }
        if (data.setting_array.length<20){
            data.setting_array[data.setting_array.length-1]=d.getSetting_temp();
        }else {
            for (int i = 0 ; i<19 ; i ++){
                data.setting_array[i] = data.setting_array[i+1];
            }
            data.setting_array[19] = d.getSetting_temp();
        }
        return d;
    }
    public sysPara handleSys(){
        StringBuilder sb = new StringBuilder(this.Data);
        sysPara sp = new sysPara();
        String s = sb.substring(22,24);
        s = Tool.hexTOdec(s);
        sp.setDev_Addr(s);
        s = sb.substring(28,30);
        s = Tool.hexTOdec(s);
        sp.setDev_Addr(s);
        s = sb.substring(36,38);
        s = Tool.hexTOdec(s);
        sp.setDev_Addr(s);

/*        return  new sysPara(
                                  Tool.hexTOdec(sb.substring(22,24).toString()),
                                  Tool.hexTOdec(sb.substring(28,30).toString()),
                                  Tool.hexTOdec(sb.substring(36,38).toString())
                           );*/
        return sp;

    }
    public String handleCompressor(){
        String s = this.Data;
        StringBuilder sb = new StringBuilder(s);
        s = sb.substring(50,52);
        switch (s) {
            case "00":
                return "停止";
            case "01":
                return "预启动";
            case "02":
                return "启动";
            default:
                return "故障";
        }
    }


    public float handleTemp(int a,int b){
        String s = this.Data;
        StringBuilder sb2 = new StringBuilder(s);
        s = sb2.substring(a,b);
        float temp = 0f;
        //System.out.println(s);
        log.warn("还不可以认输"+s);
        s = Tool.hexString2binaryString(s,8);
        log.warn("op怎么你了"+s);
        //System.out.println("注意"+s);
        StringBuilder sb = new StringBuilder(s);
        String item;
        item = sb.substring(0,1);
        if (item.equals("1")){
            temp+= 0.5f;
        }
        for (int i = 1; i < 7; i++) {
            item = sb.substring(i,i+1);
            if (item.equals("1")){
                temp+=1*pow(2,6-i);
            }

        }
        item = sb.substring(7,8);
        if (item.equals("1")){
            temp=-temp;
        }
        return temp;
    }

//    public float handle_SettingTemp(){
//        String s = this.Data;
//        StringBuilder sb2 = new StringBuilder(s);
//        s = sb2.substring(52,54);
//        float temp = 0f;
//        //System.out.println(s);
//        s = Tool.hexString2binaryString(s,8);
//        //System.out.println("注意"+s);
//        StringBuilder sb = new StringBuilder(s);
//        String item;
//        item = sb.substring(0,1).toString();
//        if (item.equals("1")){
//            temp+= 0.5f;
//        }
//        for (int i = 1; i < 7; i++) {
//            item = sb.substring(i,i+1);
//            if (item.equals("1")){
//                temp+=1*pow(2,6-i);
//            }
//
//        }
//        item = sb.substring(7,8);
//        if (item.equals("1")){
//            temp = -temp;
//        }
//        return temp;
//    }
    public boolean[] handleDrawer(){
        String s = this.Data;
        StringBuilder sb2 = new StringBuilder(s);
        s = sb2.substring(60,64);
        s = Tool.hexString2binaryString(s,16);
        StringBuilder sb = new StringBuilder(s);
        boolean[] drawer = new boolean[10];
        for (int i = 0; i < 7; i++) {
            drawer[7-i] = sb.substring(i, i + 1).equals("1");
        }
        drawer[9] = sb.substring(15, 16).equals("1");
        drawer[8] = sb.substring(14, 15).equals("1");
        return drawer;
    }


    public void decapsulate(String frame){

        StringBuilder sb = new StringBuilder(frame);
        header = sb.substring(0,4);
        Frame_Len = sb.substring(4,6);
        Frame_Num = sb.substring(6,8);
        Device_Addr = sb.substring(8,10);
        Func_Num = sb.substring(10,12);
        Data = sb.substring(12,frame.length()-8);
        Check = sb.substring(frame.length()-8,frame.length()-4);
        end = sb.substring(frame.length()-4,frame.length());


    }

    public static void main(String[] args) {
        //String f = "FFFF2C010101FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000FFFFFFFF";
        StatusFrame sf = new StatusFrame();
        //sf = sf.decapsulate(f);
        System.out.println(sf.Data);
        data d = new data();
        d= sf.getFrame_Data();
        System.out.println(d.getCompressor());
    }
}
