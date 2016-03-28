/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class RAM extends Component
{
    private String type;
    private String amountGB;

    public RAM(String groupComponent, String brandComponent, String nameComponent, String otherDetails, String type, String amountGB)
    {
        super(groupComponent, brandComponent, nameComponent, otherDetails);
        this.type = type;
        this.amountGB = amountGB;
    }
}
