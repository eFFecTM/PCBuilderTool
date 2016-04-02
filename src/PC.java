import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PC
{
    public ArrayList<Component> userCfg;
    private int totWattUsage;
    private boolean check;
    private int amount;

    public PC()
    {
        userCfg = new ArrayList<>();
        this.totWattUsage = totWattUsage;
        this.check = check;
        this.amount = amount;
    }

    public boolean checkCompatibility()
    {


        return check;
    }

    public void addComponent(Component component)
    {
        if(userCfg.size() < 6)
        {
            userCfg.add(component);
        }
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


    public int calculateWattUsage(ArrayList<Component> userCfg)
    {
        String usage;

        if(userCfg.size() == 6)
        {
            for(Component component : userCfg)
            {
                //usage = component.;
                //totWattUsage += Integer.parseInt(usage);
            }
        }
        return totWattUsage;
    }

}
