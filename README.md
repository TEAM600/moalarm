# MOALARM

### 이용 가이드 구성

- [시작하기](https://www.notion.so/API-DOCS-eb8ac7c199b647b1b16cacf5f92f4308)
- [API 사용하기](https://www.notion.so/API-DOCS-eb8ac7c199b647b1b16cacf5f92f4308)

<aside>
💡 **시작하기**

- **Moalarm 사이트 회원 가입 및 API Key 발급**
    - [https://moalarm600.com/](https://moalarm600.com/index.html) 회원가입 시, 자동으로 api key가 발급됩니다.
    - Moalarm Key : moalarm api를 사용하기 위한 인증 키 입니다.
    
- **채널 정보 등록**
    - 각각의 알림을 사용하기 위한 api key, secret key 등 필요한 정보를 등록합니다.
    
- **Moalarm Key 재발급**
    - 유출 등 기타 사유로 재발급이 필요할 수 있습니다‼️
        
        API Key 옆의 새로 고침 버튼을 통해 재발급 받을 수 있습니다.
        
    
</aside>

- **API 사용하기**
    
    ```
    POST /notification
    Host: https://k8a407.p.ssafy.io/api/v2
    ```
    

**인증**

---

| Type | Key Name | Value |
| --- | --- | --- |
| Header | Authorization | MoalarmKey {Moalarm-Key} |

**Request body**

---

```json
{
    "mail": {
        "to": ["example1@mail.com", "example2@mail.com", "example3@mail.com"],
        "title": "title",
        "content": "content"

    },
    "sms": {
        "to": ["01012341234", "01056785678"],
        "content": "content"
    },
    "push": {
        "to": ["fcmKey1", "fcmKey2"],
        "title": "title",
        "content": "content",
        "img": "img"
    }
}
```

**응답**

---

| 응답 코드 | 응답명 | 설명 |
| --- | --- | --- |
| 200 | OK | 성공 |
| 400 | Bad Request | 요청 형식 오류 |
| 400 | Bad Request | 잘못된 API Key 사용 |
| 404 | Not Found | 해당 리소스가 존재하지 않음 |
| 500 | Internal Server Error | 서버 내부 에러 |
