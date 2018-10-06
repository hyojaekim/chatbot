<?php
function user_DB($user) {
    $db_host = "localhost"; 
    $db_user = "rlagywo23"; 
    $db_passwd = "goddog123";
    $db_name = "rlagywo23";

    // MySQL - DB 접속.
    $conn = mysqli_connect($db_host,$db_user,$db_passwd,$db_name);

    // 문자셋 설정, utf8.
    mysqli_set_charset($conn,"utf8"); 

    // 테이블 쿼리 후 내용 삽입(유저키)
    $sql = "insert into campus (user) values ('$user')";
    $result = mysqli_query($conn,$sql);
}

function numberUpdate_DB($user, $number) {
    $db_host = "localhost"; 
    $db_user = "rlagywo23"; 
    $db_passwd = "goddog123";
    $db_name = "rlagywo23";

    // MySQL - DB 접속.
    $conn = mysqli_connect($db_host,$db_user,$db_passwd,$db_name);

    // 문자셋 설정, utf8.
    mysqli_set_charset($conn,"utf8"); 

    // 테이블 쿼리 후 내용 수정(number), 해당 user의 number를 바꿔라
    $sql = "UPDATE campus SET number = '$number' WHERE user = '$user'";
    $result = mysqli_query($conn,$sql);
}

function print_DB($user) {
    $db_host = "localhost"; 
    $db_user = "rlagywo23"; 
    $db_passwd = "goddog123";
    $db_name = "rlagywo23";

    // MySQL - DB 접속.
    $conn = mysqli_connect($db_host,$db_user,$db_passwd,$db_name);

    // 문자셋 설정, utf8.
    mysqli_set_charset($conn,"utf8"); 

    // 테이블 쿼리 후 내용 출력.
    $sql = "SELECT number FROM campus WHERE user = '$user'";
    $result = mysqli_query($conn,$sql);

    $row = mysqli_fetch_array($result);
    $a = $row['number'];
    mysqli_close($conn);

    return $a;
}

?>