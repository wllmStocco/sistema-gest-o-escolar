package upf.com.sistema.converter

import org.springframework.stereotype.Component
import upf.com.sistema.dto.MateriaDTO
import upf.com.sistema.dto.MateriaResponseDTO
import upf.com.sistema.model.Materia

@Component
class MateriaConverter {

    fun toMateria(dto: MateriaDTO): Materia {
        return Materia(
            id = dto.id ?: 0L,
            nome = dto.nome
        )
    }

    fun toMateriaResponseDTO(materia: Materia): MateriaResponseDTO {
        return MateriaResponseDTO(
            id = materia.id,
            nome = materia.nome
        )
    }
}