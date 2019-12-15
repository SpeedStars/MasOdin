package tif.gaskeun.masodin.Controller;

import java.util.List;

import tif.gaskeun.masodin.Model.RestaurantData;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<RestaurantData> restaurantDataList);
    void onFirebaseLoadFailed(String Message);
}
