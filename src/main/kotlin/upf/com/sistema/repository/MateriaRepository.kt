package upf.com.sistema.repository


import org.springframework.stereotype.Repository
import upf.com.sistema.model.Materia

@Repository
class MateriaRepository {

    private val materias: MutableList<Materia> = mutableListOf(
        Materia(id = 1, nome = "Matemática"),
        Materia(id = 2, nome = "História"),
        Materia(id = 3, nome = "Física"),
        Materia(id = 4, nome = "Química")
    )

    private var idCont = 5L // Próximo ID disponível

    // Função para cadastrar uma nova matéria
    fun cadastrar(materia: Materia): Materia {
        val novaMateria = materia.copy(id = idCont++)
        materias.add(novaMateria)
        return novaMateria
    }

    // Função para atualizar uma matéria
    fun update(id: Long, materiaAtualizada: Materia): Boolean {
        val materiaIndex = materias.indexOfFirst { it.id.toLong() == id }
        return if (materiaIndex != -1) {
            materias[materiaIndex] = materiaAtualizada.copy(id = id)
            true
        } else {
            false
        }
    }

    // Função para retornar todas as matérias
    fun findAll(): List<Materia> {
        return materias
    }

    // Função para deletar uma matéria
    fun deletar(id: Long): Boolean {
        return materias.removeIf { it.id.toLong() == id }
    }

    // Função para buscar uma matéria pelo ID
    fun findById(id: Long): Materia? {
        return materias.firstOrNull { it.id.toLong() == id }
    }
}
