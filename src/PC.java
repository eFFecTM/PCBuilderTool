import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PC {
    private ArrayList userCfg;
    private int wattUsage;
    private boolean check;
    private int amount;

    public PC() {
        ArrayList<Component> userCfg = new ArrayList<Component>();
        this.wattUsage = wattUsage;
        this.check = check;
        this.amount = amount;
    }

    public boolean checkCompatibility() {


        return check;
    }

    public void addComponent() {

    }

    public void removeComponent() {

    }

    public int calculateWattUsage() {
        return wattUsage;
    }

}
