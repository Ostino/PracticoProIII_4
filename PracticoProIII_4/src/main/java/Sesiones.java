import Imagenes.Imagen;
import listas.ListaGenerica;

public class Sesiones {

    ListaGenerica<Imagen> listaSesiones = new ListaGenerica<>();
    Imagen img0 = new Imagen(400,400);
    Imagen img1 = new Imagen(400,400);
    Imagen img2 = new Imagen(400,400);
    Imagen img3= new Imagen(400,400);
    public Sesiones(){
        listaSesiones.adicionar(img0);
        listaSesiones.adicionar(img1);
        listaSesiones.adicionar(img2);
        listaSesiones.adicionar(img3);
        img0.imagenBlanca();
        img1.imagenBlanca();
        img2.imagenBlanca();
        img3.imagenBlanca();
    }
    public Imagen getImg0() {
        return img0;
    }

    public Imagen getImg1() {
        return img1;
    }

    public Imagen getImg2() {
        return img2;
    }

    public Imagen getImg3() {
        return img3;
    }

}
