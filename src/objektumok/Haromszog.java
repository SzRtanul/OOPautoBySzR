/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objektumok;

import program.AuxiliaryFunctions;

/**
 *
 * @author SzabóRoland(SZOFT_20
 */
public class Haromszog {
    int a, b, c;
    public Haromszog(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
        
        assert a > 0 && b > 0 && c > 0 : "HIBA: A háromszög oldalai nem lehetnek kevesebbek 0-nál.";
    }
    
    public int getKerulet(){
        return a+b+c;
    }
    
    public int getA(){
        return a;
    }
    
    public int getB(){
        return b;
    }
    
    public int getC(){
        return c;
    }
    
    public String getOldalakAdatai(){
        return "a = %d\nb = %d\nc = %d\nKerület: %d\n".formatted(a,b,c, this.getKerulet());
    }
    
    public boolean getSzerkeszthetoE(){
        return AuxiliaryFunctions.max(new int[]{a,b,c}) > AuxiliaryFunctions.sum(new int[]{a,b,c});
    }
}
