package im.assetmanagement.data.Utils;

import java.util.List;

/**
 * Created by pkota on 12-07-2017.
 */

public class WifiDTO {

    private String assetId;
    private List<WifiDetailsDTO> wifi;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public List<WifiDetailsDTO> getWifi() {
        return wifi;
    }

    public void setWifi(List<WifiDetailsDTO> wifi) {
        this.wifi = wifi;
    }
}
