package upf.com.sistema.converter

import org.springframework.stereotype.Component
import upf.com.sistema.dto.TurmaDTO
import upf.com.sistema.dto.TurmaResponseDTO
import upf.com.sistema.model.Turma

@Component
class TurmaConverter {

    fun toTurma(dto: TurmaDTO): Turma {
        return Turma(
            id = dto.id ?: 0L,  // Se o ID for nulo no DTO, atribui 0L como padrão
            nome = dto.nome,
            idMateria = dto.idMateria.toString(),
            idProfessor = dto.idProfessor.toString()
        )
    }

    fun toTurmaResponseDTO(turma: Turma): TurmaResponseDTO {
        return TurmaResponseDTO(
            id = turma.id,
            nome = turma.nome,
            idMateria = turma.idMateria,
            idProfessor = turma.idProfessor
        )
    }
}