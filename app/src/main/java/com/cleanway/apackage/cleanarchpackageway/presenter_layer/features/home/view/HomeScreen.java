package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.adapters.HomeAdapter;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.appdetail.view.AppDetailScreen;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.presenter.HomePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity implements HomeView, HomeAdapter.HomeAdapterCallback {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private HomePresenterImpl homePresenter;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        homePresenter = new HomePresenterImpl(this, HomeScreen.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getHomeData();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getHomeData() {
        homePresenter.requestAppsData();
    }

    @Override
    public void setHomeData(List<Apps> arrdata) {
        // set data to adapter
        homeAdapter = new HomeAdapter(arrdata, this);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        homePresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onListItemClicked(Apps selectedApp) {
        AppConstant.selectedApp = selectedApp;
        Intent i = new Intent(this, AppDetailScreen.class);
        startActivity(i);
    }
}
