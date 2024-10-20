package upf.com.sistema.repository

import upf.com.sistema.model.TipoUsuario
import upf.com.sistema.model.Usuario
import upf.com.sistema.service.QRCodeService
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class UsuarioRepository(private val qrCodeService: QRCodeService) {

    private val usuarios: MutableList<Usuario> = mutableListOf(
        Usuario(
            id = 1,
            nome = "Ana Silva",
            email = "ana.silva@email.com",
            senha = "senha123hashed",
            tipo = TipoUsuario.ADM,
            dataCriacao = LocalDateTime.now(),
            qrCode = null
        ),
        Usuario(
            id = 2,
            nome = "Carlos Souza",
            email = "carlos.souza@email.com",
            senha = "senha456hashed",
            tipo = TipoUsuario.ALUNO,
            dataCriacao = LocalDateTime.now(),
            qrCode = Base64.getEncoder().encodeToString(qrCodeService.generateQRCode("2"))
        ),
        Usuario(
            id = 3,
            nome = "Maria Santos",
            email = "maria.santos@email.com",
            senha = "senha789hashed",
            tipo = TipoUsuario.PROFESSOR,
            dataCriacao = LocalDateTime.now(),
            qrCode = null
        ),
        Usuario(
            id = 4,
            nome = "José Oliveira",
            email = "jose.oliveira@email.com",
            senha = "senha012hashed",
            tipo = TipoUsuario.ALUNO,
            dataCriacao = LocalDateTime.now(),
            qrCode = Base64.getEncoder().encodeToString(qrCodeService.generateQRCode("4"))
        )
    )

    private var idCont = 5L // Próximo ID disponível

    fun cadastrar(usuario: Usuario) {
        val novoId = idCont++
        val qrCodeImage = Base64.getEncoder().encodeToString(qrCodeService.generateQRCode(novoId.toString()))
        val usuarioComQrCode = if (usuario.tipo == TipoUsuario.ALUNO) {
            usuario.copy(id = novoId.toInt(), qrCode = qrCodeImage, dataCriacao = LocalDateTime.now(),)
        } else {
            usuario.copy(id = novoId.toInt(), dataCriacao = LocalDateTime.now())
        }
        usuarios.add(usuarioComQrCode)
    }

    fun update(id: Long, usuarioAtualizado: Usuario) {
        val usuarioIndex = usuarios.indexOfFirst { it.id.toLong() == id }
        if (usuarioIndex != -1) {
            val qrCodeImage = Base64.getEncoder().encodeToString(qrCodeService.generateQRCode(id.toString()))
            val usuarioComQrCode = if (usuarioAtualizado.tipo == TipoUsuario.ALUNO) {
                usuarioAtualizado.copy(qrCode = qrCodeImage, dataCriacao = usuarios[usuarioIndex].dataCriacao)
            } else {
                usuarioAtualizado.copy(qrCode = null, dataCriacao = usuarios[usuarioIndex].dataCriacao)
            }
            usuarios[usuarioIndex] = usuarioComQrCode
        }
    }

    fun findAll(): List<Usuario> {
        return usuarios
    }

    fun deletar(id: Long) {
        val usuario = usuarios.firstOrNull { it.id.toLong() == id }
        usuario?.let {
            usuarios.remove(it)
        }
    }
}