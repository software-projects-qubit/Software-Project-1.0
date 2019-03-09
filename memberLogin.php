<?php
$username = "";
$password = "";
$database = "";
$link = mysqli_connect("146.141.21.141", $username, $password, $database);
$output=array();
$USERNAME=$_REQUEST["USERNAME"];
$PASSWORD=$_REQUEST["PASSWORD"];


if (!isset($USERNAME, $PASSWORD)){
	$output["result"]="Your input was incorrect";
        echo json_encode($output);
	die();
}

/* Select queries return a resultset */
if ($result = mysqli_query($link, "SELECT * FROM SRC_MEMBER WHERE member_username='$USERNAME' AND member_Password='$PASSWORD'")){
	while ($row=$result->fetch_assoc()){
$output[]=$row;
}
}

mysqli_close($link);
echo json_encode($output);
?>
