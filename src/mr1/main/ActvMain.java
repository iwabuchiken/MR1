package mr1.main;

import java.util.List;

import mr1.listeners.sensors.CustomSensorEventListener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class ActvMain extends Activity {

    private SensorManager sensorManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_main);
		
		this.setTitle(this.getClass().getName());
		
//		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "onResume()");
		
        // �����x�Z���T�[�I�u�W�F�N�g�擾
        List<Sensor> accelerometerSensors =
            sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        //debug
        if (accelerometerSensors != null) {
			
        	// Log
			Log.d("ActvMain.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"accelerometerSensors.size()=" + accelerometerSensors.size());
        	
		} else {//if (accelerometerSensors != null)
			
			// Log
			Log.d("ActvMain.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "accelerometerSensors => null");
			
		}//if (accelerometerSensors != null)
		
        
        // �����x�Z���T�[�I�u�W�F�N�g���擾�ł����ꍇ
        if (accelerometerSensors.size() > 0) {
            // SensorManager�C���X�^���X�ɃZ���T�[�C�x���g���X�i�[��ݒ�
            sensorManager.registerListener(sensorEventListener,
                accelerometerSensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
		
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "onStart()");
		
		setupSensor();
		
	}

	private void setupSensor() {
		// TODO Auto-generated method stub
		sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		sensorManager.unregisterListener(sensorEventListener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}

    private final SensorEventListener sensorEventListener =
    					new CustomSensorEventListener(this);
//            new SensorEventListener() {
//                // ���[�p�X�t�B���^�p�ϐ�
//                private float lowX;
//                private float lowY;
//                private float lowZ;
//
//                // ���[�p�X�t�B���^�Ώ۔͈�
//                private static final float FILTERING_VALUE = 0.2f;
//
//                // onAccuracyChanged���\�b�h(���x�ύX���C�x���g)
////                @Override
//                public void onAccuracyChanged(Sensor sensor, int accuracy) {
//                    // �����Ȃ�
//                }
//
//                // onSensorChanged���\�b�h(�Z���T�[���ύX���C�x���g)
////                @Override
//                public void onSensorChanged(SensorEvent event) {
//                    // �Z���T�[�����m�����l���擾
//                    float x = event.values[SensorManager.DATA_X];
//                    float y = event.values[SensorManager.DATA_Y];
//                    float z = event.values[SensorManager.DATA_Z];
//
//                    // ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
//                    lowX = getLowPassFilterValue(x, lowX);
//                    lowY = getLowPassFilterValue(y, lowY);
//                    lowZ = getLowPassFilterValue(z, lowZ);
//
////                    // Log
////					Log.d("ActvMain.java"
////							+ "["
////							+ Thread.currentThread().getStackTrace()[2]
////									.getLineNumber()
////							+ ":"
////							+ Thread.currentThread().getStackTrace()[2]
////									.getMethodName() + "]", "lowX=" + lowX);
////                    
//                    switch (event.sensor.getType()) {
//
//                    // ���m���ꂽ�Z���T�[��񂪉����x�Z���T�[�̏ꍇ
//                    case Sensor.TYPE_ACCELEROMETER:
////                        drawableView.effectAccelaration(lowX, lowY, lowZ);
////
////                        // ��ʂ��ĕ`��
////                        drawableView.invalidate();
//                    	
//                    	showData(lowX, lowY, lowZ);
//                    	
//                        break;
//
//                    // �����x�Z���T�[�ȊO�͖���
//                    default:
//                        break;
//                    }
//                }
//
//                private void showData(float lowX, float lowY, float lowZ) {
//					// TODO Auto-generated method stub
//                	
//                	
//					
//				}
//
//				// ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
//                private float getLowPassFilterValue(float eventValue, float lowValue) {
//                    return eventValue * FILTERING_VALUE + lowValue
//                        * (1.0f - FILTERING_VALUE);
//                }
//            };

}//public class ActvMain extends Activity
