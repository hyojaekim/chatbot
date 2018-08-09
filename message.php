<?php
include "menu.php";
use Cmfcmf\OpenWeatherMap;

$data = json_decode(file_get_contents('php://input'),true);
$content = $data["content"];
$kkk = "------------------------------";
$today = date("Y/m/d"); //오늘
$tomorrow = date("Y-m-d", mktime(0,0,0,date("m")  , date("d")+1, date("Y"))); //내일

$day = date('w'); //오늘 요일
    if ($day == 0){
        $day = "일요일";
    }else if ($day == 1) {
        $day = "월요일";
    }else if ($day == 2) {
        $day = "화요일";
    }else if ($day == 3) {
        $day = "수요일";
    }else if ($day == 4) {
        $day = "목요일";
    }else if ($day == 5) {
        $day = "금요일";
    }else{
        $day = "토요일";
    }
$dayT = date('w'); //내일 요일
    if ($dayT == 0){
        $dayT = "월요일";
    }else if ($dayT == 1) {
        $dayT = "화요일";
    }else if ($dayT == 2) {
        $dayT = "수요일";
    }else if ($dayT == 3) {
        $dayT = "목요일";
    }else if ($dayT == 4) {
        $dayT = "금요일";
    }else if ($dayT == 5) {
        $dayT = "토요일";
    }else{
        $dayT = "일요일";
    }

//글로벌 캠퍼스(오늘)
$GbTmenu = TGTmenu(); //캠퍼스 오늘 메뉴

$SrTDmenu = TSTDmenu(); //숭례원 오늘 조식
$SrTNmenu = TSTNmenu(); //숭례원 오늘 석식

$YhTDmenu = TYTDmenu(); //양현원 오늘 조식
$YhTNmenu = TYTNmenu(); //양현원 오늘 석식

//글로벌 캠퍼스(내일)
$GbMmenu = MGTmenu(); //캠퍼스 내일 메뉴

$SrMDmenu = MSTDmenu(); //숭례원 내일 조식
$SrMNmenu = MSTNmenu(); //숭례원 내일 석식

$YhMDmenu = MYTDmenu(); //양현원 내일 조식
$YhMNmenu = MYTNmenu(); //양현원 내일 석식



// 오늘 학식(검색)
if(strpos($content, "오늘") !== false && strpos($content, "학식") !== false){
echo <<< EOD
    {
        "message": {
            "text": "오늘 식단을 알고 싶은 식당을 \\n선택해주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 글로벌 캠퍼스",
                "오늘 숭례원",
                "오늘 양현원",
                "내일 학식",
                "처음으로"
            ]
        }
    }
EOD;
}

// 내일 학식(검색)
if(strpos($content, "내일") !== false && strpos($content, "학식") !== false){
echo <<< EOD
    {
        "message": {
            "text": "내일 식단을 알고 싶은 식당을 \\n선택해주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "내일 글로벌 캠퍼스",
                "내일 숭례원",
                "내일 양현원",
                "오늘 학식",
                "처음으로"
            ]
        }
    }
EOD;
}

