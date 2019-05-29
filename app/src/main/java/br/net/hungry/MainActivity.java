package br.net.hungry;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText txtPesquisa;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtPesquisa = findViewById(R.id.edtPesquisa);
        TextView txtFrase = findViewById(R.id.txtFrase);
        ImageButton btnPesquisar = findViewById(R.id.btnPesquisar);
        ImageButton btnPizza = findViewById(R.id.btnPizza);
        ImageButton btnHamburguer = findViewById(R.id.btnHamburguer);
        ImageButton btnArabe = findViewById(R.id.btnArabe);
        ImageButton btnBrasileira = findViewById(R.id.btnBrasileira);
        ImageButton btnJapones = findViewById(R.id.btnJapones);
        ImageButton btnSobremesa = findViewById(R.id.btnSobremesa);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        txtPesquisa.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ocultarTeclado();
                    try {
                        pesquisar(v);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        btnPizza.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Pizza");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        btnHamburguer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Hamburguer");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        btnArabe.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Arabe");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        btnBrasileira.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Brasileira");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        btnJapones.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Japones");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        btnSobremesa.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                txtPesquisa.setText("Sobremesa");
                try {
                    pesquisar(v);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        txtFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado();
            }
        });

        ocultarTeclado();
    }

    @Override
    public void onResume() {
        super.onResume();
        txtPesquisa.setText("");
        ocultarTeclado();
    }

    public void pesquisar(View view) throws Exception {
        String key = "O4f7ip4NoW3E1f5M_03rMdxRYmUrXslCAvCFVNAbEA9JokED5ZoU1YqTIY-n31I6VhMH3FVwO1jZHAuZ7tP_t993CVGIibVGsdiW79sotBBd0gU96JaRPoME_oi0XHYx";
        pegarLocalizacao();
        if (txtPesquisa.getText().toString().isEmpty()) {
            List<Restaurante> restaurantes = new ArrayList<>();
            String urlString = "https://api.yelp.com/v3/businesses/search" + "?latitude=" + latitude + "&longitude=" + longitude;
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Authorization", "Bearer" + " " + key);
            conexao.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String jsonString = br.readLine();
            JSONObject json = new JSONObject(jsonString);
            JSONArray conteudo = json.getJSONArray("businesses");

            for (int i = 0; i < conteudo.length(); i++) {
                JSONObject obj = conteudo.getJSONObject(i);
                JSONObject endereco = obj.getJSONObject("location");

                String id = (String) obj.opt("id");
                String nome = (String) obj.opt("name");
                String precoMedio = (String) obj.opt("price");
                Boolean fechado = (Boolean) obj.opt("is_closed");
                double classificacao = (double) obj.opt("rating");
                double distancia = (double) obj.opt("distance");
                String local = endereco.opt("address1") + ", " + endereco.get("city") + " - " + endereco.get("zip_code");
                String telefone = (String) obj.opt("display_phone");
                String imagem = (String) obj.opt("image_url");
                int avaliacoes = (int) obj.opt("review_count");

                Restaurante restaurante = new Restaurante(id, nome, precoMedio, fechado, classificacao, distancia, local, telefone, imagem, avaliacoes);
                restaurantes.add(restaurante);
            }
            exibir(view, restaurantes);
            conexao.disconnect();
        } else {
            List<Restaurante> restaurantes = new ArrayList<>();
            String urlString = "https://api.yelp.com/v3/businesses/search" + "?term=" + URLEncoder.encode(txtPesquisa.getText().toString(), "UTF-8") + "&latitude=" + latitude + "&longitude=" + longitude;
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Authorization", "Bearer" + " " + key);
            conexao.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String jsonString = br.readLine();
            JSONObject json = new JSONObject(jsonString);
            JSONArray conteudo = json.getJSONArray("businesses");

            for (int i = 0; i < conteudo.length(); i++) {
                JSONObject obj = conteudo.getJSONObject(i);
                JSONObject endereco = obj.getJSONObject("location");

                String id = (String) obj.opt("id");
                String nome = (String) obj.opt("name");
                String precoMedio = (String) obj.opt("price");
                Boolean fechado = (Boolean) obj.opt("is_closed");
                double classificacao = (double) obj.opt("rating");
                double distancia = (double) obj.opt("distance");
                String local = endereco.opt("address1") + ", " + endereco.get("city") + " - " + endereco.get("zip_code");
                String telefone = (String) obj.opt("display_phone");
                String imagem = (String) obj.opt("image_url");
                int avaliacoes = (int) obj.opt("review_count");

                Restaurante restaurante = new Restaurante(id, nome, precoMedio, fechado, classificacao, distancia, local, telefone, imagem, avaliacoes);
                restaurantes.add(restaurante);
            }
            exibir(view, restaurantes);
            conexao.disconnect();
        }
    }

    public void pegarLocalizacao() {
        int fineLocationCode = 1;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, fineLocationCode);
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location local = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        this.latitude = local.getLatitude();
        this.longitude = local.getLongitude();
    }

    public void exibir(View view, List<Restaurante> restaurantesEncontrados) {
        if (restaurantesEncontrados.size() == 0) {
			Toast.makeText(getApplicationContext(), "Nenhum restaurante econtrado.", Toast.LENGTH_LONG).show();
		} else {
			Intent intent = new Intent(view.getContext(), PesquisaActivity.class);
			intent.putExtra("restaurantesEncontrados", (Serializable) restaurantesEncontrados);
			startActivity(intent);
		}
    }

    private void ocultarTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
