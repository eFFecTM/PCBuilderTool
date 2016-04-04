import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;


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
    //Watt Usage Tab
    public JTextArea wattInfo;
    public JTextArea wattResults;
    private JTextArea welcomeTitle;
    private JTextArea userCfgTitle;
    //public String input;
    private JLabel PCBuilderIcon;
    private JProgressBar progressBar;
    //Main Tabbed Panes
    private JPanel catalogueTab;
    private JPanel compareTab;
    private JPanel wattTab;
    private JPanel compatibilityTab;
    private JPanel exportTab;
    private JTextArea detailsTitleTextArea;
    private JTextArea componentGroupTitle;
    private JTextArea specificComponentTitle;
    private JScrollPane specificComponentScrollPane;
    private JButton motherboardButton;
    private JButton CPUButton;
    private JButton RAMButton;
    private JButton GPUButton;
    private JButton PSUButton;
    private JButton drivesButton;
    private JButton addComponent;
    private JButton addCompare;
    private JButton saveUserCfg;
    //Compare Tab
    private JTextArea component1;
    private JTextArea component2;
    private JList list1;
    private JList list2;
    private JButton compareButton;
    private JLabel picture1;
    private JLabel picture2;
    //Compatibility Check Tab
    private JTextArea compatibilityInfo;
    private JButton compatibilityButton;
    private JTextArea compatibilityResults;

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

    public void setSelectComponentListener(ListSelectionListener lsl)
    {
        specificComponentList.addListSelectionListener(lsl);
    }

    public void setAddComponentActionListener(ActionListener al)
    {
        addComponent.addActionListener(al);
    }

    public void setSaveUserCfgActionListener(ActionListener al)
    {
        saveUserCfg.addActionListener(al);
    }

    public void setAddUserCfgListener(ListSelectionListener lsl)
    {
        userCfgList.addListSelectionListener(lsl);
    }

    public void setErrorPanel()
    {
        JOptionPane.showMessageDialog(errorPanel, "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
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
        PCBuilder.PCBE.myPc.wattInfo.setText(textComponent);
    }
}
