<?php
$username = "";
$password = "";
$database = "";
$link = mysqli_connect("146.141.21.141", $username, $password, $database);
$output=array();
$FIRSTNAME=$_REQUEST["NAME"];
$LASTNAME=$_REQUEST["LASTNAME"];
$USERNAME=$_REQUEST["USERNAME"];
$PASSWORD=$_REQUEST["PASSWORD"];


if (!isset($NAME, $LASTNAME, $PASSWORD)){
	$output["result"]="You didn't send the required values";
        echo json_encode($output);
	die();
}

/* queries to insert student details */

if ($result = mysqli_query($link, "INSERT INTO STUDENT VALUES ('$USERNAME','$FIRSTNAME','$LASTNAME','$PASSWORD')")){ //I have a problem with hashing the password
		echo "registered";
	}
else echo "Not Registered";
mysqli_close($link);
?>
