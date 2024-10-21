package upf.com.sistema.dto


import upf.com.sistema.model.TipoUsuario
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime


data class TurmaAlunoDTO(
    @field:NotNull(message = "O ID do usuário não pode ser nulo")
    val idTurmaAluno: Long,

    @field:NotBlank(message = "Nome do usuário não pode estar em branco")
    val idTurma: Long,

    @field:NotBlank(message = "E-mail do usuário não pode estar em branco")
    val idAluno: Long

)