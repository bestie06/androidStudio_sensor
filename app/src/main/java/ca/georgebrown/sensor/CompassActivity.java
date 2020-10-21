package ca.georgebrown.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class CompassActivity extends AppCompatActivity {
    private MyCompassView compassView;
    private SensorManager sensorManager;
    private Sensor sensor;





    private SensorEventListener sensorEventListener =new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float azimuth=event.values[0];
            compassView.updateData(azimuth);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        compassView=new MyCompassView(this);

        setContentView(compassView);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor !=null){
            sensorManager.registerListener(sensorEventListener,
                    sensor,SensorManager.SENSOR_DELAY_NORMAL
            );
        }else{
            Toast.makeText(this, "Orientation sensor not found",Toast.LENGTH_LONG).show();
        }
    }
}














