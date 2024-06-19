<template>
  <div class="home">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div v-for="item in items" :key="item.id" class="col">
            <Card :item="item"></Card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Card from '@/components/TheCard.vue'
import axios from 'axios'
import { ref } from 'vue'

const items = ref([])
axios.get('/api/items').then((res) => {
  console.log(res)
  items.value = res.data
})
</script>

<style lang="scss" scoped></style>
