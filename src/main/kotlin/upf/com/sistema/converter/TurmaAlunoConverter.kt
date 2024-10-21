package upf.com.sistema.converter

import org.springframework.stereotype.Component
import upf.com.sistema.dto.TurmaAlunoDTO
import upf.com.sistema.dto.TurmaAlunoResponseDTO
import upf.com.sistema.model.TurmaAluno

@Component
class TurmaAlunoConverter {

    fun toTurmaAluno(dto: TurmaAlunoDTO): TurmaAluno {
        return TurmaAluno(
            idTurmaAluno = dto.idTurmaAluno,
            idTurma = dto.idTurma,
            idAluno = dto.idAluno
        )
    }

    fun toTurmaAlunoResponseDTO(turmaAluno: TurmaAluno): TurmaAlunoResponseDTO {
        return TurmaAlunoResponseDTO(
            idTurmaAluno = turmaAluno.idTurmaAluno,
            idTurma = turmaAluno.idTurma,
            idAluno = turmaAluno.idAluno
        )
    }
}