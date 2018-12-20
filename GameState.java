import java.awt.*;
//State for where the game is played.
//Sets the World class and ticks and renders it.
public class GameState extends State
{
    private World world;
    private UIManager uiManager;
    
    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler);
    }
    
    public void tick()
    {
        world.tick();
    }
    
    public void render(Graphics g)
    {
        world.render(g);
    }
    
}
