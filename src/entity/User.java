package entity;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String userId;
    private String avatarUrl;
    private String name;

    public User(String userId, String avatarUrl, String name) {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<User> list = new LinkedList<>();
        for(int i=0 ;i<100;i++){
            list.add(new User("username"+i,"iconUrl"+i,"name"+i));
        }
        Gson gson = new Gson();
        String data = gson.toJson(list);
        System.out.println(data);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUserId() != null ? !getUserId().equals(user.getUserId()) : user.getUserId() != null) return false;
        if (getAvatarUrl() != null ? !getAvatarUrl().equals(user.getAvatarUrl()) : user.getAvatarUrl() != null)
            return false;
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getAvatarUrl() != null ? getAvatarUrl().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
