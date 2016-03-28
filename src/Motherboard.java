/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class Motherboard extends Component
{
    private String socket;
    private String ramSlots;
    private String maxRam;
    private String ramType;

    public Motherboard(String groupComponent, String brandComponent, String nameComponent, String otherDetails,int sheetNr, String socket, String ramSlots, String maxRam, String ramType)
    {
        super(groupComponent, brandComponent, nameComponent, otherDetails,sheetNr);
        this.socket = socket;
        this.ramSlots = ramSlots;
        this.maxRam = maxRam;
        this.ramType = ramType;
    }
}
