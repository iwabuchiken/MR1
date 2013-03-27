package mr1.listeners.sensors;

import mr1.main.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

public class CustomSensorEventListener implements SensorEventListener {

	Activity actv;
	
	private float lowX;
	private float lowY;
	private float lowZ;
	
	  // ���[�p�X�t�B���^�Ώ۔͈�
	private static final float FILTERING_VALUE = 0.2f;

	public CustomSensorEventListener(Activity actv) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
	}
	

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

      // �Z���T�[�����m�����l���擾
      float x = event.values[SensorManager.DATA_X];
      float y = event.values[SensorManager.DATA_Y];
      float z = event.values[SensorManager.DATA_Z];

      // ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
      lowX = getLowPassFilterValue(x, lowX);
      lowY = getLowPassFilterValue(y, lowY);
      lowZ = getLowPassFilterValue(z, lowZ);

      switch (event.sensor.getType()) {

      // ���m���ꂽ�Z���T�[��񂪉����x�Z���T�[�̏ꍇ
      case Sensor.TYPE_ACCELEROMETER:
//          drawableView.effectAccelaration(lowX, lowY, lowZ);
//
//          // ��ʂ��ĕ`��
//          drawableView.invalidate();
      	
      	showData(lowX, lowY, lowZ);
      	
          break;

      // �����x�Z���T�[�ȊO�͖���
      default:
          break;
      }//switch (event.sensor.getType())

	}//public void onSensorChanged(SensorEvent event)

	private void showData(float lowX, float lowY, float lowZ) {
		/***************************************
		 * X
		 ***************************************/
		TextView tvX = (TextView) actv.findViewById(R.id.actv_main_tv_val_x);
		
		tvX.setText(String.valueOf(lowX));
		
		/***************************************
		 * Y
		 ***************************************/
		TextView tvY = (TextView) actv.findViewById(R.id.actv_main_tv_val_y);
		
		tvY.setText(String.valueOf(lowY));
		
		/***************************************
		 * Z
		 ***************************************/
		TextView tvZ = (TextView) actv.findViewById(R.id.actv_main_tv_val_z);
		
		tvZ.setText(String.valueOf(lowZ));
		
		
		
//    // Log
//		Log.d("ActvMain.java"
//				+ "["
//				+ Thread.currentThread().getStackTrace()[2]
//						.getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2]
//						.getMethodName() + "]", "lowX=" + lowX);
    
  		
	}//private void showData(float lowX, float lowY, float lowZ)

	// ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
	private float getLowPassFilterValue(float eventValue, float lowValue) {
	    return eventValue * FILTERING_VALUE + lowValue
	        * (1.0f - FILTERING_VALUE);
	}
	
}//public class CustomSensorEventListener implements SensorEventListener
