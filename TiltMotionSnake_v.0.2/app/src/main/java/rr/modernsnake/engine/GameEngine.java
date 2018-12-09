package rr.modernsnake.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rr.modernsnake.classes.Coordinate;
import rr.modernsnake.enums.Direction;
import rr.modernsnake.enums.GameState;
import rr.modernsnake.enums.TileType;

public class GameEngine {
    public static final int GameWidth = 20; //Width of the game window
    public static final int GameHeight = 40;//Height of the game window

    private List<Coordinate> walls = new ArrayList<>(); // Creates wall objects
    private List<Coordinate> snake = new ArrayList<>(); // Creates the snake object
    private List<Coordinate> apples = new ArrayList<>(); // Creates the apple object

    private Random random = new Random(); // Creates a random variable
    private boolean increaseTail = false;

    private Direction currentDirection = Direction.East; // Snake moves east initially
    private GameState currentGameState = GameState.Running; // Game starts

    private Coordinate getSnakeHead(){ // Finds the location of the head
        return snake.get(0); // Gets the location of the head of the snake
    }

    public GameEngine(){ // Creates the engine of the game

    }
    public void initGame(){

        AddSnake(); // Creates the snake object
        AddWalls(); // Creates the walls around the phone
        AddApples();
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

        for(int i = 1; i < snake.size(); i++){ // Check for self collision. Starts with 1 since 0 is the head
            if(getSnakeHead().equals(snake.get(i))){ // Snake collision to itself
                currentGameState = GameState.Lost; // Game lost
                return;
            }
        }

        //Check apples
        Coordinate appleToRemove = null;
        for(Coordinate apple : apples){
            if(getSnakeHead().equals(apple)){
                appleToRemove = apple;
                increaseTail = true;
            }
        }
        if(appleToRemove != null){
            apples.remove(appleToRemove); // Removes the eaten apple
            AddApples();  // Adds a new apple
        }
    }

    private void UpdateSnake(int x, int y){
        int newX = snake.get(snake.size() - 1).getX();
        int newY = snake.get(snake.size() - 1).getY();

        for(int i = snake.size() -1; i > 0; i--)
        {
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }

        if(increaseTail){ // Increases the tail after moving the snake body
            snake.add(new Coordinate(newX, newY)); // Adds the new snake body at the newx, new y position
            increaseTail = false; // Restart the growing
        }

        snake.get(0).setX(snake.get(0).getX() + x); // Declares the head  coordinate
        snake.get(0).setY(snake.get(0).getY() + y); // Declares the head coordinate


    }

    private void AddSnake(){ // Makes a snake with 5 elements
        snake.clear();
        snake.add(new Coordinate(7,7)); //Location of the snake
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

    public TileType[][] getMap(){ // Creates the map of the game
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for(int x = 0; x < GameWidth; x++)
        {
            for(int y = 0; y < GameHeight; y++)
            {
                map[x][y] = TileType.Nothing; // Empty space added
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

        for(Coordinate a: apples){
            map[a.getX()][a.getY()] = TileType.Apple; // Declares the apple object
        }


        return map;
    }

    public void UpdateDirection(Direction newDirection){ // Updates the direction of the snake
        if(Math.abs(newDirection.ordinal()-currentDirection.ordinal()) % 2 == 1){ // Calculates if its a new direction
            currentDirection = newDirection; // The current direction is the new direction
        }
    }

    private void AddApples(){
        Coordinate coordinate = null;

        boolean added = false;

        while(!added) { // Prevents an apple from spawning inside the snake or wall
            int x = 1 + random.nextInt(GameWidth - 2);
            int y = 1 + random.nextInt(GameHeight - 2);

            coordinate = new Coordinate(x,y);
            boolean collision = false; // Declares boolean variable

            for(Coordinate s:snake){
                if(s.equals(coordinate)){
                    collision = true; // Snake eats the apple
                    break;
                }
            }

            for(Coordinate a:apples){
                if(a.equals(coordinate)){
                    collision = true;
                    break;
                }
            }
            added = !collision;
        }
        apples.add(coordinate);
    }

    public GameState getCurrentGameState(){ // Gets the current game state
        return currentGameState;
    }


}
