import router from './router'
import store from './store'
import getPageTitle from '@/utils/get-page-title'

import NProgress from 'nprogress' 
import 'nprogress/nprogress.css'
import {getToken} from "@/utils/auth"; 

// NProgress Configuration
NProgress.configure({showSpinner: false}) 

// 当我们跳转之前，完成的异步操作
// from A to B
// next 是一个回调函数，如果调用next，就可以完成跳转，否则就不可以
router.beforeEach(async (to, from, next) => {
    
    NProgress.start()
    document.title = getPageTitle(to.meta.title) // router 里面的meta
    const hasToken = getToken();

    if (hasToken) {
        if (to.path === '/login') {
            // 登录，跳转首页
            next({path: '/'})
            NProgress.done()
        } else {
            // 每一次跳转的时候，都要去获取用户信息
            await store.dispatch('user/getInfo')
            next()
        }
    } else if (!to.meta.requireAuth)
    {
        next()
    }
    else {
        next('/login')
    }
})

router.afterEach(() => {
    NProgress.done()
})
