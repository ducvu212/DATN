package com.ekyc.app;

import com.lugg.ReactNativeConfig.ReactNativeConfigPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.reactnativenavigation.NavigationApplication;
import com.reactnativenavigation.react.NavigationReactNativeHost;
import com.reactnativenavigation.react.ReactGateway;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import org.devio.rn.splashscreen.SplashScreenReactPackage;
import com.oblador.keychain.KeychainPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.horcrux.svg.SvgPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.reactnativecommunity.cameraroll.CameraRollPackage;
import org.reactnative.camera.RNCameraPackage;
import com.facebook.soloader.SoLoader;
import fr.bamlab.rnimageresizer.ImageResizerPackage;
import com.kishanjvaghela.cardview.RNCardViewPackage;
import com.reactnativecommunity.rnpermissions.RNPermissionsPackage;
import com.rnfingerprint.FingerprintAuthPackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends NavigationApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }

    @Override
    protected ReactGateway createReactGateway() {
        ReactNativeHost host = new NavigationReactNativeHost(this, isDebug(), createAdditionalReactPackages()) {
            @Override
            protected String getJSMainModuleName() {
                return "index";
            }
        };
        return new ReactGateway(this, isDebug(), host);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    protected List<ReactPackage> getPackages() {
        // Add additional packages you require here
        // No need to add RnnPackage and MainReactPackage
        return Arrays.<ReactPackage>asList(
                new ReactNativeConfigPackage(),
                new FastImageViewPackage(),
                new AsyncStoragePackage(),
                new SplashScreenReactPackage(),
                new KeychainPackage(),
                new RNDeviceInfo(),
                new SvgPackage(),
                new BackgroundTimerPackage(),
                new CameraRollPackage(),
                new RNCameraPackage(),
                new ImageResizerPackage(),
            new RNCardViewPackage(),
             new RNPermissionsPackage(),
                new FingerprintAuthPackage()
        );
    }

    @Override
    public List<ReactPackage> createAdditionalReactPackages() {
        return getPackages();
    }

}
