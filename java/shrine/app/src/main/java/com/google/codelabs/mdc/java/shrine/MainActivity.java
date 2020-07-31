package com.google.codelabs.mdc.java.shrine;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.codelabs.mdc.java.shrine.api.CoronaApiService;
import com.google.codelabs.mdc.java.shrine.api.HttpClientService;
import com.google.codelabs.mdc.java.shrine.api.impl.Covid19ApiServiceImpl;
import com.google.codelabs.mdc.java.shrine.api.impl.OkHttpServiceImpl;
import com.google.codelabs.mdc.java.shrine.api.model.Country;
import com.google.codelabs.mdc.java.shrine.api.model.DailyReportByCountry;
import com.google.codelabs.mdc.java.shrine.api.util.AppUtils;
import com.google.codelabs.mdc.java.shrine.design.MainScreenFragment;

import java.io.IOException;
import java.text.ParseException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    Context applicationContext;
    HttpClientService httpClientService;
    CoronaApiService coronaApiService;
    Country myCountry;
    DailyReportByCountry dailyReportByCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_main_activity);

        httpClientService = new OkHttpServiceImpl();
        coronaApiService = new Covid19ApiServiceImpl(httpClientService);
        applicationContext = getApplicationContext();
        myCountry = AppUtils.getCountryOfDeviceLocation(applicationContext);

        new MainActivityRequest().execute();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new MainScreenFragment())
                    .commit();
        }

    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    class MainActivityRequest extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                dailyReportByCountry = coronaApiService.getDailyReportByCountry(myCountry);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }
}
