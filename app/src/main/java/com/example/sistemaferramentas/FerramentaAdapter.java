package com.example.sistemaferramentas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FerramentaAdapter extends ArrayAdapter<Ferramenta> {
    private final Context context;
    private final List<Ferramenta> lista;

    public FerramentaAdapter(Context context, List<Ferramenta> lista) {
        super(context, R.layout.item_ferramenta, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_ferramenta, parent, false);
        }
        Ferramenta f = lista.get(position);
        TextView txtNome = view.findViewById(R.id.txtNomeItem);
        TextView txtDetalhe = view.findViewById(R.id.txtDetalheItem);

        txtNome.setText(f.getNome());

        String detalhe = "Quantidade: " + f.getQuantidade();
        if (f.getLocalizacao() != null && !f.getLocalizacao().isEmpty()) {
            detalhe += "  |  Local: " + f.getLocalizacao();
        }
        txtDetalhe.setText(detalhe);

        return view;
    }
}
