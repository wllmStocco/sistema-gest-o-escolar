package upf.com.sistema.converter

import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO
import upf.com.sistema.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioConverter {
    fun toUsuario(dto: UsuarioDTO): Usuario {
        return Usuario(
            id = dto.id,
            nome = dto.nome,
            email = dto.email,
            senha = dto.senha,
            tipo = dto.tipo,
            dataCriacao = dto.dataCriacao,
            qrCode = dto.qrCode
        )
    }

    fun toUsuarioResponseDTO(usuario: Usuario): UsuarioResponseDTO {
        return UsuarioResponseDTO(
            id = usuario.id,
            nome = usuario.nome,
            email = usuario.email,
            senha = usuario.senha,
            tipo = usuario.tipo,
            dataCriacao = usuario.dataCriacao,
            qrCode = usuario.qrCode
        )
    }
}