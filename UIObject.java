import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
//Abstract base class for menu start button. Allows mouse to detect button.
public abstract class UIObject
{
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;
    
    public UIObject(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x,(int)y,width,height);
    }

    public void onMouseMove(MouseEvent e)
    {
        if(bounds.contains(e.getX(),e.getY()))
            hovering = true;
        else
            hovering = false;
    }
    
    public void onMouseRelease(MouseEvent e)
    {
        if(hovering)
            onClick();
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
    
    public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	public abstract void tick();
    public abstract void render(Graphics G);
    public abstract void onClick();
}
