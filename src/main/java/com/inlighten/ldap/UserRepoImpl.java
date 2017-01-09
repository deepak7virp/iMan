package com.inlighten.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;

@Repository("ldapDao")
public class UserRepoImpl implements UserRepoInterface{

	@Autowired(required = true)
	@Qualifier(value = "ldapTemplate")
	private LdapTemplate ldapTemplate;
	
	private static String iBase = "dc=inlighten,dc=local";
	
	@Override
	public boolean authenticate(String userName, String password) {
		// TODO Auto-generated method stub
		AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("member", userName)).and(new EqualsFilter("sAMAccountName", userName));
        EqualsFilter ef = new EqualsFilter("sAMAccountName", userName);
        return ldapTemplate.authenticate(iBase, ef.toString(), password);
	}
	
	

}
