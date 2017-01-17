package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
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
    private ListView listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarioscadastrados);

        carregaLista();

        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Usuarios usuario = (Usuarios) lista.getItemAtPosition(position);
                Intent vaiproFormulario = new Intent(Cadastros.this, Formulario_Cadastro_Usuario.class);
                vaiproFormulario.putExtra("usuario", usuario);
                startActivity(vaiproFormulario);
            }
        });

        FloatingActionButton botaoflutuanteCadastrar = (FloatingActionButton) findViewById(R.id.botaoFlutuante_adicionar);
        botaoflutuanteCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiproFormularioCadastroUsuario = new Intent(Cadastros.this, Formulario_Cadastro_Usuario.class);
                startActivity(vaiproFormularioCadastroUsuario);

            }
        });

        registerForContextMenu(listagem);

    }

    private void carregaLista() {
        listagem = (ListView) findViewById(R.id.lista_cadastrados);
        dbhelper = new DBHelper(this);
        UsuarioDAO dao = new UsuarioDAO(dbhelper);
        List<Usuarios> usuarios = dao.buscaUsuarios();
        dbhelper.close();
        //CircleImageView imagem_circular= (CircleImageView) findViewById(R.id.imagem_perfil);
        //lista = (ListView) findViewById(R.id.lista_cadastrados);
        //ArrayAdapter<Usuarios> adapter = new ArrayAdapter<Usuarios>(this,R.layout.foto_circular,usuarios);
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
                Toast.makeText(Cadastros.this, usuario.getNome() + "Excluido com sucesso ", Toast.LENGTH_SHORT).show();
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

}
