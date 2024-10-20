package upf.com.sistema.repository


import org.springframework.stereotype.Repository
import upf.com.sistema.model.Turma

@Repository
class TurmaRepository {

    private val turmas: MutableList<Turma> = mutableListOf(
        Turma(id = 1, nome = "Turma A - Matemática", idMateria = 1.toString(), idProfessor = 3.toString()),
        Turma(id = 2, nome = "Turma B - História", idMateria = 2.toString(), idProfessor = 3.toString()),
        Turma(id = 3, nome = "Turma C - Física", idMateria = 3.toString(), idProfessor = 3.toString()),
        Turma(id = 4, nome = "Turma D - Química", idMateria = 4.toString(), idProfessor = 3.toString())
    )

    private var idCont = 5L // Próximo ID disponível

    // Retorna todas as turmas
    fun findAll(): List<Turma> = turmas

    // Busca uma turma por ID
    fun findById(id: Long): Turma? {
        return turmas.firstOrNull { it.id == id }
    }

    // Cadastra uma nova turma
    fun cadastrar(turma: Turma) {
        val novaTurma = turma.copy(id = idCont++)
        turmas.add(novaTurma)
    }

    // Atualiza uma turma existente
    fun update(id: Long, turmaAtualizada: Turma) {
        val turmaIndex = turmas.indexOfFirst { it.id == id }
        if (turmaIndex != -1) {
            turmas[turmaIndex] = turmaAtualizada.copy(id = id)
        }
    }

    // Deleta uma turma pelo ID
    fun deletar(id: Long): Boolean {
        return turmas.removeIf { it.id == id }
    }
}