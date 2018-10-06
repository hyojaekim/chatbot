<?php
include "menu.php";
include "weather.php";
include "include/campusdb.php";

$data = json_decode(file_get_contents('php://input'),true);
$content = $data["content"];
$user_key = $data["user_key"];

//user_DB, numberUpdate_DB, print_DB
$user_DB = user_DB($user_key); //DB 테이블 안에 유저키 넣기
$print_DB = print_DB($user_key);//DB 유저키에 해당하는 number를 출력하라

$kkk = "------------------------------";

$today = date("Y/m/d"); //오늘 날짜
$tomorrow = date("Y-m-d", mktime(0,0,0,date("m")  , date("d")+1, date("Y"))); //내일 날짜

$day = date('w'); //오늘 요일
    if ($day == 0){$day = "일요일";}
    else if ($day == 1) {$day = "월요일";}
    else if ($day == 2) {$day = "화요일";}
    else if ($day == 3) {$day = "수요일";}
    else if ($day == 4) {$day = "목요일";}
    else if ($day == 5) {$day = "금요일";}
    else{$day = "토요일";}
    $dayT = date('w'); //내일 요일
    if ($dayT == 0){$dayT = "월요일";}
    else if ($dayT == 1) {$dayT = "화요일";}
    else if ($dayT == 2) {$dayT = "수요일";}
    else if ($dayT == 3) {$dayT = "목요일";}
    else if ($dayT == 4) {$dayT = "금요일";}
    else if ($dayT == 5) {$dayT = "토요일";}
    else{$dayT = "일요일";}

    //글로벌(고성) 캠퍼스
    $GlobalURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C01";
    $SrwURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C02";
    $YhwURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C03";

    //메트로폴(양주) 캠퍼스
    $MtrURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C04";

    //메디컬(원주) 캠퍼스
    $MdcURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C05";
    $MdcGURL = "http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C06";

    $todaynumber = date('w'); //요일 0 ~ 6 숫자 반환

    $todayBreakfast = 0; //오늘 조식(1번쨰)
    $todayDinner = 0; //오늘 석식(2번째)
    $todayFDinner = 0; //오늘 석식(4번쨰)

    $tomorrowBreakfast = $todaynumber; //내일 조식(1번쨰)
    $tomorrowDinner = 0; //내일 석식(2번쨰)
    $tomorrowFDinner = 0; //오늘 석식(4번쨰)
