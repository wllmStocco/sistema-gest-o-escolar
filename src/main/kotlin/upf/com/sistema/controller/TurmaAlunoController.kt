package upf.com.sistema.controller


import org.springframework.web.bind.annotation.*
import upf.com.sistema.dto.TurmaAlunoDTO
import upf.com.sistema.dto.TurmaAlunoResponseDTO
import upf.com.sistema.service.TurmaAlunoService


@RestController
@RequestMapping("/turma-aluno")
class TurmaAlunoController(val service: TurmaAlunoService) {

    @GetMapping
    fun listar(): List<TurmaAlunoResponseDTO> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TurmaAlunoResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody dto: TurmaAlunoDTO) {
        service.cadastrar(dto)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody dto: TurmaAlunoDTO) {
        service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}