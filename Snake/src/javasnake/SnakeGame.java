/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasnake;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author sayre
 */
public class SnakeGame extends Applet implements Runnable, KeyListener {
    
    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    boolean gameOver, winner;
    Token token;
    int bestMove;
    int score, length, time;
    createLog log = new createLog();
    
    //Initialize frame
    public void init(){
        this.resize(400, 420);
        gameOver = false;
        winner = false;
        score = 0;
        length = 0;
        img = createImage(400, 420);
        gfx = img.getGraphics();
        this.addKeyListener(this);
        snake = new Snake();
        token = new Token(snake);
        thread = new Thread(this);
        thread.start();
    }
    //Paint Frame
    public void paint(Graphics g){
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 400, 400);
        gfx.setColor(Color.white);
        gfx.fillRect(0, 400, 400, 420);
        gfx.setColor(Color.black);
        gfx.drawString("Speed: " + time, 5, 415);
        gfx.drawString("Score: " + score, 150, 415);
        gfx.drawString("Press P to Pause", 280, 415);
        
        //Pause menu
        if(!snake.isMoving){
            gfx.setColor(Color.red);
            gfx.drawString("< ^ > to Start", 280, 390);
        }
        else{
            gfx.setColor(Color.yellow);
                            ai();
        }
        //Draw snakes and Tokens
        if(!gameOver){
            snake.draw(gfx);
            token.draw(gfx);
            //if(checkClip(snake.getX(), snake.getY(), 2) == 1) {snake.setXDir(1); snake.setYDir(0);}  
        }
        //Draws GameOVer Menu
        else{
           
           gfx.setColor(Color.RED);
           if(winner){
              gfx.drawString("You Won!", 150, 110); 
           }
           else{
               gfx.drawString("Game Over", 150, 110);
           }
           gfx.drawString("- Score:" + token.getScore(), 165, 130);
           gfx.drawString("- Length: " + token.getLength(), 165, 150);
           gfx.drawString("All Time High Score: ", 150, 200);
           gfx.drawString("- " + log.selectHigh(), 165, 220);
           gfx.drawString("Replay and Record: ", 150, 260);
           gfx.drawString("- Press R", 165, 280);
           
        }
        g.drawImage(img, 0, 0, null);
    }
    
    public void update(Graphics g){
        paint(g);
        
    }
    
    public void repaint(Graphics g){
        paint(g);
        
    }
