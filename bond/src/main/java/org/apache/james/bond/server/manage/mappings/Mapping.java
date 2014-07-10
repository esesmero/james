package org.apache.james.bond.server.manage.mappings;

import java.util.Set;

/**
 * It contains all the mapping for a concrete user and domain.
 */
public class Mapping {
  private String userAndDomain;
  private Set<String> mappings;
  private Integer version;

  public Mapping() {
  }

  /**
   * Creates the mapping with the user and domain received and all the mappings
   * 
   * @param userAndDomain
   * @param mappings
   */
  public Mapping(String userAndDomain, Set<String> mappings) {
    this.userAndDomain = userAndDomain;
    this.setMappings(mappings);
  }

  public String getId() {
    return userAndDomain;
  }

  public Integer getVersion() {
    if (version == null)
      version = 0;
    return version;
  }

  public void setId(String id) {
    this.userAndDomain = id;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getUserAndDomain() {
    return userAndDomain;
  }

  public void setUserAndDomain(String userAndDomain) {
    this.userAndDomain = userAndDomain;
  }

  public Set<String> getMappings() {
    return mappings;
  }

  public void setMappings(Set<String> mappings) {
    this.mappings = mappings;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    if (!(obj instanceof Mapping))
      return false;
    Mapping mappingObj = (Mapping) obj;
    return this.getId().equals(mappingObj.getId());
  }
}
