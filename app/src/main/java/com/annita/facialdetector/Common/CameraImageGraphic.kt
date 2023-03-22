package com.annita.facialdetector.Common

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.annita.facialdetector.UIcomponents.GraphicOverlay

class CameraImageGraphic(overlay: GraphicOverlay?,private val bitmap: Bitmap): GraphicOverlay.Graphic(Overlay!!){
    override fun draw(canvas: Canvas?){
        canvas!!.drawBitmap(bitmap,null, Rect(left:0, top:0, canvas!!.width, canavas!!.height),paint null)
    }
}