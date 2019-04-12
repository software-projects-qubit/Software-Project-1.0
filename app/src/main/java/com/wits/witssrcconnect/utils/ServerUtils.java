package com.wits.witssrcconnect.utils;

public interface ServerUtils {

    //actions
    String ACTION = "action";
    String CREATE = "create";
    String LOG_IN = "login";

    //server responses
    String SUCCESS = "1";
    String FAILED = "0";

    //LOG_IN
    String LOG_IN_LINK = "http://1627982.ms.wits.ac.za/~student/auth.php";
    String USERNAME = "USERNAME";
    String PASSWORD = "PASSWORD";
    String NAME = "name";
    String SURNAME = "surname";

    //student
    String STUDENT_LINK = "http://1627982.ms.wits.ac.za/~student/student.php";
    String STUDENT_FIRSTNAME = "student_firstName";
    String STUDENT_LASTNAME = "student_lastName";
    String STUDENT_PASSWORD = "student_Password";

    //src member
    String SRC_MEMBER_LINK = "http://1627982.ms.wits.ac.za/~student/src_member.php";
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
    String HOME_PAGE_JSON_LINK = "http://1627982.ms.wits.ac.za/~student/Homepage.json";
    String MISSION = "Mission";
    String VISION = "Vision";
    String CONTACT_US = "ContactUs";

    //activities comment
    int ANONYMOUS_COMMENT_ON = 1;
    int ANONYMOUS_COMMENT_OFF = 0;
    String COMMENT_LINK = "http://1627982.ms.wits.ac.za/~student/student_comment.php";
    String STUDENT_USERNAME = "stud_username";
    String POST_COMMENT = "postComment";
    String READ_COMMENT = "readComment";
    String STUDENT_COMMENT = "stud_comment";
    String STUDENT_ANONYMITY = "stud_anonymity";
    String STUDENT_DATE = "stud_date";
    String STUDENT_TIME = "stud_time";

    //src poll
    String POLL_LINK = "http://1627982.ms.wits.ac.za/~student/polls.php";
    String READ_ALL_POLLS = "readAllPolls";
    String POST_POLL = "postPoll";
    String POLL_TITLE ="poll_title";
    String POLL_DESC = "poll_desc";
    String POLL_CHOICE="poll_choices";
    String POLL_DATE = "poll_date";
    String POLL_TIME = "poll_time";
    String POLL_TYPE = "poll_type";

    int POLL_TYPE_SINGLE_SELECT = 1;
    int POLL_TYPE_MULTI_SELECT = 0;
}
