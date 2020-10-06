import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Panel
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private static final boolean ACCEPT_MOUSE = true;
    private static int MOVE_SPEED = PongWorld.getMoveSpeed();
    
    public void act() 
    {
        if(Greenfoot.mouseMoved(null) && ACCEPT_MOUSE){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(getX(),mouse.getY());
        }
        
        //Keyboard handeling
        if(Greenfoot.isKeyDown("up")){
            this.setLocation(this.getX(), this.getY() - MOVE_SPEED);
        }
        if(Greenfoot.isKeyDown("down")){
            this.setLocation(this.getX(), this.getY() + MOVE_SPEED);
        }
    }    
}
