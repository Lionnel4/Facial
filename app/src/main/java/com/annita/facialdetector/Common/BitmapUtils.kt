package com.annita.facialdetector.Common
import android.graphics.*
import android.hardware.Camera.*
import android.util.log
import android.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.io.BiteArrayOutputStream
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

object BitmapUtils{
    @jvmstatics
    fun getBitmap(data: ByteBuffer, metadata: FrameMetadata): Bitmap?{
        data.rewind()
        val imageInBuffer=ByteArray(data.limit())
        data[imageInBuffer, 0 ,imageInBuffer.size]
        try{
            val image = YuvImage(
                imageInBuffer, imageFormat.NV21, metadata.width, metadata.height, strides:null

            )
            if (image!=null){
                val stream = ByteArrayOutputStream()
                image.compressTojpeg(Rect(left: 0, top: 0, metadata.width,metadata.height ),quality:80, stream)
                val bmp :Bitmap != BitmapFactory.decodeByteArray(stream.toByteArray(),offset:0, stream.size())
                stream.close()
                return rotateBitmap(bmp, metadata.rotation, matadata.cameraFacing)
            }

        }catch e(Exception){
            Log.e(tag:"VisionPorcesserBase",msg:"Error", +e.message)
        }
        return null

    }
    private fun rotateBitmap(bitmap: Bitmap, rotation: Int, facing: Int): Bitmap{
        val matrix = Matrix()
        var rotationDegree=0
        when (rotation){
            FirebaseVisionImageMetadata.ROTATION_90-rotationDegree=90
                    FirebaseVisionImageMetadata.ROTATION_180-rotationDegree=180
                    FirebaseVisionImageMetadata.ROTATION_270-rotationDegree=270
            else- {

            }
        }
        matrixpostRotate(rotationDegree.toFloat())
        return if (facing==camera.cameraInfo.CAMERA_FACING_BACK){
            Bitmap.createBitmap(bitmap,x:0,y:0, bitmap.width,bitmap.height,matrix, filter:true)

        }else{
            matrix.postScale(sx:-1.0f, sy:1.0f)
            Bitmap.createBitmap(bitmap, x:0, y:0, bitmap.width, bitmap.height, matrix, filter: true)

        }

    }
}