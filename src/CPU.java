/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class CPU extends Component
{
    private String socket;

    public CPU(String groupComponent, String nameComponent, String brandComponent, String wattUsage, String otherDetails, String socket)
    {
        super(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails);
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
        return super.getWattUsage();
    }

    public String getSocketCPU()
    {
        return socket;
    }

    public String getOtherDetails()
    {
        return socket + "\n" + super.getOtherDetails();
    }

}
