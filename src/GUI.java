import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre on 17/03/2016.
 */
public class GUI {

    //Main Pane
    public JPanel mainPanel;
    private JTabbedPane mainTabbedPanel;
    private JTextArea welcomeTitle;
    public JTextField loginText;
    private JTextArea userCfgTitle;
    private JList userCfgList;
    private JLabel PCBuilderIcon;
    private JProgressBar progressBar;
    //public String input;

    //Main Tabbed Panes
    private JPanel catalogueTab;
    private JPanel compareTab;
    private JPanel wattTab;
    private JPanel compatibilityTab;
    private JPanel exportTab;

    //catalogue Tab
    private JTextArea detailsTextArea;
    private JTextArea detailsTitleTextArea;
    private JTextArea componentGroupTitle;
    private JTextArea specificComponentTitle;
    private JButton motherboardButton;
    private JButton CPUButton;
    private JButton RAMButton;
    private JButton GPUButton;
    private JButton PSUButton;
    private JButton drivesButton;
    private JButton addComponent;
    private JButton addCompare;
    private JLabel selectedComponentPicture;

    //Compare Tab
    private JTextArea component1;
    private JTextArea component2;
    private JList list1;
    private JList list2;
    private JButton compareButton;
    private JLabel picture1;
    private JLabel picture2;


    //Watt Usage Tab
    private JTextArea wattInfo;
    private JButton calculateButton;
    private JTextArea wattResults;

    //Compatibility Check Tab
    private JTextArea compatibilityInfo;
    private JButton compatibilityButton;
    private JTextArea compatibilityResults;

    //Export (offer file) Tab
    private JTextArea exportInfo;
    private JButton exportButton;
    private JTextArea exportResults;


    public GUI()
    {
        JFrame frame = new JFrame("PCBuilder Tool");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(900, 400);
        frame.setVisible(true);

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
}
