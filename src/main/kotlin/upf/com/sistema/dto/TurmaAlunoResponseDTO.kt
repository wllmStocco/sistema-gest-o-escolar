package upf.com.sistema.dto

import upf.com.sistema.model.TipoUsuario
import java.time.LocalDateTime

data class UsuarioResponseDTO(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val tipo: TipoUsuario,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    var qrCode: String? = null
)