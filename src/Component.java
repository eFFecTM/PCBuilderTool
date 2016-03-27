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

    private String groupComponent;
    private String brandComponent;
    private String nameComponent;
    private String otherDetails;

    //read from Excel file
    private int rowIndex;

    public Component()
    {
        ArrayList<String> details = new ArrayList<String>();
        this.groupComponent = groupComponent;
        this.brandComponent = brandComponent;
        this.nameComponent = nameComponent;
        this.otherDetails = otherDetails;
        rowIndex = 0;
    }


    public void makeComponent()
    {

    }

    public int getDetails(int rowIndex)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("Catalogue.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheetMotherboard = workbook.getSheetAt(0);
            XSSFSheet sheetCPU = workbook.getSheetAt(1);
            XSSFSheet sheetRAM = workbook.getSheetAt(2);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheetMotherboard.iterator();

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " ");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + " ");
                            break;
                    }
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
