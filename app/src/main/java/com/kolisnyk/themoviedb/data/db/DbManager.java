package com.kolisnyk.themoviedb.data.db;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import io.realm.Realm;
import io.realm.RealmResults;

@Singleton
public class DbManager implements DbHelper {
    private Realm realm;

    @Inject
    public DbManager(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Single<List<MovieDetail>> getFavorMovies() {
        return Single.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(MovieDetail.class).findAll());
            }
        });
    }

    @Override
    public void insert(MovieDetail movieDetail) {
        realm.executeTransactionAsync(realm -> realm.insertOrUpdate(movieDetail));
    }

    @Override
    public void delete(int id) {
        realm.executeTransactionAsync(realm -> {
            RealmResults<MovieDetail> result = realm.where(MovieDetail.class).equalTo("id", id).findAll();
            result.deleteAllFromRealm();
        });
    }

    @Override
    public Single<MovieDetail> getByID(int id) {
        return Single.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(MovieDetail.class).equalTo("id", id).findAll().first());
            }
        });
    }
}
