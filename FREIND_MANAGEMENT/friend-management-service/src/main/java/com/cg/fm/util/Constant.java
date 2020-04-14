package com.cg.fm.util;

public class Constant {
	
	public static final String JPA_REPO_PACKAGE = "com.cg.fm.repo";

    public static final String ENTITY_SCAN_PACKAGE = "com.cg.fm.domain";

    public static final String SERVICE_COMPONENT_SCAN_PACKAGE = "com.cg.fm.service";

    public static final String EMAIL_REGEX_MATCHER = "([a-z0-9_.-]+)@([a-z0-9_.-]+[a-z])";

    
    public static final String VALID_EMAIL_REGEX =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String STS_PENDING = "Pending";
    public static final String STS_ACCEPTED = "Accepted";
    public static final String STS_DECLINED = "Declined";
    public static final String STS_BLOCKED = "Blocked";

}
