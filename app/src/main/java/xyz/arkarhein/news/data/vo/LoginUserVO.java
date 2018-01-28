package xyz.arkarhein.news.data.vo;

/**
 * Created by Arkar Hein on 1/21/2018.
 */

public class LoginUserVO {
    private int userId;
    private String name;
    private String email;
    private String phoneNo;
    private String profileUrl;
    private String coverUrl;

    public LoginUserVO(int userId, String name, String email,
                       String phoneNo, String profileUrl, String coverUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.profileUrl = profileUrl;
        this.coverUrl = coverUrl;
    }

    public LoginUserVO() {
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}
