import java.util.ArrayList;

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class Catalogue
{

    public ArrayList<Component> allComponentList;
    public ArrayList<Component> searchList;
    private ArrayList<Component> groupComponentList;
    private ArrayList<Component> compareList;
    private Component component;
    private boolean checkRows;
    public Catalogue()
    {
        allComponentList = new ArrayList<>();
        groupComponentList = new ArrayList<>();
        searchList = new ArrayList<>();
        compareList = new ArrayList<>();
        component = new Component("", "", "", "");
        for(int sheetNr=0;sheetNr<6;sheetNr++)
        {
//            int i = 0;
//            checkRows = true;
//            do
//            {
//                addComponent(component.getDetails(sheetNr, i));
//                i++;
//            } while (checkRows);
//
//            checkRows = true;
            for(int i=0;i<5;i++)
            {
                addComponent(component.getDetails(sheetNr, i));
            }

        }
    }

    public void addComponent(Component component)
    {
        allComponentList.add(component);
    }
    
    ArrayList<Component> filterGroupComponent(int index)
    {
        for (Component component : allComponentList)
        {
            switch (index)
            {
                case 0:
                    if (component.getGroupComponent().equals("Motherboard"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
                case 1:
                    if (component.getGroupComponent().equals("CPU"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
                case 2:
                    if (component.getGroupComponent().equals("RAM"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
                case 3:
                    if (component.getGroupComponent().equals("GPU"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
                case 4:
                    if (component.getGroupComponent().equals("PSU"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
                case 5:
                    if (component.getGroupComponent().equals("Drive"))
                    {
                        groupComponentList.add(component);
                    }
                    break;
            }
        }

        search("");
        return groupComponentList;
    }

    public void sort()
    {

    }

    public void compare()
    {

    }

    public ArrayList<Component> search(String searchText)
    {
        searchList.clear();
        for (Component component : groupComponentList)
        {
            if ((component.getBrandComponent() + " " + component.getNameComponent()).toLowerCase().contains(searchText.toLowerCase()))
            {
                searchList.add(component);
            }
        }
        return searchList;
    }

}
