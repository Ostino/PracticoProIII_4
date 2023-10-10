import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuilderComandoServidorWeb {
    static final String OPERACION_REGEXss = "^\\/get\\/s([0-9])\\/([a-z]+)-([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$";
    private BuilderComandoServidorWeb() {
    }

    public static ComandoServidorWeb crear(String cmdGet) {

        Pattern patronExpRegFill = Pattern.compile(OPERACION_REGEXss);
        Matcher Fill = patronExpRegFill.matcher(cmdGet);
        Fill.find();
        String operacion= Fill.group(2);
        System.out.println(operacion);
            if ((operacion.trim().equals("rectangulo") || operacion.trim().equals("linea"))|| operacion.trim().equals("imagen")) {
                System.out.println("Entre al rectangulo");
                return new ComandoImagen(cmdGet);
        }
        if (cmdGet == null || cmdGet.isEmpty()) {
            return new ComandoVacio(cmdGet);
        }
        if (cmdGet.trim().equals("/tabla")) {
            return new ComandoTabla(cmdGet);
        }

        return new ComandoVacio(cmdGet);
    }
}
