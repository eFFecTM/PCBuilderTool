/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PCBuilder
{
    static PCBuilderEngine PCBE;
    static GUI gui;
    static String loginName = "";
    static DefaultListModel<String> DLM;
    static ArrayList<String> componentNameList;
    private static String compareGroup1 = "";
    private static String compareName1 = "";
    private static String compareGroup2 = "";
    private static String compareName2 = "";
    private static int selectComponentGroupIndex; //Purpose: case switch to know what button was pressed
    private static int selectComponentIndex; //Purpose: which component was selected
    private static boolean checkCompare1 = false;
    private static boolean checkCompare2 = false;
    private static boolean saveCheck = false;
    private static Component selectedComponent;
    private static ArrayList<Component> componentList; //Specific array to be shown
    private static DefaultListModel<String> userCfgDLM;

    public static void main(String[] args) throws IOException
    {
        //Extracting excel files in directory
        try
        {
            java.util.jar.JarFile jar = new java.util.jar.JarFile("PCBuilderTool.jar");
            java.util.Enumeration enumEntries = jar.entries();
            while (enumEntries.hasMoreElements())
            {

                java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();

                if (file.getName().contains(".xlsx"))
                {
                    java.io.File f = new java.io.File(file.getName());
                    java.io.InputStream is = jar.getInputStream(file); // get the input stream

                    java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
                    while (is.available() > 0)
                    {  // write contents of 'is' to 'fos'
                        fos.write(is.read());
                    }

                    fos.close();
                    is.close();
                }
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("This is only needed if a jar file is made.");
        }


        PCBE = new PCBuilderEngine();
        gui = new GUI();
        DLM = new DefaultListModel<>();
        userCfgDLM = new DefaultListModel<>();
        componentList = new ArrayList<>();
        componentNameList = new ArrayList<>();

        // When logging in, it displays the usercfg and refreshes all the tabs
        gui.setLoginActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loginName = gui.loginText.getText();
                userCfgDLM.clear();
                PCBE.myPc.userCfg.clear();
                PCBE.getUserCfg(loginName);

                for (Component component : PCBE.myPc.userCfg)
                {
                    userCfgDLM.addElement(component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.userCfgList.setModel(userCfgDLM);
                gui.setProgressBar();
                gui.updateWattTab();
                gui.updateCheckCompatibilityTab();
                gui.updateExportTab();
                saveCheck = true;
            }
        });

        // When closing the window
        gui.frame.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                if (!PCBuilder.loginName.equals("") && !saveCheck)
                {
                    if (gui.setVerification("Are you sure to close this window without saving ?"))
                    {
                        System.exit(0);
                    }
                } else
                {
                    System.exit(0);
                }

            }
        });

        // Calculate watt usage when pressed on watt button
        gui.setCalculateWattButtonActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!loginName.equals(""))
                {
                    float totWattUsage = PCBE.myPc.calculateWattUsage();
                    gui.wattResults.setText("Total amount of watt usage of the current configuration: " + totWattUsage + " Watt\n");
                } else
                {
                    gui.setErrorPanel("No user logged in: No configuration found !");
                }
            }
        });

        // Exporting current usercfg to text file when pressed on export button
        gui.setExportButtonActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean success = PCBE.makeOfferFile();
                if (success)
                {
                    gui.exportResults.setText("Your offer has been written succesfully to the file: Offer_" + PCBuilder.loginName + ".txt");
                } else
                {
                    gui.exportResults.setText("The writing of your offer file has failed or has been canceled!");
                }
            }
        });

        // Checking compatibility when pressed on compatibility button
        gui.setCompatibilityCheckActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.updateCheckCompatibilityTab();
                boolean check = PCBE.myPc.checkCompatibility();
                if (check)
                {
                    gui.compatibilityResults.setText("Congratulations !! Your PC is fully compatible. All components are compatible.\n\nYou can export your PC to an offer file from within the 'Export Configuration' tab. Happy Gaming !!");
                } else if (PCBE.myPc.notCompatible == null)
                {
                    gui.compatibilityResults.setText("Not enough components selected to do a compatibility check !");
                } else
                {
                    gui.compatibilityResults.setText("Some parts are incompatible with each other: \n\n" + PCBE.myPc.notCompatible);
                }

            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectMotherboardActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 0;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectCPUActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 1;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectRAMActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 2;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectGPUActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 3;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectPSUActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 4;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Selecting component group and displaying the possible components to chose
        gui.setSelectDriveActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectComponentGroupIndex = 5;
                //reset ArrayList
                componentList.clear();
                //reset JList specificComponentList
                DLM.clear();
                componentList = PCBE.catalogue.filterGroupComponent(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Searching on input
        gui.setSearchActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String searchText = gui.searchText.getText();
                ArrayList<Component> searchList = PCBE.catalogue.search(searchText);
                DLM.clear();
                componentNameList.clear();

                for (Component component : searchList)
                {
                    componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                    DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());

                }

                gui.specificComponentList.setModel(DLM);
            }
        });

        // Sorting on alphabetical order
        gui.setSortAZActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                componentNameList.clear();
                for (Component component : PCBE.catalogue.searchList)
                {
                    componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                }
                Collections.sort(componentNameList);

                DLM.clear();

                for (String string : componentNameList)
                {
                    DLM.addElement(string);
                }
                gui.updateSpecificComponentList();
                gui.specificComponentList.setModel(DLM);

            }
        });

        // Sorting on inverse alphabetical order
        gui.setSortZAActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                componentNameList.clear();
                for (Component component : PCBE.catalogue.searchList)
                {
                    componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                }
                Collections.sort(componentNameList, Collections.reverseOrder());

                DLM.clear();

                for (String string : componentNameList)
                {
                    DLM.addElement(string);
                }

                gui.updateSpecificComponentList();
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Showing details and picture of selected component
        gui.specificComponentList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent)
            {
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                if (!adjust)
                {
                    selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                    if (selectComponentIndex >= 0)
                    {
                        selectedComponent = PCBE.catalogue.searchList.get(selectComponentIndex);
                        String textComponent = selectedComponent.toString();
                        gui.detailsTextArea.setText(textComponent);
                        try
                        {
                            gui.componentIcon.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                        } catch (NullPointerException e)
                        {

                            e.printStackTrace();
                            gui.componentIcon.setIcon(null);
                        }

                    }
                }
            }
        });

        // Adding a component to the current usercfg
        gui.setAddComponentActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean duplicate = PCBE.myPc.addComponent(selectedComponent, loginName);

                if (!duplicate)
                {
                    userCfgDLM.addElement(selectedComponent.getGroupComponent() + ": " + selectedComponent.getBrandComponent() + " " + selectedComponent.getNameComponent());
                    gui.setProgressBar();
                    saveCheck = false;
                } else
                {
                    gui.setErrorPanel("You already have a " + selectedComponent.getGroupComponent() + " in your configuration. Please delete if you want to continue.");
                }
                gui.userCfgList.setModel(userCfgDLM);
                gui.updateWattTab();
                gui.updateCheckCompatibilityTab();
                gui.updateExportTab();
            }
        });

        // The possibility to add 2 components to the compare tab
        gui.setAddToCompareActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!checkCompare1 || !checkCompare2)
                {
                    if (!checkCompare1)
                    {
                        selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                        if (selectComponentIndex >= 0)
                        {
                            selectedComponent = PCBE.catalogue.searchList.get(selectComponentIndex);
                            if ((!checkCompare1 && !checkCompare2) || (checkCompare2 && compareGroup2.equals(selectedComponent.getGroupComponent()) && !compareName2.equals(selectedComponent.getNameComponent())))
                            {
                                String textComponent = selectedComponent.toString();
                                gui.compareArea1.setText(textComponent);
                                try
                                {
                                    gui.compareIcon1.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                                } catch (NullPointerException en)
                                {
                                    en.printStackTrace();
                                    gui.compareIcon1.setIcon(null);
                                }
                                checkCompare1 = true;
                                compareGroup1 = selectedComponent.getGroupComponent();
                                compareName1 = selectedComponent.getNameComponent();
                            } else
                            {
                                gui.setErrorPanel("Comparing same component or different component group !");
                            }
                        }
                    } else
                    {
                        selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                        if (selectComponentIndex >= 0)
                        {
                            selectedComponent = PCBE.catalogue.searchList.get(selectComponentIndex);
                            if (checkCompare1 && compareGroup1.equals(selectedComponent.getGroupComponent()) && !compareName1.equals(selectedComponent.getNameComponent()))
                            {
                                String textComponent = selectedComponent.toString();
                                gui.compareArea2.setText(textComponent);
                                try
                                {
                                    gui.compareIcon2.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                                } catch (NullPointerException en)
                                {
                                    en.printStackTrace();
                                    gui.compareIcon2.setIcon(null);
                                }
                                checkCompare2 = true;
                                compareGroup2 = selectedComponent.getGroupComponent();
                                compareName2 = selectedComponent.getNameComponent();
                            } else
                            {
                                gui.setErrorPanel("Comparing same component or different component group !");
                            }
                        }
                    }
                } else
                {
                    gui.setErrorPanel("Compare is full, please remove a component to continue !");
                }
            }
        });

        // Saving current usercfg when pressed on save usercfg button
        gui.setSaveUserCfgActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveCheck = PCBE.saveUserCfg(loginName);
            }
        });

        // Removing the first component from the compare tab
        gui.setRemoveCompare1ActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.compareArea1.setText("");
                gui.compareIcon1.setIcon(null);
                checkCompare1 = false;
            }
        });

        // Removing the second component from the compare tab
        gui.setRemoveCompare2ActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.compareArea2.setText("");
                gui.compareIcon2.setIcon(null);
                checkCompare2 = false;
            }
        });

        // Selecting usercfgList elements and deleting elements with mouseclick = 2
        // Removing a component from the current usercfg
        gui.userCfgList.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 2)
                {
                    int index = gui.userCfgList.locationToIndex(evt.getPoint());
                    Component removeComponent = PCBE.myPc.userCfg.get(index);
                    if (gui.setVerification("Are you sure you want " +
                            "to remove selected component from your configuration ?"))
                    {
                        PCBE.myPc.removeComponent(removeComponent);
                        saveCheck = false;
                        userCfgDLM.clear();
                        for (Component component : PCBE.myPc.userCfg)
                        {
                            userCfgDLM.addElement(component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent());
                            gui.setProgressBar();
                        }
                        gui.userCfgList.setModel(userCfgDLM);
                        gui.updateWattTab();
                        gui.updateCheckCompatibilityTab();
                        gui.updateExportTab();
                    }
                }
            }
        });

    }

}
