package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.view.RegistrationView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 20/05/2017.
 */

public class RegistrationPresenter {
    private RegistrationView registrationView;

    public RegistrationPresenter(RegistrationView registrationView) {
        this.registrationView = registrationView;
    }

    public void registration(final String username, final String password, final String firstName, final String lastName) {
        API.registration(username, password, firstName, lastName, new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {
                registrationView.onRegistrationSuccess(response);
            }

            @Override
            public void onError(String errorMessage) {
                registrationView.onRegistrationFail();
            }
        });
    }
}
