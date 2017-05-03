package com.user;

import com.agenda.R;
import com.qrcreate.GenerateQRCodeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	public void QR(View v){	
		Intent itnt = new Intent(this, GenerateQRCodeActivity.class);
		startActivity(itnt);
}
 public void Hist(View v){	
	Intent itnt = new Intent(this, HistoricoActivity.class);
	startActivity(itnt);
}
	

}
