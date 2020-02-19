import request from '@/utils/request'

export function register(object) {
  return request({
    url: '/register',
    method: 'post',
    data: object
  })
}
