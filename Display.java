//KIND OF UNNECESSARY CLASS CAN DELETE LATER
import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;

public class Display
{
    //Creating JFrame and Canvas to add to the JFrame
    private JFrame frame;
    private Canvas canvas;

    //Private variables for the game window title and sizing
    private String title;
    private int width, height;
    //Constructor
    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }
    //The big kahuna. Instantiates the JFrame and adds the canvas to the display.
    private void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

		
        frame.add(canvas);
        frame.pack();
    }
    //Accessor method for canvas
    public Canvas getCanvas()
    {
        return canvas;
    }

    //Accessor method for JFrame
    public JFrame getFrame()
    {
        return frame;
    }
}
