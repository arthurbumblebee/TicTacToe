package hu.ait.android.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class TicTacToeView extends View {


    private Paint paintBackground;
    private Paint normalPaintLine;
    private Paint crossPaintLine;
    private Paint circlePaintLine;
    private Paint paintText;
    private Bitmap bitmapBg;

    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.DKGRAY);
        paintBackground.setStyle(Paint.Style.FILL);

        normalPaintLine = new Paint();
        normalPaintLine.setColor(Color.WHITE);
        normalPaintLine.setStyle(Paint.Style.STROKE);
        normalPaintLine.setStrokeWidth(5);

        circlePaintLine = new Paint();
        circlePaintLine.setColor(Color.YELLOW);
        circlePaintLine.setStyle(Paint.Style.STROKE);
        circlePaintLine.setStrokeWidth(5);

        crossPaintLine = new Paint();
        crossPaintLine.setColor(Color.RED);
        crossPaintLine.setStyle(Paint.Style.STROKE);
        crossPaintLine.setStrokeWidth(5);

        paintText = new Paint();
        paintText.setColor(Color.CYAN);
        paintText.setTextSize(50);

        bitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.devayvay);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//      draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

        canvas.drawBitmap(bitmapBg, 0,0,null);

//        draw game field
        drawGameArea(canvas);

//        draw player
        drawPlayers(canvas);

//        canvas.drawText("6", 40, 140, paintText);
    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getTicTacToeModelInstance().getFieldContent(i, j)
                        == TicTacToeModel.CIRCLE) {
//                    draw circle
                    int centerX = i * getWidth() / 3 + getWidth() / 6;
                    int centerY = j * getHeight() / 3 + getHeight() / 6;

                    canvas.drawCircle(centerX,
                            centerY,
                            getWidth() / 6 - 2,
                            circlePaintLine);


                } else if (TicTacToeModel.getTicTacToeModelInstance().getFieldContent(i, j)
                        == TicTacToeModel.CROSS) {
//                    draw cross
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, crossPaintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, crossPaintLine);

                }
            }
        }
    }

    private void drawGameArea(Canvas canvas) {
//        border
        canvas.drawRect(0, 0, getWidth(), getHeight(), normalPaintLine);

//        vertical lines
        canvas.drawLine(
                getWidth() / 3,
                0,
                getWidth() / 3,
                getHeight(),
                normalPaintLine);

        canvas.drawLine(2 * (
                        getWidth() / 3),
                0,
                2 * (getWidth() / 3),
                getHeight(),
                normalPaintLine);

        // horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, normalPaintLine);
        canvas.drawLine(0, 2 * (getHeight() / 3), getWidth(), 2 * (getHeight() / 3), normalPaintLine);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);

            TicTacToeModel currentTicTacInstance = TicTacToeModel.getTicTacToeModelInstance();


            if (currentTicTacInstance.getFieldContent(tX, tY)
                    == TicTacToeModel.EMPTY) {
                currentTicTacInstance.setFieldContent(
                        tX,
                        tY,
                        currentTicTacInstance.getNextPlayer()

                );


                currentTicTacInstance.switchNextPlayer();

                String nextPlayer = "O";

                if (currentTicTacInstance.isWinner(currentTicTacInstance, tX, tY)) {
                    ((MainActivity) getContext()).showWinner(nextPlayer);
                    invalidate();
                    clearBoard();
                    return true;

                }

                if (currentTicTacInstance.getNextPlayer()
                        == TicTacToeModel.CROSS) {
                    nextPlayer = "X";
                }


                ((MainActivity) getContext()).setNextPlayer(nextPlayer);

                invalidate();
            }

            return true;
        }

        return super.onTouchEvent(event);
    }

    public void clearBoard() {
        TicTacToeModel.getTicTacToeModelInstance().resetGame();

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paintText.setTextSize(getWidth()/3);

        bitmapBg = Bitmap.createScaledBitmap( bitmapBg, getWidth(), getHeight(), false);
    }
}
