package upf.com.sistema.controller

import jakarta.validation.Valid
import upf.com.sistema.service.UsuarioService
import org.springframework.web.bind.annotation.*
import upf.com.sistema.dto.UsuarioDTO
import upf.com.sistema.dto.UsuarioResponseDTO

@RestController
@RequestMapping("/usuarios")
class UsuarioController(val service: UsuarioService) {
    @GetMapping
    fun listar(): List<UsuarioResponseDTO> {
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): UsuarioResponseDTO {
        return service.buscarPorId(id)
    }
    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: UsuarioDTO) {
        service.cadastrar(dto)
    }
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: UsuarioDTO) {
        service.atualizar(id, dto)
    }
    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}

