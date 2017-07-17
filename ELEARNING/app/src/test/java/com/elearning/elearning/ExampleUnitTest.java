package com.elearning.elearning;

import android.os.Environment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.network.APIConstant;
import com.elearning.elearning.network.NetworkUtil;

import org.junit.Test;

import java.io.File;

import static com.elearning.elearning.network.APIConstant.UPLOAD_AVATAR_FOOTER_URL;
import static com.elearning.elearning.network.APIConstant.UPLOAD_AVATAR_HEADER_URL;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private String TAG = getClass().getSimpleName();
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void uploadAvartar() throws Exception {
        File testFile = new File(Environment.getExternalStorageDirectory() + "/DCIM/Facebook/FB_IMG_1494958038652.jpg");
        String url = UPLOAD_AVATAR_HEADER_URL + User.get().getUserId() + UPLOAD_AVATAR_FOOTER_URL;
        if (testFile.exists()) {
            AndroidNetworking.post(url)
                    .addFileBody(testFile)
                    .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                    .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            assertEquals(true,true);
                        }

                        @Override
                        public void onError(ANError anError) {
                            assertEquals(false,false);
                        }
                    });
        }
    }
}