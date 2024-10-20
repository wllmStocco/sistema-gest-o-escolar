package upf.com.sistema.controller

import jakarta.validation.Valid
import upf.com.sistema.service.UsuarioService
import org.springframework.web.bind.annotation.*
import upf.com.sistema.dto.*
import upf.com.sistema.service.MateriaService
import upf.com.sistema.service.TurmaService

@RestController
@RequestMapping("/turmas")
class TurmaController(private val service: TurmaService) {

    @GetMapping
    fun listar(): List<TurmaResponseDTO> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TurmaResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: TurmaDTO) {
        service.cadastrar(dto)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: TurmaDTO) {
        service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}