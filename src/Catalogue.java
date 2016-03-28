import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class Catalogue
{

    private ArrayList<Component> list;
    private ArrayList componentList;
    private ArrayList searchList;
    private ArrayList compareList;

    public Catalogue()
    {
        list = new ArrayList<Component>();
        ArrayList<Component> componentList = new ArrayList<Component>();
        ArrayList<Component> searchList = new ArrayList<Component>();
        ArrayList<Component> compareList = new ArrayList<Component>();
        for(int sheetNr=0;sheetNr<6;sheetNr++)
        {
            for(int i=0;i<2;i++)
            {
                Component component = new Component("", "", "", "", sheetNr);
                component.getDetails(sheetNr,i);
                addComponent(component);
            }

        }
    }

    public void show()
    {
        for(Component component: list)
        {
            component.display();
            System.out.println();
        }
    }

    public void sort()
    {

    }

    public void compare()
    {

    }

    public void search()
    {

    }

    public void addComponent(Component component)
    {
        list.add(component);
    }

}
