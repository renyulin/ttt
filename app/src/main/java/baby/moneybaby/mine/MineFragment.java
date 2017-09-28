package baby.moneybaby.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import baby.moneybaby.R;

/**
 * mine
 */
public class MineFragment extends Fragment {
    private double mCurrentLat;
    private double mCurrentLon;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private TextView mine_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        location();
        mine_tv = view.findViewById(R.id.mine_tv);
        mine_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocClient.start();
            }
        });
        return view;
    }

    private void location() {
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
//        mLocClient = new LocationClient(getActivity());
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);//可选，默认0，即仅定位一次
////        option.setIsNeedAddress(true); //可选，设置是否需要地址信息，默认不需要
////
////        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe
////        // 里得到，结果类似于“在北京天安门附近”
////        option.setIsNeedLocationDescribe(true);
////        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//
//        mLocClient.setLocOption(option);
//        mLocClient.start();
    }

    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double mCurrentLat = location.getLatitude();
            double mCurrentLon = location.getLongitude();
            Log.e("location____", mCurrentLat + "--" + mCurrentLon);
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            String addrStr = location.getAddrStr();    //获取地址信息
            String country = location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            String city = location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            String district = location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息
//            mLocClient.stop();
            /*  mCurrentLat = 4.9E-324
             mCurrentLon = 4.9E-324
             */
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
