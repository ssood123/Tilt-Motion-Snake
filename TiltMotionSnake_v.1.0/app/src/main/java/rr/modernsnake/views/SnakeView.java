package rr.modernsnake.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import rr.modernsnake.enums.TileType;

public class SnakeView extends View {

    private Paint mPaint = new Paint();
    private TileType snakeViewMap[][];
    private int Orange = Color.rgb(255, 165,0);
    private int Pink = Color.rgb(255, 175, 175);

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSnakeViewMap(TileType[][] map)
    {
        this.snakeViewMap = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(snakeViewMap != null){
            float tileSizeX = canvas.getWidth() / snakeViewMap.length;
            float tileSizeY = canvas.getHeight() / snakeViewMap[0].length;


            float circleSize = Math.min(tileSizeX, tileSizeY);

            for(int x = 0; x < snakeViewMap.length; x++){
                for(int y = 0; y < snakeViewMap[x].length; y++){
                    switch (snakeViewMap[x][y]){ // Declares the color of the objects in the game

                        case Nothing:
                            mPaint.setColor(Color.WHITE);
                            break;
                        case Wall:
                            mPaint.setColor(Color.GRAY);
                            break;
                        case SnakeHead:
                            mPaint.setColor(Color.RED);
                            break;
                        case SnakeTail:
                            mPaint.setColor(Color.GREEN);
                            break;
                        case Apple:
                            mPaint.setColor(Orange); // Draws the apple as oranges
                            break;
                        case SnakeHead2:
                            mPaint.setColor(Color.RED);
                            break;
                        case  SnakeTail2:
                            mPaint.setColor(Pink);
                            break;
                        case SnakeHead3:
                            mPaint.setColor(Color.BLUE);
                            break;
                        case SnakeTail3:
                            mPaint.setColor(Color.CYAN);
                            break;

                    }
                    canvas.drawRect(x*tileSizeX-tileSizeX/2,tileSizeY*y-tileSizeY/2,x*tileSizeX+tileSizeX/2,y*tileSizeY+tileSizeY/2, mPaint);
                }
            }
        }
    }
}
