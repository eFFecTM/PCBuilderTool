import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre.
 */

public class PCBuilder
{
    static String loginName;
    public static void main(String[] args) throws IOException
    {

        PCBuilderEngine PCBE = new PCBuilderEngine();
        GUI gui = new GUI();

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
    }

}
