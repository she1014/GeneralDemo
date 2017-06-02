package com.techfly.demo.selfview.luckypan;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * 
 * 抽奖盘模板
 * @author fuxingkai
 *
 */
public class SurfaceViewTempalte extends SurfaceView implements Callback, Runnable{
	
	private SurfaceHolder mHolder;
	
	private Canvas mCanvas;
	
	/**
	 * 用于绘制的线程
	 */
	private Thread t;
	
	/**
	 * 线程的绘制开光
	 */
	private boolean isRuning;
	
	public SurfaceViewTempalte(Context context) {
		this(context, null);
	}
	
	public SurfaceViewTempalte(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mHolder = getHolder();
		
		mHolder.addCallback(this);
		
		//可获得焦点
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		//设置常量
		setKeepScreenOn(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		isRuning = true;
		
		t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		isRuning = false;
		
		
	}

	@Override
	public void run() {
		//不断进行绘制
		while (isRuning) {
			draw();
		}
	}

	private void draw() {
		try {
			mCanvas = mHolder.lockCanvas();
			
			if(mCanvas != null){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mCanvas != null) {
				mHolder.unlockCanvasAndPost(mCanvas);
			}
		}
		
	}
 
}
