package upf.com.sistema.service

import upf.com.sistema.converter.UsuarioConverter
import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO
import upf.com.sistema.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository,
                     private val converter: UsuarioConverter
) {
    fun listar(): List<UsuarioResponseDTO> {
        return repository.findAll()
                .map(converter::toUsuarioResponseDTO)
    }
    fun buscarPorId(id: Long): UsuarioResponseDTO {
        val usuario = repository.findAll().first{ it.id.toLong() == id}
        return converter.toUsuarioResponseDTO(usuario)
    }
    fun cadastrar(usuario: UsuarioDTO) {
        repository.cadastrar(converter.toUsuario(usuario))
    }
    fun atualizar(id: Long, dto: UsuarioDTO) {
        repository.update(id, converter.toUsuario(dto))
    }
    fun deletar(id: Long) {
        repository.deletar(id)
    }

}


