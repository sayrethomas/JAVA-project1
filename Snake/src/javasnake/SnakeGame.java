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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //Draw snakes and Tokens
        if(!gameOver){
            snake.draw(gfx);
            token.draw(gfx);
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
                token.snakeCollision();
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
        if(snake.snakeCollision()){
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
    
}
