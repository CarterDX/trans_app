package net.trans.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class TransUserTable implements UserDetails {

  private Integer uUid;
  private String uUsername;
  private String uPassword;
  private String uPhone;
  private String uEmail;
  private java.sql.Date uBirthday;
  private Integer uSex;
  private Integer uStatus;
  private Integer uJurisdiction;
  private String uImg;
  private java.sql.Timestamp uCreatetime;
  private java.sql.Timestamp uUpdatetime;
  /**
   * 权限信息
   */
  private List<SysRole> roles;

  public Integer getUUid() {
    return uUid;
  }

  public void setUUid(Integer uUid) {
    this.uUid = uUid;
  }


  public String getUUsername() {
    return uUsername;
  }

  public void setUUsername(String uUsername) {
    this.uUsername = uUsername;
  }


  public String getUPassword() {
    return uPassword;
  }

  public void setUPassword(String uPassword) {
    this.uPassword = uPassword;
  }


  public String getUPhone() {
    return uPhone;
  }

  public void setUPhone(String uPhone) {
    this.uPhone = uPhone;
  }


  public String getUEmail() {
    return uEmail;
  }

  public void setUEmail(String uEmail) {
    this.uEmail = uEmail;
  }


  public java.sql.Date getUBirthday() {
    return uBirthday;
  }

  public void setUBirthday(java.sql.Date uBirthday) {
    this.uBirthday = uBirthday;
  }


  public Integer getUSex() {
    return uSex;
  }

  public void setUSex(Integer uSex) {
    this.uSex = uSex;
  }


  public Integer getUStatus() {
    return uStatus;
  }

  public void setUStatus(Integer uStatus) {
    this.uStatus = uStatus;
  }


  public Integer getUJurisdiction() {
    return uJurisdiction;
  }

  public void setUJurisdiction(Integer uJurisdiction) {
    this.uJurisdiction = uJurisdiction;
  }


  public String getUImg() {
    return uImg;
  }

  public void setUImg(String uImg) {
    this.uImg = uImg;
  }


  public java.sql.Timestamp getUCreatetime() {
    return uCreatetime;
  }

  public void setUCreatetime(java.sql.Timestamp uCreatetime) {
    this.uCreatetime = uCreatetime;
  }


  public java.sql.Timestamp getUUpdatetime() {
    return uUpdatetime;
  }

  public void setUUpdatetime(java.sql.Timestamp uUpdatetime) {
    this.uUpdatetime = uUpdatetime;
  }

  public List<SysRole> getRoles() {
    return roles;
  }

  public void setRoles(List<SysRole> roles) {
    this.roles = roles;
  }
  /**
   *	 接口属性都要标记不参与json的处理。
   */
  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return uPassword;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return uPhone;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }
}
