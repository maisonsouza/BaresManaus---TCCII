package com.maiso.baresmanaus;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.UsuarioHelper;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.io.File;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

/**
 * Created by maiso on 22/11/2016.
 */

public class Formulario_Cadastro_Usuario extends AppCompatActivity {

    private UsuarioHelper helper;
    private String caminhoFoto;
    public static final int CODIGO_CAMERA = 12;
    private Button botaoAdicionarCadastro;
    private Usuarios usuario;
    private String tipo_selecionado;
    private Button botao_escolher_galeria;
    public static final int SELECT_PICTURE = 1234;
    private Button botao_adicionar_foto;

    private DBHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);

        dbhelper = new DBHelper(this);
        helper = new UsuarioHelper(this, this);
        Intent intent = getIntent();
        usuario = (Usuarios) intent.getSerializableExtra("usuario");

        botaoAdicionarCadastro = (Button) findViewById(R.id.botao_Cadastrar);
        if (usuario != null) {
            usuario = helper.preencheFormulario(usuario);
            Button botaoalterar = (Button) findViewById(R.id.botao_alterar);
            botaoalterar.setVisibility(View.VISIBLE);
            botaoAdicionarCadastro.setVisibility(View.INVISIBLE);
            botaoalterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UsuarioDAO dao = new UsuarioDAO(dbhelper);
                    usuario = helper.pegaUsuario();
                    dao.altera(usuario);
                    dbhelper.close();
                    Toast.makeText(Formulario_Cadastro_Usuario.this, usuario.getTipo_de_usuario() + " " + usuario.getNome() + " Alterado com sucesso", Toast.LENGTH_LONG).show();
                    Intent vaiprosCadastrados = new Intent(Formulario_Cadastro_Usuario.this, Cadastros.class);
                    startActivity(vaiprosCadastrados);
                }
            });
        }

        botao_adicionar_foto = (Button) findViewById(R.id.formulario_botao_adicionar_imagem);
        botao_adicionar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vai_pra_camera = new Intent(ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivo_foto = new File(caminhoFoto);
                Uri photoURI = FileProvider.getUriForFile(Formulario_Cadastro_Usuario.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        arquivo_foto);

                vai_pra_camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(vai_pra_camera, CODIGO_CAMERA);

            }
        });

        FloatingActionButton botao_escolher_galeria = (FloatingActionButton) findViewById(R.id.floatingActionButton_galeria);
        botao_escolher_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);

            }
        });


        botaoAdicionarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(dbhelper);
                UsuarioHelper helper = new UsuarioHelper(Formulario_Cadastro_Usuario.this, Formulario_Cadastro_Usuario.this);
                Usuarios usuario = helper.pegaUsuario();
                dao.insere(usuario);
                Toast.makeText(Formulario_Cadastro_Usuario.this, usuario.getTipo_de_usuario() + " " + usuario.getNome() + " cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent vaipraUsuariosCadastrados = new Intent(Formulario_Cadastro_Usuario.this, Cadastros.class);
                startActivity(vaipraUsuariosCadastrados);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.seletor_tipo_usuario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.xml_tipos_usuarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo_selecionado = (String) parent.getItemAtPosition(position);
                Toast.makeText(Formulario_Cadastro_Usuario.this, tipo_selecionado, Toast.LENGTH_LONG).show();
                if (usuario != null) {
                    usuario.setTipo_de_usuario(tipo_selecionado);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == CODIGO_CAMERA) {
                helper.carregaImagem(caminhoFoto);
            }
        }
        if (requestCode == SELECT_PICTURE) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            caminhoFoto = cursor.getString(columnIndex);
            cursor.close();
            helper.carregaImagem(caminhoFoto);
        }
    }


}
