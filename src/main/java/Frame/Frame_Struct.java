package Frame;

public interface Frame_Struct {
    public static  String header = "ffff";
    public static String end = "fff7";
    public long Frame_Len = 0;
    public long Frame_Num = 0;
    public long Device_Addr = 0;
    public long Func_Num = 0;
    public long Data = 0;
    public long Check = 0;
    public void setFrame_Num(long num);
    public void setDevice_Addr(long addr);
    public void setData(long data);
    public void setFunc_Num(long num);
    public void setCheck(long check) ;
    public String buildFrame();
}
