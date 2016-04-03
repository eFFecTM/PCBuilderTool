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
    private Component component;

    public Catalogue()
    {
        list = new ArrayList<>();
        componentList = new ArrayList<>();
        searchList = new ArrayList<>();
        compareList = new ArrayList<>();
        component = new Component("", "", "", "");
        for(int sheetNr=0;sheetNr<6;sheetNr++)
        {
            for(int i=0;i<5;i++)
            {
                addComponent(component.getDetails(sheetNr, i));
            }

        }

        // Mag later ook weg
        show();
    }

    public void addComponent(Component component)
    {
        list.add(component);
    }

    // Mag later ook weg
    public void show()
    {
        for(Component component: list)
        {
            component.display();
        }
    }

    public ArrayList<Component> filterGroupComponent(int index)
    {
        for (Component component : list)
        {
            switch (index)
            {
                case 0:
                    if (component.getGroupComponent().equals("Motherboard"))
                    {
                        componentList.add(component);
                    }
                    break;
                case 1:
                    if (component.getGroupComponent().equals("CPU"))
                    {
                        componentList.add(component);
                    }
                    break;
                case 2:
                    if (component.getGroupComponent().equals("RAM"))
                    {
                        componentList.add(component);
                    }
                    break;
                case 3:
                    if (component.getGroupComponent().equals("GPU"))
                    {
                        componentList.add(component);
                    }
                    break;
                case 4:
                    if (component.getGroupComponent().equals("PSU"))
                    {
                        componentList.add(component);
                    }
                    break;
                case 5:
                    if (component.getGroupComponent().equals("Drive"))
                    {
                        componentList.add(component);
                    }
                    break;
            }

        }

        for (Component component : componentList)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Group: " + component.getGroupComponent() + "\nName: " + component.getNameComponent() + "\nBrand: " + component.getBrandComponent() + "\nDetails: " + component.getOtherDetails() + "\n");
        }

        return componentList;
    }

    public void sort()
    {

    }

    public void compare()
    {

    }

    public ArrayList<Component> search(String name)
    {
        for (Component component : componentList)
        {
            if (component.getNameComponent().contains(name))
            {
                searchList.add(component);
            }
        }

        // To check: mag later weg
        for (Component component : searchList)
        {
            System.out.println("---------------------------------------");
            System.out.println("Group: " + component.getGroupComponent() + "\nName: " + component.getNameComponent() + "\nBrand: " + component.getBrandComponent() + "\nDetails: " + component.getOtherDetails() + "\n");
        }

        return searchList;
    }

}
