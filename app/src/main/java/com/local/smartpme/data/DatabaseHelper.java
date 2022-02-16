package com.local.smartpme.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.local.smartpme.model.Encomenda;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "smartpme.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE_NAME = "user";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_USERNAME = "username";
    private static final String USER_COLUMN_PASSWORD = "password";

    private static final String PRODUTO_TABLE_NAME = "produto";
    private static final String PRODUTO_COLUMN_ID = "id";
    private static final String PRODUTO_COLUMN_DESCRICAO = "descricao";
    private static final String PRODUTO_COLUMN_VALIDADE = "validade";
    private static final String PRODUTO_COLUMN_PRECO = "preco";
    private static final String PRODUTO_COLUMN_QUANTIDADE = "quantidade";
    private static final String PRODUTO_COLUMN_CATEGORIA = "categoria";

    private static final String CATEGORIA_TABLE_NAME = "categoria";
    private static final String CATEGORIA_COLUMN_ID = "id";
    private static final String CATEGORIA_COLUMN_DESCRICAO = "descricao";


    private static final String CLIENTE_PRODUTO_TABLE_NAME= "cliente";
    private static final String CLIENTE_COLUMN_ID = "id";
    private static final String CLIENTE_COLUMN_NOME = "nome";
    private static final String CLIENTE_COLUMN_APELIDO = "apelido";
    private static final String CLIENTE_COLUMN_CLASSIFICACAO = "status";

    private static final String LOCALIZACAO_TABLE_NAME = "localizacao";
    private static final String LOCALIZACAO_COLUMN_ID= "id";
    private static final String LOCALIZACAO_COLUMN_NAME= "local";
    private static final String LOCALIZACAO_COLUMN_CLIENTE= "cliente_id";

    private static final String CONTACTO_TABLE_NAME = "contacto";
    private static final String CONTACTO_COLUMN_ID= "id";
    private static final String CONTACTO_COLUMN_CELULAR= "cell";
    private static final String CONTACTO_COLUMN_EMAIL= "cell";
    private static final String CONTACTO_COLUMN_CLIENTE= "cliente_id";


    private static final String ENCOMENDA_TABLE_NAME = "encomenda";
    private static final String ENCOMENDA_COLUMN_ID = "id";
    private static final String ENCOMENDA_COLUMN_DIA_MARCACAO = "dia_marcacao";
    private static final String ENCOMENDA_COLUMN_DIA_ENTREGA= "dia_entrega";
    private static final String ENCOMENDA_COLUMN_ESTADO_PAGAMENTO = "estado_pagamento";
    private static final String ENCOMENDA_COLUMN_HORA_ENTREGA = "hora_entrega";
    private static final String ENCOMENDA_COLUMN_VALOR_TOTAL = "total";
    private static final String ENCOMENDA_COLUMN_ADIANTAMENTO = "valor_adiantado";
    private static final String ENCOMENDA_COLUMN_EM_FALTA = "valor_em_falta";
    private static final String ENCOMENDA_COLUMN_CLIENTE = "cliente_id";

    private static final String ENCOMENDA_HAS_PRODUTO_TABLE_NAME = "encomenda_has_produto";
    private static final String ENCOMENDA_HAS_PRODUTO_COLUMN_ID = "id";
    private static final String ENCOMENDA_HAS_PRODUTO_COLUMN_ENCOMENDA = "encomenda";
    private static final String ENCOMENDA_HAS_PRODUTO_COLUMN_PRODUTO= "produto";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabelaCategoria = "create table if not exists "+CATEGORIA_TABLE_NAME+" ( " +
               CATEGORIA_COLUMN_ID +"INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                CATEGORIA_COLUMN_DESCRICAO+" TEXT NOT NULL UNIQUE );";

        String criarTabelaProduto = "create table if not exists "+PRODUTO_TABLE_NAME+" ("+
                PRODUTO_COLUMN_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PRODUTO_COLUMN_DESCRICAO+" text not null,"+
                PRODUTO_COLUMN_PRECO+" double not null,"+
                PRODUTO_COLUMN_QUANTIDADE+ " double not null, "+
                PRODUTO_COLUMN_VALIDADE+" date, "+
                PRODUTO_COLUMN_CATEGORIA+" integer," +
                " FOREIGN KEY ("+PRODUTO_COLUMN_CATEGORIA+ ") references "+ CATEGORIA_TABLE_NAME +"("+CATEGORIA_COLUMN_ID+") "+
                ");";

        String criarTabelaUser = "create table if not exists "+ USER_TABLE_NAME+" (" +
                USER_COLUMN_ID+" integer not null primary key autoincrement, "+
                USER_COLUMN_PASSWORD+" text not null, "+
                USER_COLUMN_USERNAME+ " text not null unique"+
                ");";

        String criarTabelaLocalizacao = "create table if not exists "+LOCALIZACAO_TABLE_NAME+"(" +
                LOCALIZACAO_COLUMN_ID+" integer not null primary key,"+
                LOCALIZACAO_COLUMN_NAME +" text not null, "+
                LOCALIZACAO_COLUMN_CLIENTE+"integer not null, "+
                " FOREIGN KEY ("+LOCALIZACAO_COLUMN_CLIENTE+ ") references "+ CLIENTE_PRODUTO_TABLE_NAME +"("+CLIENTE_COLUMN_ID+") "+
                ");";

        String criarTabelaCliente = "create table if not exists "+CLIENTE_PRODUTO_TABLE_NAME+"(" +
                CLIENTE_COLUMN_ID+" integer not null primary key,"+
                CLIENTE_COLUMN_NOME +" text not null, "+
                CLIENTE_COLUMN_APELIDO+" text, "+
                CLIENTE_COLUMN_CLASSIFICACAO+"text"+
                ");";
        String criarTabelaContacto = "create table if not exists "+CONTACTO_TABLE_NAME+"(" +
                CONTACTO_COLUMN_ID+" integer not null primary key,"+
                CONTACTO_COLUMN_CELULAR +" text not null, "+
                CONTACTO_COLUMN_EMAIL+" text, "+
                CONTACTO_COLUMN_CLIENTE+"integer not null, "+
                " FOREIGN KEY ("+CONTACTO_COLUMN_CLIENTE+ ") references "+ CLIENTE_PRODUTO_TABLE_NAME +"("+CLIENTE_COLUMN_ID+") "+
                ");";

        String criarTabelaEncomenda = "create table if not exists "+ENCOMENDA_TABLE_NAME+"(" +
                ENCOMENDA_COLUMN_ID+" integer not null primary key,"+
                ENCOMENDA_COLUMN_DIA_ENTREGA +" date not null, "+
                ENCOMENDA_COLUMN_DIA_MARCACAO +" date not null, "+
                ENCOMENDA_COLUMN_ESTADO_PAGAMENTO +" boolean not null, "+
                ENCOMENDA_COLUMN_VALOR_TOTAL +" double not null, "+
                ENCOMENDA_COLUMN_ADIANTAMENTO +" double, "+
                ENCOMENDA_COLUMN_EM_FALTA+" double, "+
                ENCOMENDA_COLUMN_HORA_ENTREGA +" text, "+
                ENCOMENDA_COLUMN_CLIENTE+"integer not null, "+
                " FOREIGN KEY ("+ENCOMENDA_COLUMN_CLIENTE+ ") references "+ CLIENTE_PRODUTO_TABLE_NAME +"("+CLIENTE_COLUMN_ID+") "+
                ");";

        String criarTabelaEncomendaHasProds = "create table if not exists "+ENCOMENDA_HAS_PRODUTO_TABLE_NAME+"(" +
                ENCOMENDA_HAS_PRODUTO_COLUMN_ID+" integer not null primary key,"+
                ENCOMENDA_HAS_PRODUTO_COLUMN_ENCOMENDA +" integer not null, "+
                ENCOMENDA_HAS_PRODUTO_COLUMN_PRODUTO+" integer not null, "+
                " FOREIGN KEY ("+ENCOMENDA_HAS_PRODUTO_COLUMN_ENCOMENDA+ ") references "+ ENCOMENDA_TABLE_NAME +"("+ENCOMENDA_COLUMN_ID+"), "+
                " FOREIGN KEY ("+ENCOMENDA_HAS_PRODUTO_COLUMN_PRODUTO+ ") references "+ PRODUTO_TABLE_NAME +"("+PRODUTO_COLUMN_ID+") "+
                ");";


        db.execSQL(criarTabelaCategoria);
        db.execSQL(criarTabelaProduto);
        db.execSQL(criarTabelaUser);
        db.execSQL(criarTabelaCliente);
        db.execSQL(criarTabelaLocalizacao);
        db.execSQL(criarTabelaContacto);
        db.execSQL(criarTabelaEncomenda);
        db.execSQL(criarTabelaEncomendaHasProds);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Retorna as encomendas nao enviadas ao servidor
     */
    public ArrayList<Encomenda> getNotSyncedEncomenda(){
        Cursor cursor = null;
        ArrayList<Encomenda> lista = new ArrayList<>();

        return  lista;
    }

    public void updateEncomenda(Encomenda e){
        // TODO
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("COLUMN_TITLE","");

        long result = db.update("nome da tabela", cv, "_id=?", new String[]{""+ e.getId()});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id){
        // TODO
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("TABLE_NAME", "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
