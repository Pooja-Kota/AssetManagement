package im.assetmanagement.view;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import im.assetmanagement.R;
import im.assetmanagement.data.Constants.ApplicationConstants;
import im.assetmanagement.data.Utils.ApiResponse;
import im.assetmanagement.data.Utils.UserDTO;
import im.assetmanagement.network_interface.NetworkController;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getName();
    EditText userName, pin, confirmPin, firstName, lastName;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName = (EditText) findViewById(R.id.signUpUserName);
        pin = (EditText) findViewById(R.id.signUpPassword);
        firstName = (EditText) findViewById(R.id.signUpFirsName);
        lastName = (EditText) findViewById(R.id.signUpLastName);
        confirmPin = (EditText) findViewById(R.id.signUpConfirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        initListeners();
    }

    protected void initListeners() {
        signUpButton.setOnClickListener(this);
    }

    public void signUpRequest(UserDTO user) {
        showProgressDialog();
        String userJson = new Gson().toJson(user);
        Log.d(TAG, "loginRequest() - " + userJson);
        JsonObjectRequest jsObjRequest = null;
        try {
            jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, ApplicationConstants.signUpUrl, new JSONObject(userJson), new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            dismissProgressDialog();
                            ApiResponse apiResponse = new Gson().fromJson(response.toString(), ApiResponse.class);
                            if (apiResponse.getCode().equals("200")) {
                                Intent dashboard = new Intent(SignUpActivity.this, LoginActivity.class);
                                SignUpActivity.this.startActivity(dashboard);
                            } else {

                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "loginRequest() - " + error);
                            dismissProgressDialog();

                        }
                    });
            jsObjRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
        } catch (JSONException e) {
            dismissProgressDialog();
        }

// Access the RequestQueue through your singleton class.
        NetworkController.getInstance().addToRequestQueue(jsObjRequest);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            UserDTO user = new UserDTO();
            user.setUsername(userName.getText().toString());
            user.setPin(pin.getText().toString());
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setMACId(getMacAddress(this));
            signUpRequest(user);
        } else {
        }

    }

    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;
    }
}
