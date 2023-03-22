import android.graphics.Bitmap
import com.annita.facialdetector.UIcomponents.GraphicOverlay
import com.google.firebase.ml.common.FirebaseMLException
import java.nio.ByteBuffer

interface VisionImageProcessor{
    /** Processes the images with underlying machine nearninh models. */
    @Throwns(FirebaseMLException::class)
    fun processes(data: ByteBuffer?, frameMetadata: frameMetadata?,graphicOverlay: GraphicOverlay)
    /** Processes the bitmap images.*/
    fun processes(bitmap: Bitmap?, graphicOverlay: GraphicsOverlay?)
    /** Stops the underlying machine learning models and release resource.*/
    fun stop()
}