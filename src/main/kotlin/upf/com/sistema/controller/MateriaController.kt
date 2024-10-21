package upf.com.sistema.controller

import jakarta.validation.Valid
import upf.com.sistema.service.UsuarioService
import org.springframework.web.bind.annotation.*
import upf.com.sistema.dto.MateriaDTO
import upf.com.sistema.dto.MateriaResponseDTO
import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO
import upf.com.sistema.service.MateriaService

@RestController
@RequestMapping("/materias")
class MateriaController(private val service: MateriaService) {

    @GetMapping
    fun listar(): List<MateriaResponseDTO> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): MateriaResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: MateriaDTO) {
        service.cadastrar(dto)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: MateriaDTO) {
        service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}

