<template>
  <section>
      <LoginWelcome></LoginWelcome> <br>
      <TipCard></TipCard><br>
      <PromotionCard></PromotionCard>
        
  </section>
</template>

<script>
import LoginWelcome from '@/views/card/LoginWelcome'
import PromotionCard from '@/views/card/Promotion'
import TipCard from '@/views/card/Tip'

export default {
  name: "CardBar",
  data() {
    return {};
  },
  components:{
      LoginWelcome,
      PromotionCard,
      TipCard
  },
  created() {
   
  },
  methods: {
      
  }
    
};
</script>
