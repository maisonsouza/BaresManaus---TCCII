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

public class Formulario_Cadastro_Usuario extends AppCompatActivity{

    private Spinner spinner;
    private Usuarios usuario;
    private UsuarioHelper helper;
    private DBHelper dbhelper;
    private String caminhoFoto;
    private String tipo_selecionado;
    public static final int CODIGO_CAMERA = 12;
    public static final int SELECT_PICTURE = 1234;
    private FloatingActionButton botao_adicionar_foto;
    private Button botao_Alterar_Cadastro;
    private Button botao_Cadastrar_Usuario;
    private FloatingActionButton botao_flutuante_escolher_galeria;
    private FloatingActionButton botao_flutuante_tirar_foto;

    private String selecionado;
    private int ultima_posicao;
    private String padrao;
    public static int posicao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);


        dbhelper = new DBHelper(this);
        helper = new UsuarioHelper(this, this);
        Intent intent = getIntent();
        usuario = (Usuarios) intent.getSerializableExtra("usuario");

        //Carregar os componentes da View
        botao_flutuante_tirar_foto = (FloatingActionButton) findViewById(R.id.fltng_actn_bttn_tirar_foto);
        botao_flutuante_escolher_galeria = (FloatingActionButton) findViewById(R.id.fltng_actn_btton_galeria);
        botao_Alterar_Cadastro = (Button) findViewById(R.id.bttn_alterar_usuario);
        botao_Cadastrar_Usuario = (Button) findViewById(R.id.bttn_cadastrar_usuario);
        spinner = (Spinner) findViewById(R.id.spnnr_seletor_tipo_usuario);
        //verifica se veio de alterar usuario
        if (usuario != null) {
            usuario = helper.preencheFormulario(usuario);
            botao_Alterar_Cadastro.setVisibility(View.VISIBLE);
            botao_Cadastrar_Usuario.setVisibility(View.INVISIBLE);
            botao_Alterar_Cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UsuarioDAO dao = new UsuarioDAO(dbhelper);
                    usuario = helper.pegaUsuario();
                    posicao = spinner.getSelectedItemPosition();
                    dao.altera(usuario);
                    dbhelper.close();
                    Toast.makeText(Formulario_Cadastro_Usuario.this, usuario.getTipo_de_usuario() + "  " + usuario.getNome() + " Alterado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent vai_pros_Cadastradados = new Intent(Formulario_Cadastro_Usuario.this, Cadastros.class);
                    startActivity(vai_pros_Cadastradados);
                }
            });
        }


        botao_flutuante_tirar_foto.setOnClickListener(new View.OnClickListener() {
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


        botao_flutuante_escolher_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vai_para_galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(vai_para_galeria, SELECT_PICTURE);
            }
        });


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.xml_tipos_usuarios, android.R.layout.simple_spinner_item);
        spinner.setPrompt("Escolha um tipo");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        botao_Cadastrar_Usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(dbhelper);
                UsuarioHelper helper = new UsuarioHelper(Formulario_Cadastro_Usuario.this, Formulario_Cadastro_Usuario.this);
                Usuarios usuario = helper.pegaUsuario();

                posicao = spinner.getSelectedItemPosition();
                selecionado = spinner.getSelectedItem().toString();
                usuario.setTipo_de_usuario(selecionado);

                dao.insere(usuario);
                Toast.makeText(Formulario_Cadastro_Usuario.this, usuario.getTipo_de_usuario() + " " + usuario.getNome() + " cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent vaipraUsuariosCadastrados = new Intent(Formulario_Cadastro_Usuario.this, Cadastros.class);
                startActivity(vaipraUsuariosCadastrados);
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
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == SELECT_PICTURE) {
                Uri imagem_selecionada_na_galeria = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imagem_selecionada_na_galeria, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                caminhoFoto = cursor.getString(columnIndex);
                cursor.close();
                helper.carregaImagem(caminhoFoto);
            }
        }
    }

    @Override
    protected void onResume() {
        spinner.setSelection(posicao);
        super.onResume();
    }
}
