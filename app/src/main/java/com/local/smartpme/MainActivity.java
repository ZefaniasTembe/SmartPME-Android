package com.local.smartpme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.local.smartpme.databinding.ActivityMainBinding;
import com.local.smartpme.ui.Encomendas;
import com.local.smartpme.ui.ProdutoActivity;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView btnEncomenda, btnCliente, btnFornecedor, btnFinanca, btnProduto, btnAgenda;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgenda = (MaterialCardView) findViewById(R.id.btnAgendaMain);
        btnEncomenda = (MaterialCardView) findViewById(R.id.btnEncomendasMain);
        btnCliente = (MaterialCardView) findViewById(R.id.btnEncomendasMain);
        btnFornecedor = (MaterialCardView) findViewById(R.id.btnFornecedoresMain);
        btnFinanca = (MaterialCardView) findViewById(R.id.btnFinancasMain);
        btnProduto = (MaterialCardView) findViewById(R.id.btnProdutosMain);

        btnEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Encomendas.class));
            }
        });

        btnProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, ProdutoActivity.class));
            }
        });
    }
}