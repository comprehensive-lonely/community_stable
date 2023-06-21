import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// 1.创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_SERVER_URL,

  timeout: 10 * 1000
})

// 2.请求拦截器request interceptor
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      // bearer：w3c规范
      config.headers['Authorization'] = 'Bearer ' + getToken()
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 设置cross跨域 并设置访问权限 允许跨域携带cookie信息,使用JWT可关闭
service.defaults.withCredentials = false

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 200) {
      // 50008: 非法Token; 50012: 异地登录; 50014: Token失效;
      if (res.code === 401 || res.code === 50012 || res.code === 50014) {
        // 重新登录
        MessageBox.confirm('会话失效，您可以留在当前页面，或重新登录', '权限不足', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
          window.location.href = '#/login'
        })
      } else { // 其他异常直接提示
        Message({
          showClose: true,
          message: '⚠' + res.message || 'Error',
          type: 'error',
          duration: 3 * 1000
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    Message({
      showClose: true,
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
export default service