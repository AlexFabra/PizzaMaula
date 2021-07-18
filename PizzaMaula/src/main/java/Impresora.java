import java.awt.*;
import java.io.*;
import java.io.IOException;
public class Impresora{
    //para que funcione la impresora tiene que ser impresora por defecto.
    public static void imprimir(String comanda) {

        comanda = "\n\n\n"+comanda+"\n\n\n"+".";

        try {
            FileWriter fichero = new FileWriter("C:/Users/Public/Documents/impresion.txt");
            fichero.write(comanda);
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("no se ha podido crear el documento");
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        java.io.File ficheroHecho = new java.io.File("C:/Users/Public/Documents/impresion.txt");
        if (desktop.isSupported(Desktop.Action.PRINT)){
            try {
                desktop.print(ficheroHecho);
                desktop.print(ficheroHecho);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("El sistema no permite imprimir usando la clase Desktop");
        }
    }
}
