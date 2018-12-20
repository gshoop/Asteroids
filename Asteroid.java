import java.awt.*;
import java.util.Random;
//Asteroid class (our enemies)
public class Asteroid extends Entity
{
    //public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_ASTEROID_WIDTH = 64,
                            DEFAULT_ASTEROID_HEIGHT = 64;
    protected float spawnX, spawnY;
    protected float radius;
    //Bounding variables for where we want to spawn our asteroids along the x-axis
    protected float minRandom = 40.0f;
    protected float maxRandom = 740.0f;
    protected Color color;
    //Takes in a player so we can detect collisions
    private Player player;
    //Constructor
    public Asteroid(Handler handler,Player player,float x, float y, int width, int height)
    {
        super(handler,x,y,width,height);
        deltaX = 0;
        deltaY = 0;
        this.spawnX = x;
        this.spawnY = y;
        this.radius = height*0.5f;
        this.player = player;
        //Asteroids are colored randomly so initialize color as random color
        Random gen = new Random();
        //Initially set rgb values equally.
        int red, green, blue = 50;
        do
        {
            red = gen.nextInt(255);
            green = gen.nextInt(255);
            blue = gen.nextInt(255);
        } while (red != green && red != blue);  //This makes sure that our random color won't be black
                                                //or too dark of a color
        this.color = new Color(red,blue,green);
    }
    //Movement for the asteroid. Will only fall straight down at constant speed.
    private void fall()
    {
		if (handler.getWorld().AsteroidCount < 200)
		{
        	deltaX = 0;
        	deltaY = speed;
		}
		else
		{
			if (x < player.getX())
			{
				deltaX = speed;
				deltaY = speed;
			}
			if (x > player.getX()+player.getWidth())
			{
				deltaX = -speed;
				deltaY = speed;
			}
		}
    }
    //Boolean for detecting collisions takes in Player object so we can have access to position.
    public boolean containsPoint(Player player)
    {
        //Initially calculate the squared values of the distance between the player and the object
        float xSquared = (player.getX() - x)*(player.getX() - x);
		float ySquared = (player.getY() - y)*(player.getY() - y);
        float radiusSquared = radius*radius;
        //While our asteroid is within the x bounds of our player.
        while (x > player.getX() && x < player.getX()+player.getWidth())
        {
            xSquared = 0;                               //Set to zero so we can calculate distance from the top
                                                        //of the square rather than the top left x value
            //If statement for if the asteroid is below the player
            if (y > player.getY()+player.getHeight())
            {
                ySquared = ((player.getY()+player.getHeight())-y)*((player.getY()+player.getHeight())-y);
                if (xSquared + ySquared - radiusSquared <= 0)
                    return true;
                else
                    return false;
            }
            //For if the asteroid is above the player do nothing but compare
		    if (xSquared + ySquared - radiusSquared <= 0)
			    return true;
		    else
                return false;
        }
        //While our asteroid is within the y bounds of our player
        while (y > player.getY() && y < player.getY()+player.getHeight())
        {
            ySquared = 0;                               //Similar to pervious while statement. We only care about
                                                        //the x-component this time rather than the y-component.
            //If the asteroid is to the right of the player
            if (x > player.getX()+player.getWidth())
            {
                xSquared = ((player.getX()+player.getWidth())-x)*((player.getX()+player.getWidth())-x);
                if (xSquared + ySquared - radiusSquared <= 0)
                    return true;
                else
                    return false;
            }
            //Asteroid is to the left so compare
            if (xSquared + ySquared - radiusSquared <= 0)
			    return true;
		    else
                return false;
        }
        return false;
    }
    //Boolean method to determine if the asteroid has reached the end of the screen or not
    public boolean hasPassed()
    {
        if (y<=450.0f)
            return false;
        else
            return true;
    }
    //Tick and Render methods
    public void tick()
    {
        //We have to respawn the circles if they reach the bottom of the screen
        if (hasPassed())
        {
            //Randomly reset the x coordinates.
            this.x = minRandom + new Random().nextFloat()*(maxRandom-minRandom);
            //Fixed Y spawn
            this.y = spawnY;
            //Randomly reset the width,height, and radius of the new asteroid
            int newHeight, newWidth;
            newHeight = 32 + (int) (new Random().nextFloat()*(64.0f-32.0f));
            newWidth = newHeight;
            setWidth(newWidth);
            setHeight(newHeight);
            this.radius = width*0.5f;
            //Create a new Random color
            Random gen = new Random();
            int red, green, blue = 50;
            do
            {
                red = 40 + (int) (gen.nextFloat()*(255.0f-40.0f));
                green = 40 + (int) (gen.nextFloat()*(255.0f-40.0f));
                blue = 40 + (int) (gen.nextFloat()*(255.0f-40.0f));
            } while (red != green && red != blue);
            
            this.color = new Color(red,green,blue);
        }
        else
        {
            fall();
            move();
        }
    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int)(x-radius),(int)(y-radius),(int)(radius*2),(int)(radius*2));
    }
    //Gets and sets
    public void setRadius()
    {
        this.radius = height*0.5f;
    }
    public float getRadius()
    {
        return radius;
    }
}
