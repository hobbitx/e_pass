package com.user;

import com.agenda.R;
import com.formats.Mask;
import com.user.ContextoDados.ContatosCursor;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.jansenfelipe.androidmask.BuildConfig;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends Activity {

	Button btnSalvar, btnCancelar, btnNovo;
	EditText txtNome, txtCartao, txtSenha, txtCpf;
	private TextWatcher maskcart, maskcpf;
	SharedPreferences prefs = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            CarregarInterfaceListagem();
            prefs.edit().putBoolean("firstrun", false).commit();
        }else{
        	checarLoguin(this);
        }
    }
	
	
	public void checarLoguin(Context c) {
		
		ContextoDados db = new ContextoDados(c);
		ContatosCursor cursor = db.RetornarContatos(ContatosCursor.OrdenarPor.NomeCrescente);
		String log = "";
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			log = cursor.getLog();
			if (log.equals("1")) {
				
				menu();
			}

			else {
				
				CarregarInterfaceListagem();
			}
		}
		CarregarInterfaceListagem();

	}
	public void CarregarInterfaceListagem() {
		setContentView(R.layout.main);

		// configurando o bot�o de criar novo cadastro
		btnNovo = (Button) findViewById(R.id.btnNovo);
		btnNovo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CarregarInterfaceCadastro();
			}
		});
	}
	public void CarregarInterfaceCadastro() {
		setContentView(R.layout.cadastro);

		// configurando o botão de cancelar cadastro
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CarregarInterfaceListagem();
			}
		});

		// configurando o formulário de cadastro
		txtNome = (EditText) findViewById(R.id.txtNome);
		txtCartao = (EditText) findViewById(R.id.txtCartao);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		txtCpf = (EditText) findViewById(R.id.txtCPF);
		maskcart = Mask.insert("####.####.####.####", txtCartao);
		txtCartao.addTextChangedListener(maskcart);
		maskcpf = Mask.insert("###.###.###-##", txtCpf);
		txtCpf.addTextChangedListener(maskcpf);
		// configurando o botão de salvar
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SalvarCadastro();
			}
		});
	}

	public void SalvarCadastro() {

		ContextoDados db = new ContextoDados(this);
		db.InserirContato(txtNome.getText().toString(), txtSenha.getText().toString(), txtCartao.getText().toString(),
				txtCpf.getText().toString());
		Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
		Intent itnt = new Intent(this, LogarActivity.class);
		startActivity(itnt);
	}

	public void loga(View v) {
		Intent itnt = new Intent(this, LogarActivity.class);
		startActivity(itnt);
	}

	public void menu() {
		Intent itnt = new Intent(this, MenuActivity.class);
		startActivity(itnt);
	}

}