/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Firas
 */
public class Cour {
    
    private int id;
    private String nom;
    private String dated;
    private String datef;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Cour(int id, String nom, String dated, String datef, String type) {
        this.id = id;
        this.nom = nom;
        this.dated = dated;
        this.datef = datef;
        this.type = type;
    }

    public Cour() {
    }

    @Override
    public String toString() {
        return "Cour{" + "id=" + id + ", nom=" + nom + ", dated=" + dated + ", datef=" + datef + ", type=" + type + '}';
    }
  
    
}
