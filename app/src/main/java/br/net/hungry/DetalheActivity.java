package br.net.hungry;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DetalheActivity extends AppCompatActivity {

    private TextView txtLocal;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageButton btnVoltar = findViewById(R.id.btnVoltar);
        ImageButton btnMapa = findViewById(R.id.btnMapa);

        ImageView img = findViewById(R.id.dtImg);
        TextView txtNome = findViewById(R.id.txtNome);
        txtLocal = findViewById(R.id.txtLocal);
        TextView txtSituacao = findViewById(R.id.txtSituacao);
        TextView txtDistancia = findViewById(R.id.txtDistancia);
        TextView txtClassificacao = findViewById(R.id.txtClassificacao);
        TextView txtPrecoMedio = findViewById(R.id.txtPrecoMedio);
        TextView txtTelefone = findViewById(R.id.txtTelefone);

        exibirImagem(Objects.requireNonNull(getIntent().getExtras()).getString("imagem"), img);
        txtNome.setText(getIntent().getExtras().getString("nome"));
        txtLocal.setText(getIntent().getExtras().getString("local"));
        txtSituacao.setText(getIntent().getExtras().getString("situacao"));
        txtDistancia.setText(String.format("%.2f", getIntent().getExtras().getDouble("distancia")) + " KM");
        txtClassificacao.setText(getIntent().getExtras().getDouble("classificacao") + " estrelas (" + getIntent().getExtras().getInt("avaliacoes") + " avaliações)");
        txtPrecoMedio.setText(getIntent().getExtras().getString("precoMedio"));
        txtTelefone.setText(getIntent().getExtras().getString("telefone"));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(v.getContext(), MapaActivity.class);
                    intent.putExtra("endereco", txtLocal.getText().toString());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void exibirImagem(String fileUrl, ImageView iv) {
        if (fileUrl.isEmpty()) {
            return;
        }
        try {
            URL myFileUrl = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            iv.setImageBitmap(BitmapFactory.decodeStream(is));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
