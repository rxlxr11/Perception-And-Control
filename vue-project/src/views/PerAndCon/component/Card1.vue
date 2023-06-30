<template>
<div :style="{mode:mode}">
      <el-card class="box-card" v-loading="loading">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 25px">压缩机</span>

        </div>
        <div>
          <i class="el-icon-cpu"></i>
          <span style="font-size: 55px;float: right" >{{value}}</span>
        </div>
      </el-card>
</div>
</template>

<script>
import axios from "axios";


export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Card1",
  props: {
    mode: {
      type: Boolean,
      default: false
    }
  },
  data(){
    return {
      value: "停止" ,
      loading: true
    }
  },
  mounted() {
    axios.get("http://localhost:8080/perception").then((result) => {
    //axios.get("http://yapi.smart-xwork.cn/mock/264565/compressor").then((result) => {
      this.value= result.data.value.compressor;
      //this.value = result.data.result.value;
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
.el-icon-cpu{
  font-size: 100px;
  color: #407eff;
}

.switch-compressor{
  zoom: 1.5;
}
</style>