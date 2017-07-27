package com.elearning.elearning.fragment;

import android.util.Log;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.elearning.elearning.prefs.Constant.CHAR_SPLIT_SCAN;

/**
 * Created by vomin on 27/07/2017.
 */

public class QRScanFragment extends BaseFragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView qrScan;

    @Override
    public void initView() {
        qrScan = (ZXingScannerView) view.findViewById(R.id.qrScan);

    }

    @Override
    public void initValue() {
        qrScan.setResultHandler(this);
        qrScan.startCamera();
    }

    @Override
    public void initAction() {

    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_qr_scan;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        qrScan.stopCamera();
        super.onPause();
    }
    @Override
    public void handleResult(Result result) {
        Log.d("QR Scan","On result");
        playBeep();
        qrScan.stopCamera();
        try {
            String[] data=result.getText().split(CHAR_SPLIT_SCAN);
            int idCourse = Integer.parseInt(data[0]);
            String nameCourse=data[1];
            Toast.makeText(context, getResources().getString(R.string.cap_loading), Toast.LENGTH_LONG).show();
            Thread.sleep(500);
            getMainActivity().goToListLesson(idCourse,nameCourse);
        }catch (Exception ex ){
            Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_LONG).show();
            qrScan.setResultHandler(this);
            qrScan.startCamera();
        }

    }
}
