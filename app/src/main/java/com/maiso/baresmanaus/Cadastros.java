package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maiso.baresmanaus.adapter.UsuarioAdapter;
import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.util.List;

public class Cadastros extends AppCompatActivity {

    private DBHelper dbhelper;
    private Usuarios usuario;
    private ListView listagem,listagem_pesquisados;
    private FloatingActionButton botaoflutuanteCadastrar;
    private Intent vai_pro_Formulario_cadastro;
    private SearchView pesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios_cadastrados);
        carregaLista();

        botaoflutuanteCadastrar = (FloatingActionButton) findViewById(R.id.fltng_actn_bttn_adicionar);

        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                usuario = (Usuarios) lista.getItemAtPosition(position);
                vai_pro_Formulario_cadastro = new Intent(Cadastros.this, Formulario_Cadastro_Usuario.class);
                vai_pro_Formulario_cadastro.putExtra("usuario", usuario);
                startActivity(vai_pro_Formulario_cadastro);
            }
        });


        botaoflutuanteCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formulario_Cadastro_Usuario.posicao=0;
                vai_pro_Formulario_cadastro = new Intent(Cadastros.this, Formulario_Cadastro_Usuario.class);
                startActivity(vai_pro_Formulario_cadastro);

            }
        });

        registerForContextMenu(listagem);

    }

    private void carregaLista() {
        //Prepara para consultar o banco
        listagem = (ListView) findViewById(R.id.lst_cadastrados);
        dbhelper = new DBHelper(this);
        UsuarioDAO dao = new UsuarioDAO(dbhelper);
        //Preenche a lista com os dados do banco
        List<Usuarios> usuarios = dao.buscaUsuarios();
        dbhelper.close();
        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
        listagem.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Excluir");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                usuario = (Usuarios) listagem.getItemAtPosition(info.position);
                UsuarioDAO dao = new UsuarioDAO(dbhelper);
                dao.deleta(usuario);
                dbhelper.close();
                //Encerra a conexão com o banco
                Toast.makeText(Cadastros.this, usuario.getNome() + " Excluido com sucesso ", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent vaiprosModulos = new Intent(Cadastros.this, Modulos.class);
        startActivity(vaiprosModulos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes, menu);
        MenuItem itemPesquisar =  menu.findItem(R.id.item_app_bar_pesquisar);
        MenuItemCompat.setOnActionExpandListener(itemPesquisar, new MenuItemCompat.OnActionExpandListener(){
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                carregaLista();
                return true;
            }
        });

        pesquisar = (SearchView) menu.findItem(R.id.item_app_bar_pesquisar).getActionView();
        pesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listagem = (ListView) findViewById(R.id.lst_cadastrados);
                dbhelper = new DBHelper(Cadastros.this);
                UsuarioDAO dao = new UsuarioDAO(dbhelper);
                //Preenche a lista com os dados do banco
                List<Usuarios> usuarios_encontrados = dao.buscaUsuariosPeloNome(query);
                dbhelper.close();
                UsuarioAdapter adapter = new UsuarioAdapter(Cadastros.this, usuarios_encontrados);
                listagem.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.menu.menu_opcoes:
                pesquisar.isShown();
                return true;

        }
        carregaLista();
        return super.onOptionsItemSelected(item);
    }


}
