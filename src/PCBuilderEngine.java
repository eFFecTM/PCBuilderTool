/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

class PCBuilderEngine
{
    Catalogue catalogue;
    PC myPc;
    ArrayList<Component> componentList;


    PCBuilderEngine() throws IOException
    {
        catalogue = new Catalogue();
        myPc = new PC();
    }

    boolean makeOfferFile()
    {


        if (!PCBuilder.loginName.equals(""))
        {
            try
            {
                if (!myPc.check || myPc.totWattUsage == 0)
                {
                    if (PCBuilder.gui.setVerification("Are you sure you want " +
                            "to continue without doing a Watt Usage and/or a compatibility check ?"))
                    {
                        PrintWriter writer = new PrintWriter("Offer_" + PCBuilder.loginName + ".txt", "UTF-8");
                        writer.println("--------------------------------------------------");
                        writer.println("| User: " + PCBuilder.loginName.toUpperCase());
                        writer.println("--------------------------------------------------");
                        writer.println("| List of components");
                        String textComponent = "";
                        for (Component component : PCBuilder.PCBE.myPc.userCfg)
                        {
                            textComponent += "--------------------------------------------------\n| " + component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent() + "\n";
                        }
                        writer.println(textComponent + "--------------------------------------------------");

                        if (myPc.totWattUsage != 0)
                        {
                            writer.println("| Total watt usage: " + myPc.totWattUsage + " Watt");
                            writer.println("--------------------------------------------------");
                        } else
                        {
                            writer.println("| Watt usage was not calculated!");
                            writer.println("--------------------------------------------------");
                        }

                        if (myPc.check)
                        {
                            writer.println("| Compatibility check: SUCCESS");
                            writer.println("--------------------------------------------------");
                        } else
                        {
                            writer.println("| Compatibility check: FAILURE");
                            writer.println("--------------------------------------------------");
                        }
                        writer.close();
                        return true;
                    } else
                    {
                        return false;
                    }

                } else
                {
                    PrintWriter writer = new PrintWriter("Offer_" + PCBuilder.loginName + ".txt", "UTF-8");
                    writer.println("--------------------------------------------------");
                    writer.println("| User: " + PCBuilder.loginName.toUpperCase());
                    writer.println("--------------------------------------------------");
                    writer.println("| List of components");
                    String textComponent = "";
                    for (Component component : PCBuilder.PCBE.myPc.userCfg)
                    {
                        textComponent += "--------------------------------------------------\n| " + component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent() + "\n";
                    }
                    writer.println(textComponent + "--------------------------------------------------");
                    writer.println("| Total watt usage: " + myPc.totWattUsage + " Watt");
                    writer.println("--------------------------------------------------");

                    if (myPc.check)
                    {
                        writer.println("| Compatibility check: SUCCESS");
                        writer.println("--------------------------------------------------");
                    } else
                    {
                        writer.println("| Compatibility check: FAILURE");
                        writer.println("--------------------------------------------------");
                    }
                    writer.close();
                    return true;
                }

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
                return false;
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                return false;
            }

        } else
        {
            PCBuilder.gui.setErrorPanel("You are not logged in!");
            return false;
        }

    }

    void getUserCfg(String name)
    {
        myPc.totWattUsage = 0;
        myPc.check = false;

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
                                for (Component component : catalogue.allComponentList)
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
                if (PCBuilder.gui.setVerification("User not found. Are you sure you wish to make a new user ?"))
                {
                    XSSFSheet sheet = workbook.createSheet(name.toLowerCase());
                    FileOutputStream out = new FileOutputStream(new File("userCfg.xlsx"));
                    workbook.write(out);
                    out.close();
                    System.out.println("User: " + name.toLowerCase() + " written successfully on userCfg.xlsx.");
                } else
                {
                    System.out.println("No new user is made");
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    boolean saveUserCfg(String name)
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

                //clearing sheet
                String sheetName = sheet.getSheetName();
                workbook.removeSheetAt(sheetFoundIndex);
                workbook.createSheet(sheetName);
                //new sheet is placed at the last spot
                sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);

                row = sheet.createRow(rowNum++);
                cell = row.createCell(0);
                cell.setCellValue("groupComponent");
                cell = row.createCell(1);
                cell.setCellValue("nameComponent");

                for (Component component : myPc.userCfg)
                {
                    row = sheet.createRow(rowNum++);

                    for (int cellNum = 0; cellNum < 2; cellNum++)
                    {
                        cell = row.createCell(cellNum);
                        switch (cellNum)
                        {
                            case 0:
                                cell.setCellValue(component.getGroupComponent());
                                break;
                            case 1:
                                cell.setCellValue(component.getNameComponent());
                                break;
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(new File("userCfg.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("userCfg.xlsx written successfully on disk.");
                return true;
            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
