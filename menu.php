<?php
    include ("Snoopy.class.php");
        
    function MenuData($url, $TimeData)
    {
        $snoopy = new Snoopy;
        $snoopy->fetch($url);
        $search = '/<td(?:.*?)>(?:.*?)<\/td>/s';
        preg_match_all($search, $snoopy->results, $pregs);
        $data =  $pregs[0][$TimeData];
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
?>