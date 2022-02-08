# 윷놀이 플러그인

원래 설날 이벤트에 했어야 했지만..  
진행하지 못했으니 오픈 소스로 풀어둡니다.

## 다운로드 방법

1. 이 레포지토리의 [릴리즈 페이지](https://github.com/chabyik/yut-plugin/releases) 에 들어갑니다.
2. `.jar` 확장자로 된 파일을 다운로드 합니다.
3. 다운로드한 파일을 서버의 `plugins` 폴더에 이동합니다.
4. 서버를 실행한 후 `/yut toggle` 을 입력하면 실행됩니다. _(피드백 메시지는 따로 없습니다)_

## 플레이 방법

### 명령어 설명

`/yut toggle` : 윷 플러그인을 키거나 끕니다. _(리로드 혹은 재시작 시 이 설정값은 초기화 됩니다)_  
`/yut summon` : 말을 소환합니다. _(서버 내의 각 플레이어 위치에 말이 3개씩 소환됩니다)_

### 윷 던지기, 말 옮기기

이 동작들은 모두 우클릭으로 이루어집니다.  
  
`막대기` 를 우클릭하면 윷이 던져집니다.  
윷은 `도, 개, 걸, 윷, 모, 백도` 가 있습니다.  
모든 확률은 똑같이 1/6 입니다.
  
`화살` 을 말에 대고 우클릭하면 말이 시선을 따라 움직입니다.  
이 상태에서 한 번 더 우클릭하면, 말이 그 자리에 고정됩니다.  
너무 가까이서 우클릭을 하면, 화살이 갑옷 거치대에 씌워질 수 있으니 어느정도 거리를 두는 것을 추천합니다.  
최대 거리는 50칸입니다. 또한 다른 사람의 말도 옮길 수 있습니다.

### 템전

심심해서 아이템 기능을 추가로 넣었습니다.  
`아이템 이름` 의 이름으로 된 종이를 우클릭하면 아이템이 사용됩니다.  
아이템은 다른 사람 턴에서도 사용됩니다.
  
**도박** : 다음 턴에 윷을 던졌을 때 모 아니면 백도가 나옵니다.  
**인생역전** : 다음 턴에 윷을 던졌을 때 윷 아니면 모가 나옵니다.  
**나락**: 다음 턴에 윷을 던졌을 떄 윷 혹은 모가 나오면, 무효화됩니다.  
  
아이템을 조합하여 사용할 수도 있습니다.  
_예: 도박과 인생역전 둘 다 사용된 상태라면, 다음 턴에 무조건 모가 나옴_  
  
## 그 외에..

- 이 플러그인 관련한 아이디어도 받고 있습니다. (특히 아이템)
- 코드를 까보면 알겠지만, 원래 배포용으로 생각하고 짠게 아니라서 코드가 유사 스파게티입니다.  
  _(나중에 기회되면 개선하겠습니다..)_