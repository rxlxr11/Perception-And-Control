<template>
<div>

      <el-card class="box-card" v-loading="loading">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 25px">实时温度</span>
        </div>
        <div>
          <i class="el-icon-sunny"></i>
          <span style="font-size: 75px;font-weight: bold;float: right" class="temp">℃</span>
          <span style="font-size: 55px;float: right" >{{temp}}</span>
        </div>
      </el-card>

</div>
</template>

<script>

import axios from "axios";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Card3",
  data(){
    return {
      temp: 0,
      loading: true
    };
  },
  mounted() {
    axios.get("http://localhost:8080/perception").then((result) => {
    //axios.get("http://yapi.smart-xwork.cn/mock/264565/temp").then((result) => {
      this.temp = result.data.value.temp;
      //this.temp= result.data.result.temp;
      this.loading = false;
    });
  },
  methods: {

  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 350px;
  height: 200px;
}


.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-icon-sunny{
  font-size: 100px;
  color: #ffff1e;
}
body {
  margin: 0;
}
</style>