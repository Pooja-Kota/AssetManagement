package im.assetmanagement.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import im.assetmanagement.R;
import im.assetmanagement.data.Constants.ApplicationConstants;
import im.assetmanagement.data.Utils.ApiResponse;
import im.assetmanagement.data.Utils.WifiDTO;
import im.assetmanagement.data.Utils.WifiDetailsDTO;
import im.assetmanagement.network_interface.NetworkController;

/**
 * Created by pkota on 12-07-2017.
 */

public class WifiFragment extends BaseFragment {

    private final String TAG = this.getClass().getName();
    String assetId;
    TextView assetIdTextView;
    RecyclerView recyclerView;
    WifiListAdapter wifiListApadter;
    List<WifiDetailsDTO> wifiDetailsDTOList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wifi, container, false);
        assetId = getArguments().getString("assetId");
        wifiDetailsDTOList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.wifiList);
        assetIdTextView = (TextView) view.findViewById(R.id.assetId);
        assetIdTextView.setText(assetId);
        wifiListApadter = new WifiListAdapter(wifiDetailsDTOList, assetId, mContext);

        // recycler view
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(mContext));
        recyclerView.setAdapter(wifiListApadter);
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
                        Log.d(TAG, response.toString());
                        ApiResponse apiResponse = new Gson().fromJson(response.toString(), ApiResponse.class);
                        if (apiResponse.getCode().equals("200")) {
                            WifiDTO wifiDTO = new WifiDTO();
                            try {
                                wifiDTO = new Gson().fromJson(response.get("result").toString(), WifiDTO.class);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, response.toString() + wifiDTO.getWifi().size());
                            wifiListApadter.setPayerResultSet(wifiDTO.getWifi());
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

// Access the RequestQueue through your singleton class.
        NetworkController.getInstance().addToRequestQueue(jsObjRequest);

    }


    @Override
    protected void initListeners() {

    }
}

