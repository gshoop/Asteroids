import java.awt.*;
//Player class (our ship)
public class Player extends Entity
{
    //Default health when game starts
    public static final int DEFAULT_HEALTH = 3;
    //health variable
    protected int health;
    //Constructor
    public Player(Handler handler,float x,float y)
    {
        //Inherits handler,x,y,width,height from entity class
        super(handler,x,y,Entity.DEFAULT_ENTITY_WIDTH,Entity.DEFAULT_ENTITY_HEIGHT);
        health =  DEFAULT_HEALTH;
    }
    //Method to decrement the health of our player
    public void loseLife()
    {
        health--;
    }
    //Tick and Rendering Methods.
    public void tick()
    {
        getInput();         //First get input from keys
        move();             //Move the player
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,width,height);
    }
    //Get and Set Methods
    private void getInput()
    {
        deltaX = 0;                  //Initially set change in x & y to 0
        deltaY = 0;
        //Bound the player within the window and set the deltaX and deltaY to corresponding directions
        if(handler.getKeyManager().up && y >= 0.0f)
            deltaY = -speed;
        if(handler.getKeyManager().down && y <= 420.0f)
            deltaY = speed;
        if(handler.getKeyManager().left && x >= 0.0f)
            deltaX = -speed;
        if(handler.getKeyManager().right && x <= 752.0f)
            deltaX = speed;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    public int getHealth()
    {
        return health;
    }
}