// 글로벌 캠퍼스 학식 메뉴(오늘)
elseif(strpos($content, "오늘") !== false && strpos($content, "글로벌") !== false 
        && strpos($content, "캠퍼스") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈글로벌 캠퍼스 학식 메뉴◈\\n$GbTmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "오늘") !== false && strpos($content, "숭례원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈숭례원 조식 메뉴◈\\n $SrTDmenu \\n$kkk\\n◈숭례원 석식 메뉴◈\\n $SrTNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "오늘") !== false && strpos($content, "양현원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$today $day \\n$kkk\\n◈양현원 조식 메뉴◈\\n $YhTDmenu \\n$kkk\\n◈양현원 석식 메뉴◈\\n $YhTNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 글로벌 캠퍼스 학식 메뉴(내일)
elseif(strpos($content, "내일") !== false && strpos($content, "글로벌") !== false 
        && strpos($content, "캠퍼스") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈글로벌 캠퍼스 학식 메뉴◈\\n$GbMmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "내일") !== false && strpos($content, "숭례원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈숭례원 조식 메뉴◈\\n $SrMDmenu \\n$kkk\\n◈숭례원 석식 메뉴◈\\n $SrMNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "내일") !== false && strpos($content, "양현원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "$tomorrow $dayT \\n$kkk\\n◈양현원 조식 메뉴◈\\n $YhMDmenu \\n$kkk\\n◈양현원 석식 메뉴◈\\n $YhMNmenu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 날씨
if(strpos($content, "날씨") !== false){
echo <<< EOD
    {
        "message": {
            "text": "어떤 정보가 필요하신가요?"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "기온",
                "미세먼지",
                "처음으로"
            ]
        }
    }
EOD;
}

// 날씨-기온
elseif(strpos($content, "기온") !== false){
    require 'vendor/autoload.php';
    $lang = 'ko'; //한국
    $units = 'metric'; //단위?!
    $owm = new OpenWeatherMap('d3ffd29705aaaa87adf50c825caf6361'); //API키
    $weather = $owm->getWeather('Sogcho', $units, $lang); //속초 지역 날씨 받아옴
    $weather_final=$weather->temperature; //기온 파싱해옴
    $weather_final=str_replace(" &deg;C", null, $weather_final); //&deg;C라고 출력을 NULL값으로 변경
    if($weather_final <= 5 ) {
        $mean = "많이 추워요..ㅠ \\n\\n겨울 옷(야상, 패딩, 목도리 등)이 좋아요~";
    } elseif($weather_final <= 9 ) {
        $mean = "후.. 추워..\\n\\n코트, 가죽자켓 등이 좋아요~";
    } elseif($weather_final <= 11 ) {
        $mean = "쌀쌀해요!\\n\\n트렌치코트, 간절기 야상, 여러겹 껴입는 것이 좋아요~";
    } elseif($weather_final <= 16 ) {
        $mean = "서늘하지만 밤에는 조금 추울수도..\\n\\n자켓, 셔츠, 가디건, 간절기 야상 등이 좋아요~";
    } elseif($weather_final <= 19 ) {
        $mean = "덥지도 춥지도 않아여~\\n\\n긴팔, 후드티, 맨투맨, 원피스 등이 적당합니닷!";
    } elseif($weather_final <= 22 ) {
        $mean = "날씨 좋은데~\\n\\n긴팔티가 적당해요!";
    } elseif($weather_final <= 26 ) {
        $mean = "더워 더워;;; \\n\\n반팔, 얇은 셔츠, 얇은 긴팔 등이 딱이에요!";
    } else{
        $mean = "아따 덥구마잉;; \\n\\n반팔, 나시티, 반바지 등이 적당해요!";
    }
echo <<< EOD
    {
        "message": {
            "text": "현재 기온은 $weather_final ℃ 입니다. \\n\\n$mean"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

// 날씨-미세먼지
elseif(strpos($content, "미세") !== false && strpos($content, "먼지") !== false){
echo <<< EOD
    {
        "message": {
            "text": "현재 대기환경 정보입니다.\\n\\n통합대기환경 : $khai\\nPM10(미세먼지) : $pm10\\nPM2.5(초미세먼지) : $pm25"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

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
                "학교홈페이지",
                "포털시스템",
                "스마트출결시스템",
                "교내연락처",
                "처음으로"
            ]
        }
    }
EOD;
}

//홈페이지-학교홈페이지
elseif(strpos($content, "학교") !== false && strpos($content, "홈페이지") !== false){
echo <<< EOD
    {
        "message": {
            "text": "PC 버전 \\n http://www.kduniv.ac.kr/ \\n 모바일 버전 \\n http://m.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "포털") !== false && strpos($content, "시스템") !== false){
echo <<< EOD
    {
        "message": {
            "text": "포털시스템(학사정보) \\n http://portal.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "출결") && strpos($content, "시스템") !== false){
echo <<< EOD
    {
        "message": {
            "text": "스마트출결시스템 \\n http://attend.kduniv.ac.kr/"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
elseif(strpos($content, "교내") !== false && strpos($content, "연락처") !== false){
echo <<< EOD
    {
        "message": {
            "text": "교내연락처 \\n http://kduniv.ac.kr/www/index.php?pCode=1410508209"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
                "홈페이지",
                "검색",
                "건의하기",
                "캠퍼스 변경"
            ]
        }
    }
EOD;
}

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
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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
                "오늘 학식",
                "내일 학식",
                "교통",
                "날씨",
                "연락처 검색",
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