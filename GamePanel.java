package Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

// we will be setting constants that will be use later on for WINDOW SIZE,
 static final int SCREEN_WIDTH = 600;
 static final int SCREEN_HEIGHT = 600;
 static final int UNIT_SIZE =15; // size of a single unit or block in the game's grid ( ex if grid of squares, then each block has a side of length 15 pixels)
 static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // calculating the total amount of units in the grid using the size of it and the size of a unit
 static final int DELAY =75; // number of milliseconds between the time i press a button and the action occurs in the game and it updates.
    // this makes sense later
        final int x[] = new int[GAME_UNITS];
        // these arrays hold all of the coordinates for the body parts of the snake,
        // including its head. ( GAME UNITS IS BECAUSE THE SNAKE WON'T BE BIGGER THAN THE GAME ITSELF
        final int y[] = new int[GAME_UNITS];
        //Holds y coordinates


        int bodyParts = 6;
        // we begin with 6 body parts for the snake
        int applesEaten=0;
        // will not have eaten any apples at the start
        int appleX;
        // x position of the apple
        int appleY;
    // y position of the apple
        char direction = 'R';
        //direction at which the snake goes ( we initialize so the snake begins going right )
        boolean running = false;
        // indicator of whether or not the game is running or not
        Timer timer;
        // we create a variable called timer ( we will use it later for intervals of time and all )
        Random random;
        // create class called random

       GamePanel(){
        // The instanciation of this class
        random = new Random();
        // instnance of the random class

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        // we set sizes for the panel of the game ( with our already declared dimensions ))
        this.setBackground(Color.black); // just background color
        this.setFocusable(true); // focusable = allows interaction between GUI and the inputs ( from keyboard )
        this.addKeyListener(new MyKeyAdapter()); //makes us recieve and use the keyboard touches and whatever we get from keyboard
        startGame();



       }
       public void startGame(){
        newApple();
        // will create the first apple to appear for us
        running = true; // because was false, now will start
        timer = new Timer(DELAY,this); // we start a timer that will be used to calulate the pace of the game
        timer.start(); // now the timer will start

       }
       public void paintComponent(Graphics g){

        super.paintComponent(g); //
        draw(g);

       }
       public void draw(Graphics g) {
        if(running){


            g.setColor(new Color(150,1,60));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);


            for (int i = 0; i < bodyParts; i++) {
             if (i == 0) {
                 g.setColor(Color.GRAY);
                 g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
             } else {
                 g.setColor(new Color(255, 255, 254));
                 g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
             }

            }
         g.setColor(Color.red);
         g.setFont(new Font("Ink Free", Font.BOLD,40));
         FontMetrics metrics = getFontMetrics(g.getFont());
         g.drawString("Score" + applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score" + applesEaten)) /2 ,g.getFont().getSize());

        }
        else{
         gameOver(g);
        }


       }
        public void newApple () {

         appleX = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
         appleY = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

        }

        public void move(){

         for (int i = bodyParts; i > 0; i--) {
          x[i] = x[i - 1];
          y[i] = y[i - 1];

         }
         switch (direction) {
          case 'U':
           y[0] = y[0] - UNIT_SIZE;
           break;
          case 'D':
           y[0] = y[0] + UNIT_SIZE;
           break;
          case 'L':
           x[0] = x[0] - UNIT_SIZE;
           break;
          case 'R':
           x[0] = x[0] + UNIT_SIZE;
           break;
         }

        }

       public void checkApple(){
        if((x[0]==appleX)&&(y[0]==appleY)){
         bodyParts++;
         applesEaten++;
         newApple();
        }


       }
       public void checkCollisions(){
        // checks if the head collides with body
        for(int i = bodyParts;i>0;i--){
         if((x[0]==x[i]) && (y[0] == y[i])){
          running = false;
         }
        }
        // check if head touches left border
        if (x[0] <0){
         running = false;
        }
        // if head touches right border
        if(x[0] > SCREEN_WIDTH){
         running = false;
        }
        // if head touches top border
        if(y[0]<0){
         running = false;
        }
        // if head touches bottom border
        if(y[0] > SCREEN_HEIGHT){
         running = false;
        }
        if(running == false){
         timer.stop();
        }
       }
       public void gameOver(Graphics g){
          //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER",(SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) /2 ,SCREEN_HEIGHT/2);

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD,40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score" + applesEaten,(SCREEN_WIDTH - metrics2.stringWidth("Score" + applesEaten)) /2 ,g.getFont().getSize());



       }

 @Override
 public void actionPerformed(ActionEvent e) {
        if(running){
         move();
         checkApple();
         checkCollisions();
        }
        repaint();

 }



 public class MyKeyAdapter extends KeyAdapter {
  @Override
  public void keyPressed(KeyEvent e) {
   switch (e.getKeyCode()) {
    case (KeyEvent.VK_LEFT):
     if (direction != 'R') {
      direction = 'L';
     }
     break;
    case (KeyEvent.VK_RIGHT):
     if (direction != 'L') {
      direction = 'R';
     }
     break;
    case (KeyEvent.VK_UP):
     if (direction != 'D') {
      direction = 'U';
     }
     break;
    case (KeyEvent.VK_DOWN):
     if (direction != 'U') {
      direction = 'D';
     }
     break;


   }


  }
 }
 }