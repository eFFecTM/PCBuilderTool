import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PC
{
    private ArrayList<Component> userCfg;
    private int wattUsage;
    private boolean check;
    private int amount;

    public PC()
    {
        userCfg = new ArrayList<>();
        this.wattUsage = wattUsage;
        this.check = check;
        this.amount = amount;
    }

    public boolean checkCompatibility()
    {


        return check;
    }

    public void addComponent()
    {

    }

    public void removeComponent()
    {

    }

    public int calculateWattUsage()
    {


        return wattUsage;
    }

}
