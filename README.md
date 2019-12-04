### README.md

+ 공공데이터포털의 대기오염정보 조회 서비스 API 활용입니다.
+ REST API / XML

+ 참고
  + [한국환경공단_대기오염정보](https://www.data.go.kr/dataset/15000581/openapi.do)
  
+ 요청
 + http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?serviceKey=서비스키&numOfRows=10&pageSize=10&pageNo=1&startPage=1&itemCode=PM10&dataGubun=HOUR&searchCondition=MONTH
 
+ 입력
  + serviceKey : 서비스키
  + numOfRows : 1
  + pageSize : 1
  + pageNo : 1
  + startPage : 1
  + itemCode : PM10
  + dataGubun : HOUR
  + searchCondition : MONTH
  
+ 결과
``` xml
<response>
<header>
<resultCode>00</resultCode>
<resultMsg>NORMAL SERVICE.</resultMsg>
</header>
<body>
<items>
<item>
<dataTime>2019-12-04 08:00</dataTime>
<itemCode>PM10</itemCode>
<dataGubun>시간평균</dataGubun>
<seoul>24</seoul>
<busan>28</busan>
<daegu>39</daegu>
<incheon>29</incheon>
<gwangju>26</gwangju>
<daejeon>35</daejeon>
<ulsan>29</ulsan>
<gyeonggi>34</gyeonggi>
<gangwon>33</gangwon>
<chungbuk>34</chungbuk>
<chungnam>36</chungnam>
<jeonbuk>29</jeonbuk>
<jeonnam>21</jeonnam>
<gyeongbuk>36</gyeongbuk>
<gyeongnam>33</gyeongnam>
<jeju>25</jeju>
<sejong>35</sejong>
</item>
</items>
<numOfRows>1</numOfRows>
<pageNo>1</pageNo>
<totalCount>24</totalCount>
</body>
</response>
```

+ 결과 분류, 사용
``` java

  while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            //parser가 시작 될때

                            break;
                        case XmlPullParser.START_TAG:
                            //parser가 끝날때 - ex) <item>

                            tag = parser.getName();
                            if(tag.equals("seoul") || tag.equals("dataTime")) // <addr> 이 맞으면
                            {
                                parser.next(); // <addr> value </addr> 에서 <addr> 다음인 value에 접근, parser는 "value"
                                Log.d(" Value ",parser.getText()); // 출력 : 2019-12-04 08:00 , 24
                            }
                            break;
                        case XmlPullParser.END_DOCUMENT:

                            break;
                        case XmlPullParser.END_TAG:
                            //parser가 끝날때 - ex) </item>

                            break;
                        case XmlPullParser.TEXT:
                            // parser가 내용에 접근했을때

                            break;
                    }
                    eventType = parser.next();
                }
```
