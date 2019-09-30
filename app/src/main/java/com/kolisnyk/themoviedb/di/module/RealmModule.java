package com.kolisnyk.themoviedb.di.module;

import android.content.Context;

import com.kolisnyk.themoviedb.data.db.DbHelper;
import com.kolisnyk.themoviedb.data.db.DbManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class RealmModule {
    public RealmModule(Context context){
        Realm.init(context);
    }

    @Provides
    @Singleton
    Realm provideRealm(RealmConfiguration realmConfiguration) {
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            realm = Realm.getInstance(realmConfiguration);
        }
        return realm;
    }

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration() {
        // Get a Realm instance for this thread
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return config;
    }
    @Provides
    @Singleton
    DbHelper provideDbHelper(DbManager restApiManager) {
        return restApiManager;
    }

}
