import java.awt.*;
import java.util.ArrayList;
//This class allows us to have a dynamic array of asteroids for easy use
//by using an arraylist
public class AsteroidManager
{
    private Handler handler;
    private ArrayList<Asteroid> asteroids;
    public AsteroidManager(Handler handler)
    {
        this.handler = handler;
        asteroids = new ArrayList<Asteroid>();
    }
    public void tick()
    {
        for(int i = 0; i < asteroids.size(); i++)
        {
            Asteroid a = asteroids.get(i);
            a.tick();
        }
    }
    public void render(Graphics g)
    {
        for(Asteroid a : asteroids)
            a.render(g);
    }
    public void addAsteroid(Asteroid a)
    {
        asteroids.add(a);
    }
    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }
    public Handler getHandler()
    {
        return handler;
    }
    public void setAsteroids(ArrayList<Asteroid> asteroids)
    {
        this.asteroids = asteroids;
    }
    public ArrayList<Asteroid> getAsteroids()
    {
        return asteroids;
    }
}
