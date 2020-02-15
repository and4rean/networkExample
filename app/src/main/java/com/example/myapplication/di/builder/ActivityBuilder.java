package com.example.myapplication.di.builder;

import com.example.myapplication.activity.MainActivityTwo;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector abstract MainActivityTwo bindMainActivityTwo();
}
