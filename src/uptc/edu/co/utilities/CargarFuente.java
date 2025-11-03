package uptc.edu.co.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class CargarFuente {
    
    public static Font SourceProPrueba;
    
    public void loadFont() throws FontFormatException, IOException {
        // Usa la ruta relativa desde resources (comienza con /)
        InputStream read = getClass().getResourceAsStream("/font/Source_Code_Pro/SourceCodePro-Italic-VariableFont_wght.ttf");
        
        // Verifica si el archivo se encontró
        if (read == null) {
            throw new IOException("No se pudo encontrar el archivo de fuente");
        }
        
        SourceProPrueba = Font.createFont(Font.TRUETYPE_FONT, read);
        read.close();
    }
}