<?php
$username = "";
$password = "";
$database = "";
$link = mysqli_connect("146.141.21.141", $username, $password, $database);
$output=array();
$USERNAME = $_REQUEST["USERNAME"];
$PASSWORD=$_REQUEST["PASSWORD"];


if (!isset($PASSWORD,$ORDER_ID)){
	$output["result"]="Invalid values";
        echo json_encode($output);
	die();
}

/* Update if the values are true*/
$output="Not updated";
if ($result = mysqli_query($link, "UPDATE SRC_MEMBER SET member_Password='$PASSWORD' WHERE member_username='$USERNAME'")){
$output="updated";
};
echo json_encode($output);
?>

