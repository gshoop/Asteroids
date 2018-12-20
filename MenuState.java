import java.awt.*;
//State for the menu screen
public class MenuState extends State
{
    private UIManager uiManager;
    private MenuWorld menuWorld;
    private World world;
    
    public MenuState(Handler handler)
    {
        super(handler);
        menuWorld = new MenuWorld(handler);
        //The UIManager class allows us to create a button image that is bounded by a ClickListener 
        //Which activates when the mouse hoveres over the button and performs an onClick method
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(335,175,128,64,Assets.buttonStart,new ClickListener(){
            public void onClick()
            {
                //When the user clicks the start button we initiate the gameState
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }
    //Tick and Render Methods
    public void tick()
    {
        //System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        //if(handler.getMouseManager().isLeftPressed())
        //    State.setState(handler.getGame().gameState);
        menuWorld.tick();
        uiManager.tick();
    }
    
    public void render(Graphics g)
    {
        menuWorld.render(g);
        uiManager.render(g);
    }
}
