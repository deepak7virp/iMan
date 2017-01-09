package com.inlighten.ldap;

public interface UserRepoInterface {

	public boolean authenticate(String userName, String password);
}
