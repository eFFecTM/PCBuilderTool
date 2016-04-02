/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class PSU extends Component
{
    private String maxWattage;

    public PSU(String groupComponent, String nameComponent, String brandComponent, String maxWattage, String otherDetails)
    {
        super(groupComponent, nameComponent, brandComponent, otherDetails);
        this.maxWattage = maxWattage;
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

    public String getMaxWattage()
    {
        return maxWattage;
    }

    public String toString()
    {
        return super.toString() + "\nMax Wattage: " + maxWattage + " Watt\n";
    }

}
