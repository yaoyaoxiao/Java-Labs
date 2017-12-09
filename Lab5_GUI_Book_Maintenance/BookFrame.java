import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookFrame extends JFrame
{
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 350;

    public static void main(String[] args)
    {
        if (args.length < 1) 
        {
            System.err.println("Usage: java BookFrame <data file> ");
            System.exit(1);
        }

        JFrame frame = new BookFrame();
        BookPanel bookPanel = new BookPanel(args[0]);
        JPanel panel = bookPanel.getPanel();
        frame.add(panel);

        frame.setTitle("Book Maintenance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);  
   }
}
