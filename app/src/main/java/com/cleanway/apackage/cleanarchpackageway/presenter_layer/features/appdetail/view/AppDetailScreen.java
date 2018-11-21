package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.appdetail.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.appdetail.presenter.AppDetailPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailScreen extends AppCompatActivity implements AppDetailView {

    @BindView(R.id.appname)
    TextView appName;
    @BindView(R.id.device)
    TextView activeDevice;
    @BindView(R.id.impression)
    TextView impression;
    @BindView(R.id.sale)
    TextView sales;
    @BindView(R.id.unit)
    TextView appUnits;
    @BindView(R.id.crash)
    TextView crashes;
    @BindView(R.id.session)
    TextView sessions;
    private AppDetailPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail_screen);
        ButterKnife.bind(this);
        presenter = new AppDetailPresenterImpl(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override
    public void bindData(Apps apps) {
        appName.setText(apps.getName());
        activeDevice.setText(apps.getActive_devices());
        impression.setText(apps.getImpressions());
        sales.setText(apps.getSales());
        appUnits.setText(apps.getApp_units());
        crashes.setText(apps.getCrashes());
        sessions.setText(apps.getSessions());
    }

    @Override
    public void requestData() {
        presenter.getAppData();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
