package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.repositorios.HorarioRepo;
import org.springframework.stereotype.Service;

@Service
public class HorarioServicioImpl implements HorarioServicio {

    private HorarioRepo horarioRepo;

    public HorarioServicioImpl(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    @Override
    public Horario crearHorario(Horario horario) throws Exception {

        if (horario.getDia().isEmpty()) {
            throw new Exception("El dia es obligatorio");
        }
        if (horario.getHoraApertura() == null) {
            throw new Exception("La hora de apertura es obligatoria");
        }
        if (horario.getHoraCierre() == null) {
            throw new Exception("La hora de cierre es obligatoria");
        }
        
        return horarioRepo.save(horario);
    }

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {


        return horarioRepo.save(horario);
    }
}
