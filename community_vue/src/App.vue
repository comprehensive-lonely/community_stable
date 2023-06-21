<template>
  <div class="ancestor">
    <div class="mb-5">   
      <Header />
    </div>

    <div class="container context">
      <router-view :key="this.$route.fullPath"></router-view>
    </div>

    <div>
      <Footer />
    </div>
  </div>
</template>

<script>
import Header from "@/components/layout/Header"
import Footer from "@/components/layout/Footer"

export default {
  name: "App",
  components: { Header,Footer },
}
</script>

<style>
/* .ancestor{
  background-image: url("/assets/logo.png");
} */
</style>
