package br.net.hungry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RestaurantesAdapter extends BaseAdapter {

    private List<Restaurante> restaurantes;
    private Activity activity;

    RestaurantesAdapter(List<Restaurante> restaurantes, Activity activity) {
        this.restaurantes = restaurantes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return restaurantes.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = activity.getLayoutInflater().inflate(R.layout.item_lista, parent, false);
        Restaurante restaurante = restaurantes.get(position);

        //Instanciar objetos do xml;
        TextView nome = view.findViewById(R.id.li_nome);
        TextView situacao = view.findViewById(R.id.li_situacao);
        TextView classificacao = view.findViewById(R.id.li_classificacao);
        TextView distancia = view.findViewById(R.id.li_distancia);

        //Atribuir atributos nesses objetos;
        nome.setText(restaurante.getNome());
        situacao.setText(restaurante.getFechado());
        classificacao.setText("Classificação: " + restaurante.getClassificacao() + " estrelas (" + restaurante.getAvaliacoes() + " avaliações)" );
        distancia.setText("Distância: " + String.format("%.2f", restaurante.getDistancia()) + " KM");

        return view;
    }
}
