package br.usjt.fire;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cadastro local);

    @Query("DELETE FROM local")
    void deleteAll();

    @Query("SELECT * from local LIMIT 1")
    LiveData<Cadastro> getLocal();
}
