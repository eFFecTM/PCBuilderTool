/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class PSU extends Component
{
    private String maxWattage;

    public PSU(String groupComponent, String nameComponent, String brandComponent, String otherDetails, String maxWattage)
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

    public String getOtherDetails()
    {
        return maxWattage + "\n" + super.getOtherDetails();
    }

}
