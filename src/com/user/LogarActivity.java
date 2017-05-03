package com.user;

import com.agenda.R;
import com.user.ContextoDados.ContatosCursor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogarActivity extends Activity {
	Button btnCancelarL, btnLogar;
	EditText txtNomeL, txtSenhaL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logar);
		
		  //configurando o formulário de cadastro
        txtNomeL = (EditText)findViewById(R.id.txtNomeL);
        txtSenhaL = (EditText)findViewById(R.id.txtSenhaL);
        

      //configurando o botão de cancelar cadastro
      
	//configurando o botão de cancelar cadastro
   
		}
	
	
	public void voltar(View v){	
			Intent itnt = new Intent(this, MainActivity.class);
			startActivity(itnt);
	}
	public void menu(View v){	
		Intent itnt = new Intent(this, MenuActivity.class);
		startActivity(itnt);
}
	public void entrar(View view) {
		checarLoguin(this);
	
	}
	public void checarLoguin(Context c) {
		ContextoDados db = new ContextoDados(c);
		ContatosCursor cursor = db.RetornarContatos(ContatosCursor.OrdenarPor.NomeCrescente);

		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			/*Toast.makeText(this,""+ cursor.getNome() +"-"+ cursor.getSenha(), Toast.LENGTH_SHORT).show();
			Toast.makeText(this,""+ txtNomeL.getText() +"-"+ txtSenhaL.getText(), Toast.LENGTH_SHORT).show();
			 */
			if (txtNomeL.getText().toString().equals(cursor.getNome()) && txtSenhaL.getText().toString().equals(cursor.getSenha())){
				Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
				View v = null;
				menu(v);
			}else{
				Toast.makeText(this, "Loguin ou Senha errados", Toast.LENGTH_SHORT).show();
				
			}
				
	
		}
		
	}
}
