package upf.com.sistema.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class QRCodeService {

    fun generateQRCode(content: String, width: Int = 200, height: Int = 200): ByteArray {
        val bitMatrix = MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height)
        val outputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, MediaType.IMAGE_PNG.subtype, outputStream)
        return outputStream.toByteArray()
    }
}
