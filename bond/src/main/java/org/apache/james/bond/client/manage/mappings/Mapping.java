package org.apache.james.bond.client.manage.mappings;

public class Mapping {
  private String userDomain;
  private String mapping;

  public Mapping(String userDomain, String mapping) {
    this.userDomain = userDomain;
    this.mapping = mapping;
  }

  public String getUserDomain() {
    return userDomain;
  }

  public void setUserDomain(String userDomain) {
    this.userDomain = userDomain;
  }

  public String getMapping() {
    return mapping;
  }

  public void setMapping(String mapping) {
    this.mapping = mapping;
  }

  public String getUser() {
    return userDomain.substring(0, userDomain.indexOf("@"));
  }

  public String getDomain() {
    return userDomain.substring(userDomain.indexOf("@") + 1);
  }
}
