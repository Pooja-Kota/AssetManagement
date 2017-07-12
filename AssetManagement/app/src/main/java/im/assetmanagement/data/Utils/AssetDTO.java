package im.assetmanagement.data.Utils;

/**
 * Created by pkota on 11-07-2017.
 */

public class AssetDTO {

    private String assetId;
    private String deviceId;
    private String MACId;
    private String Imei1;
    private String Imei2;
    private String Imei3;
    private boolean status;
    private String type;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMACId() {
        return MACId;
    }

    public void setMACId(String MACId) {
        this.MACId = MACId;
    }

    public String getImei1() {
        return Imei1;
    }

    public void setImei1(String imei1) {
        Imei1 = imei1;
    }

    public String getImei2() {
        return Imei2;
    }

    public void setImei2(String imei2) {
        Imei2 = imei2;
    }

    public String getImei3() {
        return Imei3;
    }

    public void setImei3(String imei3) {
        Imei3 = imei3;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
