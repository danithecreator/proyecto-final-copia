package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface define el deposito de datos de horario
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface HorarioRepo extends JpaRepository<Horario,Integer> {

}
