<template>
  <div class="seat-div mx-auto">

請選擇員工：<div style="width: 200px" class="mb-4">
  <select v-model="selectEmp.empId" class="form-select">
    <option readonly="請選擇員工編號">請選擇員工編號</option>
    <option v-for="emp in emps" :value="emp.empId">{{ emp.empId }}</option>
  </select>
</div>

    <b-btn-group>
      <b-button class="mb-3" :variant="selectedSeatSeq === seat.floorSeatSeq ? 'success' : (seat.empId ? 'danger' : 'light')"
                @click="selectSeat(seat)" v-for="seat in seats">
        {{ seat.floorNo }}樓:座位{{ seat.seatNo }}[員工{{ seat.empId ? seat.empId : '未分配' }}]
      </b-button>
    </b-btn-group>
    
    <table>
      <tbody>
      <tr>
        <td style="background-color: #f8f9fa;width: 30px;height: 20px;border-radius: 5px"></td>
        <td>空位</td>
      </tr>
      <tr>
        <td style="background-color: red;border-radius: 5px"></td>
        <td>已佔用</td>
      </tr>
      <tr>
        <td style="background-color: green;border-radius: 5px"></td>
        <td>已選擇</td>
      </tr>
      </tbody>
    </table>

    <b-button variant="outline-primary" @click="updateSeat">送出</b-button>
</div>
</template>

<script setup>
import axios from 'axios';
import {reactive, ref, onMounted } from "vue";
import Swal from "sweetalert2";

const seats = ref({});
const emps = ref({});
const selectEmp = reactive({
  empId: null,
  floorSeatSeq: null
});
const selectedSeatSeq = ref(null);

const getSeat = async() => {
  try{
    const url = `http://localhost:8080/seats`
    console.log(url);
    const response = await axios.get(url);
    console.log(response.data);
    seats.value = response.data;
  }catch(error){
    console.log(error);
  }
}
const getEmps = async() => {
try{  
  const url = `http://localhost:8080/employees`
  console.log(url);
  const response = await axios.get(url);
  console.log(response.data);
  emps.value = response.data;
}catch(error){
  console.log(error);
}
}

const selectSeat = (seat) => {
  if(seat.empId !=null){
    Swal.fire({
      title: "此座位已有員工",
      icon: "error"
    })
  }else {
    selectedSeatSeq.value = seat.floorSeatSeq;
    selectEmp.floorSeatSeq = seat.floorSeatSeq;
  }
}

const updateSeat = async () =>{
  let flag = {isConfirmed: false};
  if (selectEmp.floorSeatSeq == null) {
    flag = await Swal.fire({
      title: "尚未選擇座位",
      text: "尚未選擇座位，送出後將清空座位，確定嗎?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: '確定',
      cancelButtonText: '取消'
    })
  }

  if (flag.isConfirmed || selectEmp.floorSeatSeq !== null) {
    try {
      sendEmpSeats.push(selectEmp);
      await axios.put("http://localhost:8080/seats", sendEmpSeats)
      await getSeat();
      selectEmp.floorSeatSeq = null;
      selectedSeatSeq.value =null;
      await Swal.fire({
        title: "更改成功",
        icon: "success"
      })
    } catch (e) {
      Swal.fire({
        title: "更改失敗",
        icon: "error"
      })
    }
  }
}



onMounted(() => {
  getSeat();
  getEmps();
})
</script>

<style scoped>
.seat-div {
  width: 1000px;
}
</style>