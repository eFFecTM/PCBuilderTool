/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Component
{

    private String groupComponent;
    private String nameComponent;
    private String brandComponent;
    private String otherDetails;

    public Component(String groupComponent, String nameComponent, String brandComponent, String otherDetails)
    {
        this.groupComponent = groupComponent;
        this.nameComponent = nameComponent;
        this.brandComponent = brandComponent;
        this.otherDetails = otherDetails;
    }


    public Component makeComponent(String groupComponent, String nameComponent, String brandComponent,String wattUsage, String otherDetails, int sheetNr, String socket, String type, String maxWattage, String ramSlots, String amountGB, String maxRam, String amountSticks, String ramType)
    {
        switch(sheetNr)
        {
            case 0:
                return new Motherboard(groupComponent, nameComponent, brandComponent,wattUsage, otherDetails, socket, ramSlots, maxRam, ramType);

            case 1:
                return new CPU(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails, socket);

            case 2:
                return new RAM(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails, type, amountGB, amountSticks);

            case 3:
                return new GPU(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails);

            case 4:
                return new PSU(groupComponent, nameComponent, brandComponent, maxWattage, otherDetails);

            case 5:
                return new Drive(groupComponent, nameComponent, brandComponent, wattUsage, otherDetails);
        }

        return null;
    }

    public Component getDetails(int sheetNr,int rij)
    {
        String wattUsage = "";
        String socket = "";
        String type = "";
        String maxWattage = "";
        String ramSlots = "";
        String amountGB = "";
        String maxRam = "";
        String amountSticks = "";
        String ramType = "";

        try
        {
            FileInputStream file = new FileInputStream(new File("Catalogue.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(sheetNr);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            Row row = rowIterator.next();

            for (int i = 0; i <= rij; i++)
            {
                row = rowIterator.next();
            }

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();

                groupComponent = sheet.getSheetName();
                switch (cell.getColumnIndex())
                {
                    case 0:
                        nameComponent = cell.getStringCellValue();
                        break;
                    case 1:
                        brandComponent = cell.getStringCellValue();
                        break;
                    case 2:
                        if (sheetNr == 4)
                        {
                            maxWattage = cell.getStringCellValue();
                            System.out.println(maxWattage);
                        } else
                        {
                            wattUsage = cell.getStringCellValue();
                        }
                        break;
                    case 3:
                        otherDetails = cell.getStringCellValue();
                        break;
                    case 4:
                        if (sheetNr == 0)
                        {
                            socket = cell.getStringCellValue();
                        } else if (sheetNr == 1)
                        {
                            socket = cell.getStringCellValue();
                        } else
                        {
                            type = cell.getStringCellValue();
                        }
                        break;
                    case 5:
                        if (sheetNr == 0)
                        {
                            ramSlots = cell.getStringCellValue();
                        } else
                        {
                            amountGB = cell.getStringCellValue();
                        }
                        break;
                    case 6:
                        if (sheetNr == 0)
                        {
                            maxRam = cell.getStringCellValue();
                        } else
                        {
                            amountSticks = cell.getStringCellValue();
                        }
                        break;
                    case 7:
                        ramType = cell.getStringCellValue();
                        break;
                }
            }

            file.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Component comp = makeComponent(groupComponent, nameComponent, brandComponent,wattUsage, otherDetails, sheetNr, socket, type, maxWattage, ramSlots, amountGB, maxRam, amountSticks, ramType);
        return comp;
    }

    public String getGroupComponent()
    {
        return groupComponent;
    }

    public String getNameComponent()
    {
        return nameComponent;
    }

    public String getBrandComponent()
    {
        return brandComponent;
    }


    public String getOtherDetails()
    {
        return otherDetails;
    }

    public String toString()
    {
        String alles = "\nGroup: " + getGroupComponent() + "\nName: " + getNameComponent() + "\nBrand: " + getBrandComponent() + "\nOther Details: " + getOtherDetails();
        return alles;
    }

    public void display()
    {
        System.out.println(toString());
    }

    public String getDetailedDetails()
    {
        return toString();
    }
}