/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class RAM extends Component
{
    private String type;
    private String amountGB;
    private String amountSticks;

    public RAM(String groupComponent, String brandComponent, String nameComponent, String otherDetails,int sheetNr, String type, String amountGB,String amountSticks)
    {
        super(groupComponent, brandComponent, nameComponent, otherDetails,sheetNr);
        this.type = type;
        this.amountGB = amountGB;
        this.amountSticks = amountSticks;
    }
}
