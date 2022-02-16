package com.local.smartpme.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.local.smartpme.model.Encomenda;
import com.local.smartpme.model.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class APIConnection {
    private Context context;
    private ProgressDialog progress;
    private ArrayList<Encomenda>  encomendaArrayList;
    private ArrayList<Produto> produtoArrayList;
    private JSONArray jsonArray;

    public APIConnection(Context context){
        this.progress = new ProgressDialog(context);
        this.context = context;
    }


    private void postRequest(final HashMap jsonToSend, String temp) throws JSONException {
        progress.setMessage("Enviando Dados");
        progress.setTitle("Aguarde: ");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url="http://192.168.43.96:8080/api/"+temp;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonToSend),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            progress.dismiss();
                            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(progress.isShowing()){
                    progress.dismiss();
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestQueue.add(request);
    }

    private JSONArray getRequest(String temp) {
        this.progress.setMessage("Descarregando Dados");
        this.progress.setTitle("Aguarde: ");
        this.progress.setCanceledOnTouchOutside(false);
        this.progress.show();
        RequestQueue queue= Volley.newRequestQueue(context);
        String url="http://192.168.43.96:8080/api/"+temp;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    if(progress.isShowing()){
                        progress.dismiss();
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonArray = null;
                if(progress.isShowing()){
                    progress.dismiss();
                }
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
        return jsonArray;
    }

    public ArrayList<Encomenda> getEncomendaArrayList() {

        return encomendaArrayList;
    }

    public void setEncomendaArrayList() {
        if (getRequest("encomenda").length()!=0 && getRequest("encomenda")!=null){
            for (int i = 0; i < getRequest("encomenda").length(); i++) {
                Encomenda encomenda = new Encomenda();

                this.encomendaArrayList.add(encomenda);
            }
        }
    }

    public ArrayList<Produto> getProdutoArrayList() {

        return produtoArrayList;
    }

    public void setProdutoArrayList(ArrayList<Produto> produtoArrayList) {
        this.produtoArrayList = produtoArrayList;
    }

    public  void preencher(){
        // TODO buscar dados na base de dados que ainda nao foram ao servidor e colocalos na lista


        for (Encomenda e : encomendaArrayList) {
            try{
                HashMap dados = new HashMap();

                dados.put("cliente",e.getClienteId());
                dados.put("diaEntrega",e.getDiaEntrega());
                dados.put("diaMarcacao",e.getDiaMarcacao());
                dados.put("localEntrega",e.getLocalEntrega());
                dados.put("numProdutos",e.getQuantidadeProduto());

                postRequest(dados,"encomenda");
                // TODO mudar o estado na base de dados na variavel de sync

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


}
