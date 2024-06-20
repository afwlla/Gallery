<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="item in items" :key="item.id">
          <img :src="item.imgPath" />
          <span class="name">{{ item.name }}</span>
          <span class="price"
            >\
            {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer) / 100) }}
          </span>
          <i class="fa fa-trash" @click="remove(item.id)"></i>
        </li>
      </ul>
      <RouterLink to="/order" class="btn btn-primary">Buy</RouterLink>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
import lib from '@/scripts/lib'

const items = ref([])
const load = async () =>
  await axios.get('/api/cart/items').then(({ data }) => {
    console.log(data)
    items.value = data
  })
load()
const remove = (itemId) => {
  axios.delete(`/api/cart/items/${itemId}`).then(() => {
    load()
  })
}
</script>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}

.cart .btn {
  width: 300px;
  display: block;
  margin: 0 auto;
  padding: 30px 50px;
  font-size: 20px;
}
</style>
