/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieee.convertor.Model;

/**
 *
 * @author Andres Monsalve
 */
public class IEEEConverter {
        
    //=======================CONVERSION DE DECIMAL A IEEE=======================
    
    //CONVERSION A PRESICION DOBLE (64 BITS)
    
    //METODO PRINCIPAL (64 BITS)
    public String[] decimalToDoble(String numero){
    
        double num = Double.parseDouble(numero);
        
        if (num == 0){
            String signo = numero.charAt(0) == '0' ? "0" : "1";
            return new String[]{signo, "00000000000", 
                "0000000000000000000000000000000000000000000000000000"};
        }    
                
        int exponente = 1023;
        
        if(num >= 1 || num <= -1){
            
            while(Math.abs(num) > 2){
                num /= 2;
                exponente++;                
            }
        }
        
        else{
           while(Math.abs(num) < 1){
                num *= 2;
                exponente--;                
            }        
        }
        
        String[] rta = new String[3];
        
        rta[0] = num>0 ? "0" : "1";
        rta[1] = exponenteToBinarioDoble(exponente);
        rta[2] = mantisaToBinarioDoble(Math.abs(num) - 1);
        
        return rta;
    
    }
    
    //METODOS ADICIONALES (64 BITS)
    public String exponenteToBinarioDoble(int decimal){
        String rta = "";
        
        for(int i = 10; i>=0; i--){
            int j = 1<<i;
            if(decimal >= j) {
                decimal -= j;
                rta += "1";
            }
            else rta += "0";
        }
               
        return rta;
    }
    
    public String mantisaToBinarioDoble(double numero){
        
        String rta = "";
        
        for(int i = 0; i<52; i++){
            numero *= 2;
            if(numero>=1){
                rta += "1";
                numero -= 1;
            }
            else{
                if(numero<1 || numero==1) rta += "0";
            }
            
            if(numero==1) numero-=1;
        }
        
        return rta;
    }
    
    //CONVERSION A PRECISION SIMPLE (32 BITS)
    
    //METODO PRINCIPAL (32 BITS)
    public String[] decimalToSimple(String numero){
    
        double num = Double.parseDouble(numero);
        
        if (num == 0){ 
            String signo = numero.charAt(0) == '0' ? "0" : "1";
            return new String[]{signo, "00000000", "00000000000000000000000"};
        }
        
        int exponente = 127;
        
        if(num >= 1 || num <= -1){
            
            while(Math.abs(num) > 2){
                num /= 2;
                exponente++;                
            }
        }
        
        else{
           while(Math.abs(num) < 1){
                num *= 2;
                exponente--;                
            }        
        }
        
        String[] rta = new String[3];
        
        rta[0] = num>0 ? "0" : "1";
        rta[1] = exponenteToBinarioSimple(exponente);
        rta[2] = mantisaToBinarioSimple(Math.abs(num) - 1);

        return rta;
    }
    
    //METODOS ADICIONALES (32 BITS)
    public String exponenteToBinarioSimple(int decimal){
        String rta = "";
        
        for(int i = 7; i>=0; i--){
            int j = 1<<i;
            if(decimal >= j) {
                decimal -= j;
                rta += "1";
            }
            else rta += "0";
        }
               
        return rta;
    }
    
    public String mantisaToBinarioSimple(double numero){
        
        String rta = "";
        
        for(int i = 0; i<23; i++){
            numero *= 2;
            if(numero>=1){
                rta += "1";
                numero -= 1;
            }
            else{
                if(numero<1 || numero==1) rta += "0";
            }
            
            if(numero==1) numero-=1;
        }
        
        return rta;
    }
    
    //=======================CONVERSION DE IEEE A DECIMAL=======================
    
    //METODO PRINCIPAL (64 BITS)
    public double dobleToDecimal(String[] numero){
        
        if(Integer.parseInt(numero[1]) == 0 &&
           Integer.parseInt(numero[2]) == 0) return 0;
        
        double num = 1.0;
        num += mantisaToDecimalDoble(numero[2]);
        if(numero[0].equals("1")) num *= -1;
        num *= Math.pow(2, exponenteToDecimalDoble(numero[1]));
    
        return num;
    }
    
