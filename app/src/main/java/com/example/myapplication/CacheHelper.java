package com.example.myapplication;

/**
 * Singleton example
 */
public class CacheHelper {

    private static CacheHelper INSTANCE = new CacheHelper();

    private CacheHelper() {}

    public static CacheHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheHelper();
        }
        return INSTANCE;
    }
}
