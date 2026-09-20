package com.example.sistemaferramentas;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        DatabaseHelper db = new DatabaseHelper(this);
        List<Ferramenta> lista = db.listarTodas();

        ListView listView = findViewById(R.id.listViewFerramentas);
        TextView txtVazio = findViewById(R.id.txtVazio);

        if (lista.isEmpty()) {
            txtVazio.setVisibility(View.VISIBLE);
        } else {
            txtVazio.setVisibility(View.GONE);
            FerramentaAdapter adapter = new FerramentaAdapter(this, lista);
            listView.setAdapter(adapter);
        }
    }
}
