#!/usr/bin/env bash
# 쉬고 있는 profile 찾기: real1 사용중일 경우 real2가 쉬고, 반대일 경우 real1이 쉬고, real1이 살아 있음
function find_idle_profile() {
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}\n" http://localhost/profile)
  # -s 진행내역, 메시지 출력x, -o 원격데이터를 /dev/null로보내 아무런 출력 x, http_code- response code(200인가 아닌가)
  if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 에러로 봄 - 정상코드 200
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [${CURRENT_PROFILE} -eq real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}