package xyz.arkarhein.news.network;

/**
 * Created by Arkar Hein on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api.
     */
    void loadNews();

    /**
     * @param phoneNo
     * @param password
     */
    void loginUser(String phoneNo, String password);

    void registerUser(String phoneNo, String password, String name);

}
