package upf.com.sistema.repository

import org.springframework.stereotype.Repository
import upf.com.sistema.model.TurmaAluno

@Repository
class TurmaAlunoRepository {

    private val turmaAlunos: MutableList<TurmaAluno> = mutableListOf(
        TurmaAluno(idTurmaAluno = 1, idTurma = 1, idAluno = 2),
        TurmaAluno(idTurmaAluno = 2, idTurma = 1, idAluno = 4),
        TurmaAluno(idTurmaAluno = 3, idTurma = 2, idAluno = 2),
        TurmaAluno(idTurmaAluno = 4, idTurma = 3, idAluno = 4)
    )

    private var idCont = 5L // Próximo ID disponível

    fun findAll(): List<TurmaAluno> = turmaAlunos

    fun findById(id: Long): TurmaAluno? {
        return turmaAlunos.firstOrNull { it.idTurmaAluno.toLong() == id }
    }

    fun cadastrar(turmaAluno: TurmaAluno) {
        val novaTurmaAluno = turmaAluno.copy(idTurmaAluno = idCont++)
        turmaAlunos.add(novaTurmaAluno)
    }

    fun update(id: Long, turmaAlunoAtualizado: TurmaAluno) {
        val index = turmaAlunos.indexOfFirst { it.idTurmaAluno.toLong() == id }
        if (index != -1) {
            turmaAlunos[index] = turmaAlunoAtualizado.copy(idTurmaAluno = id)
        }
    }

    fun deletar(id: Long): Boolean {
        return turmaAlunos.removeIf { it.idTurmaAluno.toLong() == id }
    }
}