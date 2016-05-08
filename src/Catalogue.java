/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Catalogue
{

    ArrayList<Component> allComponentList;
    ArrayList<Component> searchList;
    private ArrayList<Component> groupComponentList;

    public Catalogue() throws IOException
    {
        allComponentList = new ArrayList<>();
        groupComponentList = new ArrayList<>();
        searchList = new ArrayList<>();

        Component component = new Component("", "", "", "");
        for(int sheetNr=0;sheetNr<6;sheetNr++)
        {
            int maxRow = getAmountRowsExcelSheet(sheetNr);
            for (int i = 0; i < maxRow; i++)
            {
                addComponent(component.getDetails(sheetNr, i));
            }
        }
    }

    private int getAmountRowsExcelSheet(int sheetNr) throws IOException
    {
        FileInputStream file = new FileInputStream(new File("Catalogue.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(sheetNr);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();

        rowIterator.next();
        int maxRow = 0;

        while (rowIterator.hasNext())
        {
            rowIterator.next();
            maxRow++;
        }

        file.close();
        return maxRow;

    }

    private void addComponent(Component component)
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

    ArrayList<Component> search(String searchText)
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
