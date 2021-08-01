package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Horario;

public interface HorarioServicio {

    Horario crearHorario(Horario horario) throws Exception;
    Horario actualizarHorario(Horario horario) throws Exception;
}
