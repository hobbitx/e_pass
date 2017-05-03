package com.user;


import com.agenda.R;
import com.user.ContextoDados.ContatosCursor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoricoActivity extends Activity {

	TextView saldo ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico);
		saldo = (TextView) findViewById(R.id.lblSaldo);
		checarSaldo(this);
	}


	public void menu(View v){	
		Intent itnt = new Intent(this, MenuActivity.class);
		startActivity(itnt);
}
public void checarSaldo(Context c) {
	ContextoDados db = new ContextoDados(c);
	ContatosCursor cursor = db.RetornarContatos(ContatosCursor.OrdenarPor.NomeCrescente);

	for (int i = 0; i < cursor.getCount(); i++) {
		cursor.moveToPosition(i);
		saldo.setText(cursor.getSaldo().toString());

		}};
}


