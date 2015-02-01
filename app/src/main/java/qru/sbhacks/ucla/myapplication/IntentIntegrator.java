//package qru.sbhacks.ucla.myapplication;
//
//import android.hardware.camera2.CameraManager;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.Binarizer;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.PlanarYUVLuminanceSource;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.QRCodeReader;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.encoder.Encoder;
//import com.google.zxing.qrcode.detector.FinderPattern;
//import com.google.zxing.common.*;
///**
// * Created by Jonathan on 1/31/2015.
// */
//public class IntentIntegrator {
//    private QRCodeReader qr;
//    private QRCodeWriter qw;
//    private Encoder en;
//    private FinderPattern fp;
//public void IntentIntegrator(){
//    qr = new QRCodeReader();
//
//
//    qr.decode();
//
//    qw = new QRCodeWriter();
//    BitMatrix bm;
//    try {
//         bm =  qw.encode("test", BarcodeFormat.QR_CODE, 128, 128);
//    }catch (WriterException e)
//    {
//
//    }
//    PlanarYUVLuminanceSource source = CameraManager;
//    BinaryBitmap bbm = new BinaryBitmap(new HybridBinarizer());
//}
//}
