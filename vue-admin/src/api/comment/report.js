import request from '@/utils/request'

// 查询评论举报列表
export function listCommentReport(query) {
  return request({
    url: '/comment/report',
    method: 'get',
    params: query
  })
}

// 查询评论举报详情
export function getCommentReport(id) {
  return request({
    url: '/comment/report/' + id,
    method: 'get'
  })
}

// 处理评论举报
export function handleCommentReport(data) {
  return request({
    url: '/comment/report',
    method: 'put',
    data: data
  })
}

// 删除评论举报
export function deleteCommentReport(ids) {
  return request({
    url: '/comment/report/' + ids,
    method: 'delete'
  })
}