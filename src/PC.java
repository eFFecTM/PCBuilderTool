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

    public void addComponent(Component component)
    {
        userCfg.add(component);
    }

    public void removeComponent(Component component)
    {
        int removeIndex = 100;
        String componentName = component.getNameComponent();

        for (int i = 0; i < userCfg.size(); i++)
        {

            component = userCfg.get(i);
            if (componentName.equals(component.getNameComponent()))
            {
                removeIndex = i;
            }
        }

        userCfg.remove(removeIndex);
    }

    public int calculateWattUsage()
    {


        return wattUsage;
    }

}
