package com.example;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.react.ReactApplication;
import com.facebook.react.modules.storage.ReactDatabaseSupplier;
import com.frodinm.rnandroidjob.RNAndroidJob;
import com.frodinm.rnandroidjob.RNAndroidJobPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(new MainReactPackage(), new RNAndroidJobPackage());
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  public SQLiteDatabase linkDatabase(){
      SQLiteDatabase readableDatabase;

      readableDatabase = ReactDatabaseSupplier.getInstance(this.getApplicationContext()).getReadableDatabase();

      return readableDatabase;
  }



  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    RNAndroidJob.initializeJobManager(this);
    RNAndroidJob.linkRNdatabase(linkDatabase());
  }
}
