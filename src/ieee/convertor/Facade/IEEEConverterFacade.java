/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieee.convertor.Facade;
import ieee.convertor.Model.IEEEConverter;

/**
 *
 * @author Andres Monsalve
 */
public class IEEEConverterFacade {
    
    private final IEEEConverter iConverter;

    public IEEEConverterFacade() {
        iConverter = new IEEEConverter();
    }
    
    public double convertirToDecimal(String precision, String[] numero){   
        return iConverter.convertirToDecimal(precision, numero);
    }
    
    public String[] convertirToIEEE(String precision, String numero){   
        return iConverter.convertirToIEEE(precision, numero);
    }
        
}