//Infinite Loops for game
    public void run() {
        for(;;){
            if(!gameOver){
                score = token.getScore();
                length = token.getLength();
                snake.move();
                this.checkGameOver();
                token.snakeCollision(snake.getX(), snake.getY());
                time = 100;
                
                //Increments down Thread Sleep for Levels
                for(int i = 1; i < 10; i++){
                    if(length >= (i * 40) + 7){
                                time -= 10;
                        }
                }
                if(time == 0){
                    gameOver = true;
                    winner = true;
                }
            }
            
            this.repaint();
            
            try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void checkGameOver(){
        if(snake.getX() < 0 || snake.getX() > 392){
            gameOver = true;
        }
        if(snake.getY() < 0 || snake.getY() > 392){
            gameOver = true;
        }
        if(snake.snakeCollision(snake.getX(), snake.getY())){
            gameOver = true;
        }
    }
    
     public void reload(){
        log.insert(score, length);
        this.destroy();
        this.init();
        thread.stop();
    }
   
    public void keyTyped(KeyEvent e) {
    }
    //Key Presses
    public void keyPressed(KeyEvent e) {
        
        if(!snake.isMoving()){
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT 
                    || e.getKeyCode() == KeyEvent.VK_RIGHT){
                snake.setIsMoving(true);
            }
                
        }
        if(snake.isMoving()){
            if(e.getKeyCode() == KeyEvent.VK_P){
                snake.setIsMoving(false);
            }
        }
        if(gameOver){
            if(e.getKeyCode() == KeyEvent.VK_R){
                reload();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(snake.getYDir() != 1){
                snake.setYDir(-1);
                snake.setXDir(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(snake.getYDir() != -1){
                snake.setYDir(1);
                snake.setXDir(0);
            }   
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(snake.getXDir() != 1){
                snake.setXDir(-1);
                snake.setYDir(0);
            }    
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(snake.getXDir() != -1){
                snake.setXDir(1);
                snake.setYDir(0);
            }    
        }
    }

    public void keyReleased(KeyEvent e) {
    }
    public int wiser(int x, int y, int depth, int dir){
        Pair<Integer, Integer> up, down, left, right;
        int stride = 8;
        int move = snake.getDir();
        if(depth != 0){
        if(dir == 1){
            up = up(x, y, stride, depth);
            right = right(x, y, stride, depth);
            down = down(x, y, stride, depth);
            move = bestMove(up, right, down);
        }
        if(dir == 3){
            down = down(x, y, stride, depth);
            left = left(x, y, stride, depth);
            up = up(x, y, stride, depth);
            move = bestMove(up, left, down);
        }
        if(dir == 0){
            left = left(x, y, stride, depth);
            up = up(x, y, stride, depth);
            right = right(x, y, stride, depth);
            move = bestMove(up, right, left);
        }
        if(dir == 2){
            right = right(x, y, stride, depth);
            down = down(x, y, stride, depth);
            left = left(x, y, stride, depth);
            move = bestMove(left, right, down);
        }
        }
        return move;
    }

    public Pair<Integer, Integer> up(int x, int y, int stride, int depth) {
        int newX = x;
        int newY = y-stride;
        Pair<Integer, Integer> heu;
        heu = new Pair<> (0,0);
        gfx.fillRect(newX, newY, stride, stride);
        if(newY <=0){
            //System.out.println("Top");
            heu = new Pair<>(-10, 0);
        }
        if(snake.snakeCollision(newX, newY)){
            //System.out.println("Self");
            heu = new Pair<>(-10, 0);
        }
        if(token.treeCollision(newX, newY)){
            //System.out.println("Token");
            heu = new Pair<>(10, 0);
        }
        wiser(newX, newY, depth-1, 0);
        return heu;
    }

    public Pair<Integer, Integer> down(int x, int y, int stride, int depth) {
        int newX = x;
        int newY = y+stride;
        Pair<Integer, Integer> heu;
        heu = new Pair<> (0,2);
        gfx.fillRect(newX, newY, stride, stride);
        if(newY >=400){
            //System.out.println("Bottom");
            heu = new Pair<>(-10, 2);
        }
        if(snake.snakeCollision(newX, newY)){
            //System.out.println("Self");
            heu = new Pair<>(-10, 2);
        }
        if(token.treeCollision(newX, newY)){
            //System.out.println("Token");
            heu = new Pair<>(10, 2);
        }
        wiser(newX, newY, depth-1, 2);
        return heu;
    }

    public Pair<Integer, Integer> right(int x, int y, int stride, int depth) {
        int newX = x+stride;
        int newY = y;
        Pair<Integer, Integer> heu;
        heu = new Pair<> (0,1);
        gfx.fillRect(newX, newY, stride, stride);
        if(newX >=400){
            //System.out.println("Right");
            heu = new Pair<>(-10, 1);
        }
        if(snake.snakeCollision(newX, newY)){
            //System.out.println("Self");
            heu = new Pair<>(-10, 1);
        }
        if(token.treeCollision(newX, newY)){
            //System.out.println("Token");
            heu = new Pair<>(10, 1);;
        }
        wiser(newX, newY, depth-1, 1);
        return heu;
    }

    public Pair<Integer, Integer> left(int x, int y, int stride, int depth) {
        int newX = x-stride;
        int newY = y;
        Pair<Integer, Integer> heu;
        heu = new Pair<> (0,3);
        gfx.fillRect(newX, newY, stride, stride);
        if(newX <=0){
            //System.out.println("Left");
            heu = new Pair<>(-10, 3);
        }
        if(snake.snakeCollision(newX, newY)){
            //System.out.println("Self");
            heu = new Pair<>(-10, 3);
        }
        if(token.treeCollision(newX, newY)){
            //System.out.println("Token");
            heu = new Pair<>(10, 3);
        }
        wiser(newX, newY, depth-1, 3);
        return heu;
    }

    private int bestMove(Pair<Integer, Integer> one, Pair<Integer, Integer> two, Pair<Integer, Integer> three) {
        if(one.getKey() <= two.getKey()){
            if(two.getKey() <= three.getKey()){
                return three.getValue();
            }
            return two.getValue();
        }
        return one.getValue();
    }
    
    public void ai(){
        switch(wiser(snake.getX(), snake.getY(), 5, snake.getDir())){
                case 0: {
                    System.out.println("UP");
                    if(snake.getYDir() != 1){
                    snake.setYDir(-1);
                    snake.setXDir(0);
                    }
                    break;
                }
                case 1: {
                    System.out.println("RIGHT");
                    if(snake.getXDir() != -1){
                    snake.setYDir(0);
                    snake.setXDir(1);
                    }
                    break;
                }
                case 2: {
                    System.out.println("DOWN");
                    if(snake.getYDir() != -1){
                    snake.setYDir(1);
                    snake.setXDir(0);
                    }
                    break;
                }
                case 3: {
                    System.out.println("LEFT");
                    if(snake.getXDir() != 1){
                    snake.setYDir(0);
                    snake.setXDir(-1);
                    }
                    break;
                }
                default: break;
            }
    }
}
