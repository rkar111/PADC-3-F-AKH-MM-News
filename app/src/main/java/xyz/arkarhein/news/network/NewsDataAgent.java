package xyz.arkarhein.news.network;

import android.content.Context;

/**
 * Created by Arkar Hein on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api.
     */
    void loadNews();

    /**
     * Login user.
     * @param phoneNo
     * @param context
     * @param password
     */
    void loginUser(Context context, String phoneNo, String password);

    void registerUser(String phoneNo, String password, String name);

}
