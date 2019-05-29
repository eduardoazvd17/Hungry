package br.net.hungry;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

public class MapaActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageButton voltar = findViewById(R.id.btnMapaVoltar);
        TextView txtEndereco = findViewById(R.id.txtEndereco);
        String endereco = Objects.requireNonNull(getIntent().getExtras()).getString("endereco");
        WebView wvMapa = findViewById(R.id.wvMapa);

        txtEndereco.setText(endereco);

        wvMapa.setWebViewClient(new WebViewClient());
        wvMapa.getSettings().setJavaScriptEnabled(true);
        try {
            wvMapa.loadUrl("https://maps.google.com/maps?q=" + URLEncoder.encode(endereco, "UTF-8") + "&amp;z=15&amp;output=embed");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
