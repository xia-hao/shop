import request from '@/utils/request'

export function signIn(object) {
  return request({
    url: '/signIn',
    method: 'post',
    data: object
  })
}

export function loginOut() {
  return request({
    url: '/loginOut',
    method: 'get'
  })
}

