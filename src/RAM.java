/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class RAM extends Component
{
    private String type;
    private String amountGB;
    private String amountSticks;

    public RAM(String groupComponent, String nameComponent, String brandComponent, String wattUsage, String otherDetails, String type, String amountGB, String amountSticks)
    {
        super(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails);
        this.type = type;
        this.amountGB = amountGB;
        this.amountSticks = amountSticks;
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
        return super.getWattUsage();
    }

    public String getTypeRAM()
    {
        return type;
    }

    public String getAmountGB()
    {
        return amountGB;
    }

    public String getAmountSticks()
    {
        return amountSticks;
    }

    public String getOtherDetails()
    {
        return type + "\n" + amountGB + "\n" + amountSticks + "\n" + super.getOtherDetails();
    }

}
