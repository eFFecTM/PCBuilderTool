import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                System.out.println("main: " + loginName);
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
            }
        });

        gui.setCalculateWattButtonActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                float totWattUsage = PCBE.myPc.calculateWattUsage();
                gui.wattResults.setText("Total amount of watt usage of the current configuration: " + totWattUsage + " Watt");
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
        gui.setSearchActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String searchText = gui.searchText.getText();
                DLM.clear();
                componentNameList.clear();
                for (Component component : componentList)
                {
                    if ((component.getBrandComponent() + " " + component.getNameComponent()).toLowerCase().contains(searchText.toLowerCase()))
                    {
                            componentNameList.add(component.getBrandComponent() + " " + component.getNameComponent());
                            DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
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
                    gui.setErrorPanel();
                }
                gui.userCfgList.setModel(userCfgDLM);
                gui.updateWattTab();
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
                                clearCompare++;
                            } else
                            {
                                gui.setErrorPanel();
                            }

                        }
                        break;

                    case 2:
                        gui.setErrorPanel();
                        clearCompare = 0;
                        System.out.println("Compare tab has been cleared");
                        gui.compareArea1.setText("");
                        gui.compareArea2.setText("");

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

                    }
                }

            }
        });

        ///srhsjnfbq(uhjwrsyndh

        //hello my friend
    }
}
