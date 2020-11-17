package br.usjt.fire;

import android.app.Application;
import androidx.lifecycle.LiveData;

public class LocalViewModel extends Application {

    public LocalRepository localRepository;

    public LiveData<Cadastro> local;

    public LocalViewModel (Application application) { ;

        localRepository = new LocalRepository(application);
        local = localRepository.getLocal();
    }

    public LiveData<Cadastro> getLocal() { return local; }

    public void insert(Cadastro local) { localRepository.insert(local); }

    public <T> LiveData<T> getCadastro() {
        return (LiveData<T>) local;
    }
}
