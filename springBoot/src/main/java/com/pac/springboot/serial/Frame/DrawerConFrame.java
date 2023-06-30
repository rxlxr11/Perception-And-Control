package com.pac.springboot.serial.Frame;

public class DrawerConFrame extends FrameStruct{
    public DrawerConFrame (boolean[] drawer){
        this.Frame_Len    = "0C";
        this.Func_Num     = "03";
        this.Check        = "0000";
        this.Frame_Num    = "03";
        this.Device_Addr  = "00";
        this.Data         = fit(drawer);

    }
    private String fit(boolean[] b){
        boolean[] booleans = enlarge(b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< 16; i++){
            if (booleans[i]){
                sb.append("1");
            }else{
                sb.append("0");
            }
        }
        System.out.println(sb.toString());
        String S = Tool.bin2hex(sb.toString());
        return S;
    }

    private boolean[] enlarge(boolean[] booleans){
        boolean[] b = new  boolean[16];
        for (int i = 0; i < 8; i++){
            b[7-i] = booleans[i];
        }
        b[15] = booleans[9];
        b[14] = booleans[8];
        return b;
    }
}
