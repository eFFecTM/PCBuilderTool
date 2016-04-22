/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class RAM extends Component
{
    private String wattUsage;
    private String type;
    private String amountGB;
    private String amountSticks;

    public RAM(String groupComponent, String nameComponent, String brandComponent, String wattUsage, String otherDetails, String type, String amountGB, String amountSticks)
    {
        super(groupComponent, nameComponent, brandComponent, otherDetails);
        this.wattUsage = wattUsage;
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
        return wattUsage;
    }

    String getTypeRAM()
    {
        return type;
    }

    String getAmountGB()
    {
        return amountGB;
    }

    String getAmountSticks()
    {
        return amountSticks;
    }

    public String toString()
    {
        return super.toString() + "\nWatt Usage: " + wattUsage + " Watt\nType: " + type + "\nAmount of RAM: " + amountGB + " GB\nAmount of RAM Sticks: " + amountSticks + "\n";
    }

}
