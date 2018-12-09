package rr.modernsnake.engine;

import java.util.ArrayList;
import java.util.List;

import rr.modernsnake.classes.Coordinate;
import rr.modernsnake.enums.Direction;
import rr.modernsnake.enums.GameState;
import rr.modernsnake.enums.TileType;

public class GameEngine {
    public static final int GameWidth = 28; //Width of the game window
    public static final int GameHeight = 42;//Height of the game window

    private List<Coordinate> walls = new ArrayList<>(); // Creates wall objects
    private List<Coordinate> snake = new ArrayList<>(); // Creates the snake object

    private Direction currentDirection = Direction.East;
    private GameState currentGameState = GameState.Running;

    public GameEngine(){ // Creates the engine of the game

    }
    public void initGame(){

        AddSnake(); // Creates the snake object
        AddWalls(); // Creates the walls
    }

    public void Update(){ // This function allows the snake to move around
        switch (currentDirection){

            case North:
                UpdateSnake(0,-1); // North will be -1 in the y direction
                break;
            case East:
                UpdateSnake(1,0); // East will be 1 in the x direction
                break;
            case South:
                UpdateSnake(0,1); // South will be 1 in the y direction
                break;
            case West:
                UpdateSnake(-1,0); // West will be -1 in the x direction
                break;
        }
        // Check wall collisions
        for(Coordinate w: walls){
            if(snake.get(0).equals(w)){
                currentGameState = GameState.Lost;
            }
        }
    }

    private void UpdateSnake(int x, int y){
        for(int i = snake.size() -1; i > 0; i--)
        {
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }

        snake.get(0).setX(snake.get(0).getX() + x); // Declares the head  coordinate
        snake.get(0).setY(snake.get(0).getY() + y); // Declares the head coordinate
    }

    private void AddSnake(){ // Makes a snake with 5 elements
        snake.clear();
        snake.add(new Coordinate(7,7));
        snake.add(new Coordinate(6,7));
        snake.add(new Coordinate(5,7));
        snake.add(new Coordinate(4,7));
        snake.add(new Coordinate(3,7));

    }

    private void AddWalls(){
        //Top and Bottom Walls
        for(int x = 0; x < GameWidth; x++)
        {
            walls.add(new Coordinate(x,0)); // Creates a new wall object
            walls.add(new Coordinate(x,GameHeight -1)); // Creates a new wall object
        }
        //Left and Right Walls
        for(int y = 1; y < GameHeight; y++)
        {
            walls.add(new Coordinate(0,y)); // Creates a new wall object
            walls.add(new Coordinate(GameWidth - 1, y));

        }

    }

    public TileType[][] getMap(){
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for(int x = 0; x < GameWidth; x++)
        {
            for(int y = 0; y < GameHeight; y++)
            {
                map[x][y] = TileType.Nothing;
            }
        }
        for(Coordinate s : snake){ // Declares the body of the snake as a snake tail
            map[s.getX()][s.getY()] = TileType.SnakeTail;
        }
        map[snake.get(0).getX()][snake.get(0).getY()] =  TileType.SnakeHead; // The first to be head

        for(Coordinate wall: walls)
        {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }
        return map;
    }

    public GameState getCurrentGameState(){ // Gets the current game state
        return currentGameState;
    }
}
