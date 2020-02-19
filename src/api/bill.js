import request from '@/utils/request'

export function add(object) {
  return request({
    url: '/bill/add',
    method: 'get',
    params: object
  })
}

export function list() {
  return request({
    url: '/bill/list',
    method: 'get'
  })
}

