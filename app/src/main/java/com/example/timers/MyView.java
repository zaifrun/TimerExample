package com.example.timers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//This class has not changed since the first pacman lesson
public class MyView extends View{
	
	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
    int pacx = 50;
    int pacy = 400;
    int h,w;

	//resetting the game - setting the pacman to a
	//starting x value and invalidate view.
    public void reset()
    {
    	pacx = 50;
    	invalidate();
    }
    
    public void move(int x)
    {
    	//still within our boundaries?
    	if (pacx+x+bitmap.getWidth()<w)
    		pacx=pacx+x;
    	invalidate(); //redraw everything
	}
	
	public MyView(Context context) {
		super(context);
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}
	
	
	public MyView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context,attrs,defStyleAttr);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {  
		//Here we get the height and weight
		h = canvas.getHeight();
		w = canvas.getWidth();
		System.out.println("h = "+h+", w = "+w);
		//Making a new paint object
		Paint paint = new Paint();
		//setting the color
		paint.setColor(Color.RED);
		canvas.drawColor(Color.WHITE); //clear entire canvas to white color
		//drawing a line from (0,0) -> (300,200)
		canvas.drawLine(0,0,300,200,paint);
		paint.setColor(Color.GREEN);
		canvas.drawLine(0,200,300,0,paint);
		
		//setting the color using the format: Transparency, Red, Green, Blue
		paint.setColor(0xff000099);
		
		//drawing a circle with radius 20, and center in (100,100) 
		canvas.drawCircle(100,100,20,paint);
		
		
		canvas.drawBitmap(bitmap, pacx, pacy, paint);
		super.onDraw(canvas);
	}

}
