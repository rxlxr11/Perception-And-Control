package Frame;

public class ACKFrame extends FrameStruct{

    public ACKFrame (String frame){
        decapsulate(frame);
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


}
