package upf.com.sistema.dto


import upf.com.sistema.model.TipoUsuario
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime


data class UsuarioDTO(
    @field:NotNull(message = "O ID do usuário não pode ser nulo")
    val id: Int,

    @field:NotBlank(message = "Nome do usuário não pode estar em branco")
    val nome: String,

    @field:NotBlank(message = "E-mail do usuário não pode estar em branco")
    val email: String,

    @field:NotBlank(message = "Senha do usuário não pode estar em branco")
    val senha: String,

    @field:NotNull(message = "O tipo do usuário não pode ser nulo")
    val tipo: TipoUsuario,

    @field:NotNull(message = "Data de criação do usuário não pode ser nula")
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @field:NotBlank(message = "QR Code não pode estar em branco")
    val qrCode: String? = null
)