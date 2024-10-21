package upf.com.sistema.service

import upf.com.sistema.converter.UsuarioConverter
import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO
import upf.com.sistema.repository.UsuarioRepository
import org.springframework.stereotype.Service
import upf.com.sistema.converter.MateriaConverter
import upf.com.sistema.dto.MateriaDTO
import upf.com.sistema.dto.MateriaResponseDTO
import upf.com.sistema.repository.MateriaRepository

@Service
class MateriaService(
    private val repository: MateriaRepository,
    private val converter: MateriaConverter
) {

    fun listar(): List<MateriaResponseDTO> {
        return repository.findAll()
            .map(converter::toMateriaResponseDTO)
    }

    fun buscarPorId(id: Long): MateriaResponseDTO {
        val materia = repository.findById(id)
            ?: throw NoSuchElementException("Matéria com ID $id não encontrada")
        return converter.toMateriaResponseDTO(materia)
    }

    fun cadastrar(materiaDTO: MateriaDTO) {
        repository.cadastrar(converter.toMateria(materiaDTO))
    }

    fun atualizar(id: Long, dto: MateriaDTO) {
        repository.update(id, converter.toMateria(dto))
    }

    fun deletar(id: Long) {
        repository.deletar(id)
    }
}


