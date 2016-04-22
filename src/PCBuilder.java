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

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class PCBuilder
{
    static PCBuilderEngine PCBE;
    static GUI gui;
    static String loginName = "";
    static String compareGroup = "";
    static String compareName = "";
    static int selectComponentGroupIndex; //dient voor de case switch om te weten welke knop was ingedrukt
    static int selectComponentIndex; //dient om te weten welk component is geselecteerd
    static int clearCompare = 0;
    static Component selectedComponent;
    static ArrayList<Component> componentList; //specifieke weer te geven arraylist
    static DefaultListModel<String> DLM;
    static DefaultListModel<String> userCfgDLM;
    static ArrayList<String> componentNameList;

    public static void main(String[] args) throws IOException
    {
        //extracting excel files in directory
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
                    System.out.println(file.getName());

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
        DLM = new DefaultListModel();
        userCfgDLM = new DefaultListModel();
        componentList = new ArrayList<>();
        componentNameList = new ArrayList<>();

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
                    userCfgDLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                }
                gui.userCfgList.setModel(userCfgDLM);
                gui.setProgressBar();
                gui.updateWattTab();
                gui.updateCheckCompatibilityTab();
                gui.updateExportTab();
            }
        });

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

        // Compatibility tab button
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
                } else if (!check && PCBE.myPc.notCompatible == null)
                {
                    gui.compatibilityResults.setText("Not enough components selected to do a compatibility check !");
                } else
                {
                    gui.compatibilityResults.setText("Some parts are incompatible with each other: \n\n" + PCBE.myPc.notCompatible);
                }

            }
        });

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

        // Search listener
        // Moet gefixt worden zie gui.setSearchActionListener(new ActionListener()
        gui.setSearchActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String searchText = gui.searchText.getText();
                DLM.clear();
                componentNameList.clear();
                if(searchText.equals(""))
                {
                    for (Component component : componentList)
                    {

                        System.out.println(component.getBrandComponent() + " " + component.getNameComponent() +"\n");
                        componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                        DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());

                    }
                }
                else
                {
                    for (Component component : componentList)
                    {
                        if ((component.getBrandComponent() + " " + component.getNameComponent()).toLowerCase().contains(searchText.toLowerCase()))
                        {
                            componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                            DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
                        }
                    }
                }

                gui.updateSpecificComponentList(searchText);
                gui.specificComponentList.setModel(DLM);
            }
        });

        gui.setSortAZActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                componentNameList.clear();
                for (Component component : componentList)
                {
                    componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                }
                Collections.sort(componentNameList);

                DLM.clear();

                for (String string : componentNameList)
                {
                    DLM.addElement(string);
                }
                gui.updateSpecificComponentList("a");
                gui.specificComponentList.setModel(DLM);

            }
        });

        gui.setSortZAActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                componentNameList.clear();
                for (Component component : componentList)
                {
                    componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                }
                Collections.sort(componentNameList, Collections.reverseOrder());

                DLM.clear();

                for (String string : componentNameList)
                {
                    DLM.addElement(string);
                }

                gui.updateSpecificComponentList("a");
                gui.specificComponentList.setModel(DLM);
            }
        });

        // Component in detail weergeven
        gui.specificComponentList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent)
            {
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                String textComponent = "";
                if (!adjust)
                {
                    //gui.specificComponentList = (JList) listSelectionEvent.getSource();
                    selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                    if (selectComponentIndex >= 0)
                    {
                        selectedComponent = componentList.get(selectComponentIndex);
                        textComponent = selectedComponent.getDetailedDetails();
                        gui.detailsTextArea.setText(textComponent);
                        gui.componentIcon.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                        //gui.componentIcon.setIcon(icon); <--werkt niet!
                        //gui.componentIcon.setText("Hallo");
                    }
                }
            }
        });

        gui.setAddComponentActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean duplicate = PCBE.myPc.addComponent(selectedComponent, loginName);

                if (!duplicate)
                {
                    userCfgDLM.addElement(selectedComponent.getBrandComponent() + " " + selectedComponent.getNameComponent());
                    gui.setProgressBar();
                } else
                {
                    gui.setErrorPanel("Trying to add a component which already is present in your configuration !");
                }
                gui.userCfgList.setModel(userCfgDLM);
                gui.updateWattTab();
                gui.updateCheckCompatibilityTab();
                gui.updateExportTab();
            }
        });

        gui.setAddToCompareActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch (clearCompare)
                {
                    case 0:
                        selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                        if (selectComponentIndex >= 0)
                        {
                            selectedComponent = componentList.get(selectComponentIndex);
                            compareGroup = selectedComponent.getGroupComponent();
                            compareName = selectedComponent.getNameComponent();
                            String textComponent = selectedComponent.getDetailedDetails();
                            gui.compareArea1.setText(textComponent);
                            gui.compareIcon1.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                            clearCompare++;
                        }
                        break;

                    case 1:
                        selectComponentIndex = gui.specificComponentList.getSelectedIndex();
                        if (selectComponentIndex >= 0)
                        {
                            selectedComponent = componentList.get(selectComponentIndex);
                            if (compareGroup.equals(selectedComponent.getGroupComponent()) && !compareName.equals(selectedComponent.getNameComponent()))
                            {
                                String textComponent = selectedComponent.getDetailedDetails();
                                gui.compareArea2.setText(textComponent);
                                gui.compareIcon2.setIcon(new ImageIcon(getClass().getResource("/resources/" + selectedComponent.getNameComponent() + ".jpeg")));
                                clearCompare++;
                            } else
                            {
                                gui.setErrorPanel("Comparing same component or component group !");
                            }

                        }
                        break;

                    case 2:
                        gui.setErrorPanel("Compare is full, clearing...");
                        clearCompare = 0;
                        System.out.println("Compare tab has been cleared");
                        gui.compareArea1.setText("");
                        gui.compareIcon1.setIcon(null);
                        gui.compareArea2.setText("");
                        gui.compareIcon2.setIcon(null);

                }


            }
        });

        gui.setSaveUserCfgActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PCBE.saveUserCfg(loginName);
            }
        });

        // Selecting usercfgList elements an deleting elements with mouseclick = 2

        gui.userCfgList.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 2)
                {
                    System.out.println(gui.userCfgList.locationToIndex(evt.getPoint()));
                    int index = gui.userCfgList.locationToIndex(evt.getPoint());
                    Component removeComponent = PCBE.myPc.userCfg.get(index);
                    if (gui.setRemoveComponentVerification())
                    {
                        PCBE.myPc.removeComponent(removeComponent);
                        userCfgDLM.clear();
                        for (Component component : PCBE.myPc.userCfg)
                        {
                            userCfgDLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
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
