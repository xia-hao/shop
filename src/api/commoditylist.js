import request from '@/utils/request'

export function list(object) {
  return request({
    url: '/product/list',
    method: 'get',
    params: object
  })
}

export function productById(id) {
  return request({
    url: '/product/productById/'+id,
    method: 'get'
  })
}

