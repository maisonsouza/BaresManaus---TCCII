package com.maiso.baresmanaus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.maiso.baresmanaus.adapter.TransformerAdapter;

import java.util.HashMap;


public class Anuncios extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    Integer [] anuncios = {R.drawable.anuncio_1,R.drawable.anuncio_2,R.drawable.anuncio_3,R.drawable.anuncio_4};
    private SliderLayout meuSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        meuSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Buffalo", "https://comunicamanaus.files.wordpress.com/2010/09/sinalizacao-churrascaria-bufalo.jpg");
        url_maps.put("Rei do Churrasco", "http://static-dms.guiamais.com.br/dms/97/56/4/1045697.png");
        url_maps.put("Canto da Peixada", "http://ampost.com.br/wp-content/uploads/2016/05/CANTO-DA-PEIXADA_DIVULGACAO-Copy.jpg");
        url_maps.put("Cachaçaria do Dedé e Emporio ", "https://media-cdn.tripadvisor.com/media/photo-s/04/47/26/9d/cachacaria-do-dede-e.jpg");
        url_maps.put("Ki-Tempero", "https://media-cdn.tripadvisor.com/media/photo-s/07/52/56/b2/restaurante-ki-tempero.jpg");
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.anuncio_1);
        file_maps.put("Big Bang Theory",R.drawable.anuncio_2);
        file_maps.put("House of Cards",R.drawable.anuncio_3);
        file_maps.put("Game of Thrones", R.drawable.anuncio_4);

        meuSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        meuSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        meuSlider.setCustomAnimation(new DescriptionAnimation());
        meuSlider.setDuration(4000);
        meuSlider.addOnPageChangeListener(this);
       /* ListView l = (ListView)findViewById(R.id.transformers);
        l.setAdapter(new TransformerAdapter(this));

       l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meuSlider.setPresetTransformer(((TextView) view).getText().toString());
                Toast.makeText(Anuncios.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            meuSlider.addSlider(textSliderView);
        }

    }

    @Override
    public void onSliderClick(BaseSliderView slider)    {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
