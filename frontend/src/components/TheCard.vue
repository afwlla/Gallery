<template>
  <div class="card shadow-sm">
    <span class="img" :style="{ backgroundImage: `url(${item.imgPath})` }"></span>

    <div class="card-body">
      <p class="card-text">
        {{ item.name }} <span class="discount badge bg-danger">{{ item.discountPer }}%</span>
      </p>

      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-primary">Buy</button>
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

const props = defineProps({
  item: Object
})
const { item } = toRefs(props)
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
