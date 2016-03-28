import java.util.ArrayList;

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class Catalogue
{

    private ArrayList<Component> list;
    private ArrayList<Component> componentList;
    private ArrayList<Component> searchList;
    private ArrayList<Component> compareList;

    public Catalogue()
    {
        list = new ArrayList<>();
        componentList = new ArrayList<>();
        searchList = new ArrayList<>();
        compareList = new ArrayList<>();
        for(int sheetNr=0;sheetNr<6;sheetNr++)
        {
            for(int i=0;i<5;i++)
            {
                Component component = new Component("", "", "", "");
                component.getDetails(sheetNr,i);
                addComponent(component);
            }

        }
        show();
    }

    public void addComponent(Component component)
    {
        list.add(component);
    }

    public void show()
    {
        for(Component component: list)
        {
            System.out.println("\nGroup: " + component.getGroupComponent() + "\nName: " + component.getNameComponent() + "\nBrand: " + component.getBrandComponent() + "\nDetails: " + component.getOtherDetails() + "\n");
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

}
