package com.example.myapplication.di.module;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.fragment.RecyclerViewFragment;
import com.example.myapplication.viewmodel.MyViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecyclerViewModule {

    @ContributesAndroidInjector abstract RecyclerViewFragment bindRecyclerViewFragment();

    @Provides static DatabaseHelper providesDatabaseHelper() {
        return new DatabaseHelper();
    }

    @Provides static MyViewModelFactory providesMyViewModelFactory() {
        return new MyViewModelFactory();
    }
}
