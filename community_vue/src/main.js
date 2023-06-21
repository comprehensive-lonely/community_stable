import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Buefy from 'buefy'
import 'buefy/dist/buefy.css'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import '@/assets/app.css'
import './assets/font-awesome-4.7.0/css/font-awesome.min.css'

import format from 'date-fns/format'
import '@/permission'

import relativeTime from 'dayjs/plugin/relativeTime';
import 'dayjs/locale/zh-cn'
const dayjs = require('dayjs');

dayjs.extend(relativeTime)

dayjs.locale('zh-cn') 
dayjs().locale('zh-cn').format() 

Vue.prototype.dayjs = dayjs;//可以全局使用dayjs

Vue.filter('date', (date) => {
  return format(new Date(date), 'yyyy-MM-dd')
})

Vue.use(Buefy)
Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
