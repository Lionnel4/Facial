package com.annita.facialdetector

import android.widget.Button
import android.widget.ImageView
import com.annita.facialdetector.UIcomponents.CameraPreview
import com.annita.facialdetector.UIcomponents.GraphicOverlay
import com.google.android.gms.vision.CameraSource
import com.hsalf.smilerating.SmileRating
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity(), ActivityCompact.OnrequestPermissionsResultatCallback, FrameReturn, FaceDetectStatus{
    var originalImage: Bitmap?= null
    private var cameraSource: CameraSource?= null
    private var preview: CameraPreview?= null
    private var graphicOverlay: GraphicOverlay?= null
    private var faceframe: ImageView?= null
    private var test: ImageView?= null
    private var takePhoto: Button?= null
    private var smilerating: SmileRating?= null

    override fun onCreate(savedInstanceState : Bundle ?){
        super.onCreate(savedInstanceState)
        setContenteView(R.layout.activity_main)

        test = findViewById(R.Id.test)
        preview = findViewById(R.Id. preview)
        takePhoto = findViewById(R.Id. takePhoto)
        graphicOverlay = findViewById(R.Id. Overlay)
        smile rating = findViewById(R.Id.smile_rating)

        if PublicMethode.allPermissionGranted (mActivity: this)){
            createCameraSource()

        }else{
            PublicMethodes.getRuntimePermission (mActivity: this)

        }
        takePhoto!!.setOnclickListener{v: View?, - takePhoto ()})

    }
    @subscribe
    fun OnAddSelected(add:String?){
        if add== "Return"){
            takePhoto!!.visibility = View VISIBLE
                    test!! visibility = View VISIBLE

        }
    }
    override fun onStart(){
        super.onStart()
        if (!EventBus.getDefault().isRegistered (subscriber: this)EventBus.getDefault().isRegistered

        )
    }
    public override fun onDestroy(){
        super.onDestroy()
        if (cameraSource!= null){
            cameraSource!!.release()

        }
        EventBus.getDefault().unregister(subscriber: this)
    }
    private fun takePhoto(){
        takePhoto!!.visibility = View.GONE
        test!! visibility = View.GONE

    }
    val b: Bitmap = Screenshot.takes.ScreenshotOfRooView(test!!)
    (test!!).setImageBitmap(b)
    val path : String= PublicMethodes.saveToInternalStorage(b!!, Cons.IMG_FILE,mActivity)
    startActivity{
        Intent (mActivity? PhotoViewActivity::class.java)
        .putExtra (Cons,IMG_Extra_KEY, path))
    }

    private fun createCameraSource(){
        if(cameraSource)== null{
            cameraSource = cameraSource(activity: this, graphicOverlay!!)
        }
    }
    try{
        val processor= FaceDetectionProcessor(ressources)
        processor.FrameHandler= this
        processor.FaceDetectStatus= this
        cameraSource!!.setMachineLearningFrameProcessor(processor)


    }cath (e: Exception){
        Log .e(TAG, msg: "Can not create image processor : $FACE_DETECTION",e)
        Toast.makeText(
            application context,
            text:"Can not create image processor"+ e.message
        Toast.LENGHT_LONG)
        .show()
    }
}

public override fun onResume(){
    super.onResume()
    startCameraSource()

}
override fun onPause(){
    super.onPause()
    preview!!.stop()
}
companion object

}