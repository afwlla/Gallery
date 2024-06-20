<template>
  <div class="order">
    <div class="container">
      <main>
        <div class="py-5 text-center">
          <h2>주문하기</h2>
          <p class="lead">
            Below is an example form built entirely with Bootstrap’s form controls. Each required
            form group has a validation state that can be triggered by attempting to submit the form
            without completing it.
          </p>
        </div>
        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">구입 목록</span>
              <span class="badge bg-primary rounded-pill">
                {{ items.length }}
              </span>
            </h4>
            <ul class="list-group mb-3">
              <li
                v-for="item in items"
                :key="item.id"
                class="list-group-item d-flex justify-content-between lh-sm"
              >
                <div>
                  <h6 class="my-0">
                    {{ item.name }}
                  </h6>
                </div>
                <span class="text-muted">
                  \ {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer) / 100) }}
                </span>
              </li>
            </ul>
            <h3 class="text-center total-price">\ {{ lib.getNumberFormatted(computedPrice) }}</h3>
          </div>
          <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">주문자 정보</h4>
            <div class="needs-validation" novalidate="">
              <div class="row g-3">
                <div class="col-12">
                  <label for="username" class="form-label">이름</label>
                  <input type="text" class="form-control" id="username" v-model="form.name" />
                </div>
                <div class="col-12">
                  <label for="address" class="form-label">주소</label>
                  <input type="text" class="form-control" id="address" v-model="form.address" />
                </div>
              </div>
              <hr class="my-4" />
              <h4 class="mb-3">결제 수단</h4>
              <div class="my-3">
                <div class="form-check">
                  <input
                    id="card"
                    name="paymentMethod"
                    type="radio"
                    class="form-check-input"
                    value="card"
                    v-model="form.payment"
                  />
                  <label class="form-check-label" for="card">신용카드 </label>
                </div>
                <div class="form-check">
                  <input
                    id="bank"
                    name="paymentMethod"
                    type="radio"
                    class="form-check-input"
                    value="bank"
                    v-model="form.payment"
                  />
                  <label class="form-check-label" for="bank">무통장입금</label>
                </div>
              </div>
              <label for="cc-name" class="form-label">카드 번호</label>
              <input type="text" class="form-control" id="cc-name" v-model="form.cardNumber" />
              <hr class="my-4" />
              <button class="w-100 btn btn-primary btn-lg" @click="submit()">결제하기</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import axios from 'axios'
import lib from '@/scripts/lib'

const form = ref({
  name: '',
  address: '',
  payment: '',
  cardNumber: '',
  items: ''
})
const items = ref([])

const load = async () =>
  await axios.get('/api/cart/items').then(({ data }) => {
    console.log(data)
    items.value = data
  })
load()

const submit = async () => {
  const args = JSON.parse(JSON.stringify(form.value))
  args.items = JSON.stringify(items.value)

  await axios.post('/api/orders', args).then(() => {
    console.log('submit ok')
    console.log(args)
  })
}

const computedPrice = computed(() => {
  let result = 0

  for (let i of items.value) {
    result += i.price - (i.price * i.discountPer) / 100
  }

  return result
})
</script>

<style lang="scss" scoped></style>
