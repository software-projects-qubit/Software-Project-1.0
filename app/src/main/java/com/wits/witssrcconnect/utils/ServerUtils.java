package com.wits.witssrcconnect.utils;

public interface ServerUtils {

    //actions
    String ACTION = "action";
    String CREATE = "create";
    String LOG_IN = "login";

    //server responses
    String SUCCESS = "1";
    String FAILED = "0";

    //student
    String STUDENT_LINK = "http://lamp.ms.wits.ac.za/~s1712776/student.php";
    String STUDENT_USERNAME = "student_username";
    String STUDENT_FIRSTNAME = "student_firstName";
    String STUDENT_LASTNAME = "student_lastName";
    String STUDENT_PASSWORD = "student_Password";

    //src member
    String SRC_MEMBER_LINK = "http://lamp.ms.wits.ac.za/~s1712776/src_member.php";
    String SRC_USERNAME = "member_username";
    String SRC_PASSWORD = "member_password";

    //homepage
    String HOME_PAGE_JSON_LINK = "http://lamp.ms.wits.ac.za/~s1712776/Homepage.json";
    String MISSION = "Mission";
    String VISION = "Vision";
    String CONTACT_US = "ContactUs";
}
