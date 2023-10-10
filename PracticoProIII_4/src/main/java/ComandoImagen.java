

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoImagen extends ComandoServidorWeb{

    private final String OPERACION_REGEXss = "^\\/get\\/s([0-9])\\/([a-z]+)-([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$";

    Sesiones sesiones= new Sesiones();
    public ComandoImagen(String cmd) {
        comando = cmd;
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
                if (operacion.equalsIgnoreCase("imagen")){
                    resultadoImagen = sesiones.getImg0().getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    sesiones.img0.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    sesiones.img0.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = sesiones.getImg0().getBytesPng();
                break;
            case 1:
                if (operacion.equalsIgnoreCase("imagen")){
                    resultadoImagen = sesiones.getImg1().getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    sesiones.img1.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    sesiones.img1.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = sesiones.getImg1().getBytesPng();
                break;
            case 2:
                if (operacion.equalsIgnoreCase("imagen")){
                    resultadoImagen = sesiones.getImg2().getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    sesiones.img2.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    sesiones.img2.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = sesiones.getImg2().getBytesPng();
                break;
            case 3:
                if (operacion.equalsIgnoreCase("imagen")){
                    resultadoImagen = sesiones.getImg3().getBytesPng();
                }
                if (operacion.equalsIgnoreCase("linea")) {
                    sesiones.img3.linea(x1, y1, x2, y2);
                }
                if (operacion.equalsIgnoreCase("rectangulo")){
                    sesiones.img3.rectangulo(x1,y1,x2,y2);
                }
                resultadoImagen = sesiones.getImg3().getBytesPng();
                break;

        }

    }

    @Override
    public boolean esResultadoTexto() {
        return false;
    }

}
