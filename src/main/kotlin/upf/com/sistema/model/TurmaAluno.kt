package upf.com.sistema.model

import java.time.LocalDateTime

data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val tipo: TipoUsuario,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    var qrCode: String? = null
)

enum class TipoUsuario {
    ALUNO,
    PROFESSOR,
    ADM
}

