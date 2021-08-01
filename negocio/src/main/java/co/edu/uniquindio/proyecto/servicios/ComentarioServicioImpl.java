package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    public boolean estaComentario(int id){
        Optional<Comentario> comentario= comentarioRepo.findById(id);
        return comentario.isEmpty();
    }
    public Optional<Comentario> buscarComentarioId(int id){

        return comentarioRepo.findById(id);
    }
    public boolean longitudValida(String cadena){

        return (cadena.length()>1000)?true:false;

    }

    public boolean calificacionValida(int calificacion){

        if(calificacion>5 || calificacion<=0){
            return false;
        }
        return true;
    }

    /**
     * Metodo que retorna un comentario que corresponda a un id pasado
     * @param codigo id del comentario
     * @return Comentario encontrado
     * @throws Exception En caso de que no exista un comentario con el id pasado
     */
    @Override
    public Comentario obtenerComentario(int codigo) throws Exception {
        Optional<Comentario> comentario= buscarComentarioId(codigo);
        if(comentario.isEmpty()){
            throw new Exception("El comentario con ese id no existe");
        }
        return comentario.get();
    }

    /**
     * Servicio que permite crear un nuevo comentario
     * @param comentario
     * @return comentario guardado
     * @throws Exception si se incumple con ciertos parametros
     */

    @Override
    public Comentario crearComentario(Comentario comentario) throws Exception {
        if(longitudValida(comentario.getComentario())){
            throw new Exception("El comentario debe tener menos de mil palabras");

        }
        if(!calificacionValida(comentario.getCalificacion())){
            throw new Exception("La calificacion no es valida");
        }
        if(!estaComentario(comentario.getId())){
            throw new Exception("El comentario ya existe");
        }

         comentario.setFecha( new Date());
        return comentarioRepo.save(comentario);
    }

    /**
     * Servicio que permite eliminar un comentario
     * @param comentario
     * @throws Exception en caso de que el comentario no exista
     */

    @Override
    public void eliminarComentario(Comentario comentario) throws Exception {
            if(estaComentario(comentario.getId())){
               throw new Exception("El comentario no existe");
            }
           comentarioRepo.delete(comentario);
    }

    /**
     * Servicio que permite listar todos los comentarios
     * @return lista de los comentarios
     * @throws Exception
     */

    @Override
    public List<Comentario> listarComentarios() throws Exception {
        return comentarioRepo.findAll();
    }

    @Override
    public Comentario actualizarComentario(Comentario comentario) throws Exception {
        if(longitudValida(comentario.getComentario())){
            throw new Exception("El comentario debe tener menos de mil palabras");

        }
        if(!calificacionValida(comentario.getCalificacion())){
            throw new Exception("La calificacion no es valida");
        }


        return comentarioRepo.save(comentario);
    }




}
