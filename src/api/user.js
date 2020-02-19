import request from '@/utils/request'

export function userinfo() {
  return request({
    url: '/userinfo',
    method: 'post'
  })
}
