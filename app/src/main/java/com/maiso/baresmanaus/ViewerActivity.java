package com.maiso.baresmanaus;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maiso.baresmanaus.modelo.AndroidOS;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;
import com.ftinc.kit.util.Utils;
import com.ftinc.kit.widget.AspectRatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by maiso on 14/01/2017.
 */

public class ViewerActivity extends AppCompatActivity {

    public static final String EXTRA_OS = "extra_os_version";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cover)
    AspectRatioImageView mCover;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.preco)
    TextView mVersion;
    @BindView(R.id.sdk)
    TextView mSdk;
    @BindView(R.id.position)
    TextView mPosition;

    private AndroidOS mOS;
    private SlidrConfig mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        ButterKnife.bind(this);

        // Get the status bar colors to interpolate between
        int primary = getResources().getColor(R.color.primaryDark);
        int secondary = getResources().getColor(R.color.red_500);

        // Build the slidr config
        int numPositions = SlidrPosition.values().length;
        SlidrPosition position = SlidrPosition.values()[Utils.getRandom().nextInt(numPositions)];
        mPosition.setText(position.name());

        mConfig = new SlidrConfig.Builder()
                .primaryColor(primary)
                .secondaryColor(secondary)
                .position(position)
                .velocityThreshold(2400)
                .distanceThreshold(.25f)
                .edge(true)
                .touchSize(Utils.dpToPx(this, 32))
                .build();

        // Attach the Slidr Mechanism to this activity
        Slidr.attach(this, mConfig);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mOS = getIntent().getParcelableExtra(EXTRA_OS);
        if(savedInstanceState != null) mOS = savedInstanceState.getParcelable(EXTRA_OS);

        // Set layout contents
        mTitle.setText(mOS.nome_produto);
        mDescription.setText(mOS.description);
        mDate.setText(String.valueOf(mOS.year));
        mVersion.setText(mOS.preço);
        mSdk.setText(String.valueOf(mOS.sdk_int));

        // Load header image
        Glide.with(this)
                .load(mOS.image_url)
                .crossFade()
                .into(mCover);
    }

   /* @OnClick({R.id.color1, R.id.color2, R.id.color3, R.id.color4, R.id.color5})
    void onColorClicked(View v){
        int color = ((ColorDrawable)v.getBackground()).getColor();
        getWindow().setStatusBarColor(color);
        mConfig.setColorSecondary(color);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_OS, mOS);
    }
}