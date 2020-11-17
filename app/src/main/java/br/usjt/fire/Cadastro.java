package br.usjt.fire;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "local")
public class Cadastro{

    @PrimaryKey(autoGenerate = true)
    int id;

    String nome;
    String latitude;
    String longetude;
    String descricao;

    public Cadastro() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongetude(String longetude) {
        this.longetude = longetude;
    }

    public String getLongetude() {
        return longetude;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Cadastro(int id, String nome, String latitude, String longetude, String descricao) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longetude = longetude;
        this.descricao = descricao;
    }

}

