package com.example.sistemaferramentas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AlterarActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<Ferramenta> lista;
    private Ferramenta selecionada;

    private EditText edtNome, edtQuantidade, edtLocal;
    private TextView txtVazio, txtSelecionado;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        db = new DatabaseHelper(this);

        listView = findViewById(R.id.listViewAlterar);
        txtVazio = findViewById(R.id.txtVazioAlterar);
        txtSelecionado = findViewById(R.id.txtSelecionado);
        edtNome = findViewById(R.id.edtNomeAlterar);
        edtQuantidade = findViewById(R.id.edtQuantidadeAlterar);
        edtLocal = findViewById(R.id.edtLocalAlterar);
        Button btnSalvar = findViewById(R.id.btnSalvarAlterar);

        carregarLista();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selecionada = lista.get(position);
            edtNome.setText(selecionada.getNome());
            edtQuantidade.setText(String.valueOf(selecionada.getQuantidade()));
            edtLocal.setText(selecionada.getLocalizacao());
            txtSelecionado.setText("Editando: " + selecionada.getNome());
        });

        btnSalvar.setOnClickListener(v -> {
            if (selecionada == null) {
                Toast.makeText(this, "Selecione uma ferramenta na lista acima", Toast.LENGTH_SHORT).show();
                return;
            }

            String nome = edtNome.getText().toString().trim();
            if (nome.isEmpty()) {
                Toast.makeText(this, "Informe o nome", Toast.LENGTH_SHORT).show();
                return;
            }

            String qtdStr = edtQuantidade.getText().toString().trim();
            int quantidade = 0;
            if (!qtdStr.isEmpty()) {
                try {
                    quantidade = Integer.parseInt(qtdStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            selecionada.setNome(nome);
            selecionada.setQuantidade(quantidade);
            selecionada.setLocalizacao(edtLocal.getText().toString().trim());

            int rows = db.atualizar(selecionada);
            if (rows > 0) {
                Toast.makeText(this, "Ferramenta atualizada!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarLista() {
        lista = db.listarTodas();
        if (lista.isEmpty()) {
            txtVazio.setVisibility(View.VISIBLE);
        } else {
            txtVazio.setVisibility(View.GONE);
            FerramentaAdapter adapter = new FerramentaAdapter(this, lista);
            listView.setAdapter(adapter);
        }
    }
}
