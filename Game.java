import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable
{
    private Display display;
    public final int width, height;
    public String title;
    //Boolean to tell our computer if we want to terminate the program
    private boolean running = false;
    //Thread for running our program.
    private Thread thread;
    //JFrame and Canvas. Where we want to render (paint) to.
    private JFrame frame;
    private Canvas canvas;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //States
    public State gameState;
    public State menuState;
    //KeyManager class that contains all methods for the keyListener
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Handler to allow us to access different parts of our program.
    private Handler handler;

    //Game Constructor
    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    //This method creates an instane of display with a title and size.
    private void init()
    {
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);      //Sets the window to the center of users screen on launch
        frame.setVisible(true);
        frame.addKeyListener(keyManager);
        frame.addMouseListener(mouseManager);
        frame.addMouseMotionListener(mouseManager);
        //Canvas similar to JPanel.
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);
        canvas.addMouseListener(mouseManager);
        canvas.addMouseMotionListener(mouseManager);

        frame.add(canvas);
        frame.pack();
        
        //Loading images used (lives, background)
        Assets.init();
        
        handler = new Handler(this);
        
        //endState = new EndState(handler);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
        //State.setState(gameState);
    }
    //Update screen
    private void tick()
    {
        keyManager.tick();
        
        if(State.getState() != null)
        {
            State.getState().tick();
        }
    }
    //Drawing to screen
    private void render()
    {
		bs = canvas.getBufferStrategy();
        if (bs == null)
        {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        //Drawing
        if(State.getState() != null)
        {
            State.getState().render(g);
        }
        
        bs.show();
        Toolkit.getDefaultToolkit().sync();         //Allows my linux OS to run game smoothly
        g.dispose();
    }
    //Main game run function overrided from Thread. First initialize the JFrame, canvas and
    //what we want to render to the canvas then we handle how often we want our program to tick (render).
    public void run()
    {
        init();
        
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        //Calculating intervals to tick the program so the game runs smooth. 60 ticks per second.
        while(running)
        {
            now = System.nanoTime();
            delta += (now-lastTime)/timePerTick;
            timer += now-lastTime;
            lastTime = now;
            //When the time interval is 1 or greater we want to tick (update) and render (repaint) our program
            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }
            //For testing purposes
            if(timer >= 1000000000)
            {
                //System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    //Get methods for key and mouse managers
    public KeyManager getKeyManager()
    {
        return keyManager;
    }
    
    public MouseManager getMouseManager()
    {
        return mouseManager;
    }
    //Use of threads to start and stop
    public synchronized void start()
    {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    //MAIN//
    public static void main(String[]args)
    {
        //Launches the game with window and JFrame and everything else loaded onto it
        Game game = new Game("Asteroid Game",800,500);
        game.start();
    }
}
