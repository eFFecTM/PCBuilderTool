/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class CPU extends Component
{
    private String wattUsage;
    private String socket;

    public CPU(String groupComponent, String nameComponent, String brandComponent, String wattUsage, String otherDetails, String socket)
    {
        super(groupComponent, nameComponent, brandComponent, otherDetails);
        this.wattUsage = wattUsage;
        this.socket = socket;
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

    public String getSocketCPU()
    {
        return socket;
    }

    public String toString()
    {
        return super.toString() + "\nWatt Usage: " + wattUsage + " Watt\nSocket: " + socket + "\n";
    }

}
