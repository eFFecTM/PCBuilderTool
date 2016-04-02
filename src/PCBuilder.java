import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class PCBuilder
{
    static PCBuilderEngine PCBE;
    static GUI gui;
    static String loginName;
    static int selectComponentGroupIndex; //dient voor de case switch om te weten welke knop was ingedrukt
    static ArrayList<Component> componentList;
    static DefaultListModel DLM;

    public static void main(String[] args) throws IOException
    {

        PCBE = new PCBuilderEngine();
        gui = new GUI();
        DLM = new DefaultListModel();
        componentList = new ArrayList<>();

        gui.setLoginActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loginName = gui.loginText.getText();
                System.out.println("main: " + loginName);
                PCBE.getUserCfg(loginName);
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
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
                componentList = PCBE.selectComponentGroup(selectComponentGroupIndex);

                for(Component component : componentList)
                {
                    DLM.addElement(component.getNameComponent());
                }
                gui.specificComponentList.setModel(DLM);
            }
        });

        gui.specificComponentList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                gui.specificComponentList = (JList)evt.getSource();
                int index = 0;
                if (evt.getClickCount() == 1) {

                    // Double-click detected
                    index = gui.specificComponentList.locationToIndex(evt.getPoint());
                    System.out.println("Tot hier en index: " + index);
                }


                Component componentIndex = componentList.get(index);
                String textComponent = "\nGroup: " + componentIndex.getGroupComponent() + "\n\nName: " + componentIndex.getNameComponent()
                        + "\nBrand: " + componentIndex.getBrandComponent() + "\nWatt usage: " + componentIndex.getWattUsage() + "\nDetails: " + componentIndex.getOtherDetails() + "\n";

                textComponent += componentIndex.getOtherDetails();

                }


        });


    }

}
