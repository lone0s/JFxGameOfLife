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

public class Cell {
    
    private boolean state;
    private Parameter parameter;
    public int x;
    public int y;


    /**
     *
     * @param b <-- State
     * @param p <-- InGame Parameters for the Cells
     * @param x <-- Position on X axis
     * @param y <-- Position on Y axis
     */
    public Cell(boolean b, Parameter p, int x, int y ) {
        this.state = b;
        this.parameter = p;
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @return l'état de la cellule ( true : vivante ) ( false : morte ) 
     */
    public boolean getState() {
        return this.state;
    }
    
    /**
     * 
     * @param b l'etat de la cellule ( true : vivante ) ( false : morte )
     */
    public void setState(boolean b) {
        this.state = b;
    }


    /**
     * @return les reglès selons lesquelles un cellule vie ou meurt 
     */
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter newParameter) {
        parameter = newParameter;
    }

}