    //METODOS ADICIONALES (64 BITS)
    public int exponenteToDecimalDoble(String binary){
        int rta = 0;
        binary = new StringBuilder(binary).reverse().toString();
        for(int i = 0; i<binary.length(); i++){ 
            if(binary.charAt(i) == '1') rta += (int)Math.pow(2, i);
        }       
        return (rta - 1023);
    }
    
    public double mantisaToDecimalDoble(String binary){    
        double rta = 0;
        for(int i = 0; i<binary.length(); i++){ 
            if(binary.charAt(i) == '1') rta += Math.pow(2, (-(i+1)));
        }       
        return rta;
    }
    
    //METODO PRINCIPAL (32 BITS)
    public double simpleToDecimal(String[] numero){
        
        if(Integer.parseInt(numero[1]) == 0 &&
           Integer.parseInt(numero[2]) == 0) return 0;
        
        double num = 1.0;
        num += mantisaToDecimalSimple(numero[2]);
        if(numero[0].equals("1")) num *= -1;
        num *= Math.pow(2, exponenteToDecimalSimple(numero[1]));
    
        return num;
    }
    
    //METODOS ADICIONALES (32 BITS)
    public int exponenteToDecimalSimple(String binary){
        int rta = 0;
        binary = new StringBuilder(binary).reverse().toString();
        for(int i = 0; i<binary.length(); i++){ 
            if(binary.charAt(i) == '1') rta += (int)Math.pow(2, i);
        }       
        return (rta - 127);
    }
    
    public double mantisaToDecimalSimple(String binary){
        double rta = 0;
        for(int i = 0; i<binary.length(); i++){ 
            if(binary.charAt(i) == '1') rta += Math.pow(2, (-(i+1)));
        }       
        return rta;
    }
    
    //Metodos Fachada // Decoradores
    
    public String[] convertirToIEEE(String precision, String numero){
    
        esValido(precision, numero);
       
        switch (precision) {
            case "Sencilla (32 bits)":
                return decimalToSimple(numero);
            case "Doble (64 bits)":
                return decimalToDoble(numero);
            default: throw new NumberFormatException("Precision invalida");
        }
    }
    
    public double convertirToDecimal(String precision, String[] numero){
        
        esValido(precision, numero);
    
        switch (precision) {
            case "Sencilla (32 bits)":
                return simpleToDecimal(numero);
            case "Doble (64 bits)":
                return dobleToDecimal(numero);
        }
        
        return 0;
    }
    
    public boolean esValido(String precision, String numero){
        if(!precision.equals("Sencilla (32 bits)") && 
                !precision.equals("Doble (64 bits)"))
            throw new NumberFormatException("Precision invalida");
        
        if(!numero.matches("^-?\\d+(\\.\\d+)?$"))
            throw new NumberFormatException("Número invalido!!!");
            
        return true;
    }
    
    public boolean esValido(String precision, String[] numero){
        if(precision.equals("Sencilla (32 bits)")){
            if (!numero[0].matches("^[01]$"))
                throw new NumberFormatException("El signo es invalido!!!");
            
            if (!numero[1].matches("^[01]{8}$"))
                throw new NumberFormatException("El exponente es invalido!!!");
            
            if(numero[1].matches("^[1]{8}$")){
                if(numero[2].matches("^[0]{23}$"))
                throw new NumberFormatException(numero[0].equals("0") ? 
                        "El numero es: +∞" : "El numero es: -∞");
                else throw new NumberFormatException("Not a Number (NaN)");
            }                
            
            if (!numero[2].matches("^[01]{23}$"))
                throw new NumberFormatException("La mantisa es invalida!!!");
            
            return true;
        }
        
        if(precision.equals("Doble (64 bits)")){
            if (!numero[0].matches("^[01]$"))
                throw new NumberFormatException("El signo es invalido!!!");
            
            if (!numero[1].matches("^[01]{11}$"))
                throw new NumberFormatException("El exponente es invalido!!!");
            
            if(numero[1].matches("^[1]{11}$")){
                if(numero[2].matches("^[0]{52}$"))
                throw new NumberFormatException(numero[0].equals("0") ? 
                        "El numero es: +∞" : "El numero es: -∞");
                else throw new NumberFormatException("Not a Number (NaN)");
            }                
            
            if (!numero[2].matches("^[01]{52}$"))
                throw new NumberFormatException("La mantisa es invalida!!!");
            
            return true;
        }
        
        throw new NumberFormatException("Precision invalida");        
    }
}


