package upf.com.sistema.service

import upf.com.sistema.converter.UsuarioConverter
import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO
import upf.com.sistema.repository.UsuarioRepository
import org.springframework.stereotype.Service
import upf.com.sistema.converter.TurmaAlunoConverter
import upf.com.sistema.dto.TurmaAlunoDTO
import upf.com.sistema.dto.TurmaAlunoResponseDTO
import upf.com.sistema.repository.TurmaAlunoRepository

@Service
class TurmaAlunoService(private val repository: TurmaAlunoRepository,
                        private val converter: TurmaAlunoConverter
) {

    fun listar(): List<TurmaAlunoResponseDTO> {
        return repository.findAll().map(converter::toTurmaAlunoResponseDTO)
    }

    fun buscarPorId(id: Long): TurmaAlunoResponseDTO {
        val turmaAluno = repository.findById(id)
            ?: throw IllegalArgumentException("TurmaAluno com ID $id não encontrado.")
        return converter.toTurmaAlunoResponseDTO(turmaAluno)
    }

    fun cadastrar(dto: TurmaAlunoDTO) {
        repository.cadastrar(converter.toTurmaAluno(dto))
    }

    fun atualizar(id: Long, dto: TurmaAlunoDTO) {
        repository.update(id, converter.toTurmaAluno(dto))
    }

    fun deletar(id: Long) {
        val sucesso = repository.deletar(id)
        if (!sucesso) {
            throw IllegalArgumentException("Falha ao deletar TurmaAluno com ID $id. Não encontrado.")
        }
    }
}