package cn.wdb.community.pojo;


import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name = "user")
@NameStyle
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String accountId;
    private String username;
    private String token;
    private Long gmtCreated;
    private Long gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
