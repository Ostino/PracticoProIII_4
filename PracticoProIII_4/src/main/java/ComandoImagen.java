
import Imagenes.Imagen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoImagen extends ComandoServidorWeb{

    private final String OPERACION_REGEXss = "^\\/get\\/(s[0-9])\\/([a-z]+)-([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$";

    public ComandoImagen(String cmd) {
        comando = cmd;
        System.out.println(comando);
    }
    @Override
    public void ejecutar() {
        Imagen img = new Imagen(400,400);
        img.imagenBlanca();
        System.out.println("Esto esta en comando"+comando);
        Pattern patronExpRegFill = Pattern.compile(OPERACION_REGEXss);
        Matcher Fill = patronExpRegFill.matcher(comando);
        Fill.find();
        String operacion= Fill.group(2);
        int x1 = Integer.parseInt(Fill.group(3));
        int x2 = Integer.parseInt(Fill.group(5));
        int y1 = Integer.parseInt(Fill.group(4));
        int y2 = Integer.parseInt(Fill.group(6));
        if (operacion.equalsIgnoreCase("linea")) {
            img.linea(x1, y1, x2, y2);
        }
        if (operacion.equalsIgnoreCase("rectangulo")){
        img.rectangulo(x1,y1,x2,y2);
        }
        resultadoImagen = img.getBytesPng();
    }

    @Override
    public boolean esResultadoTexto() {
        return false;
    }

}
