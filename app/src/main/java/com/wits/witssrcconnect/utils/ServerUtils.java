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

    //activity
    String POST_ACTIVITY = "postActivity";
    String READ_ALL_ACTIVITIES = "readAllActivities";
    String ACTIVITY_ID = "activity_id";
    String ACTIVITY_TITLE = "activity_title";
    String ACTIVITY_DESC = "activity_desc";
    String ACTIVITY_DATE = "activity_date";
    String ACTIVITY_TIME = "activity_time";

    //homepage
    String HOME_PAGE_JSON_LINK = "http://lamp.ms.wits.ac.za/~s1712776/Homepage.json";
    String MISSION = "Mission";
    String VISION = "Vision";
    String CONTACT_US = "ContactUs";

    //activities comment
    String COMMENT_LINK = "http://lamp.ms.wits.ac.za/~s1712776/student_comment.php";
    String POST_COMMENT = "postComment";
    int ANONYMOUS_COMMENT_ON = 1;
    int ANONYMOUS_COMMENT_OFF = 0;
    String STUDENT_COMMENT = "stud_comment";
    String STUDENT_ANONYMITY = "stud_anonymity";
    String STUDENT_DATE = "stud_date";
    String STUDENT_TIME = "stud_time";
}
