package com.annita.facialdetector.UIcomponents

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.google.android.gms.vision.CameraSource

class GraphicOverlay (context: Context, attributeSet: AttributeSet?) : View(context, attributeSet){

    private val lock = Any()
    private var previewWidth = 0
    private var widthScaleFactor = 1.0f
    private var previewHeight = 0
    private var heightScaleFactor = 1.0f
    private var facing = CameraSource.CAMERA_FACING_BACK
    private val graphics: MutableList<Graphic> = ArrayList()

    /** Removes all graphics from the overlay. */
    fun clear(){
        synchronized(lock) { graphics.clear() }
        postInvalidate()
    }

    /** Adds a graphic to the overlay. */
    fun add(graphic: Graphic) {
        synchronized(lock) { graphics.add(graphic)}
    }

    /** Removes a graphic from the overlay. */
    fun remove(graphic: Graphic) {
        synchronized(lock) { graphics.remove(graphic)}
        postInvalidate()
    }

    abstract class Graphic(private val overlay: GraphicOverlay){
        abstract fun draw(canvas: Canvas?)

        /**
         * Adjusts a horizontal value of the supplied value from the preview scale to the view scale.
         */
        protected fun scaleX(horizontal: Float): Float{
            return horizontal * overlay.widthScaleFactor
        }

        /** Returns the application context of the app. */
        val applicationContext: Context
            get() = overlay.context.applicationContext

        /**
         * Adjusts the x coordinate from the preview's coordinate system to the view coordinate system.
         */
        fun translateX(x: Float): Float{
            return if (overlay.facing == CameraSource.CAMERA_FACING_FRONT){
                overlay.width - scaleX(x)
            } else (
                    scaleX(x)
            )
        }

        fun postInvalidate() {
            overlay.postInvalidate()
        }
    }

    /**
     * Sets the camera attributes for size and facting direction, which informs how to transform image
     * coordinates later
     */
    fun setCameraInfo(previewWidth: Int, previewHeight: Int, facing: Int){
        synchronized(lock) {
            this.previewWidth = previewWidth
            this.previewHeight = previewHeight
            this.facing = facing
        }
        postInvalidate()
    }


    /** Draws the overlay with its associated graphic objets. */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        synchronized(lock) {
            if (previewWidth != 0 && previewHeight != 0) {
                widthScaleFactor = canvas.width.toFloat() / previewWidth.toFloat()
                heightScaleFactor = canvas.height.toFloat() / previewHeight.toFloat()

            }
            for (graphic in graphics) {
                graphic.draw(canvas)
            }
        }
    }
}