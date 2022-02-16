package com.local.smartpme.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.local.smartpme.R;
import com.local.smartpme.model.Encomenda;

import java.util.ArrayList;
import java.util.Date;

public class Encomendas extends AppCompatActivity {

    private EditText diaEntrega,nomeCliente, produto, quantidade, localEntrega, preco;
    private Button salvar;
    private MaterialCardView button;
    private AlertDialog dialog, dialog2;
    private ArrayList<Encomenda> encoList;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomendas);

        button = (MaterialCardView) findViewById(R.id.btnNovaEncomenda);

        encoList = new ArrayList<>();

        fillList();

        AdapterList adapterList = new AdapterList(this, encoList);

        listView = (ListView)findViewById(R.id.listview_encomenda);
        listView.setAdapter(adapterList);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registar Encomenda");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_encomenda, null);
        diaEntrega = view.findViewById(R.id.diaEntrega);
        nomeCliente = view.findViewById(R.id.nomeCliente);
        produto = view.findViewById(R.id.produto);
        quantidade = view.findViewById(R.id.quantidade);
        preco = view.findViewById(R.id.precoUnitario);
        salvar = (Button) view.findViewById(R.id.salvarEncomenda);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Preco: "+preco.getText(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    // preenchendo a lista de encomenda


    public void fillList(){
        Encomenda e = new Encomenda();
        e.setClienteId("Zefanias Tembe");
        e.setDiaEntrega(new Date());
        e.setPrecoUntario(30.5);
        e.setQuantidadeProduto(22.9);
        e.setLocalEntrega("Marracuene");
        e.setProdutoId("Chamussas");
        encoList.add(e);

        Encomenda e1 = new Encomenda();
        e1.setClienteId("Rodson Tembe");
        e1.setDiaEntrega(new Date());
        e1.setPrecoUntario(30.5);
        e1.setQuantidadeProduto(22.9);
        e1.setLocalEntrega("Marracuene");
        e1.setProdutoId("Batata-Frita");
        encoList.add(e1);

        Encomenda e2 = new Encomenda();
        e2.setClienteId("Calidia Tembe");
        e2.setDiaEntrega(new Date());
        e2.setPrecoUntario(30.5);
        e2.setQuantidadeProduto(22.9);
        e2.setLocalEntrega("Nhankuntsi");
        e2.setProdutoId("Gulabos");
        encoList.add(e2);

        Encomenda e3 = new Encomenda();
        e3.setClienteId("Laura Tembe");
        e3.setDiaEntrega(new Date());
        e3.setPrecoUntario(30.5);
        e3.setQuantidadeProduto(22.9);
        e3.setLocalEntrega("Agostinho neto");
        e3.setProdutoId("Maguinha");
        encoList.add(e3);

    }


    private class AdapterList extends BaseAdapter{
        private final Context context;
        private final LayoutInflater inflater;
        private final ArrayList<Encomenda> encomendaArrayList;
        private MaterialCardView btnConfirmar, btnCancelar, btnArquivar;

        public AdapterList(Context context, ArrayList<Encomenda> encomendaArrayList) {
            this.context = context;

           // this.inflater = LayoutInflater.from(getApplicationContext());
            this.inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            this.encomendaArrayList = encomendaArrayList;
        }

        @Override
        public int getCount() {
            return encomendaArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return encomendaArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view2, ViewGroup viewGroup) {

            view2 = inflater.inflate(R.layout.model_encomenda_item_list, null);

            TextView nomeCliente = view2.findViewById(R.id.txtNomeClienteList);
            TextView produto = view2.findViewById(R.id.txtProdutoList);
            TextView quantidade = view2.findViewById(R.id.txtQuantidadeProdutoPList);
            TextView precoUnitario = view2.findViewById(R.id.txtPrecoTotalList);
            TextView localEntrega = view2.findViewById(R.id.txtLocalEntregaList);
            TextView diaEntrega = view2.findViewById(R.id.txtDiaEntregaList);


            nomeCliente.setText(encomendaArrayList.get(i).getClienteId());
            produto.setText(encomendaArrayList.get(i).getProdutoId());
            quantidade.setText(Double.toString(encomendaArrayList.get(i).getQuantidadeProduto()));
            precoUnitario.setText( Double.toString(encomendaArrayList.get(i).getQuantidadeProduto() * encomendaArrayList.get(i).getPrecoUntario()) );
            localEntrega.setText(encomendaArrayList.get(i).getLocalEntrega());
            //diaEntrega.setText(encomendaArrayList.get(i).getDiaEntrega().toString());

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle("Status da Encomenda ");
            View alertView = getLayoutInflater().inflate(R.layout.model_encomenda_click, null);
            btnConfirmar = (MaterialCardView) alertView.findViewById(R.id.btnConfirmarPagamento);
            btnCancelar = (MaterialCardView) alertView.findViewById(R.id.btnAnularPagamento);
            btnArquivar = (MaterialCardView) alertView.findViewById(R.id.btnArquivarPagamento);

            btnArquivar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Arquivado Com sucesso", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                }
            });
            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Confirmado Com sucesso", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                }
            });
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Cancelado Com sucesso", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                }
            });

            builder1.setView(alertView);
            dialog2 = builder1.create();

            view2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.show();
                    }
                }
            );

            return view2;
        }
    }
}