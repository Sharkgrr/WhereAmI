package br.usjt.fire;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class LocalRepository {
    private LocalDao localDao;
    private LiveData<Cadastro> local;

    LocalRepository(Application application) {
        LocalRoomDatabase db = LocalRoomDatabase.getDatabase(application);
        localDao = db.localDao();
        local = localDao.getLocal();
    }
    LiveData<Cadastro> getLocal() {
        return local;
    }
    void insert(Cadastro local) {
        LocalRoomDatabase.databaseWriteExecutor.execute(() -> {
            localDao.insert(local);
        });
    }
}
