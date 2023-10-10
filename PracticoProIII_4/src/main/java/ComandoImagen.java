
import Imagenes.Imagen;
import listas.ListaGenerica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoImagen extends ComandoServidorWeb{

    private final String OPERACION_REGEXss = "^\\/get\\/s([0-9])\\/([a-z]+)-([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$";
    Imagen img0 = new Imagen(400,400);
    Imagen img1 = new Imagen(400,400);
    Imagen img2 = new Imagen(400,400);
    Imagen img3 = new Imagen(400,400);
    ListaGenerica<Imagen> listaSesiones = new ListaGenerica<Imagen>();

    public ComandoImagen(String cmd) {
        comando = cmd;
        listaSesiones.adicionar(img0);
        listaSesiones.adicionar(img1);
        listaSesiones.adicionar(img2);
        listaSesiones.adicionar(img3);
        System.out.println(comando);
        img0.imagenBlanca();
        img1.imagenBlanca();
        img2.imagenBlanca();
        img3.imagenBlanca();
    }
    @Override
    public void ejecutar() {

        System.out.println("Esto esta en comando"+comando);
        Pattern patronExpRegFill = Pattern.compile(OPERACION_REGEXss);
        Matcher Fill = patronExpRegFill.matcher(comando);
        Fill.find();
        int posicionLista = Integer.parseInt(Fill.group(1));
        String operacion= Fill.group(2);
        int x1 = Integer.parseInt(Fill.group(3));
        int x2 = Integer.parseInt(Fill.group(5));
        int y1 = Integer.parseInt(Fill.group(4));
        int y2 = Integer.parseInt(Fill.group(6));
        switch (posicionLista) {
            case 0:
                if (operacion.equalsIgnoreCase("IMAGEN")){
                    resultadoImagen = img0.getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    img0.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    img0.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = img0.getBytesPng();
                break;
            case 1:
                if (operacion.equalsIgnoreCase("IMAGEN")){
                    resultadoImagen = img1.getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    img1.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    img1.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = img1.getBytesPng();
                break;
            case 2:
                if (operacion.equalsIgnoreCase("IMAGEN")){
                    resultadoImagen = img2.getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    img2.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    img2.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = img2.getBytesPng();
                break;
            case 3:
                if (operacion.equalsIgnoreCase("IMAGEN")){
                    resultadoImagen = img3.getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    img3.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    img3.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = img3.getBytesPng();
                break;

        }

    }

    @Override
    public boolean esResultadoTexto() {
        return false;
    }

}
