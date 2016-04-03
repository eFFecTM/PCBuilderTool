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
                if (workbook.getSheetName(i).equals(name.toLowerCase()))
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
                                for (Component component : catalogue.list)
                                {
                                    if (component.getNameComponent().equals(nameComponent)) //Vergelijken van database met componenten in de excel file
                                    {
                                        myPc.userCfg.add(component);
                                    }
                                }
                                break;
                        }

                    }

                }
                file.close();

            } else
            {
                //Vragen voor input van de user: ja of nee?
                XSSFSheet sheet = workbook.createSheet(name.toLowerCase());
                FileOutputStream out = new FileOutputStream(new File("userCfg.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("User: " + name.toLowerCase() + " written successfully on userCfg.xlsx.");
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void saveUserCfg(String name)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("userCfg.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int amountSheet = workbook.getNumberOfSheets();
            int i = 0;
            int sheetFoundIndex = 0;
            int rowNum = 0;
            boolean sheetFound = false;
            Row row;
            Cell cell;


            while (i < amountSheet && !sheetFound)
            {

                if (workbook.getSheetName(i).equals(name.toLowerCase()))
                {
                    sheetFound = true;
                    sheetFoundIndex = i;
                }
                i++;
            }
            file.close(); //voor eventuele conflicten te vermijden
            if (sheetFound)
            {
                XSSFSheet sheet = workbook.getSheetAt(sheetFoundIndex);
                System.out.println("sheetindex " + sheetFoundIndex);
                row = sheet.createRow(rowNum++);
                cell = row.createCell(0);
                cell.setCellValue("groupComponent");
                cell = row.createCell(1);
                cell.setCellValue("nameComponent");

                for (Component component : myPc.userCfg)
                {
                    row = sheet.createRow(rowNum++);
                    System.out.println("Row num: " + rowNum);

                    for (int cellNum = 0; cellNum < 2; cellNum++)
                    {
                        cell = row.createCell(cellNum);
                        System.out.println("Cell num: " + cellNum);
                        switch (cellNum)
                        {
                            case 0:
                                cell.setCellValue(component.getGroupComponent());
                                System.out.println(component.getGroupComponent());
                                break;
                            case 1:
                                cell.setCellValue(component.getNameComponent());
                                System.out.println(component.getNameComponent());
                                break;
                        }
                    }
                }

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
