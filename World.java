import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;
//This is where all the playing graphics and methods are happening.
public class World
{
    private Handler handler;
    public AsteroidManager asteroidManager;
    private Player player;
    public boolean gameOver = false;
    //Keep count of the asteroids passing by for scoring and difficulty incrementing purposes.
    public static int AsteroidCount = 0;
    public static int finalCount;
    //Constructor. Create the player and 7 asteroids all falling at random positions with random colors at default speed
    public World(Handler handler)
    {
        this.handler = handler;
        player = new Player(handler,(0.5f*handler.getWidth()-0.5f*48.0f),handler.getHeight()-100.0f);
        asteroidManager = new AsteroidManager(handler);
        for (int i = 0; i < 7; i++)
        {
            asteroidManager.getAsteroids().add(new Asteroid(handler, player,50.0f + new Random().nextFloat()*(750.0f-50.0f),10.0f,32,32));
        }
    }
    //Tick and Render Methods
    public void tick()
    {
        //As long as we have lives left tick..
        if(!gameOver)
		{
            player.tick();
            asteroidManager.tick();
            //Collision detection. Iterate through all of the asteroids to see if a collision has occurred.
            for (int i = 0; i < asteroidManager.getAsteroids().size(); i++)
            {
                //If a collision has occurred take away a life
                if (asteroidManager.getAsteroids().get(i).containsPoint(player))
                {
                    //System.out.println("COLLISION");              //For Testing Purposes
                    player.loseLife();
                    //If the player has lost all of their lives set their final score and
                    //set gameOver as true
                    if (player.getHealth()<0)
                    {
                        gameOver = true;
                        finalCount = AsteroidCount;
                    }
                    //Remove the asteroid if we detect a collision
                    asteroidManager.getAsteroids().remove(i);
                    i--;                                            //Decrement counter since we removed an element
                }
            }
            //If we have lost an asteroid due to collision then we respawn a new one
            if (asteroidManager.getAsteroids().size() < 7)
                asteroidManager.addAsteroid(new Asteroid(handler, player,50.0f + new Random().nextFloat()*(750.0f-50.0f),100.0f,32,32));
            //Detect if an asteroid has passed and increment the asteroid count
            for (int i = 0; i < asteroidManager.getAsteroids().size(); i++)
            {
                if (asteroidManager.getAsteroids().get(i).hasPassed())
                {
                    AsteroidCount++;
                    //System.out.println(AsteroidCount);			//For testing purposes
                }
            }
            //The different "levels" due to asteroid count
            if (AsteroidCount == 7)
            {
                for (int j = 0; j < asteroidManager.getAsteroids().size(); j++)
                    asteroidManager.getAsteroids().get(j).setSpeed(3.0f + new Random().nextFloat()*(4.0f-3.0f));
            }
            if (AsteroidCount == 49)
            {
                for (int j = 0; j < asteroidManager.getAsteroids().size(); j++)
				{
                    asteroidManager.getAsteroids().get(j).setSpeed(3.0f + new Random().nextFloat()*(6.0f-3.0f));
				}
            }
            if (AsteroidCount == 100)
            {
                for (int j = 0; j < asteroidManager.getAsteroids().size(); j++)
                    asteroidManager.getAsteroids().get(j).setSpeed(3.0f + new Random().nextFloat()*(8.0f-3.0f));
            }
        }
    }    
    
    public void render(Graphics g)
    {
        g.drawImage(Assets.world,0,0,handler.getWidth(),handler.getHeight(),null);                            //World Background
        
        if(player.getHealth() == 3)
        {
            g.drawImage(Assets.life,10,10,null);                                                                    //Life 1
            g.drawImage(Assets.life,52,10,null);                                                                    //Life 2
            g.drawImage(Assets.life,94,10,null);                                                                    //Life 3
        }
        else if(player.getHealth() == 2)
        {
            g.drawImage(Assets.life,10,10,null);
            g.drawImage(Assets.life,52,10,null);
        }
        else if(player.getHealth() == 1)
        {
            g.drawImage(Assets.life,10,10,null);
        }
        player.render(g);
        asteroidManager.render(g);

        g.setColor(Color.WHITE);
        if(!gameOver)
            g.drawString("SCORE: " + AsteroidCount, 670, 27);
        
        g.setColor(asteroidManager.getAsteroids().get(0).color);
        if (gameOver)
        {
            g.drawString("GAME OVER",350,200);
            g.drawString("FINAL SCORE: " + finalCount,350,210);
        }
    }
}
