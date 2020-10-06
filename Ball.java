import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball  extends Actor
{
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Ball(){
        GreenfootImage img = new GreenfootImage(10, 10);
        img.fillRect(0, 0, 10, 10);
        setImage(img);
    }
    
    public void act() 
    {
        //if(this.intersects((Table)getWorld().getComputer())){
          //  dx = -dx;
       // }
    }
}
