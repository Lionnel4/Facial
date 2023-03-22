package com.annita.facialdetector.Common

import com.annita.facialdetector.UIcomponents.GraphicOverlay

abstract class VisionProcessorBase {
    @guardedby(value: "this")
    private var latestImage: ByteBuffer? = null

    @guardedby(value: "this")
    private var latestImageMetadata: FrameMetadata? = null

    @guardedby(value: "this")
    private var processingImage: ByteBuffer = null

    @guardedby(value: "this")
    private var processingMetadata: FrameMetadata? = null

    override fun process(
        data: ByteBuffer?,
        frameMetadata: FrameMetadata?,
        graphicOverlay: GraphicOverlay
    ) {
        latestImage = data
        latestImageMetadata = frameMetadata
        if (processingImage == null && processingMetadata == null) {
            processLatestImage(graphicOverlay!!)

        }
    }

    override fun processLatestImage(graphicOverlay: GraphicOverlay) {
        processingImage = latestImage
        processingMetaData = latestImageMetaData
        latestImage = null
        latestImageMetaData = null
        if (processingImage != null && processingMetadata == null) {


        }
    }
}