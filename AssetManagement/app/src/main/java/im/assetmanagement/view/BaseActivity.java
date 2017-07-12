package im.assetmanagement.view;

/**
 * Created by pkota on 08-01-2017.
 */


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import im.assetmanagement.R;
import im.assetmanagement.data.Constants.ErrorCodesConstants;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void longToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null)
            initProgressDialog();
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void showProgressDialog(String msg) {
        if (mProgressDialog == null)
            initProgressDialog();
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    public void dismissProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        } catch (Exception e) {
        } finally {
            this.mProgressDialog = null;
        }
    }

    /**
     * Fragement  navigation method
     *
     * @param tagName        : identifying the fragment with tagname
     * @param fragment       ; navigate to screen
     * @param addToBackStack : adding the the backstacj
     */
    public void fragmentTransaction(String tagName, Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.containerView, fragment, tagName);
            if (addToBackStack) {
                transaction.addToBackStack(tagName);
            }
            transaction.commit();
        }
    }

    public void handleErrors(Object errorCode) {
        Gson gson = new Gson();
        String error = gson.toJson(errorCode);
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        List<Integer> errorList = gson.fromJson(error, listType);
        if (errorList != null && !errorList.isEmpty()) {
            showAlert(ErrorCodesConstants.getErrorMessage(errorList.get(0)));
        }
    }

    public void showAlert(String message) {
        final android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message)
                .setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });


        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
