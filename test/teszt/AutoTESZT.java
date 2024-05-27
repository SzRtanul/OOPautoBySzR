package teszt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import objektumok.*;
import program.AuxiliaryFunctions;

/**
 *
 * @author SzabóRoland(SZOFT_20
 */
public class AutoTESZT {
    static Auto k = new Auto(false, 2, false, 5);
    
    static TesztObjektum[] tesztesetek = new TesztObjektum[]{
        // Motor nélkül
        new TesztObjektum(3, false), // Az autó megpróbál elindulni
       new TesztObjektum(2, true), // tankol
        // Defekt
       new TesztObjektum(1, true), // Motrot indít
       new TesztObjektum(4, true), // Kereket kiszúr
       new TesztObjektum(3, false), // Megy
       //5
       new TesztObjektum(3, false), // Menni próbál
       new TesztObjektum(5, true), // Kereket cserél
       // Motorleállítás
       new TesztObjektum(0, true),
       new TesztObjektum(2, true),  // tankol
       new TesztObjektum(3, false), // Megy
       // Üzemanyag nélkül
       //10 
       new TesztObjektum(1, true), // Motrot indít
       new TesztObjektum(6, true), // Tét nélkül megy
       new TesztObjektum(6, true), // Tét nélkül megy
       new TesztObjektum(6, true), // Tét nélkül megy
       new TesztObjektum(6, true), // Tét nélkül megy
       //15
       new TesztObjektum(6, false), // Tét nélkül megy
    };
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte aktualeredmeny = -1;
        String muveletNev = "";
        for (int i = 0; i < tesztesetek.length; i++) {
             switch (tesztesetek[i].getMuveletszam()) {
                  case 0: // Leállít
                      muveletNev = "Motor leállítása";
                    aktualeredmeny = (byte)(k.motor(false) ? 1 : 0);
                    break;
                case 1: // Indít
                    muveletNev = "Motor indítása";
                    aktualeredmeny = (byte)(k.motor(true) ? 1 : 0);
                    break;
                case 2: // Tankol
                    muveletNev = "Tankolás";
                    aktualeredmeny = (byte)(k.tankol() ? 1 : 0);
                    break;
                case 3: // Megy
                    muveletNev = "Láb a gázon";
                    aktualeredmeny = (byte)(k.megy() ? 1 : 0);
                    break;
                case 4: // Kereket kiszúr
                    muveletNev = "Kerékkiszúrás";
                    aktualeredmeny = (byte)(k.kereketKiszur() ? 1 : 0);
                    break;
                case 5: // Kereket cserél
                    muveletNev = "Kerékcsere";
                    aktualeredmeny = (byte)(k.kereketCserel() ? 1 : 0);
                    break;
                case 6: // Elindulás defekt nélkül pótkerékkel
                    muveletNev = "Megy amíg van üzemanyag";
                    k.kereketKiszur();
                    k.potkereketberak(1);
                    k.kereketCserel();
                    aktualeredmeny = (byte)(k.megy() ? 1: 0);
                default:
            }
            boolean both = aktualeredmeny == (byte)(tesztesetek[i].getVartEredmeny() ? 1 : 0);
            
            
            
            //if(true /*&& !both*/) {AuxiliaryFunctions.KonzolraKiir("%d. ".formatted(i) + muveletNev + allapot());}
            assert both : "A %d. teszteset nem működött megfelelően.".formatted(i);
        }
    }
    
    public static String allapot(){
        return /*"-".repeat(16)+*/
                "\nÜzemanyagszint: %d\nMotor: %s\nGumik: %s\nPótkerekek: %d\n".formatted(k.getUzemanyag(), k.getMegye() ? 
                        "Megy" : "Nem megy.", k.getDefekt() ? "Defektesek" : "Használhatóak", k.getPotkerekek()) +
                "-".repeat(16);
    }
    
    static class TesztObjektum{
        int muveletszam;
        boolean varteredmeny;
        
        public TesztObjektum(int muveletszam, boolean varteredmeny){
            this.muveletszam = muveletszam;
            this.varteredmeny = varteredmeny;
        }
        
        public int getMuveletszam(){
            return muveletszam;
        }
        
        public boolean getVartEredmeny(){
            return varteredmeny;
        }
    }
}
