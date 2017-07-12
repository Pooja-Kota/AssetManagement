package im.assetmanagement.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import im.assetmanagement.R;
import im.assetmanagement.data.Utils.WifiDetailsDTO;

/**
 * Created by pkota on 12-07-2017.
 */

public class WifiListAdapter extends RecyclerView.Adapter<WifiListAdapter.MyViewHolder> {

    private List<WifiDetailsDTO> wifiDetailsDTOList;
    Context context;
    String assetId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, time;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
        }

    }

    public void setPayerResultSet(List<WifiDetailsDTO> wifiDetailsDTOList) {
        this.wifiDetailsDTOList = wifiDetailsDTOList;
        notifyDataSetChanged();
    }

    public WifiListAdapter(List<WifiDetailsDTO> wifiDetailsDTOList, String assetId, Context context) {
        this.wifiDetailsDTOList = wifiDetailsDTOList;
        this.context = context;
        this.assetId = assetId;
    }

    @Override
    public WifiListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new WifiListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WifiListAdapter.MyViewHolder holder, final int position) {
        final WifiDetailsDTO wifiDetailsDTO = wifiDetailsDTOList.get(position);
        holder.title.setText(wifiDetailsDTO.getWifiName());
        holder.time.setText(wifiDetailsDTO.getCreatedTime());
    }

    @Override
    public int getItemCount() {
        return wifiDetailsDTOList.size();
    }

}
