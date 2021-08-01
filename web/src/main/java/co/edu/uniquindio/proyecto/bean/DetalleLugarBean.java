package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.LugarDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Value("#{param['lugar']}")
    private String idLugar;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Getter
    @Setter
    private List<Horario> horarios;

    private List<String> images;

    @Value(value="#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Integer calificacionPromedio;

    @Getter @Setter
    private Comentario comentario;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private String icono;

    @Getter @Setter
    private boolean esFavorito;

    @PostConstruct
    public void inicializar() {

        this.nuevoComentario = new Comentario();
       // this.icono = "pi pi-star-o";
        try {
            images = new ArrayList<String>();

            images.add("https://www.eltiempo.com/files/image_640_428/uploads/2019/03/30/5ca02668e04df.jpeg");
            images.add("https://www.revistalabarra.com/wp-content/uploads/2019/02/Potada-nota-enano-1500x800.jpg");
            images.add("https://www.entornoturistico.com/wp-content/uploads/2016/06/clientes-en-un-bar-660x330.jpg");

            int id = Integer.parseInt(idLugar);
            this.lugar = lugarServicio.obtenerLugar(id);
            this.comentarios = lugarServicio.listarComentariosDeUnLugar(id);
            this.calificacionPromedio= lugarServicio.obtenerCalificacionPromedio(id);
            if(personaLogin!=null) {
                this.esFavorito = lugarServicio.esFavorito(this.lugar, (Usuario) personaLogin);
            }
            if(esFavorito)
            {
                this.icono= "pi pi-star";
            }
            else {
                this.icono = "pi pi-star-o";
            }
//            LugarDTO markerLugar = new LugarDTO(this.lugar.getCodigo(), this.lugar.getNombre(), this.lugar.getDescripcion(), this.lugar.getLatitud(), this.lugar.getLongitud(), this.lugar.getTipo().getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void crearComentario()
    {
        try {

            if(personaLogin!=null) {
                nuevoComentario.setLugarComentario(this.lugar);
                nuevoComentario.setUsuarioComentario((Usuario) personaLogin);
                comentarioServicio.crearComentario(nuevoComentario);
                this.nuevoComentario = new Comentario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void marcarFavorito()
    {
        if(icono.equals("pi pi-star-o"))
        {
            this.icono="pi pi-star";
        }else{
            this.icono="pi pi-star-o";
        }

        if(personaLogin!=null)
        {
          lugarServicio.marcarFavorito(this.lugar, (Usuario) personaLogin);
        }
    }

    public List<String> getImages() {
        return images;
    }


}
