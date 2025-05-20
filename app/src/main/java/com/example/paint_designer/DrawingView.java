package com.example.paint_designer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {
    private Paint currentPaint;
    private Path currentPath;
    private List<DrawPath> paths = new ArrayList<>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        currentPaint = new Paint();
        currentPaint.setColor(Color.BLACK);
        currentPaint.setAntiAlias(true);
        currentPaint.setStrokeWidth(8f);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(DrawPath drawPath : paths) {
            canvas.drawPath(drawPath.path, drawPath.paint);
        }

        if(currentPath != null) {
            canvas.drawPath(currentPath, currentPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:

                paths.add(new DrawPath(currentPath, new Paint(currentPaint)));
                currentPath = null;
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setCurrentColor(int color) {
        currentPaint.setColor(color);
    }

    public void clearCanvas() {
        paths.clear();
        currentPath = null;
        invalidate();
    }


    private static class DrawPath {
        Path path;
        Paint paint;

        DrawPath(Path path, Paint paint) {
            this.path = path;
            this.paint = new Paint(paint);
        }
    }
}
