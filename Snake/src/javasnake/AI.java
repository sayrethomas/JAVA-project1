/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasnake;

/**
 *
 * @author sayre
 */
public class AI {
    
    public AI(){
        
    }
    
    public int wiser(SnakeGame game, int depth){
        int left, right, up, down;
        int choice = 0;
        if(depth != 0){
            if(game.gameOver){
                return -10;
            }
            else if(game.snake.snakeCollision()){
                return -10;
            }
            else if(game.token.snakeCollision()){
                return 10;
            }
            else if(game.snake.getYDir() == -1){
                System.out.println("UP");
                left = goLeft(game, depth);
                right = goRight(game, depth);
                up = goUp(game, depth);
                choice = greater(left, right, up);
                System.out.println(game.snake.getX());
            }
            else if(game.snake.getYDir() == 1){
                System.out.println("DOWN");
                left = goLeft(game, depth);
                right = goRight(game, depth);
                down = goDown(game, depth);
                choice = greater(left, right, down);
                System.out.println(game.snake.getX());
            }
            else if(game.snake.getXDir() == -1){
                System.out.println("LEFT");
                down = goDown(game, depth);
                left = goLeft(game, depth);
                up = goUp(game, depth);
                choice = greater(left, down, up);
                System.out.println(game.snake.getX());
            }
            else if(game.snake.getXDir() == 1){
                System.out.println("RIGHT");
                up = goUp(game, depth);
                right = goRight(game, depth);
                down = goDown(game, depth);
                choice = greater(down, right, up);
                System.out.println(game.snake.getX());
            }
        }
        return choice;
    }

    private int goLeft(SnakeGame game, int depth) {
        SnakeGame newGame = new SnakeGame();
        newGame = game;    
        newGame.snake.setYDir(0);
        newGame.snake.setXDir(-1);
        int newDepth = depth-1;
        return wiser(newGame, newDepth);
    }

    private int goRight(SnakeGame game, int depth) {
        SnakeGame newGame = new SnakeGame();
        newGame = game;  
        newGame.snake.setYDir(0);
        newGame.snake.setXDir(1);
        int newDepth = depth-1;
        return wiser(newGame, newDepth);
    }

    private int goUp(SnakeGame game, int depth) {
        SnakeGame newGame = new SnakeGame();
        newGame = game;
        newGame.snake.setYDir(-1);
        newGame.snake.setXDir(0);
        int newDepth = depth-1;
        return wiser(newGame, newDepth);
    }

    private int goDown(SnakeGame game, int depth) {
        SnakeGame newGame = new SnakeGame();
        newGame = game;    
        newGame.snake.setYDir(1);
        newGame.snake.setXDir(0);
        int newDepth = depth-1;
        return wiser(newGame, newDepth);
    }

    private int greater(int one, int two, int three) {
        if(one >= two){
            return one;
        }
        else if(two >= three){
            return two;
        }
        else{
            return three;
        }
    }
    
}
