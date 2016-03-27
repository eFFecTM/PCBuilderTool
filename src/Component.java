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

    //read from Excel file
    private int rowIndex;

    public Component(String groupComponent, String brandComponent, String nameComponent, String otherDetails)
    {
        ArrayList<String> details = new ArrayList<String>();
        this.groupComponent = groupComponent;
        this.brandComponent = brandComponent;
        this.nameComponent = nameComponent;
        this.otherDetails = otherDetails;
        this.sheetNr = sheetNr;
        rowIndex = 0;
    }


    public void makeComponent(String groupComponent, String brandComponent, String nameComponent, String otherDetails)
    {

    }

    public int getDetails(int rowIndex, int sheetNr)
    {
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

                                Motherboard.changeSocket(cell.getStringCellValue());
                            } else if (sheetNr == 1)
                            {
                                CPU.changeSocket(cell.getStringCellValue());
                            } else if (sheetNr == 2)
                            {
                                RAM.changeType(cell.getStringCellValue());
                            }
                            else{
                                PSU.changeWatt(cell.getStringCellValue());
                            }

                            break;
                        case 4:
                            if (sheetNr == 0)
                            {
                                Motherboard.ramSlots(cell.getStringCellValue());
                            } else
                            {
                                RAM.changeAmount() = cell.getStringCellValue();
                            }
                            break;
                        case 5:
                            if(sheetNr == 0)
                            {
                                Motherboard.changeMaxRAM(cell.getStringCellValue());
                            } else
                            {
                                RAM.changeAmountSt(cell.getStringCellValue());
                            }
                            break;
                        case 6:
                            Motherboard.changeRAMType(cell.getStringCellValue());
                            break;

                    }

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

        return rowIndex++;
    }
}
