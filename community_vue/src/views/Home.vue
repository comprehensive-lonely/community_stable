<template>
  <div>
    <div class="box">  公告：{{ billboard.content }}  </div>
    <div class="columns">
      <div class="column is-three-quarters"> <!-- 左边主内容 -->
        <TopicList></TopicList>
      </div>
      <div class="column"> <!-- 右边悬浮框 -->
        <CardBar></CardBar>
      </div>
    </div>
  </div>
</template>

<script>
import {getBillBoard} from '@/api/billboard'
import CardBar from '@/views/card/CardBar'
import PostList from '@/views/post/Index'

export default {
  name: 'Home',
  components:{
    CardBar,
    TopicList: PostList
  },
  data(){
    return {
      // 定义一个空对象
      billboard: {
        content: ''
      } 
    }
  },
  created(){ // 创建的时候就调用这个方法
    this.fetchBillBoard();
  },
  methods:{
    async fetchBillBoard(){
      // 回调函数
      getBillBoard().then((value) =>{
        const { data } = value;
        this.billboard = data;
      }) 
    }
  }
  
}
</script>
