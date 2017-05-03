package com.qrcreate;
import com.agenda.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.user.ContextoDados;
import com.user.ContextoDados.ContatosCursor;
import com.user.MenuActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GenerateQRCodeActivity extends Activity {

	public final static int WHITE = 0xFFFFFFFF;
	public final static int BLACK = 0xFF000000;
	public final static int WIDTH = 400;
	public final static int HEIGHT = 400;
	public static String STR = "A string to be encoded as QR code";
	public ImageView imageView ;
	public TextView tv_text ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr);
		
		criarText(this);
	}
	public void menu(View v){	
		Intent itnt = new Intent(this, MenuActivity.class);
		startActivity(itnt);
}
	public void criarText(Context c) {
		ContextoDados db = new ContextoDados(c);
		ContatosCursor cursor = db.RetornarContatos(ContatosCursor.OrdenarPor.NomeCrescente);

		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			STR = cursor.getCPF();
			imageView = (ImageView) findViewById(R.id.iv_qrcode);

			try {
			    Bitmap bitmap = encodeAsBitmap(STR);
			    imageView.setImageBitmap(bitmap);
			} catch (WriterException e) {
			    e.printStackTrace();
			}
		}}
	
	
	

	Bitmap encodeAsBitmap(String str) throws WriterException {
		BitMatrix result;
		try {
			result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
		} catch (IllegalArgumentException iae) {
			// Unsupported format
			return null;
		}
		
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			int offset = y * width;
			for (int x = 0; x < width; x++) {
				pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
			}
		}
		
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
}