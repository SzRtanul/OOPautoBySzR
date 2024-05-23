/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program;

import control.EI;

/**
 *
 * @author SzabóRoland(SZOFT_20
 */
public class OOPautoKonzol implements EI.BBListener{
    static objektumok.Auto k = new objektumok.Auto(true, 2, false, 1);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AuxiliaryFunctions.KonzolraKiir("Üdvözlet!\nEz egy autó!\n");
        AuxiliaryFunctions.KonzolraKiir(allapot()+"\n");
        AuxiliaryFunctions.KonzolraKiir("""
                            Implementált műveletek:
                            1. Motor indítása/leállítása
                            2. Tankolás
                            3. Láb a gázon
                            4. Kereket kiszúr
                            5. Kereket cserél
                            -1: Kilépés
                            """);
        int muvelet = 0;
        do {
            muvelet = AuxiliaryFunctions.intBekeres("Válassz egy műveletet: ");
            switch (muvelet) {
                case -1:
                    AuxiliaryFunctions.KonzolraKiir("Kilépés...");
                    break;
                case 1:
                    k.motor(!k.getMegye());
                    AuxiliaryFunctions.KonzolraKiir("A motor %s.".formatted(k.getMegye() ? "megy" : "nem megy"));
                    break;
                case 2:
                    AuxiliaryFunctions.KonzolraKiir("%s".formatted(k.tankol() ? 
                            "Az üzemanyagszint megnövekedett. Jelenleg %d".formatted(k.getUzemanyag()) 
                            : "HIBA: A motor még jár."));
                    break;
                case 3:
                    if(!k.megy()){
                        if(!k.getMegye()){
                            AuxiliaryFunctions.KonzolraKiir("HIBA: A motor nem megy.");
                        }
                        if(k.getDefekt()){ 
                            AuxiliaryFunctions.KonzolraKiir("HIBA: Az autó defektet kapott.");
                        }
                        if(k.getUzemanyag() == 0){ 
                            AuxiliaryFunctions.KonzolraKiir("HIBA: Az autó üzemanyaga elfogyott.");
                        }
                    }
                    else{
                        AuxiliaryFunctions.KonzolraKiir("Az autó megtett pár métert.\n"+allapot());
                    }
                    break;
                case 4:
                    if(k.kereketKiszur())AuxiliaryFunctions.KonzolraKiir("A kerék kiszúrva.");
                    else AuxiliaryFunctions.KonzolraKiir("Az autó defektes volt.");
                    break;
                case 5:
                    if(k.kereketCserel()) AuxiliaryFunctions.KonzolraKiir("A kerék kicserélve.\nPótkerekek száma: %d"
                            .formatted(k.getPotkerekek()));
                    else AuxiliaryFunctions.KonzolraKiir("Nincs elég pótkerék.");
                    break;
                default:
                    AuxiliaryFunctions.KonzolraKiir("Nincs ilyen művelet.");
            }
        } while (muvelet != -1);
    }

    public static String allapot(){
        return "-".repeat(16)+
                "\nÜzemanyagszint: %d\nMotor: %s\nGumik: %s\n".formatted(k.getUzemanyag(), k.getMegye() ? 
                        "Megy" : "Nem megy.", k.getDefekt() ? "Defektesek" : "Használhatóak") +
                "-".repeat(16);
    }
    
    
    
    @Override
    public void actionValueChanged() {
        
    }
    
}
