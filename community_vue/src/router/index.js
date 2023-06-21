import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: ()=> import('@/views/Home')
  },
  {
    path: '/register',
    name: 'register',
    component: ()=> import('@/views/auth/Register'),
    meta: { title: '注册' }
  },
  {
    path: '/404',
    name: '404',
    component: ()=> import('@/views/error/404'),
    meta: { title: '404-Not Found' }
  },
  {
    path: '/login',
    name: 'login',
    component: ()=> import('@/views/auth/Login'),
    meta: { title: '登录'}
  },
  {
    path: '/post/create',
    name: 'post-create',
    component: ()=> import('@/views/post/Create'),
    meta: { title: '发帖', requireAuth: true}
  },
  {
    path: '/post/:id',
    name: 'post-detail',
    component: ()=> import('@/views/post/Detail')
  },
  // 编辑
  {
    path: '/topic/edit/:id',
    name: 'topic-edit',
    component: ()=> import('@/views/post/Edit'),
    meta: { title: '编辑' }
  },
  // 搜索
  {
    path: '/search',
    name: 'search',
    component: ()=> import('@/views/Search'),
    meta: { title: '检索' }
  },
  {
    path: '/tag/:name',
    name: 'tag',
    component: ()=> import('@/views/tag/Tag'),
    meta: { title: '主题列表' }
  },
  {
    path: '/member/:username/home',
    name: 'user',
    component: ()=> import('@/views/user/Profile'),
    meta: { title: '用户主页' }
  },
  {
    path: '/member/:username/setting',
    name: 'user-setting',
    component: ()=> import('@/views/user/Setting'),
    meta: { title: '设置' }
  },
  { 
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location){
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  routes
})

export default router
