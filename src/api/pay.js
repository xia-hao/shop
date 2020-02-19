import request from '@/utils/request'

export function paybill(product,bid) {
  return request({
    url: '/pay/paybill/'+bid,
    method: 'get',
    params: product
  })
}
