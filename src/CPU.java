/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class CPU extends Component
{
    private String socket;

    public CPU(String groupComponent, String brandComponent, String nameComponent, String otherDetails, String socket)
    {
        super(groupComponent, brandComponent, nameComponent, otherDetails);
        this.socket = socket;
    }
}
