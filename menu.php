<?php
    include ("Snoopy.class.php");
    //글로벌 캠퍼스 학식메뉴
    function TGTmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
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
        $data =  $pregs[0][$date];
        $data = str_replace('<br />', "", $data);
        $data = str_replace('<td>', '', $data);
        $data = str_replace('</td>', '', $data);

        $array = array($data);
        $result = json_encode($array,JSON_UNESCAPED_UNICODE);
        $result = str_replace('"', '', $result);
        $result = str_replace('[', '', $result);
        $result = str_replace(']', '', $result);
        $result = str_replace('\t', '', $result);
        $result = str_replace('\r', '', $result);
        $result = str_replace('\/', '', $result);
        $result = str_replace('\n', "\\n", $result);
        $result = str_replace("<span class='cred'>", '', $result);
        $result = str_replace("<\/span>", '', $result);
        $result = str_replace('<span>', "", $result);
        $result = str_replace('<br>', "\\n", $result);

        return $result;
    }

    //숭례원 조식 메뉴
    function TSTDmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $day = 0;
            if($d == 0){
                $day = $d + 6;
            }else{
                $day = $d - 1;
            }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C02&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $dayMenu = $pregs[0][$day];

        $dayMenu = str_replace('<br />', "", $dayMenu);
        $dayMenu = str_replace('<td>', '', $dayMenu);
        $dayMenu = str_replace('</td>', '', $dayMenu);

        $array1 = array($dayMenu);
        $result1 = json_encode($array1,JSON_UNESCAPED_UNICODE);
        $result1 = str_replace('"', '', $result1);
        $result1 = str_replace('[', '', $result1);
        $result1 = str_replace(']', '', $result1);
        $result1 = str_replace('\t', '', $result1);
        $result1 = str_replace('\r', '', $result1);
        $result1 = str_replace('\/', '', $result1);
        $result1 = str_replace('\n', "\\n", $result1);
        $result1 = str_replace("<span class='cred'>", '', $result1);
        $result1 = str_replace("<\/span>", '', $result1);
        $result1 = str_replace('<span>', "", $result1);
        $result1 = str_replace('<br>', "\\n", $result1);

        return $result1;
    }
    //숭례원 석식 메뉴
    function TSTNmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $night = 0;
        if($d == 0){
            $night = $d + 13;
        }else{
            $night = $d +6;
        }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C02&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $nightMenu = $pregs[0][$night];

        $nightMenu = str_replace('<br />', "", $nightMenu);
        $nightMenu = str_replace('<td>', '', $nightMenu);
        $nightMenu = str_replace('</td>', '', $nightMenu);

        $array2 = array($nightMenu);
        $result2 = json_encode($array2,JSON_UNESCAPED_UNICODE);
        $result2 = str_replace('"', '', $result2);
        $result2 = str_replace('[', '', $result2);
        $result2 = str_replace(']', '', $result2);
        $result2 = str_replace('\t', '', $result2);
        $result2 = str_replace('\r', '', $result2);
        $result2 = str_replace('\/', '', $result2);
        $result2 = str_replace('\n', "\\n", $result2);
        $result2 = str_replace("<span class='cred'>", '', $result2);
        $result2 = str_replace("<\/span>", '', $result2);
        $result2 = str_replace('<span>', "", $result2);
        $result2 = str_replace('<br>', "\\n", $result2);

        return $result2;
    }

    //양현원 조식 메뉴
    function TYTDmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $day = 0;
            if($d == 0){
                $day = $d + 6;
            }else{
                $day = $d - 1;
            }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&date=2018-06-17&mode=view&CgCode=C03");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $dayMenu = $pregs[0][$day];

        $dayMenu = str_replace('<br />', "", $dayMenu);
        $dayMenu = str_replace('<td>', '', $dayMenu);
        $dayMenu = str_replace('</td>', '', $dayMenu);

        $array1 = array($dayMenu);
        $result1 = json_encode($array1,JSON_UNESCAPED_UNICODE);
        $result1 = str_replace('"', '', $result1);
        $result1 = str_replace('[', '', $result1);
        $result1 = str_replace(']', '', $result1);
        $result1 = str_replace('\t', '', $result1);
        $result1 = str_replace('\r', '', $result1);
        $result1 = str_replace('\/', '', $result1);
        $result1 = str_replace('\n', "\\n", $result1);
        $result1 = str_replace("<span class='cred'>", '', $result1);
        $result1 = str_replace("<\/span>", '', $result1);
        $result1 = str_replace('<span>', "", $result1);
        $result1 = str_replace('<br>', "\\n", $result1);

        return $result1;
    }
    //양현원 석식 메뉴
    function TYTNmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $night = 0;
        if($d == 0){
            $night = $d + 13;
        }else{
            $night = $d +6;
        }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&date=2018-06-17&mode=view&CgCode=C03");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $nightMenu = $pregs[0][$night];

        $nightMenu = str_replace('<br />', "", $nightMenu);
        $nightMenu = str_replace('<td>', '', $nightMenu);
        $nightMenu = str_replace('</td>', '', $nightMenu);

        $array2 = array($nightMenu);
        $result2 = json_encode($array2,JSON_UNESCAPED_UNICODE);
        $result2 = str_replace('"', '', $result2);
        $result2 = str_replace('[', '', $result2);
        $result2 = str_replace(']', '', $result2);
        $result2 = str_replace('\t', '', $result2);
        $result2 = str_replace('\r', '', $result2);
        $result2 = str_replace('\/', '', $result2);
        $result2 = str_replace('\n', "\\n", $result2);
        $result2 = str_replace("<span class='cred'>", '', $result2);
        $result2 = str_replace("<\/span>", '', $result2);
        $result2 = str_replace('<span>', "", $result2);
        $result2 = str_replace('<br>', "\\n", $result2);

        return $result2;
    }

    function MGTmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $date = 0;
            if($d == 0){
            $date = $d;
            }else{
            $date = $d;
            }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C01&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);
        $data =  $pregs[0][$date];
        $data = str_replace('<br />', "", $data);
        $data = str_replace('<td>', '', $data);
        $data = str_replace('</td>', '', $data);

        $array = array($data);
        $result = json_encode($array,JSON_UNESCAPED_UNICODE);
        $result = str_replace('"', '', $result);
        $result = str_replace('[', '', $result);
        $result = str_replace(']', '', $result);
        $result = str_replace('\t', '', $result);
        $result = str_replace('\r', '', $result);
        $result = str_replace('\/', '', $result);
        $result = str_replace('\n', "\\n", $result);
        $result = str_replace("<span class='cred'>", '', $result);
        $result = str_replace("<\/span>", '', $result);
        $result = str_replace('<span>', "", $result);
        $result = str_replace('<br>', "\\n", $result);

        return $result;
    }

    //숭례원 조식 메뉴
    function MSTDmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $day = 0;
            if($d == 0){
                $day = $d;
            }else{
                $day = $d;
            }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C02&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $dayMenu = $pregs[0][$day];

        $dayMenu = str_replace('<br />', "", $dayMenu);
        $dayMenu = str_replace('<td>', '', $dayMenu);
        $dayMenu = str_replace('</td>', '', $dayMenu);

        $array1 = array($dayMenu);
        $result1 = json_encode($array1,JSON_UNESCAPED_UNICODE);
        $result1 = str_replace('"', '', $result1);
        $result1 = str_replace('[', '', $result1);
        $result1 = str_replace(']', '', $result1);
        $result1 = str_replace('\t', '', $result1);
        $result1 = str_replace('\r', '', $result1);
        $result1 = str_replace('\/', '', $result1);
        $result1 = str_replace('\n', "\\n", $result1);
        $result1 = str_replace("<span class='cred'>", '', $result1);
        $result1 = str_replace("<\/span>", '', $result1);
        $result1 = str_replace('<span>', "", $result1);
        $result1 = str_replace('<br>', "\\n", $result1);

        return $result1;
    }
    //숭례원 석식 메뉴
    function MSTNmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $night = 0;
        if($d == 0){
            $night = $d + 7;
        }else{
            $night = $d + 7;
        }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=C02&date=2018-06-17&mode=view");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $nightMenu = $pregs[0][$night];

        $nightMenu = str_replace('<br />', "", $nightMenu);
        $nightMenu = str_replace('<td>', '', $nightMenu);
        $nightMenu = str_replace('</td>', '', $nightMenu);

        $array2 = array($nightMenu);
        $result2 = json_encode($array2,JSON_UNESCAPED_UNICODE);
        $result2 = str_replace('"', '', $result2);
        $result2 = str_replace('[', '', $result2);
        $result2 = str_replace(']', '', $result2);
        $result2 = str_replace('\t', '', $result2);
        $result2 = str_replace('\r', '', $result2);
        $result2 = str_replace('\/', '', $result2);
        $result2 = str_replace('\n', "\\n", $result2);
        $result2 = str_replace("<span class='cred'>", '', $result2);
        $result2 = str_replace("<\/span>", '', $result2);
        $result2 = str_replace('<span>', "", $result2);
        $result2 = str_replace('<br>', "\\n", $result2);

        return $result2;
    }

    //양현원 조식 메뉴
    function MYTDmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $day = 0;
            if($d == 0){
                $day = $d;
            }else{
                $day = $d;
            }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&date=2018-06-17&mode=view&CgCode=C03");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $dayMenu = $pregs[0][$day];

        $dayMenu = str_replace('<br />', "", $dayMenu);
        $dayMenu = str_replace('<td>', '', $dayMenu);
        $dayMenu = str_replace('</td>', '', $dayMenu);

        $array1 = array($dayMenu);
        $result1 = json_encode($array1,JSON_UNESCAPED_UNICODE);
        $result1 = str_replace('"', '', $result1);
        $result1 = str_replace('[', '', $result1);
        $result1 = str_replace(']', '', $result1);
        $result1 = str_replace('\t', '', $result1);
        $result1 = str_replace('\r', '', $result1);
        $result1 = str_replace('\/', '', $result1);
        $result1 = str_replace('\n', "\\n", $result1);
        $result1 = str_replace("<span class='cred'>", '', $result1);
        $result1 = str_replace("<\/span>", '', $result1);
        $result1 = str_replace('<span>', "", $result1);
        $result1 = str_replace('<br>', "\\n", $result1);

        return $result1;
    }
    //양현원 석식 메뉴
    function MYTNmenu()
    {
        $d = date('w'); //0 ~ 6 숫자 반환
        $night = 0;
        if($d == 0){
            $night = $d + 7;
        }else{
            $night = $d + 7;
        }
        $snoopy = new Snoopy;
        $snoopy->fetch("http://kduniv.ac.kr/www/index.php?pCode=1414720005&date=2018-06-17&mode=view&CgCode=C03");
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);

        $nightMenu = $pregs[0][$night];

        $nightMenu = str_replace('<br />', "", $nightMenu);
        $nightMenu = str_replace('<td>', '', $nightMenu);
        $nightMenu = str_replace('</td>', '', $nightMenu);

        $array2 = array($nightMenu);
        $result2 = json_encode($array2,JSON_UNESCAPED_UNICODE);
        $result2 = str_replace('"', '', $result2);
        $result2 = str_replace('[', '', $result2);
        $result2 = str_replace(']', '', $result2);
        $result2 = str_replace('\t', '', $result2);
        $result2 = str_replace('\r', '', $result2);
        $result2 = str_replace('\/', '', $result2);
        $result2 = str_replace('\n', "\\n", $result2);
        $result2 = str_replace("<span class='cred'>", '', $result2);
        $result2 = str_replace("<\/span>", '', $result2);
        $result2 = str_replace('<span>', "", $result2);
        $result2 = str_replace('<br>', "\\n", $result2);

        return $result2;
    }
?>