package models.beans;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 26.12.11
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class UserCacheBean implements Serializable {
    private String username;
    private Long userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
