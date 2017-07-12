package im.assetmanagement.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import im.assetmanagement.R;
import im.assetmanagement.data.Constants.ApplicationConstants;
import im.assetmanagement.network_interface.NetworkController;

/**
 * Created by pkota on 12-07-2017.
 */

public class WifiFragment extends BaseFragment {

    private final String TAG = this.getClass().getName();
    String assetId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wifi, container, false);
        getWifiRecords(assetId, 10);
        return view;
    }

    public void getWifiRecords(String assetId, int limit) {
        showProgressDialog();
        JsonObjectRequest jsObjRequest = null;
        jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, ApplicationConstants.getWifiDetailsUrl + assetId + "/" + limit, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        dismissProgressDialog();
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

// Access the RequestQueue through your singleton class.
        NetworkController.getInstance().addToRequestQueue(jsObjRequest);

    }


    @Override
    protected void initListeners() {

    }
}

