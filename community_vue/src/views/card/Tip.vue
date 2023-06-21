<template>
    <el-card class="box-card" shadow="never">
      <div slot="header">
        <span>每日推荐</span>
      </div>
      <div>
        <div class="has-text-left block"> {{ tip.content }} </div>
        <div class="has-text-right block">{{ tip.author }}</div>
      </div>
    </el-card>
</template>

<script>
import {getTodayTip} from '@/api/tip'
export default {
  name: "Tip",
  data() {
    return {
      tip:{}
    };
  },

  created() {
   this.fetchTodayTip();
  },
  
  methods: {
      async fetchTodayTip(){
        getTodayTip(this.type).then(response =>{
          const { data } = response
          this.tip = data;
        })
      }
  }    
};
</script>
