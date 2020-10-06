import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Panel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Panel extends Actor
{
    /**
     * Act - do whatever the Panel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Panel() {
        GreenfootImage img = new GreenfootImage(10, 50);
        img.fillRect(0, 0, 10, 50);
        setImage(img);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
