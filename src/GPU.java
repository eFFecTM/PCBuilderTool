/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class GPU extends Component
{
    private String wattUsage;

    public GPU(String groupComponent, String nameComponent, String brandComponent, String wattUsage, String otherDetails)
    {
        super(groupComponent, nameComponent, brandComponent, otherDetails);
        this.wattUsage = wattUsage;
    }

    public String getGroupComponent()
    {
        return super.getGroupComponent();
    }

    public String getNameComponent()
    {
        return super.getNameComponent();
    }

    public String getBrandComponent()
    {
        return super.getBrandComponent();
    }

    public String getWattUsage()
    {
        return wattUsage;
    }


    public String toString()
    {
        return super.toString() + "\nWatt Usage: " + wattUsage + " Watt\n";
    }

}
