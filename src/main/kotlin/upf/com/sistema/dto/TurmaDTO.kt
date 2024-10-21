package upf.com.sistema.dto


import upf.com.sistema.model.TipoUsuario
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime


data class TurmaDTO(
    @field:NotNull(message = "O ID do usuário não pode ser nulo")
    val id: Int,

    @field:NotBlank(message = "Nome do usuário não pode estar em branco")
    val nome: String,

    @field:NotBlank(message = "Nome da matéria não pode estar em branco")
    val idMateria: Long,

    @field:NotBlank(message = "Nome do professor não pode estar em branco")
    val idProfessor: Long
)

