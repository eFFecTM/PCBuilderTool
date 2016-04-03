import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PCBuilderEngine
{
    Catalogue catalogue;
    PC myPc;
    ArrayList<Component> componentList;


    public PCBuilderEngine()
    {
        catalogue = new Catalogue();
        myPc = new PC();
        //catalogue.search("M");
        //System.out.println("Totale WattUsage: " + myPc.calculateWattUsage(ArrayList<Component> userCfg));
    }
/*
    public ArrayList<Component> selectComponentGroup(int index)
    {
        return componentList = catalogue.filterGroupComponent(index);
    }
    */

    public void makeOfferFile()
    {

    }

    public void getUserCfg(String name)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("userCfg.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int amountSheet = workbook.getNumberOfSheets();
            int i = 0;
            int sheetFoundIndex = 0;
            boolean sheetFound = false;
            String groupComponent;
            String nameComponent;

            while (i < amountSheet && !sheetFound)
            {
                if (workbook.getSheetName(i).equals(name))
                {
                    sheetFound = true;
                    sheetFoundIndex = i;
                }
                i++;
            }

            if (sheetFound)
            {
                XSSFSheet sheet = workbook.getSheetAt(sheetFoundIndex);
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext())
                {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        switch (cell.getColumnIndex())
                        {
                            case 0:
                                groupComponent = cell.getStringCellValue();
                                break;
                            case 1:
                                nameComponent = cell.getStringCellValue();
                                break;
                        }

                    }
                    //Component comp = .makeComponent(groupComponent, nameComponent);

                }
                file.close();

            } else
            {
                XSSFSheet sheet = workbook.createSheet(name);
                FileOutputStream out = new FileOutputStream(new File("userCfg.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("userCfg.xlsx written successfully on disk.");
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
