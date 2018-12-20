import java.awt.Graphics;
import java.util.Random;
//World for our Menu. All graphics happen here.
public class MenuWorld
{
    private Handler handler;    
    private AsteroidManager asteroidManager;
    private Player player;
    //Constructor. Initialize Player and create an array of 7 asteroids all flying by at random speeds and colors.
    public MenuWorld(Handler handler)
    {
        this.handler = handler;
        player = new Player(handler,(0.5f*handler.getWidth()-0.5f*48.0f),handler.getHeight()-100.0f);
        asteroidManager = new AsteroidManager(handler);
        for (int i = 0; i < 7; i++)						//Initializes Asteroids at random x-values
            asteroidManager.getAsteroids().add(new Asteroid(handler,player,50.0f + new Random().nextFloat()*(750.0f-50.0f),10.0f,32,32));
        for (int i = 0; i < 7; i++)
            asteroidManager.getAsteroids().get(i).setSpeed(3.0f + new Random().nextFloat()*(8.0f-3.0f));
    }
    //Tick and render methods
    public void tick()
    {
        player.tick();
        asteroidManager.tick();
    }
    
    public void render(Graphics g)
    {
        //Draw the background to our world, In menu we won't show the lives
        g.drawImage(Assets.world,0,0,handler.getWidth(),handler.getHeight(),null);                              //World Background
        // g.drawImage(Assets.life,10,10,null);                                                                 //Life 1
        // g.drawImage(Assets.life,52,10,null);                                                                 //Life 2
        // g.drawImage(Assets.life,94,10,null);                                                                 //Life 3
        player.render(g);
        asteroidManager.render(g);
    }
}
