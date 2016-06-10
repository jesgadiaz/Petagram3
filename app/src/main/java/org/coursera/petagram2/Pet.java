package org.coursera.petagram2;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class Pet {

    private int id;
    private int foto;
    private String name;
    private int rate;

    public Pet(){

    }

    public Pet(int id,int foto, String name, int rate){
        this.id = id;
        this.foto = foto;
        this.name = name;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }



}
