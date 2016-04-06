import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PC
{
    public ArrayList<Component> userCfg;
    private float totWattUsage;
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

    public boolean addComponent(Component component, String name)
    {
        if (!name.equals(""))
        {
            boolean duplicate = false;
            if (userCfg.size() < 6)
            {
                for (Component comp : userCfg)
                {
                    if (component.getGroupComponent().equals(comp.getGroupComponent()))
                    {
                        duplicate = true;
                        System.out.println("Duplicate element found : " + component.getGroupComponent());
                    }
                }

                if (!duplicate)
                {
                    userCfg.add(component);
                }
                System.out.println("Duplicate: " + duplicate);
                return duplicate;
            }
        }
        return true;
    }

    // connect with button
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

    public float calculateWattUsage()
    {
        totWattUsage = 0;
        if(userCfg.size() == 6)
        {
            for(Component component : userCfg)
            {
                if (!component.getGroupComponent().equals("PSU"))
                {
                    totWattUsage += Float.parseFloat(component.getWattUsage());
                }
            }
        } else if (PCBuilder.gui.setCalcWattVerification())
        {
            for (Component component : userCfg)
            {
                if (!component.getGroupComponent().equals("PSU"))
                {
                    totWattUsage += Float.parseFloat(component.getWattUsage());
                }
            }
        }
        return totWattUsage;
    }

}
