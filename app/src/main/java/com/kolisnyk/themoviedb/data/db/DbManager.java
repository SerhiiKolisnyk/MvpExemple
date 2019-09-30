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

//        Single.create<List<MovieDetail>> { emitter ->
//                Realm.getDefaultInstance().use { realm ->
//                val results = realm.where(RealmSuggestedFriends::class.java).findAll()
//            emitter.onSuccess(realm.copyFromRealm(results))
//        }
//        }.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { results ->
//            for(firstResults in results) {
//                val requestPojo = RequestPojo()
//                requestPojo.email = firstResults.friendEmail
//                requestPojo.image = firstResults.friendImage
//                requestPojo.name = firstResults.friendName
//                requestPojo.status = firstResults.friendStatus
//                requestPojo.thumb_image = firstResults.friendThumbImage
//                requestPojo.uid = firstResults.friendUid
//            }
//        }.subscribe({
//                list: List<RequestPojo> ->  userAdapter.updateData(list)
// },{
//            t: Throwable? ->
//        },{
//        })
//        return null;
    }

    @Override
    public void insert(MovieDetail movieDetail) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(movieDetail);
            }
        });
    }

    @Override
    public void delete(int id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MovieDetail> result = realm.where(MovieDetail.class).equalTo("id", id).findAll();
                result.deleteAllFromRealm();
            }
        });
    }

    public Single<MovieDetail> getByID(int id) {
        return Single.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(MovieDetail.class).equalTo("id", id).findAll().first());
            }
        });
    }
}
