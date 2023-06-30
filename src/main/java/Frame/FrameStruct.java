package Frame;

public class FrameStruct {
    public static String header = "ffff";
    public static String end = "fff7";
    public String Frame_Len;
    public String Frame_Num;
    public String Device_Addr;
    public String Func_Num;
    public String Data;
    public String Check;
    public String CRC_Func(){
        return "ffff";
    }

    public FrameStruct(){

    }
//    public void setFrame_Num(String num){
//        this.Frame_Num = num;
//    }
//    public void setDevice_Addr(String addr){
//        this.Device_Addr = addr;
//    }
//    public void setData(String data){
//       // this.Data = data;
//    }
//    public void setFunc_Num(String num){
//        this.Func_Num = num;
//    }
//    public void setCheck(String check) {
//        this.Check = check;
//    }

    public String encapsulate(){

        StringBuilder sb = new StringBuilder();
        sb.append(this.header).append(this.Frame_Len).append(Frame_Num).append(this.Device_Addr);
        sb.append(this.Func_Num).append(this.Data).append(this.Check).append(this.end);

        return sb.toString();
    }

    public FrameStruct decapsulate(String frame){

        FrameStruct frameStruct = new FrameStruct();
        StringBuilder sb = new StringBuilder(frame);
        frameStruct.header = sb.substring(0,4);
        frameStruct.Frame_Len = sb.substring(4,6);
        frameStruct.Frame_Num = sb.substring(6,8);
        frameStruct.Device_Addr = sb.substring(8,10);
        frameStruct.Func_Num = sb.substring(10,12);
        frameStruct.Data = sb.substring(12,frame.length()-8);
        frameStruct.Check = sb.substring(frame.length()-8,frame.length()-4);
        frameStruct.end = sb.substring(frame.length()-4,frame.length());

        return frameStruct;
    }

}
