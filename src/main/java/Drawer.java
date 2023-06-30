import java.util.ArrayList;

public class Drawer  {
    private boolean status;
    private long DrawerID;
    public Drawer(long id){
        this.DrawerID = id;
        this.status = false;
    }

    public long getDrawerID() {
        return DrawerID;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }


    public void open_Drawer(){
        this.status = true;
    }
    public void close_Drawer(){
        this.status = false;
    }

}
