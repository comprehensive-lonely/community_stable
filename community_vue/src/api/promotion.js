import request from '@/utils/request'

export function getPromotionList(){
    return request({
        url: '/promotion/all',
        method: 'get'
    })
}
