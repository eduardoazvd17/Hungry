package br.net.hungry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;


public class PesquisaActivity extends AppCompatActivity {

    private ListView lvRestaurantes;
    private RestaurantesAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        TextView txtRestaurantesEncontrados = findViewById(R.id.txtRestaurantesEncontrados);
        ImageButton btnVoltar = findViewById(R.id.btnVoltarInicio);
        lvRestaurantes = findViewById(R.id.lvRestaurantes);
        List<Restaurante> restaurantes = (List<Restaurante>) getIntent().getSerializableExtra("restaurantesEncontrados");
        txtRestaurantesEncontrados.setText(restaurantes.size()+" Restaurantes Econtrados");

        exibir(restaurantes);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try{
                    Restaurante restauranteSelecionado = (Restaurante) adapter.getItem(position);
                    Intent intent = new Intent(view.getContext(), DetalheActivity.class);
                    intent.putExtra("nome", restauranteSelecionado.getNome());
                    intent.putExtra("classificacao", restauranteSelecionado.getClassificacao());
                    intent.putExtra("avaliacoes", restauranteSelecionado.getAvaliacoes());
                    intent.putExtra("situacao", restauranteSelecionado.getFechado());
                    intent.putExtra("distancia", restauranteSelecionado.getDistancia());
                    intent.putExtra("precoMedio", restauranteSelecionado.getPrecoMedio());
                    intent.putExtra("local", restauranteSelecionado.getLocal());
                    intent.putExtra("telefone", restauranteSelecionado.getTelefone());
                    intent.putExtra("imagem", restauranteSelecionado.getImagem());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void exibir(List<Restaurante> restaurantesEncontrados) {
        adapter = new RestaurantesAdapter(restaurantesEncontrados, this);
        lvRestaurantes.setAdapter(adapter);
    }
}
