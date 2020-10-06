    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
    /**
     * Write a description of class Table here.
     * 
     * @Qrashi 
     * @1.1
     */

    
    public class PongWorld  extends World
    {
        int player_score_int = 0;
        int computer_score_int = 0;
        int dx = 10;
        int dy = getRandomIntNoZero(6)-2;
        Player player = new Player();
        Computer computer = new Computer();
        Ball ball = new Ball();
        ScoreComputer computer_score = new ScoreComputer();
        ScorePlayer player_score = new ScorePlayer();
        GreenfootImage computer_img = new GreenfootImage(200,100);
        GreenfootImage player_img = new GreenfootImage(200,100);
        String player_score_string = String.valueOf(player_score_int);
        String computer_score_string = String.valueOf(computer_score_int);
        
        
        private static final int MOVE_SPEED = 10;
        private static final double BALL_SPEED = 1.5;
        
        
        public PongWorld()
        {   
            //Create world
            super(550, 400, 1);
            //add player (constructed above)
            addObject(player, 50, getHeight()/2);
            //add computer 
            addObject(computer, getWidth()-50 , getHeight()/2);
            //add ball
            addObject(ball, getWidth()/2, getHeight()/2);
            this.setBackground(new GreenfootImage(550, 40));
            drawScores();
            //Add the computerScore
            addObject(computer_score,getWidth()/4,50);
            //Add the playerScore
            addObject(player_score,(getWidth()/4)*3,50);
    
            //Set simulation speed
            Greenfoot.setSpeed(45);
        }
        
        public static int getMoveSpeed() {
            return MOVE_SPEED;
        }
        private int getRandomIntNoZero(int limit) {
            if(limit < 2) throw new RuntimeException("Can't return a not zero random number between zero and zero or one and one (or negative number)");
            int number = Greenfoot.getRandomNumber(limit);
            if(number != 0) return number;
            return getRandomIntNoZero(limit);
        }
        
        /*
         * This function updates both images
         * of the scores (player & computer)
         */
        
        private void drawScores() {
            
            //Update scores
            String player_score_string = String.valueOf(player_score_int);
            String computer_score_string = String.valueOf(computer_score_int);
            
            //Draw computer score
            computer_img.clear();
            computer_img.drawString(player_score_string,2,20);
            computer_score.setImage(computer_img);
            //Draw player score
            player_img.clear();
            player_img.drawString(computer_score_string, 2, 20);
            player_score.setImage(player_img);
            
        }
        
        public void act(){
            
            /*
             * This is done in the main world class
             * just so i dont have to ALWAYS do "PongWorld.getPlayerDY()"
             * and so on.
             * 
             * Here we move the ball "+ dx" and "+ dy"
             */
            ball.setLocation(ball.getX() + dx* (int) BALL_SPEED, ball.getY() + dy* (int) BALL_SPEED);
            
            /*
             * 
             * If the ball is LESS than 5 "units" off - move the computer DIRECTLY to the balls position
             * ELSE, move the computer 5 "units" into the balls direction.
             * 
             */
            if(ball.getY() < computer.getY() + MOVE_SPEED && ball.getY() > computer.getY() - MOVE_SPEED){
                computer.setLocation(computer.getX(), ball.getY());
            }
            //Ball is far away from computer
            else{
                if(ball.getY() > computer.getY()){
                    computer.setLocation(computer.getX(),computer.getY() + MOVE_SPEED);
                }
                else{
                    computer.setLocation(computer.getX(),computer.getY() - MOVE_SPEED);
                }
            }
            
            
            /*Collision with panels
             * 
             * The dx part is pretty easy -  just invert it
             * For dy i just calculate the difference the difference from the MIDDLE of the panel to the middle of the computer
             * 
             * INFO: One panel = 50 "units" high
             */
            
            if(ball.getY() >= computer.getY() - 25 && ball.getY() <= computer.getY() + 25 && ball.getX() + dx >= computer.getX()){
                dx = -dx;
                dy = (ball.getY() - computer.getY())/2;
                if(dy == 0) {
                    //If dy is 0 a "cheat" can be used (players pong infinte)
                    dy = -getRandomIntNoZero(4)-2;
                }
            }
            if(ball.getY() >= player.getY() - 25 && ball.getY() <= player.getY() + 25 && ball.getX() + dx <= player.getX()){
                dx = -dx;
                dy = (ball.getY() - player.getY())/2;
                if(dy == 0) {
                    //If dy is 0 a "cheat" can be used (players pong infinte)
                    dy = -getRandomIntNoZero(4)-2;
                }
            }
            
            //Collision with upper and lower barriers
            
            if(ball.getY() <= 5){
                ball.setLocation(ball.getX(),6);
                dy = - dy;
            }
            if(ball.getY() >= this.getHeight() - 5 ){ //Due to my use of "this.getHeight() i can make the world as high as i want without pong breaking
                ball.setLocation(ball.getX(),this.getHeight() -6);
                dy = -dy;
            }
            
            /*
             * If no collison with the box Boundaries was detected
             * look for collision on the other two sides.
             */
        
            else if(ball.getX() <= 35 || ball.getX() >= this.getWidth() -35){
               //Check on wich side the collision occurs
                if(ball.getX() <= 35){
                    //Add a point to the computer
                    computer_score_int++;
                }
                else {
                    //Add a point to the player
                    player_score_int++;
                    }   
                //If one of the players has 3 points, display the winner
                    if(computer_score_int >= 3 || player_score_int >= 3){
                        //System.out.println("Win detected - " + computer_score_int + " - " + player_score_int);
                        if(computer_score_int >= 3) {
                            //Display "computer wins"
                            drawScores();
                            computer_img.clear();
                            computer_img.drawString("Computer wins!", 2, 20);
                            computer_score.setImage(computer_img);
                        }
                        if(player_score_int >= 3) {
                            //Display "computer wins"
                            drawScores();
                            player_img.clear();
                            player_img.drawString("Player wins!", 2, 20);
                            player_score.setImage(player_img);
                        
                        }
                        reset();
                        Greenfoot.stop();
                        return;
                }
                //This code gets executed IF THERE WAS A COLLISION IN THE BACK / SOMEBODY CLAIMED A POINT
                //Reset ball, player, computer positions
                reset();
                
                //Draw scores
                drawScores();
        }
    }
    private void reset() {
        ball.setLocation(getWidth()/2, getHeight()/2);
        player.setLocation(50, getHeight()/2);
        computer.setLocation(getWidth()-50 , getHeight()/2);
        dx = 10;
        dy = getRandomIntNoZero(6)-2;
    }
}
