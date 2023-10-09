package Imagenes;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class Imagen implements IDibujador {
    private int alto;
    private int ancho;
    private int[][] pixeles;
    private PropertyChangeSupport observado;
    private boolean pintable;
    private boolean baldePintura;
    private boolean borrador;
    private int LapizFinalX = 256;
    private int LapizFinalY = 256;
    private int tempo =10;
    private int contador;



    public Imagen(int w, int h) {
        ancho = w;
        alto = h;
        pixeles = new int[ancho][alto];
        observado = new PropertyChangeSupport(this);
        pintable = true;
        baldePintura =false;
    }

    @Override
    public void dibujar(Graphics g) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(pixeles[i][j]));
                g.drawLine(i,j,i,j);
            }
        }
    }

    public void imagenBlanca(){
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = 0x00FFFFFF;
            }
            observado.firePropertyChange("IMAGEN", true, false);
        }
    }

    public void addObserver(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public void achicar(int t) {
        int[][] nuevosPixeles = new int[ancho/t][alto/t];

        for (int i = 0; i < ancho; i+=t) {
            for (int j = 0; j < alto; j+=t) {
                nuevosPixeles[i/2][j/2] = pixeles[i][j];
            }
        }
        pixeles = nuevosPixeles;
        ancho = ancho/t;
        alto = alto /t;
        observado.firePropertyChange("IMAGEN",true, false);
    }

    public void Agrandar(int t) {
        int[][] nuevosPixeles = new int[ancho*t][alto*t];
        for (int i = 0; i < ancho*t; i++) {
            for (int j = 0; j < alto*t; j++) {
                nuevosPixeles[i][j] = pixeles[i/t ][j/t];
            }
        }
        pixeles = nuevosPixeles;
        ancho = ancho*t;
        alto = alto *t;
        observado.firePropertyChange("IMAGEN",true, false);
    }

    public void aplicarMatriz(MatrizDeTransformacion m) {
        int[][] nuevosPixeles = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Vectores entrada = new Vectores(i,j);
                Vectores salida = m.aplicarMatriz(entrada);
                if ((int)salida.getX() >= 0 &&
                        (int)salida.getX() < ancho &&
                        (int)salida.getY() >= 0 &&
                        (int)salida.getY() < alto) {
                    nuevosPixeles[(int)salida.getX()][(int)salida.getY()] =
                            pixeles[i][j];
                }
            }
        }
        pixeles = nuevosPixeles;
        observado.firePropertyChange("IMAGEN",true, false);
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getPixeles(int x, int y) {
        return pixeles[x][y];
    }

    public void Airededor(int x, int y){
        if(isBaldePintura()) {/*
            try {
                pixeles[x][y] = colorHex;
                contador = 0;
                if (pixeles[x][y - 1] != colorHex && (y-1)<513 ) {
                    pixeles[x][y - 1] = colorHex;
                    observado.firePropertyChange("IMAGEN", true, false);
                    Airededor(x, y - 1);
                } else {
                    contador++;
                }
                if (pixeles[x - 1][y] != colorHex && (x-1)<513) {
                    pixeles[x - 1][y] = colorHex;
                    observado.firePropertyChange("IMAGEN", true, false);
                    Airededor(x - 1, y);

                } else {
                    contador++;
                }
                if (pixeles[x][y + 1] != colorHex && (y+1)<513) {
                    pixeles[x][y + 1] = colorHex;
                    observado.firePropertyChange("IMAGEN", true, false);
                    Airededor(x, y + 1);
                } else {
                    contador++;
                }
                if (pixeles[x + 1][y] != colorHex&& (x+1)<513) {
                    pixeles[x + 1][y] = colorHex;
                    observado.firePropertyChange("IMAGEN", true, false);
                    Airededor(x + 1, y);
                } else {
                    contador++;
                }
                if (contador == 4) {
                    return;
                }
            }catch (StackOverflowError e){
                System.out.println("Es demasiado grande para llenar");
            }*/
            int width = 512;
            int height =512;
            if (x < 0 || x >= width || y < 0 || y >= height || pixeles[x][y] == colorHex) {
                return;
            }
            pixeles[x][y] = colorHex;
            observado.firePropertyChange("IMAGEN", true, false);
            Airededor( x - 1, y);
            Airededor(x + 1, y);
            Airededor( x, y - 1);
            Airededor( x, y + 1);
        }

    }

    public void movArriba(int num) throws InterruptedException {
        if (isPintable()) {
            for (int i = 0; i < num; i++) {
                pixeles[LapizFinalX][LapizFinalY] = colorHex;
                LapizFinalY--;
                observado.firePropertyChange("IMAGEN", true, false);
                sleep(tempo);
            }
        }
    }

    public void movAbajo(int num) throws InterruptedException {
        if (isPintable()) {
            for (int i = 0; i < num; i++) {
                pixeles[LapizFinalX][LapizFinalY] = colorHex;
                LapizFinalY++;
                observado.firePropertyChange("IMAGEN",true, false);
                sleep(tempo);
            }
        }
    }

    public void movIzquierda(int num) throws InterruptedException {
        if (isPintable()) {
            for (int i = 0; i < num; i++) {
                pixeles[LapizFinalX][LapizFinalY] = colorHex;
                LapizFinalX--;
                observado.firePropertyChange("IMAGEN",true, false);
                sleep(tempo);
            }
        }
    }

    public void movDerecha(int num) throws InterruptedException {
        if (isPintable()) {
            for (int i = 0; i < num; i++) {
                pixeles[LapizFinalX][LapizFinalY] = colorHex;
                LapizFinalX++;
                observado.firePropertyChange("IMAGEN",true, false);
                sleep(tempo);
            }
        }
    }

    public void punto(int x, int y, int tamano) {
        if (isBorrador() == false) {
            for (int i = x - tamano / 2; i < x + tamano / 2; i++) {
                for (int j = y - tamano / 2; j < y + tamano / 2; j++) {
                    if (i >= 0 && i < ancho &&
                            j >= 0 && j < alto) {
                        pixeles[i][j] = colorHex;
                    }
                }
            }
            observado.firePropertyChange("IMAGEN", true, false);
        }else{
            for (int i = x - tamano / 2; i < x + tamano / 2; i++) {
                for (int j = y - tamano / 2; j < y + tamano / 2; j++) {
                    if (i >= 0 && i < ancho &&
                            j >= 0 && j < alto) {
                        pixeles[i][j] = 0x00FFFFFF;
                    }
                }
            }
            observado.firePropertyChange("IMAGEN", true, false);
        }
    }

    public boolean CheckHex(String hex){
                String regex = "^0x[A-Fa-f0-9]{8}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(hex);
                if (matcher.matches()) {
                    return true;
                } else {
                    return false;
        }
    }
    public byte[] getBytesPng() {
        BufferedImage bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();

        byte[] imageBytes = null;
        int[] rasterPixels = transformarPuntos();
        raster.setPixels(0, 0, ancho, alto, rasterPixels);

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
    private int[] transformarPuntos() {
        int[] r = new int[3*ancho*alto];

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int red = (pixeles[i][j] & 0x00ff0000) >> 16;
                int green = (pixeles[i][j] & 0x0000ff00) >> 8;
                int blue = pixeles[i][j] & 0x000000ff;

                r[3*(j * ancho + i)] = red;
                r[3*(j * ancho + i)+1] = green;
                r[3*(j * ancho + i)+2] = blue;
            }
        }

        return r;
    }
    public void rectangulo(int x1, int y1, int alto,int ancho){
        for (int i = y1; i < y1 + alto; i++) {
            for (int j = x1; j < x1 + ancho; j++) {
                pixeles[i][j] = 0;
            }
        }
    }
    public void linea(int x1, int y1, int x2, int y2) {
        /**
         * int step;
         *         if (x1 > x2) {
         *             step = -1;
         *         } else {
         *             step = 1;
         *         }
         */

        // ---- Si son lineas horizontales
        if (Math.abs(x2 - x1) > Math.abs(y2 - y1)) {
            int stepX = (x1 > x2 ? -1 : 1);
            int stepY = (y1 > y2 ? -1 : 1);
            double ratio = (double)Math.abs(y2 - y1) / (double)Math.abs(x2 - x1);
            int x = 0;
            for (int i = x1; i != x2; i += stepX) {

                pixeles[i][y1 + stepY * (int)(x * ratio)] = 0;
                x++;
            }
        }
        if (Math.abs(x2 - x1) <= Math.abs(y2 - y1)) {
            int stepX = (x1 > x2 ? -1 : 1);
            int stepY = (y1 > y2 ? -1 : 1);
            double ratio = (double)Math.abs(x2 - x1) / (double)Math.abs(y2 - y1);
            int y = 0;
            for (int j = y1; j != y2; j += stepY) {
                pixeles[x1 + stepX * (int)(y * ratio)][j] = 0;
                y++;
            }
        }
        observado.firePropertyChange("IMAGEN",true, false);
    }
    public boolean isPintable() {
        return pintable;
    }

    public boolean isBorrador() {
        return borrador;
    }

    public void setBorrador(boolean borrador) {
        this.borrador = borrador;
    }

    public void setPintable(boolean pintable) {
        this.pintable = pintable;
    }

    public int getColorHex() {
        return colorHex;
    }

    public void setColorHex(int colorHex) {
        this.colorHex = colorHex;
    }

    private int colorHex = 0x00000000;

    public boolean isBaldePintura() {
        return baldePintura;
    }

    public void setBaldePintura(boolean baldePintura) {
        this.baldePintura = baldePintura;
    }

    public void  HacerGris (){
        for (int i = 0; i <ancho ; i++) {
            for (int j = 0; j <alto ; j++) {
                Color colorOriginal = Color.decode(String.valueOf(pixeles[i][j]));
                int r = colorOriginal.getRed();
                int g = colorOriginal.getGreen();
                int b = colorOriginal.getBlue();
                int rgb = (r+g+b)/3;
                String hex= Integer.toHexString(rgb);
                 hex = "00"+hex+""+hex+""+hex;
                 int HexV = Integer.parseInt(hex,16);
                pixeles[i][j]= HexV;
                observado.firePropertyChange("IMAGEN",true, false);
            }
        }
    }

    public void cargarImagen(File archivoConImagen) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(archivoConImagen);
        }
        catch(Exception q)
        {
            System.out.println("No pudo cargar la imagen");
            return;
        }

        ancho = bi.getWidth();
        alto = bi.getHeight();

        pixeles = new int[ancho][alto];
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                int bgr = bi.getRGB(i, j);
                pixeles[i][j] = bgr;
            }
        }

        observado.firePropertyChange("IMAGEN", true, false);
    }
}