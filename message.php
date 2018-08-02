<?php
//include('include/dbconfig.php');
use Cmfcmf\OpenWeatherMap;

$data = json_decode(file_get_contents('php://input'),true);
$content = $data["content"];
/*$user_key=$data["user_key"];

$sql = "insert into log(user_key, content) values('$user_key', '$content')";
$mysqli->query($sql);*/
$campus;
$x = 0;

//캠퍼스 지정
if($x == 0){
    $campus = "글로벌(고성)";
} elseif($x == 1) {
    $campus = "메트로폴(양주)";
} elseif($x == 2) {
    $campus = "메디컬(원주문막)";
} else{
    $x = 0;
}

// 급식
if($content=="학식"){
echo <<< EOD
    {
        "message": {
            "text": "오늘? 내일?"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "오늘 학식",
                "내일 학식",
                "처음으로"
            ]
        }
    }
EOD;
}

// 오늘 학식(검색)
elseif(strpos($content, "오늘") !== false && strpos($content, "학식") !== false){
echo <<< EOD
    {
        "message": {
            "text": "오늘 식단을 알고 싶은 식당을 \\n선택해주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "글로벌 캠퍼스",
                "숭례원",
                "양현원",
                "내일 학식",
                "처음으로"
            ]
        }
    }
EOD;
}

// 내일 학식(검색)
elseif(strpos($content, "내일") !== false && strpos($content, "학식") !== false){
echo <<< EOD
    {
        "message": {
            "text": "내일 식단을 알고 싶은 식당을 \\n선택해주세요."
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "글로벌 캠퍼스",
                "숭례원",
                "양현원",
                "오늘 학식",
                "처음으로"
            ]
        }
    }
EOD;
}

// 글로벌 캠퍼스 식단
elseif(strpos($content, "글로벌") !== false && strpos($content, "캠퍼스") !== false){
    include ("Snoopy.class.php");
      function test()
      {
        $d = date('w'); //날짜를 0 ~ 6 숫자 반환
        $date = 0;
          if($d == 0){
            $date = $d + 6;
          }else{
            $date = $d - 1;
          }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C01&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);
        return $pregs[0][$date];
      }
    $menu = test();
echo <<< EOD
    {
        "message": {
            "text": "$menu"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
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

// 숭례원 기숙사 식단
elseif(strpos($content, "숭례원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "2018-00-00 숭례원 기숙사 식단 \\n0000"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
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

// 양현원 기숙사 식단
elseif(strpos($content, "양현원") !== false){
echo <<< EOD
    {
        "message": {
            "text": "2018-00-00 양현원 기숙사 식단 \\n0000"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
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
elseif(strpos($content, "날씨") !== false){
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
                "학식",
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
                "학식",
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
elseif($content == "홈페이지"){
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
                "학식",
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
                "학식",
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
                "학식",
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
                "학식",
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
elseif(strpos($content, "건의") !== false){
echo <<< EOD
    {
        "message": {
            "text": "컴퓨터공학과\\n\\n기능 개선이 필요하거나 \\n추가적인 기능이 필요하다면 \\n메시지 남겨주세요!"
        },
        "keyboard": { 
            "type": "buttons",
            "buttons": [
                "학식",
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
            "text": "$campus 캠퍼스 $x 입니다."
        },
        "keyboard": {
            "type": "buttons",
            "buttons": [
                "학식",
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
                "학식",
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