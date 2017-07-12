package im.assetmanagement.view;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import im.assetmanagement.data.Utils.AssetDTO;
import im.assetmanagement.data.Utils.UserDTO;
import im.assetmanagement.data.Utils.WifiDTO;
import im.assetmanagement.network_interface.NetworkController;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getName();
    EditText userName, password;
    TextView signUpLink, forgotPasswordLink;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);
        signUpLink = (TextView) findViewById(R.id.loginSignUpLink);
        forgotPasswordLink = (TextView) findViewById(R.id.loginForgotPasswordLink);
        loginButton = (Button) findViewById(R.id.loginButton);
        initListeners();
    }

    protected void initListeners() {
        signUpLink.setOnClickListener(this);
        forgotPasswordLink.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    public void loginRequest(UserDTO user) {
        showProgressDialog();
        String userJson = new Gson().toJson(user);
        Log.d(TAG, "loginRequest() - " + userJson);
        JsonObjectRequest jsObjRequest = null;
        try {
            jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, ApplicationConstants.loginUrl, new JSONObject(userJson), new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            dismissProgressDialog();
                            Log.d(TAG, response.toString());
                            ApiResponse apiResponse = new Gson().fromJson(response.toString(), ApiResponse.class);
                            if (apiResponse.getCode().equals("200")) {
                                Log.d(TAG, apiResponse.getResult().toString());
                                AssetDTO assetDTO = null;
                                try {
                                    assetDTO = new Gson().fromJson(response.get("result").toString(), AssetDTO.class);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent dashboard = new Intent(LoginActivity.this, DashboardActivity.class);
                                dashboard.putExtra("assetId", assetDTO.getAssetId());
                                dashboard.putExtra("username", userName.getText().toString());
                                LoginActivity.this.startActivity(dashboard);
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
        if (v.getId() == R.id.loginSignUpLink) {
            Intent signUp = new Intent(LoginActivity.this, SignUpActivity.class);
            LoginActivity.this.startActivity(signUp);
        } else if (v.getId() == R.id.loginForgotPasswordLink) {
        } else if (v.getId() == R.id.loginButton) {
            UserDTO user = new UserDTO();
            user.setUsername(userName.getText().toString());
            user.setPin(password.getText().toString());
            user.setMACId(getMacAddress(this));
            loginRequest(user);
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
