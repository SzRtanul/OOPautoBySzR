/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objektumok;

import program.*;
import control.EI;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author SzabóRoland(SZOFT_20
 */
public class Auto {
    
    // <editor-fold defaultstate="collapsed" desc="Event implmentation">
   private Set<EI.BBListener> listeners = new HashSet();
    
    public void addListener(EI.BBListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(EI.BBListener listener) {
        listeners.remove(listener);
    }

    private void broadcast(Broad sw) {
        if(listeners.stream().count() > 0){ 
            switch(sw) {
                case adatvaltozas:
                    listeners.forEach(x -> x.actionValueChanged());
                    break;
            } 
        }
    }
    
    private enum Broad{
        adatvaltozas
    }
    // </editor-fold> 
    
    private boolean motormegy;
    private int uzemanyag;
    private boolean defektes;
    private int potkerek;
    private int defektesely;
    
    public Auto(){
        this(false);
    }
    
    public Auto(boolean motormegy){
        this(motormegy, 1);
    }
    
    public Auto(boolean motormegy, int uzemanyag){
        this(motormegy, uzemanyag, false);
    }
    
    public Auto(boolean motormegy, int uzemanyag, boolean defektes){
        this(motormegy, uzemanyag, defektes, 1);
    }
    
    public Auto(boolean motormegy, int uzemanyag, boolean defektes, int potkerek){
        this.motormegy = uzemanyag > 0 ? motormegy : false;
        this.uzemanyag = uzemanyag >= 0 ? uzemanyag : 0;
        this.defektes = defektes;
        this.potkerek = potkerek >= 0 ? potkerek : 0;
        this.defektesely = 5;
        broadcast(Broad.adatvaltozas);
    }
    // <editor-fold defaultstate="collapsed" desc="control">
    public boolean megy(){
        boolean both = motormegy && uzemanyag > 0 && !defektes;
        uzemanyag -= both ? 1 : 0;
        motormegy = motormegy && uzemanyag > 0;
        defektes = both && (int)(Math.random() * 100) < defektesely; // Valamennyi eséllyel defektet kap
        broadcast(Broad.adatvaltozas);
        return both;
    }
    
    public boolean setDefektEsely(int esely){
        this.defektesely = esely >= 0 ? (esely <= 20 ? esely : 20) : 0;
        return true;
    }
    
    public boolean tankol(){
        boolean both = !motormegy;
        uzemanyag+= both ? 1 : 0;
        broadcast(Broad.adatvaltozas);
        return both;
    }
    
    public boolean motor(boolean indit){
        boolean both = uzemanyag > 0;
        motormegy = both ? indit : false;
        broadcast(Broad.adatvaltozas);
        return both;
    }
    
    public boolean kereketKiszur(){
        boolean both = !defektes;
        defektes = true;
        broadcast(Broad.adatvaltozas);
        return both;
    }
    
    public boolean kereketCserel(){
        boolean both = potkerek > 0;
        if(both){
            potkerek -= 1;
            defektes = false;
        }
        broadcast(Broad.adatvaltozas);
        return both;
    }
    
    public boolean potkereketberak(int potkerek){
        boolean both = true;
        this.potkerek += potkerek;
        broadcast(Broad.adatvaltozas);
        return true;
    }
    // </editor-fold>
    
    public boolean getMegye(){
        return motormegy;
    }
    
    public int getUzemanyag(){
        return uzemanyag;
    }
    
    public boolean getDefekt(){
        return defektes;
    }
    
    public int getDefektEsely(){
        return defektesely;
    }
    
    public int getPotkerekek(){
        return potkerek;
    }
}
