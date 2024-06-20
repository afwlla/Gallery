<template>
  <div class="card shadow-sm">
    <span class="img" :style="{ backgroundImage: `url(${item.imgPath})` }"></span>

    <div class="card-body">
      <p class="card-text">
        {{ item.name }} <span class="discount badge bg-danger">{{ item.discountPer }}%</span>
      </p>

      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button @click="addToCart(item.id)" type="button" class="btn btn-primary">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
          </button>
        </div>
        <small class="price text-muted">\ {{ lib.getNumberFormatted(item.price) }}</small>
        <small class="real">
          \ {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer) / 100) }}
        </small>
      </div>
    </div>
  </div>
</template>

<script setup>
import { toRefs } from 'vue'
import lib from '@/scripts/lib'
import axios from 'axios'

const props = defineProps({
  item: Object
})
const { item } = toRefs(props)

const addToCart = (itemId) => {
  axios.post(`/api/cart/items/${itemId}`).then(() => {
    console.log('ok')
  })
}

axios.get('/api/cart/items').then(({ data }) => {
  console.log(data)
})
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}
.card .card-body .price {
  text-decoration: line-through;
}
</style>
