

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Este servidor responde a las siguientes consultas de parte del navegador:
 * /tabla
 * /imagen
 * /otro : retornará con error cualquier otra cosa
 */
public class test {
    private final int puerto;
    private final Logger logger = LogManager.getRootLogger();

    public test(int puerto) {
        this.puerto = puerto;

    }

    public static void main(String[] args) {
        test servidor = new test(8080);
        servidor.start();
    }

    /**
     * En este metodo se abre un socketserver en el puerto indicado
     * y se espera una conexion. Una vez que se da la conexion se llama
     * a otra clase pasandole esta conexion para que se haga carga de la
     * petición y se devuelva la respuesta.
     */
    private void start() {
        ServerSocket socketServer = null;
        try {
            socketServer = new ServerSocket(puerto);
            logger.info("Servidor iniciado en el puerto " + puerto);
            while (true) {
                // Se espera una conexion
                Socket clt = socketServer.accept();
                // Se crea un objeto de la clase que se encarga de procesar la peticion
                ProcesarPeticion procesadorPeticion = new ProcesarPeticion(clt);
                // Se procesa la peticion
                procesadorPeticion.procesarPeticion();
            }
        } catch (Exception e) {
            logger.error("Error en el servidor: " + e.getMessage());
            try {
                socketServer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
