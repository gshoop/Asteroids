//This class allows us to set and get parts of our game from other parts
public class Handler
{
    private Game game;
    private World world;
    public Handler(Game game)
    {
        this.game = game;
    }    
    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }
    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }
    public int getWidth()
    {
        return game.getWidth();
    }
    public int getHeight()
    {
        return game.getHeight();
    }
    public void setGame(Game game)
    {
        this.game = game;
    }
    public Game getGame()
    {
        return game;
    }
    public void setWorld(World world)
    {
        this.world = world;
    }
    public World getWorld()
    {
        return world;
    }
}
