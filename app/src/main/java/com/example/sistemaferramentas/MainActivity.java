package com.example.sistemaferramentas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        Button btnAlterar = findViewById(R.id.btnAlterar);
        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnExcluir = findViewById(R.id.btnExcluir);

        btnCadastrar.setOnClickListener(v -> startActivity(new Intent(this, CadastrarActivity.class)));
        btnAlterar.setOnClickListener(v -> startActivity(new Intent(this, AlterarActivity.class)));
        btnConsultar.setOnClickListener(v -> startActivity(new Intent(this, ConsultarActivity.class)));
        btnExcluir.setOnClickListener(v -> startActivity(new Intent(this, ExcluirActivity.class)));
    }
}
