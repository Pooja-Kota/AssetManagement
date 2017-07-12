package im.assetmanagement.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.assetmanagement.R;

/**
 * Created by pkota on 12-07-2017.
 */

public class ApplicationsFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        return view;
    }

    @Override
    protected void initListeners() {

    }
}

