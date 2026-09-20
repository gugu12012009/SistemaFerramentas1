package com.example.sistemaferramentas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastrarActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        db = new DatabaseHelper(this);

        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtQuantidade = findViewById(R.id.edtQuantidade);
        EditText edtLocal = findViewById(R.id.edtLocal);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            String qtdStr = edtQuantidade.getText().toString().trim();
            String local = edtLocal.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Informe o nome da ferramenta", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantidade = 0;
            if (!qtdStr.isEmpty()) {
                try {
                    quantidade = Integer.parseInt(qtdStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Ferramenta f = new Ferramenta();
            f.setNome(nome);
            f.setQuantidade(quantidade);
            f.setLocalizacao(local);

            long id = db.inserir(f);
            if (id > 0) {
                Toast.makeText(this, "Ferramenta cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                edtNome.setText("");
                edtQuantidade.setText("");
                edtLocal.setText("");
            } else {
                Toast.makeText(this, "Erro ao cadastrar ferramenta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
