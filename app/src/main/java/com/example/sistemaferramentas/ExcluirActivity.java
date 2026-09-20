package com.example.sistemaferramentas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ExcluirActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<Ferramenta> lista;
    private ListView listView;
    private TextView txtVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewExcluir);
        txtVazio = findViewById(R.id.txtVazioExcluir);

        carregarLista();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ferramenta f = lista.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir ferramenta")
                    .setMessage("Deseja realmente excluir \"" + f.getNome() + "\"?")
                    .setPositiveButton("Excluir", (dialog, which) -> {
                        db.excluir(f.getId());
                        Toast.makeText(this, "Ferramenta excluída", Toast.LENGTH_SHORT).show();
                        carregarLista();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    private void carregarLista() {
        lista = db.listarTodas();
        if (lista.isEmpty()) {
            txtVazio.setVisibility(View.VISIBLE);
            listView.setAdapter(null);
        } else {
            txtVazio.setVisibility(View.GONE);
            FerramentaAdapter adapter = new FerramentaAdapter(this, lista);
            listView.setAdapter(adapter);
        }
    }
}
