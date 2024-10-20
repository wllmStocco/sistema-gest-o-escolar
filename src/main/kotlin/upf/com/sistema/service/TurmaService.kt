package upf.com.sistema.service


import org.springframework.stereotype.Service
import upf.com.sistema.converter.TurmaConverter
import upf.com.sistema.dto.TurmaDTO
import upf.com.sistema.dto.TurmaResponseDTO
import upf.com.sistema.repository.TurmaRepository

@Service
class TurmaService(private val repository: TurmaRepository,
                   private val converter: TurmaConverter) {

    fun listar(): List<TurmaResponseDTO> {
        return repository.findAll().map(converter::toTurmaResponseDTO)
    }

    fun buscarPorId(id: Long): TurmaResponseDTO {
        val turma = repository.findById(id)
            ?: throw IllegalArgumentException("Turma com ID $id não encontrada.")
        return converter.toTurmaResponseDTO(turma)
    }

    fun cadastrar(dto: TurmaDTO) {
        repository.cadastrar(converter.toTurma(dto))
    }

    fun atualizar(id: Long, dto: TurmaDTO) {
        repository.update(id, converter.toTurma(dto))
    }

    fun deletar(id: Long) {
        val sucesso = repository.deletar(id)
        if (!sucesso) {
            throw IllegalArgumentException("Falha ao deletar turma com ID $id. Não encontrada.")
        }
    }
}