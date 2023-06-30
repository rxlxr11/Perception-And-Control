import java.util.ArrayList;

public class ConPanel {
    private long conPanelID;
    private boolean compressor;
    private float measuring_temp;
    private float setting_temp;
    private Drawer[] drawerList= new Drawer[10];
    private int DrawerNum;
    public ConPanel(long id){
        this.conPanelID = id;
        this.compressor = false;
        this.measuring_temp = 0;
        this.setting_temp = 0;
        this.DrawerNum = 0;
    }

    public int getDrawerStatus(long DrawerID){
        for (int i = 0; i < DrawerNum; i++){
            if (this.drawerList[i].getDrawerID() == DrawerID){
                if (this.drawerList[i].getStatus() == true)
                    return 1;
                else
                    return 0;
            }
        }
        return -1;
    }

}
