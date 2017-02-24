package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.myappbasicframe.R;


public class SenserListenerActivity extends Activity implements SensorListener {
	 SensorManager sm = null;
	 TextView acx = null;
	 TextView acy = null;
	 TextView o = null;
	    double ax = 0;
	    double ay = 0;
	    double oy = 0;
	    double oz = 0;
	    double axp=0;
	    double ayp=0;
	    double g=10;
	    double gp=0;
	   
	    double tmp1=0,tmp2=0,tmp3=0,tmp4=0;
	    public double getAccelerationX()
	    {
	     return axp;
	    }
	    public double getAccelerationY()
	    {
	     return ayp;
	    }
	    public double getOrientation()
	    {
	     
	        return Math.asin(tmp4)/Math.PI*180.0;
	    }
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	       // get reference to SensorManager
	        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
	        setContentView(R.layout.main);
	       
	        acx = (TextView) findViewById(R.id.xbox);
	        acy = (TextView) findViewById(R.id.ybox);
	        o = (TextView) findViewById(R.id.obox);
	    }
	    public void onSensorChanged(int sensor, float[] values) {
	        synchronized (this) {
	            if (sensor == SensorManager.SENSOR_ORIENTATION) {
	                oy=values[1];
	                oz=values[2];
	            }
	            if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
	                ax=values[0];
	                ay=values[1];
	            }   
	           
	           tmp1=Math.sin(oz*Math.PI/180.0);
	           tmp2=Math.sin(Math.abs(oy)*Math.PI/180.0);
	           tmp3=Math.sqrt(tmp1*tmp1+tmp2*tmp2);
	           tmp4=tmp1/tmp3;
	          
	           
	           
	            gp=10*tmp3;
	            axp=ax*Math.cos(tmp4)+ay*Math.sin(tmp4);
	            ayp=-ax*Math.sin(tmp4)+ay*Math.cos(tmp4)+gp;
	            acx.setText("a X: " + getAccelerationX());
	            acy.setText("a Y: " + getAccelerationY());
	            o.setText("Orientation : " + getOrientation());
	            
	            if ((Math.abs(getAccelerationX()))>3&&(Math.abs(getOrientation())>10)) {
					Toast.makeText(SenserListenerActivity.this, "heng", 0).show();
				}	            
	            
	        }
	    }
	   
	    public void onAccuracyChanged(int sensor, int accuracy) {
	    }
	    @Override
	    protected void onResume() {
	     super.onResume();
	      // register this class as a listener for the orientation and accelerometer sensors
	        sm.registerListener(this,
	                SensorManager.SENSOR_ORIENTATION |SensorManager.SENSOR_ACCELEROMETER,
	                SensorManager.SENSOR_DELAY_NORMAL);
	    }
	    @Override
	    protected void onStop() {
	        // unregister listener
	        sm.unregisterListener(this);
	        super.onStop();
	    }   
	}