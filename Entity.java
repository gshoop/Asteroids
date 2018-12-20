import java.awt.*;
//Main abstract class that the player and asteroid inherit
public abstract class Entity
{
    public static float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_ENTITY_WIDTH = 48,
                            DEFAULT_ENTITY_HEIGHT = 48;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected float speed;
    protected float deltaX, deltaY;
    protected Rectangle bounds;
    
    public Entity(Handler handler,float x, float y, int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        speed = DEFAULT_SPEED;
        deltaX = 0;
        deltaY = 0;
        
        bounds = new Rectangle(0,0,width,height);
    }
    /*          ACCESSOR/MUTATOR METHODS          */
    public void move()
    {
        moveX();
        moveY();
    }
    
    public void moveX()
    {
        x += deltaX;
    }
    
    public void moveY()
    {
        y += deltaY;
    }
    
    public void setX(float x)
    {
        this.x = x;
    }
    
    public float getX()
    {
        return x;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
    
    public float getY()
    {
        return y;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public void setDeltaX(float deltaX)
    {
        this.deltaX = deltaX;
    }
    
    public float getDeltaX()
    {
        return deltaX;
    }
    
    public void setDeltaY(float deltaY)
    {
        this.deltaY = deltaY;
    }
    
    public float getDeltaY()
    {
        return deltaY;
    }
    //Methods to override in sub-classes
    public abstract void tick();
    public abstract void render(Graphics g);
}
