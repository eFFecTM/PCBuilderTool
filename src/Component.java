/**
 * Created by IMac-Windows on 27/03/2016.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Component
{

    private ArrayList details;
    private String groupComponent;
    private String brandComponent;
    private String nameComponent;
    private String otherDetails;
    private int sheetNr;

    public Component(String groupComponent, String brandComponent, String nameComponent, String otherDetails,int sheetNr)
    {
        ArrayList<String> details = new ArrayList<String>();
        this.groupComponent = groupComponent;
        this.brandComponent = brandComponent;
        this.nameComponent = nameComponent;
        this.otherDetails = otherDetails;
        this.sheetNr = sheetNr;
    }


    public Component makeComponent(String groupComponent, String brandComponent, String nameComponent, String otherDetails, int sheetNr,String socket,String type,String maxWattage,String ramSlots,String amountGB,String maxRam,String amountSticks,String ramType)
    {
        switch(sheetNr)
        {
            case 0:
                Motherboard mb = new Motherboard(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr,socket,ramSlots,maxRam,ramType);
                return mb;

            case 1:
                CPU cpu = new CPU(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr,socket);
                return cpu;

            case 2:
                RAM ram = new RAM(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr,type,amountGB,amountSticks);
                return ram;

            case 3:
                GPU gpu = new GPU(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr);
                return gpu;

            case 4:
                PSU psu = new PSU(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr,maxWattage);
                return psu;

            case 5:
                Drive drive = new Drive(groupComponent, brandComponent, nameComponent, otherDetails,sheetNr);
                return drive;

        }

        return null;
    }

    public void getDetails(int sheetNr)
    {
        String socket ="";
        String type="";
        String maxWattage="";
        String ramSlots="";
        String amountGB="";
        String maxRam="";
        String amountSticks="";
        String ramType="";
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
            //for()    xcfgnh,j;khdgsdhgf
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex())
                    {
                        case 0:
                            nameComponent = cell.getStringCellValue();
                            break;
                        case 1:
                            brandComponent = cell.getStringCellValue();
                            break;
                        case 2:
                            otherDetails = cell.getStringCellValue();
                            break;
                        case 3:
                            if (sheetNr == 0)
                            {
                                socket = cell.getStringCellValue();
                            } else if (sheetNr == 1)
                            {
                                socket = cell.getStringCellValue();
                            } else if (sheetNr == 2)
                            {
                                type = cell.getStringCellValue();
                            }
                            else{
                                maxWattage = cell.getStringCellValue();
                            }

                            break;
                        case 4:
                            if (sheetNr == 0)
                            {
                                ramSlots = cell.getStringCellValue();
                            } else
                            {
                                amountGB = cell.getStringCellValue();
                            }
                            break;
                        case 5:
                            if(sheetNr == 0)
                            {
                                maxRam = cell.getStringCellValue();
                            } else
                            {
                                amountSticks = cell.getStringCellValue();
                            }
                            break;
                        case 6:
                                ramType = cell.getStringCellValue();
                            break;

                    }
                    Component comp = makeComponent(groupComponent,brandComponent,nameComponent,otherDetails,sheetNr,socket,type,maxWattage,ramSlots,amountGB,maxRam,amountSticks,ramType);
                    catalogue.addComponent(comp);
                    System.out.println("\n");
            }
            file.close();


        } catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
