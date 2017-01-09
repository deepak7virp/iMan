package com.inlighten.ldap;

import java.io.UnsupportedEncodingException;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UserAttrMapper implements AttributesMapper<AppUser> {

	@Override
	public AppUser mapFromAttributes(Attributes attributes) throws NamingException {
		// TODO Auto-generated method stub
		AppUser appUser;
		if (attributes == null)
			return null;
		appUser = new AppUser();
		if (attributes.get("userPassword") != null) {
			String userPassword = null;
			try {
				userPassword = new String((byte[]) attributes.get("userPassword").get(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				//log.error("unable to process", e);
			}
			appUser.setUserPassword(userPassword);
		}
		if (attributes.get("sAMAccountName") != null) {
			appUser.setsAMAccountName(attributes.get("sAMAccountName").get().toString());
		}
		return appUser;
	}

}
