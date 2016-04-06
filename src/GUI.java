import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre on 17/03/2016.
 */
public class GUI {

    //Main Pane
    public JPanel mainPanel;
    public JTextField loginText;

    //catalogue Tab
    public JTextArea detailsTextArea;
    public JList specificComponentList;
    public JList userCfgList;
    public JButton calculateButton;
    public JTabbedPane mainTabbedPanel;
    public JTextField searchText;
    //Watt Usage Tab
    public JTextArea wattInfo;
    public JTextArea wattResults;
    public JButton addCompare;
    //Compare Tab
    public JTextArea compareArea1;
    public JTextArea compareArea2;
    public JProgressBar progressBar;
    //Compatibility Check Tab
    public JTextArea compatibilityInfo;
    public JTextArea compatibilityResults;
    private JButton sortZA;
    private JButton sortAZ;
    private JTextArea welcomeTitle;
    private JTextArea userCfgTitle;
    //public String input;
    private JLabel PCBuilderIcon;
    //Main Tabbed Panes
    private JPanel catalogueTab;
    private JPanel compareTab;
    private JPanel wattTab;
    private JPanel compatibilityTab;
    private JPanel exportTab;
    private JTextArea detailsTitleTextArea;
    private JTextArea specificComponentTitle;
    private JScrollPane specificComponentScrollPane;
    private JButton motherboardButton;
    private JButton CPUButton;
    private JButton RAMButton;
    private JButton GPUButton;
    private JButton PSUButton;
    private JButton drivesButton;
    private JButton addComponent;
    private JButton saveUserCfg;
    private JButton compatibilityButton;


    //Export (offer file) Tab
    private JTextArea exportInfo;
    private JButton exportButton;
    private JTextArea exportResults;


    //Error message plane
    private JPanel errorPanel;
    private JOptionPane loginPane;


    public GUI()
    {
        JFrame frame = new JFrame("PCBuilder Tool");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(900, 400);
        frame.setVisible(true);

        JPanel panel = new JPanel();

        /*
        loginText.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                input = loginText.getText();
                System.out.println("Login name: " + input);
            }
        });
        */

    }

    public void setLoginActionListener(ActionListener al)
    {
        loginText.addActionListener(al);
    }

    public void setCalculateWattButtonActionListener(ActionListener al)
    {
        calculateButton.addActionListener(al);
    }

    public void setCompatibilityCheckActionListener(ActionListener al)
    {
        compatibilityButton.addActionListener(al);
    }

    public void setSelectMotherboardActionListener(ActionListener al)
    {
        motherboardButton.addActionListener(al);
    }

    public void setSelectCPUActionListener(ActionListener al)
    {
        CPUButton.addActionListener(al);
    }

    public void setSelectRAMActionListener(ActionListener al)
    {
        RAMButton.addActionListener(al);
    }

    public void setSelectGPUActionListener(ActionListener al)
    {
        GPUButton.addActionListener(al);
    }

    public void setSelectPSUActionListener(ActionListener al)
    {
        PSUButton.addActionListener(al);
    }

    public void setSelectDriveActionListener(ActionListener al)
    {
        drivesButton.addActionListener(al);
    }

    public void setSearchActionListener(ActionListener al)
    {
        searchText.addActionListener(al);
    }

    public void setSortAZActionListener(ActionListener al)
    {
        sortAZ.addActionListener(al);
    }

    public void setSortZAActionListener(ActionListener al)
    {
        sortZA.addActionListener(al);
    }



    public void setAddComponentActionListener(ActionListener al)
    {
        addComponent.addActionListener(al);
    }

    // Compare Button in Component Catalogue tab

    public void setAddToCompareActionListener(ActionListener al)
    {
        addCompare.addActionListener(al);
    }

    public void setSaveUserCfgActionListener(ActionListener al)
    {
        saveUserCfg.addActionListener(al);
    }

    public void setErrorPanel()
    {
        JOptionPane.showMessageDialog(errorPanel, "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setProgressBar()
    {
        switch (PCBuilder.PCBE.myPc.userCfg.size())
        {
            case 1:
                progressBar.setValue(20);
                break;
            case 2:
                progressBar.setValue(40);
                break;
            case 3:
                progressBar.setValue(60);
                break;
            case 4:
                progressBar.setValue(80);
                break;
            case 5:
                progressBar.setValue(100);
                break;
            case 6:
                progressBar.setValue(120);
                break;
        }
    }

    public boolean setLoginVerification()
    {
        int result = JOptionPane.showConfirmDialog(null,
                "User not found. Are you sure you wish to make a new user ?", null, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public void updateWattTab()
    {
        String textComponent = "";
        for (Component component : PCBuilder.PCBE.myPc.userCfg)
        {
            textComponent += "\n" + component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent() + "\nIndividual Watt Usage: " + component.getWattUsage() + " Watt\n";
        }
        wattInfo.setText(textComponent);
    }

    public void updateCheckCompatibilityTab()
    {
        String textComponent = "Your configuration:\n";
        for (Component component : PCBuilder.PCBE.myPc.userCfg)
        {
            textComponent += "\n" + component.getGroupComponent() + ": " + component.getBrandComponent() + " " + component.getNameComponent();
        }
        compatibilityInfo.setText(textComponent);
    }

    public void updateSpecificComponentList(String searchText)
    {
        ArrayList<Component> tempList = new ArrayList<>();
        int size = PCBuilder.componentNameList.size();
        if (!searchText.equals(""))
        {
            for (int i = 0; i < size; i++)
            {
                for (Component component : PCBuilder.componentList)
                {
                    if ((component.getBrandComponent() + " " + component.getNameComponent()).equals(PCBuilder.componentNameList.get(i)))
                    {
                        tempList.add(component);
                    }
                }
            }

            PCBuilder.componentList.clear();
            for (Component component : tempList)
            {
                PCBuilder.componentList.add(component);
            }
        } else
        {
            PCBuilder.DLM.clear();

            for (Component component : PCBuilder.componentList)
            {
                PCBuilder.DLM.addElement(component.getBrandComponent() + " " + component.getNameComponent());
            }
            specificComponentList.setModel(PCBuilder.DLM);
        }
    }

    public boolean setRemoveComponentVerification()
    {
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to remove selected component from your configuration ?", null, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

}
