<template>
  <div class="order-list">
    <div class="container">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>번호</th>
            <th>주문자명</th>
            <th>주소</th>
            <th>결제수단</th>
            <th>구입항목</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(order, index) in orderList" :key="order.id">
            <td>{{ orderList.length - index }}</td>
            <td>{{ order.name }}</td>
            <td>{{ order.address }}</td>
            <td>{{ order.payment }}</td>
            <td>
              <div v-for="(i, idx2) in order.items" :key="idx2">{{ i.name }}</div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'

const orderList = ref([])
const getOrderList = async () =>
  await axios.get('/api/orders').then(({ data }) => {
    for (let d of data) {
      if (d.items) {
        d.items = JSON.parse(d.items)
      }

      orderList.value.push(d)
    }
  })
getOrderList()
</script>

<style scoped>
.table {
  margin-top: 30px;
}

.table > tbody {
  border-top: 1px solid #eee;
}
</style>
