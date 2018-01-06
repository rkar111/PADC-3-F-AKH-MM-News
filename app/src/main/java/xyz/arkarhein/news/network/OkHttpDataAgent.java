package xyz.arkarhein.news.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xyz.arkarhein.news.MMNewsApp;
import xyz.arkarhein.news.data.vo.NewsVO;
import xyz.arkarhein.news.events.LoadedNewsEvent;
import xyz.arkarhein.news.network.responses.GetNewsResponse;

/**
 * Created by Arkar Hein on 1/6/2018.
 */

public class OkHttpDataAgent implements NewsDataAgent {

    private static OkHttpDataAgent sObjInstance;

    private OkHttpDataAgent() {

    }

    public static OkHttpDataAgent getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new OkHttpDataAgent();
        }
        return sObjInstance;
    }

    @Override
    public void loadNews() {
        new LoadNewsTask()
                .execute("http://padcmyanmar.com/padc-3/mm-news/apis/v1/getMMNews.php");
    }

    private static class LoadNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", "b002c7e1a528b7cb460933fc2875e916")
                    .add("page", "1")
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            String responseString = null;
            try {
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    responseString = response.body().string();
                }
            } catch (Exception e) {
                Log.e(MMNewsApp.LOG_TAG, e.getMessage());
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Gson gson = new Gson();
            GetNewsResponse getNewsResponse = gson.fromJson(response, GetNewsResponse.class);

            LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(event);
        }
    }


}