//------------------------------------------------------------------------------------------날짜, url, 학식 배열
// 학식
if($content == "고성 캠퍼스"){
    $numberUpdate_DB = numberUpdate_DB($user_key, 1); //DB 유저키에 해당하는 number를 1로 바꿔라
echo <<< EOD
    {
        "message": {
            "text": "글로벌(고성) 캠퍼스 입니다."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
} elseif($content == "양주 캠퍼스"){
    $numberUpdate_DB = numberUpdate_DB($user_key, 2); //DB 유저키에 해당하는 number를 2로 바꿔라
echo <<< EOD
    {
        "message": {
            "text": "메트로폴(양주) 캠퍼스 입니다."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}elseif($content == "원주 캠퍼스"){
    $numberUpdate_DB = numberUpdate_DB($user_key, 3); //DB 유저키에 해당하는 number를 3로 바꿔라
echo <<< EOD
    {
        "message": {
            "text": "메디컬(원주) 캠퍼스 입니다."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------캠퍼스 분리
if($content == "캠퍼스 변경"){
    echo <<< EOD
    {
        "message": {
            "text": "캠퍼스를 선택해 주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "고성 캠퍼스",
                "양주 캠퍼스",
                "원주 캠퍼스",
                "처음으로"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------캠퍼스 변경 버튼
if($print_DB == 1){
    // 학식
    if($content == "학식"){
    echo <<< EOD
        {
            "message": {
                "text": "식단을 알고 싶은 식당을 선택해 주세요."
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "해바라기(오늘)",
                    "숭례원(오늘)",
                    "양현원(오늘)",
                    "해바라기(내일)",
                    "숭례원(내일)",
                    "양현원(내일)",
                    "처음으로"
                ]
            }
        }
EOD;
    }
    //날씨
    elseif($content == "날씨"){
        $kosong = "Kosong";
        $weatherData = WeatherData($kosong);
    echo <<< EOD
        {
            "message": {
                "text": "$weatherData"
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "학식",
                    "날씨",
                    "홈페이지",
                    "검색",
                    "건의하기",
                    "캠퍼스 변경"
                ]
            }
        }
EOD;
    }
}
//------------------------------------------------------------------------------------------고성 캠퍼스 버튼
if($print_DB == 2){
    // 학식
    if($content == "학식"){
    echo <<< EOD
        {
            "message": {
                "text": "식단을 알고 싶은 식당을 선택해 주세요."
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "조식&석식(오늘)",
                    "중식&점심특식(오늘)",
                    "조식&석식(내일)",
                    "중식&점심특식(내일)",
                    "처음으로"
                ]
            }
        }
EOD;
    }
    //날씨
    elseif($content == "날씨"){
        $vijongbu = "Vijongbu";
        $weatherData = WeatherData($vijongbu);
    echo <<< EOD
        {
            "message": {
                "text": "$weatherData"
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "학식",
                    "날씨",
                    "홈페이지",
                    "검색",
                    "건의하기",
                    "캠퍼스 변경"
                ]
            }
        }
EOD;
    }
}
//------------------------------------------------------------------------------------------양주 캠퍼스 버튼
if($print_DB == 3){
    // 학식
    if($content == "학식"){
    echo <<< EOD
        {
            "message": {
                "text": "식단을 알고 싶은 식당을 선택해 주세요."
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "한식&특식(오늘)",
                    "행복 공공 기숙사(오늘)",
                    "한식&특식(내일)",
                    "행복 공공 기숙사(내일)",
                    "처음으로"
                ]
            }
        }
EOD;
    }
    // 날씨
    elseif($content == "날씨"){
        $wonju = "Wonju";
        $weatherData = WeatherData($wonju);
    echo <<< EOD
        {
            "message": {
                "text": "$weatherData"
            },
            "keyboard": { 
                "type": "buttons",
                "buttons": [
                    "학식",
                    "날씨",
                    "홈페이지",
                    "검색",
                    "건의하기",
                    "캠퍼스 변경"
                ]
            }
        }
EOD;
}
    
}
//------------------------------------------------------------------------------------------원주 캠퍼스 버튼
// 해바라기 식당 메뉴(오늘)
if($content == "해바라기(오늘)"){
    
    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    $GbTmenu = MenuData($GlobalURL, $todayBreakfast); //캠퍼스 오늘 메뉴

echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈해바라기 식당 메뉴◈\\n$GbTmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 숭례원 기숙사 조식 석식 메뉴(오늘)
elseif($content == "숭례원(오늘)"){

    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    if($todaynumber == 0){$todayDinner = $todaynumber + 13;}
    else{$todayDinner = $todaynumber +6;}

    $SrTDmenu = MenuData($SrwURL, $todayBreakfast);
    $SrTNmenu = MenuData($SrwURL, $todayDinner);
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈숭례원 조식 메뉴◈\\n $SrTDmenu \\n$kkk\\n◈숭례원 석식 메뉴◈\\n $SrTNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 양현원 기숙사 조식 석식 메뉴(오늘)
elseif($content == "양현원(오늘)"){

    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    if($todaynumber == 0){$todayDinner = $todaynumber + 13;}
    else{$todayDinner = $todaynumber +6;}

    $YhTDmenu = MenuData($YhwURL, $todayBreakfast); //양현원 오늘 조식
    $YhTNmenu = MenuData($YhwURL, $todayDinner); //양현원 오늘 석식

echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈양현원 조식 메뉴◈\\n $YhTDmenu \\n$kkk\\n◈양현원 석식 메뉴◈\\n $YhTNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 해바라기 식당 메뉴(내일)
elseif($content == "해바라기(내일)"){

    $GbMmenu = MenuData($GlobalURL, $tomorrowBreakfast); //캠퍼스 내일 메뉴

echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈해바라기 식당 메뉴◈\\n$GbMmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 숭례원 기숙사 조식 석식 메뉴(내일)
elseif($content == "숭례원(내일)"){
   
    if($todaynumber){$tomorrowDinner = $todaynumber + 7;}

    $SrMDmenu = MenuData($SrwURL, $tomorrowBreakfast); //숭례원 내일 조식
    $SrMNmenu = MenuData($SrwURL, $tomorrowDinner); //숭례원 내일 석식

echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈숭례원 조식 메뉴◈\\n $SrMDmenu \\n$kkk\\n◈숭례원 석식 메뉴◈\\n $SrMNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 양현원 기숙사 조식 석식 메뉴(내일)
elseif($content == "양현원(내일)"){

    if($todaynumber){$tomorrowDinner = $todaynumber + 7;}

    $YhMDmenu = MenuData($YhwURL, $tomorrowBreakfast); //양현원 내일 조식
    $YhMNmenu = MenuData($YhwURL, $tomorrowDinner); //양현원 내일 석식

echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈양현원 조식 메뉴◈\\n $YhMDmenu \\n$kkk\\n◈양현원 석식 메뉴◈\\n $YhMNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------고성 캠퍼스 학식
// 메트로폴 캠퍼스(양주) 조식 &석식 (오늘)
if($content == "조식&석식(오늘)"){
    
    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    if($todaynumber == 0){$todayFDinner = $todaynumber + 27;}
    else{$todayFDinner = $todaynumber + 20;}

    $MtBTmenu = MenuData($MtrURL, $todayBreakfast); //캠퍼스 오늘 조식 메뉴
    $MtDTmenu = MenuData($MtrURL, $todayFDinner); //캠퍼스 오늘 석식 메뉴
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈메트로폴 조식 메뉴◈\\n $MtBTmenu \\n$kkk\\n◈메트로폴 석식 메뉴◈\\n $MtDTmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 메트로폴 캠퍼스(양주) 중식 &점식특식 (오늘)
elseif($content == "중식&점심특식(오늘)"){
    
    if($todaynumber == 0){$todayBreakfast = $todaynumber + 13;}
    else{$todayBreakfast = $todaynumber + 6;}

    if($todaynumber == 0){$todayFDinner = $todaynumber + 20;}
    else{$todayFDinner = $todaynumber + 13;}

    $MtBTmenu = MenuData($MtrURL, $todayBreakfast); //캠퍼스 오늘 중식 메뉴
    $MtDTmenu = MenuData($MtrURL, $todayFDinner); //캠퍼스 오늘 점심특식 메뉴
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈메트로폴 중식 메뉴◈\\n $MtBTmenu \\n$kkk\\n◈메트로폴 점심특식 메뉴◈\\n $MtDTmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 메트로폴 캠퍼스(양주) 조식 &석식 (내일)
elseif($content == "조식&석식(내일)"){
    if($todaynumber){$tomorrowFDinner = $todaynumber + 21;}

    $MtBMmenu = MenuData($MtrURL, $tomorrowBreakfast); //캠퍼스 오늘 조식 메뉴
    $MtDMmenu = MenuData($MtrURL, $tomorrowFDinner); //캠퍼스 오늘 석식 메뉴
echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈메트로폴 조식 메뉴◈\\n $MtBMmenu \\n$kkk\\n◈메트로폴 석식 메뉴◈\\n $MtDMmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
// 메트로폴 캠퍼스(양주) 중식 &점식특식 (내일)
elseif($content == "중식&점심특식(내일)"){
    
    if($todaynumber == 0){$tomorrowBreakfast = $todaynumber + 7;}
    else{$todayBreakfast = $todaynumber + 7;}

    if($todaynumber == 0){$tomorrowDinner = $todaynumber + 14;}
    else{$todayFDinner = $todaynumber + 14;}

    $MtBTmenu = MenuData($MtrURL, $todayBreakfast); //캠퍼스 오늘 중식 메뉴
    $MtDTmenu = MenuData($MtrURL, $todayFDinner); //캠퍼스 오늘 점심특식 메뉴
echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈메트로폴 중식 메뉴◈\\n $MtBTmenu \\n$kkk\\n◈메트로폴 점심특식 메뉴◈\\n $MtDTmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------양주 캠퍼스 학식
// 문막 학식 한식 특식 메뉴(오늘)
if($content == "한식&특식(오늘)"){
   
    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    if($todaynumber == 0){$todayDinner = $todaynumber + 13;}
    else{$todayDinner = $todaynumber +6;}

    $Mdcmenu = MenuData($MdcURL, $todayBreakfast); //캠퍼스 오늘 한식 메뉴
    $MdcGmenu = MenuData($MdcURL, $todayDinner); //캠퍼스 오늘 특식 메뉴

echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈메디컬 한식 메뉴◈\\n $Mdcmenu \\n$kkk\\n◈메디컬 특식 메뉴◈\\n $MdcGmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 문막 기숙사 조식 석식 메뉴(오늘)
elseif($content == "행복 공공 기숙사(오늘)"){
   
    if($todaynumber == 0){$todayBreakfast = $todaynumber + 6;}
    else{$todayBreakfast = $todaynumber - 1;}

    if($todaynumber == 0){$todayDinner = $todaynumber + 13;}
    else{$todayDinner = $todaynumber +6;}

    $Mdcmenu = MenuData($MdcGURL, $todayBreakfast); //기숙사 오늘 조식 메뉴
    $MdcGmenu = MenuData($MdcGURL, $todayDinner); //기숙사 오늘 석식 메뉴

echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈행복 공공 기숙사 조식 메뉴◈\\n $Mdcmenu \\n$kkk\\n◈행복 공공 기숙사 석식 메뉴◈\\n $MdcGmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 문막 학식 한식 특식 메뉴(내일)
elseif($content == "한식&특식(내일)"){
   
    if($todaynumber){$tomorrowDinner = $todaynumber + 7;}

    $Mdcmenu = MenuData($MdcURL, $tomorrowBreakfast); //캠퍼스 내일 한식
    $Mdc2menu = MenuData($MdcURL, $tomorrowDinner); //캠퍼스 내일 특식

echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈메디컬 한식 메뉴◈\\n $Mdcmenu \\n$kkk\\n◈메디컬 특식 메뉴◈\\n $Mdc2menu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 문막 기숙사 조식 석식 메뉴(내일)
elseif($content == "행복 공공 기숙사(내일)"){
   
    if($todaynumber){$tomorrowDinner = $todaynumber + 7;}

    $Gssmenu = MenuData($MdcGURL, $tomorrowBreakfast); //문막 기숙사 내일 조식
    $Gss2menu = MenuData($MdcGURL, $tomorrowDinner); //문막 기숙사 내일 석식

echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈행복 공공 기숙사 조식 메뉴◈\\n $Gssmenu \\n$kkk\\n◈행복 공공 기숙사 석식 메뉴◈\\n $Gss2menu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------원주 캠퍼스 학식
// 홈페이지
if($content == "홈페이지"){
echo <<< EOD
    {
        "message": {
            "text": "들어가고 싶은 사이트를 선택해주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "메인페이지",
                "포털시스템",
                "스마트출결시스템",
                "교내연락처",
                "처음으로"
            ]
        }
    }
EOD;
}

//홈페이지-메인페이지
elseif($content == "메인페이지"){
echo <<< EOD
    {
        "message": {
            "text": "PC 버전 \\n http://www.kduniv.ac.kr/ \\n 모바일 버전 \\n http://m.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 홈페이지-포털시스템
elseif($content == "포털시스템"){
echo <<< EOD
    {
        "message": {
            "text": "포털시스템(학사정보) \\n http://portal.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 홈페이지-스마트출결시스템
elseif($content == "스마트출결시스템"){
echo <<< EOD
    {
        "message": {
            "text": "스마트출결시스템 \\n http://attend.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 홈페이지-교내연락처
elseif($content == "교내연락처"){
echo <<< EOD
    {
        "message": {
            "text": "교내연락처 \\n http://kduniv.ac.kr/www/index.php?pCode=1410508209"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
//------------------------------------------------------------------------------------------홈페이지
// 건의하기
if(strpos($content, "건의") !== false){
echo <<< EOD
    {
        "message": {
            "text": "컴퓨터공학과(김효재)\\n\\n기능 개선이 필요하거나 \\n추가적인 기능이 필요하다면 \\n페이스북이나 인스타(hyoja2_)로\\n메시지 남겨주세요!"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 처음으로
elseif($content == "처음으로"){
echo <<< EOD
    {
        "message": {
            "text": "메인 화면 입니다."
        },
        "keyboard": {
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 검색창
if($content == "검색"){
echo <<< EOD
    {
        "message": {
            "text": "원하시는 기능을 검색해주세요!!\\n('처음으로' 입력시 처음 화면으로 이동합니다.)"
        }
    }
EOD;
}
//오타나 오류 발생시
else {
echo <<< EOD
    {
        "message": {
            "text": "개발중인 기능이거나 \\n잘못된 입력입니다."
        }, 
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
                "날씨",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}
?>