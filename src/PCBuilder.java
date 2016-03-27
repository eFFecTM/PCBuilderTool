import javax.swing.*;

/**
 * Created by students UA:FTI-EI De Laet Jan & Yigit Yunus Emre on 17/03/2016.
 */

public class PCBuilder {

    public static void main(String[] args) {
        JFrame frame = new JFrame("PCBuilder Tool");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
