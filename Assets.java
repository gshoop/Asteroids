import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage life, world, continueButton;
    public static BufferedImage[] buttonStart;
    public static BufferedImage[] yesButton;
    public static BufferedImage[] noButton;
    
    public static void init()
    {
        //Two different versions to main menu start button. One for when no mouse is hovering
        //and another for if the mouse is hovering over it.
        buttonStart = new BufferedImage[2];
        buttonStart[0] = ImageLoader.loadImage("/resources/StartButtonPassive.png");
        buttonStart[1] = ImageLoader.loadImage("/resources/StartButtonActive.png");
        yesButton = new BufferedImage[2];
        yesButton[0] = ImageLoader.loadImage("/resources/YesButtonDark.png");
        yesButton[1] = ImageLoader.loadImage("/resources/YesButtonLight.png");
        noButton = new BufferedImage[2];
        noButton[0] = ImageLoader.loadImage("/resources/NoButtonDark.png");
        noButton[1] = ImageLoader.loadImage("/resources/NoButtonLight.png");
        continueButton = ImageLoader.loadImage("/resources/ContinueButton.png");
        //Image resources for the lives and the background used.
        life = ImageLoader.loadImage("/resources/heart pixel art/heart pixel art 32x32.png");
        world = ImageLoader.loadImage("/resources/BackdropBlackLittleSparkBlack.png");
    }
}
