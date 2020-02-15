package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dialog.InfoDialog;
import com.example.myapplication.fragment.RecyclerViewFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivityTwo extends DaggerAppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in) void onSignInClicked() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_activity_parent, new RecyclerViewFragment(), RecyclerViewFragment.TAG)
                .addToBackStack(RecyclerViewFragment.TAG)
                .commit();

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(RecyclerViewFragment.TAG);
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }

        InfoDialog dialog = new InfoDialog();
//        dialog.show(getSupportFragmentManager(), );
    }
}
