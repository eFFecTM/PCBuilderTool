/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class RAM extends Component
{
    private String type;
    private String amountGB;
    private String amountSticks;

    public RAM(String groupComponent, String nameComponent, String brandComponent, String otherDetails, String type, String amountGB, String amountSticks)
    {
        super(groupComponent, nameComponent, brandComponent, otherDetails);
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

    public String getOtherDetails()
    {
        return type + "\n" + amountGB + "\n" + amountSticks + "\n" + super.getOtherDetails();
    }

}
