/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modele;

/**
 *
 * @author Lucas
 * @author Souhaïl
 */
public class Parameter {

    /**
     * @param mortSolitude indiquant que si une cellule a moins de x voisines, elle meurt
     * @param mortAsphyxie indiquant que si une cellule plus de x voisines, elle meurt
     * @param vieMin indiquant le nombre de voisines minimum nécessaires
     * à la création d'une cellule vivante
     * @param vieMax indiquant le nombre de voisines maximum nécessaires
     * à la création d'une cellule vivante
     *
     */
    private int mortSolitude;
    private int mortAsphyxie;
    private int vieMin;
    private int vieMax;
    
    

    
    public Parameter(){
        this.mortSolitude = 1;
        this.mortAsphyxie =  4;
        this.vieMin = 3;
        this.vieMax = 3;
    }


    public Parameter(int mortSolitudeValue, int mortAsphyxieValue, int vieMinValue, int vieMaxValue) {
        this.mortSolitude = mortSolitudeValue;
        this.mortAsphyxie = mortAsphyxieValue;
        this.vieMin = vieMinValue;
        this.vieMax = vieMaxValue;
    }
    /**
     * @return the mortSolitude
     */
    public int getMortSolitude() {
        return mortSolitude;
    }

    /**
     * @param mortSolitude the mortSolitude to set
     */
    public void setMortSolitude(int mortSolitude) {
        this.mortSolitude = mortSolitude;
    }

    /**
     * @return the mortAsphyxie
     */
    public int getMortAsphyxie() {
        return mortAsphyxie;
    }

    /**
     * @param mortAsphyxie the mortAsphyxie to set
     */
    public void setMortAsphyxie(int mortAsphyxie) {
        this.mortAsphyxie = mortAsphyxie;
    }

    /**
     * @return the vieMin
     */
    public int getVieMin() {
        return vieMin;
    }

    /**
     * @param vieMin the vieMin to set
     */
    public void setVieMin(int vieMin) {
        this.vieMin = vieMin;
    }

    /**
     * @return the vieMax
     */
    public int getVieMax() {
        return vieMax;
    }

    /**
     * @param vieMax the vieMax to set
     */
    public void setVieMax(int vieMax) {
        this.vieMax = vieMax;
    }
    
    
    
}
