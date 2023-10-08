import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuilderComandoServidorWeb {
    private final String OPERACION_REGEX =
            "^get\/(s[0-9])\/([a-z]+)-([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$";
    private BuilderComandoServidorWeb() {
    }
    public static ComandoServidorWeb crear(String cmdGet) {
        if (cmdGet == null || cmdGet.isEmpty()) {
          //  return new ComandoVacio(cmdGet);
        }
        if (cmdGet.trim().equals(""))
        if (cmdGet.trim().equals("/tabla")) {
           // return new ComandoTabla(cmdGet);
        }

        if (cmdGet.trim().equals("/imagen")) {
          //  return new ComandoImagen(cmdGet);
        }

       // return new ComandoVacio(cmdGet);
        return null;
    }
}
