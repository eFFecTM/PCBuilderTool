/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PSU extends Component
{
    private String maxWattage;

    public PSU(String groupComponent, String brandComponent, String nameComponent, String otherDetails, String maxWattage)
    {
        super(groupComponent, brandComponent, nameComponent, otherDetails);
        this.maxWattage = maxWattage;
    }
}
