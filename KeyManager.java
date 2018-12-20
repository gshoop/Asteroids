import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Class for managing and keeping track of pressed and released keys
//In our case only the up, down, left, and right keys
public class KeyManager implements KeyListener
{
    //Boolean array for keys to use
    private boolean[] keys;
    public boolean up, down, left, right;
    //Constructor
    public KeyManager()
    {
        keys = new boolean[256];
    }
    //Continuously updates boolean values associated to the up, down, left
    //and right arrow keys
    public void tick()
    {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
    //Sets the keyCode to true if pressed.
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        //System.out.println("Pressed!"); //For testing purposes
    }
    //Sets the keyCode to false if released.
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    
    public void keyTyped(KeyEvent e)
    {
    }
}
