<?php
    use Cmfcmf\OpenWeatherMap;

    //날씨
    function WeatherData($map)
    {
        require 'vendor/autoload.php';
        $lang = 'ko'; //한국
        $units = 'metric'; //단위?!
        $owm = new OpenWeatherMap('d3ffd29705aaaa87adf50c825caf6361'); //API키
        $weather = $owm->getWeather($map, $units, $lang); //지역 날씨 받아옴
        $weather_final=$weather->temperature; //기온 파싱해옴
        $weather_final=str_replace(" &deg;C", null, $weather_final); //&deg;C라고 출력을 NULL값으로 변경

        if($weather_final <= 5 ) {
            $mean = "많이 추워요... \\n\\n겨울 옷 + 방한용 의류(머플러, 장갑, 내의, 부츠 등)가 좋습니다.";
        } elseif($weather_final <= 8 ) {
            $mean = "추워요..\\n\\n두꺼운 니트, 겨울 옷 + 패딩, 코트, 무스탕 등이 좋습니다.";
        } elseif($weather_final <= 12 ) {
            $mean = "쌀쌀해요~\\n\\n트렌치코트, 야상점퍼, 항공점퍼 등이 좋습니다.";
        } elseif($weather_final <= 16 ) {
            $mean = "서늘합니다~ \\n\\n긴팔 셔츠, 얇은 니트 + 바람막이 점퍼, 자켓, 가디건 등이 좋습니다.";
        } elseif($weather_final <= 20 ) {
            $mean = "덥지도 춥지도 않아요~\\n\\n긴팔 티셔츠 + 얇은 겉옷 등이 좋습니다.";
        } elseif($weather_final <= 24 ) {
            $mean = "날씨 좋아요~\\n\\n얇은 긴팔 셔츠, 긴팔 티 등이 좋습니다.";
        } else {
            $mean = "더워요~~~ \\n\\n얇은 반팔, 여름 옷이 좋습니다.";
        }

        $data = "현재 기온은 $weather_final ℃ 입니다. \\n\\n$mean";
        return $data;
    }
?>